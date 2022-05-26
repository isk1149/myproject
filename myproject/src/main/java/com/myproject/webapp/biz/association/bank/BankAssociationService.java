package com.myproject.webapp.biz.association.bank;

import java.util.List;

public interface BankAssociationService {
	List<BankAssociationVO> getBankList();
	BankAssociationVO getBank(String bankCode);
}
