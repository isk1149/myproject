package com.myproject.webapp.biz.bank.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.bank.BankVO;

@Repository
public class BankDAOJPA {

	@PersistenceContext
	private EntityManager em;
	
	public List<BankVO> getBank() {
		return em.createQuery("select b from BankVO as b").getResultList();
	}
}
