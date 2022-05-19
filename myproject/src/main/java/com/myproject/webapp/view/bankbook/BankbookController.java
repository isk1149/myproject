package com.myproject.webapp.view.bankbook;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.webapp.biz.bankbook.AccountService;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestService;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.user.UserVO;
import com.myproject.webapp.util.AccountNumberDashFormat;
import com.myproject.webapp.util.MoneyCommaFormat;

@Controller
public class BankbookController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private InterestService interestService;
	
	@RequestMapping(value="/bankbook.do")
	public String getBankbook(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		model.addAttribute("account", account);
		model.addAttribute("accountNo", AccountNumberDashFormat.format(account.getAccountNo()));
		model.addAttribute("deposit", MoneyCommaFormat.format(account.getDeposit()));
		
		InterestVO interest = interestService.getInterest(account);
		interestService.calculateInterest(account, interest);
		
		model.addAttribute("interestApplyCount", interest.getApplyCount());
		model.addAttribute("interest", MoneyCommaFormat.format(interest.getInterest()));
		
		return "/WEB-INF/view/bankbook.jsp";
	}
	
	@RequestMapping(value="/getInterest.do")
	public String getInterest(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		InterestVO interest = interestService.getInterest(account);
		
		accountService.receiveInterest(account, interest);
		interestService.initInterest(interest);
		return "bankbook.do";
	}
}
