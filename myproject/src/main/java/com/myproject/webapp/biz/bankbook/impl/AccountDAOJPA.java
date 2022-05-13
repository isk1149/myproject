package com.myproject.webapp.biz.bankbook.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.user.UserVO;

@Repository
public class AccountDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	public AccountVO getAccount(UserVO vo) {
		
		UserVO user = em.find(UserVO.class, vo.getUser_id());
		System.out.println("user id : " + user.getUser_id());
		AccountVO account = em.createQuery("select a from AccountVO as a where a.user = :user", AccountVO.class)
											.setParameter("user", vo)
											.getSingleResult();
		return account;
	}
}
