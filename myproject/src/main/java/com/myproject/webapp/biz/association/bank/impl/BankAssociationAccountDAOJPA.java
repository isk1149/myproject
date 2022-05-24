package com.myproject.webapp.biz.association.bank.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.association.bank.BankAssociationAccountVO;

@Repository
public class BankAssociationAccountDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	public BankAssociationAccountVO getAccount(BankAssociationAccountVO vo) {
		
		
		
		BankAssociationAccountVO account = em.createQuery("select a " + 
															"from BankAssociationAccountVO as a " +
														   "where a.accountNo = :accountNo ", BankAssociationAccountVO.class)
											.setParameter("accountNo", vo.getAccountNo())
											.getSingleResult();
		return account;
	}

}
