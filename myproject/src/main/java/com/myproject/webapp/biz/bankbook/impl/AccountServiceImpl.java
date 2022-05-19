package com.myproject.webapp.biz.bankbook.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.bankbook.AccountService;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.user.UserVO;

@Service("AccountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDAOJPA accountDAO;

	@Override
	public AccountVO getAccount(UserVO vo) {
		return accountDAO.getAccount(vo);
	}

	@Override
	public void receiveInterest(AccountVO account, InterestVO interest) {
		accountDAO.receiveInterest(account, interest);
	}
}
