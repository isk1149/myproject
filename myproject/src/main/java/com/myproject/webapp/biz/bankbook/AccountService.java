package com.myproject.webapp.biz.bankbook;

import com.myproject.webapp.biz.user.UserVO;

public interface AccountService {
	AccountVO getAccount(UserVO vo);
	void receiveInterest(AccountVO account, InterestVO interest);
}