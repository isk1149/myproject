package com.myproject.webapp.biz.bankbook.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.bank.BankVO;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.bankbook.TransactionHistoryVO;

@Repository
public class TransactionHistoryDAOJPA {
	
	@PersistenceContext
	private EntityManager em;

	public List<TransactionHistoryVO> getTransactionList() {
		return null;
	};
	
	public void receiveInterest(BankVO bank, AccountVO account, InterestVO interest) {
		TransactionHistoryVO tx = new TransactionHistoryVO();
		
		tx.setAccount(account);
		tx.setTxDate(new java.util.Date());
		tx.setTxBank(bank.getCode());
		tx.setTxBankName(bank.getName());
		tx.setTxAccountNo(account.getAccountNo());
		tx.setTxAccountName("이자");
		tx.setTxAmount(interest.getInterest());
		tx.setIO("입금");
		
		em.persist(tx);
	};
}
