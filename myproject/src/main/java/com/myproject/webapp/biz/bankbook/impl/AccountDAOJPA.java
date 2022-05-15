package com.myproject.webapp.biz.bankbook.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.user.UserVO;

@Repository
public class AccountDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	public AccountVO getAccount(UserVO vo) {
		
		UserVO user = em.find(UserVO.class, vo.getUser_id());
		AccountVO account = em.createQuery("select a from AccountVO as a where a.user = :user", AccountVO.class)
											.setParameter("user", user).getSingleResult();
		return account;
	}
	
	public void getInterest(AccountVO account, InterestVO interest) {
		long deposit = account.getDeposit() + interest.getInterest();
		account.setDeposit(deposit);
		em.merge(account);
	}
}
