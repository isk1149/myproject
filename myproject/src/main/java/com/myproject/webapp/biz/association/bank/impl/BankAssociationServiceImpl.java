package com.myproject.webapp.biz.association.bank.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.association.bank.BankAssociationService;
import com.myproject.webapp.biz.association.bank.BankAssociationVO;

@Service("bankAssociationService")
public class BankAssociationServiceImpl implements BankAssociationService {

	@Autowired
	private BankAssociationDAOJPA bankAssocDAO;
	
	@Override
	public List<BankAssociationVO> getBankList() {
		return bankAssocDAO.getBankList();
	}

	@Override
	public BankAssociationVO getBank(String bankCode) {
		return bankAssocDAO.getBank(bankCode);
	}
}
