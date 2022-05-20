package com.myproject.webapp.biz.association.bank.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.association.bank.BankAssociationVO;

@Repository
public class BankAssociationDAOJPA {

	@PersistenceContext
	private EntityManager em;
	
	public List<BankAssociationVO> getBankList() {
		return em.createQuery("select b from BankAssociationVO as b order by b.code").getResultList();
	}
	
}
