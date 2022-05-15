package com.myproject.webapp.biz.bankbook.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestService;
import com.myproject.webapp.biz.bankbook.InterestVO;

@Service("InterestService")
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestDAOJPA interestDAO;
	
	@Override
	public InterestVO getInterest(AccountVO vo) {
		return interestDAO.getInterest(vo);
	}

	@Override
	public void updateInterest(InterestVO interest) {
		interestDAO.updateInterest(interest);
	}

	@Override
	public void calculateInterest(AccountVO account, InterestVO interest) {
		
		// 이자 계산
		interestDAO.calculateInterest(account, interest);
	}

	@Override
	public void initInterest(InterestVO interest) {
		interestDAO.initInterest(interest);
	}
	
	
}
