package com.myproject.webapp.biz.association.bank.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.association.bank.BankAssociationAccountService;
import com.myproject.webapp.biz.association.bank.BankAssociationAccountVO;

@Service("BankAssociationAccountService")
public class BankAssociationAccountServiceImpl implements BankAssociationAccountService {

	@Autowired
	private BankAssociationAccountDAOJPA bankAssocAcctDAO;
	
	@Override
	public BankAssociationAccountVO getAccount(BankAssociationAccountVO vo) {
		return bankAssocAcctDAO.getAccount(vo);
	}

}
