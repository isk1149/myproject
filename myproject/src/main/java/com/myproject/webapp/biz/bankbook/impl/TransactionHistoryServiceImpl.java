package com.myproject.webapp.biz.bankbook.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.bank.BankVO;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.bankbook.TransactionHistoryService;
import com.myproject.webapp.biz.bankbook.TransactionHistoryVO;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService{

	@Autowired
	private TransactionHistoryDAOJPA txHistoryDAO;
	
	@Override
	public List<TransactionHistoryVO> getTransactionList(AccountVO account) {
		return txHistoryDAO.getTransactionList(account);
	}

	@Override
	public void receiveInterest(BankVO bank, AccountVO account, InterestVO interest) {
		txHistoryDAO.receiveInterest(bank, account, interest);
	}

	@Override
	public void insertTxHistory(AccountVO account, TransactionHistoryVO txHistory) {
		txHistoryDAO.insertTxHistory(account, txHistory);
	}
	
}
