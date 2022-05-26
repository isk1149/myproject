package com.myproject.webapp.biz.association.bank.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.association.bank.BankAssociationAccountVO;
import com.myproject.webapp.biz.association.bank.BankAssociationVO;

@Repository
public class BankAssociationAccountDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	public BankAssociationAccountVO getAccount(String bank, String txAccount) throws Exception {
		
		String jpql = "select a " + 
						"from BankAssociationAccountVO as a " +
					   "where a.accountNo = :accountNo " + 
					     "and a.bankCode = '" + bank + "'";
		
		BankAssociationAccountVO account = em.createQuery(jpql, BankAssociationAccountVO.class)
											.setParameter("accountNo", txAccount)
											.getSingleResult();
		return account;
	}

}
