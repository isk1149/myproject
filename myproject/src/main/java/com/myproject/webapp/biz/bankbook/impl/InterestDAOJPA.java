package com.myproject.webapp.biz.bankbook.impl;

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
	
	public InterestVO getInterest(AccountVO vo) {
		InterestVO interest = em.createQuery("select i from InterestVO as i where i.account = :account", InterestVO.class)
								.setParameter("account", vo).getSingleResult();
		return interest;
	}
	
	public void initInterest(InterestVO interest) {
		interest.setLastApplyDay(new java.util.Date());
		interest.setApplyCount(0);
		interest.setInterest(0L);
		em.merge(interest);
	}
	
	public void updateInterest(InterestVO interest) {
		em.merge(interest);
	}
	
	public void calculateInterest(AccountVO account, InterestVO interest) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sysdate = sdf.format(new java.util.Date());
		String lastApplyDay = sdf.format(interest.getLastApplyDay());
		
		if (sysdate.equals(lastApplyDay)) return;
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


