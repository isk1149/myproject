package com.myproject.webapp.biz.bankbook;

import java.util.List;

import com.myproject.webapp.biz.bank.BankVO;

public interface TransactionHistoryService {
	List<TransactionHistoryVO> getTransactionList();
	void receiveInterest(BankVO bank, AccountVO account, InterestVO interest);
}
