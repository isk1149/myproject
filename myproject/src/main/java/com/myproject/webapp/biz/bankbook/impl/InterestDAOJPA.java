package com.myproject.webapp.biz.bankbook.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.jdbc.JdbcUtil;

@Repository
public class InterestDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public InterestVO getInterest(AccountVO vo) {
		InterestVO interest = em.createQuery("select i from InterestVO as i where i.account = :account", InterestVO.class)
								.setParameter("account", vo).getSingleResult();
		return interest;
	}
	
	public void initInterest(InterestVO interest) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//String date = sdf.format(new java.util.Date());
		interest.setLastApplyDay(new java.util.Date());
		interest.setApplyCount(0);
		interest.setInterest(0L);
		em.merge(interest);
	}
	
	public void updateInterest(InterestVO interest) {
		em.merge(interest);
	}
	
	public void calculateInterest(AccountVO account, InterestVO interest) {
		// interest_tb의 last_apply_day와 오늘의 날짜 차이 구하기
		/*int applyCount = 0;
		try {
			Connection conn = JdbcUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select floor(to_date(to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') - " +
																   "to_date(to_char(last_apply_day, 'yyyymmdd'), 'yyyymmdd')) " +
															  "from interest_tb " + 
															 "where acct_no = ? ");
			
			pstmt.setString(1, interest.getAccount().getAccountNo());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				applyCount = rs.getInt(1);

			}
		} catch (Exception e) {
		}

		interest.setLastApplyDay(new java.util.Date());
		interest.setApplyCount(interest.getApplyCount() + applyCount);
		//interest.setInterest((long)interest);
		*/
		
		/*
		 if (deposit > 100000000L) {
			return (long)((100000000L * (rate / 100) / 365 * interestDayCount) + 
						 ((deposit - 100000000L) * (overrate / 100) / 365 * interestDayCount));
		}
		return (long)(deposit * (rate / 100) / 365 * interestDayCount);
		*/
		
		long deposit = account.getDeposit();
		long rateLimitAmount = account.getRateLimitAmount();
		double rate = account.getRate();
		double overRate = account.getOverRate();
		long addInterest;
		if (deposit > rateLimitAmount) {
			addInterest = 
				(long)( (rateLimitAmount * (overRate / 100) / 365) + ((deposit - rateLimitAmount) * (overRate / 100) / 365) );
		} else {
			addInterest = (long)(deposit * (rate / 100) / 365);
		}
		
		interest.setLastApplyDay(new java.util.Date());
		interest.setApplyCount(interest.getApplyCount() + 1);
		interest.setInterest(interest.getInterest() + addInterest);
		em.merge(interest);
	}
}


