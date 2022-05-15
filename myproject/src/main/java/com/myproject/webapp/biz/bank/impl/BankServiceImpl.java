package com.myproject.webapp.biz.bank.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.bank.BankService;
import com.myproject.webapp.biz.bank.BankVO;

@Service("bankService")
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDAOJPA bankDAO;
	
	@Override
	public List<BankVO> getBank() {
		return bankDAO.getBank();
	}
}
