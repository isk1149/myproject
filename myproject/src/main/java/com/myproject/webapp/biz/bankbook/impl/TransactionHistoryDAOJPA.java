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

	public List<TransactionHistoryVO> getTransactionList(AccountVO account) {
		List<TransactionHistoryVO> txList = em.createQuery("select t " +
															 "from TransactionHistoryVO as t " + 
															"where t.account = :account " +
														 "order by t.txDate desc")
												.setParameter("account", account)
												.getResultList();
		return txList;
	};
	
	public void receiveInterest(BankVO bank, AccountVO account, InterestVO interest) {
		TransactionHistoryVO txHistory = new TransactionHistoryVO();
		
		txHistory.setAccount(account);
		txHistory.setTxDate(new java.util.Date());
		txHistory.setTxBank(bank.getCode());
		txHistory.setTxBankName(bank.getName());
		txHistory.setTxAccountNo(account.getAccountNo());
		txHistory.setTxAccountName("이자");
		txHistory.setTxAmount(interest.getInterest());
		txHistory.setIO("입금");
		
		em.persist(txHistory);
	};
	
	public void insertTxHistory(AccountVO account, TransactionHistoryVO txHistory) {
		em.persist(txHistory);
	}
}
