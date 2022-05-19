package com.myproject.webapp.biz.bank.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.bank.BankVO;

@Repository
public class BankDAOJPA {

	@PersistenceContext
	private EntityManager em;
	
	public BankVO getBank() {
		BankVO bank = em.find(BankVO.class, "000001");
		return bank;
	}
}
