package com.myproject.webapp.view.bankbook;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.webapp.biz.bank.BankService;
import com.myproject.webapp.biz.bank.BankVO;
import com.myproject.webapp.biz.bankbook.AccountService;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.bankbook.InterestService;
import com.myproject.webapp.biz.bankbook.InterestVO;
import com.myproject.webapp.biz.bankbook.TransactionHistoryService;
import com.myproject.webapp.biz.user.UserVO;
import com.myproject.webapp.util.AccountNumberDashFormat;
import com.myproject.webapp.util.MoneyCommaFormat;

@Controller
public class BankbookController {
	@Autowired
	private BankService bankService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private InterestService interestService;
	@Autowired
	private TransactionHistoryService txhisToryService;
	
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
		
		// 이자 0인 경우 처리
		if (interest.getInterest() < 1L)
			return "bankbook.do";
		
		accountService.receiveInterest(account, interest);
		
		BankVO bank = bankService.getBank();
		txhisToryService.receiveInterest(bank, account, interest);
		
		interestService.initInterest(interest);
		
		return "bankbook.do";
	}
}
