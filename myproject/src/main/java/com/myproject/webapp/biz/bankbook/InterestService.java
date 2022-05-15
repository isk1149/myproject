package com.myproject.webapp.biz.bankbook;

public interface InterestService {
	InterestVO getInterest(AccountVO vo);
	void updateInterest(InterestVO interest);
	void calculateInterest(AccountVO account, InterestVO interest);
	void initInterest(InterestVO interest);
}