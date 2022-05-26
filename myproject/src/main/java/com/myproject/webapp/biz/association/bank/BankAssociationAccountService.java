package com.myproject.webapp.biz.association.bank;

public interface BankAssociationAccountService {
	BankAssociationAccountVO getAccount(String bank, String txAccount) throws Exception;
}
