package com.myproject.webapp.view.bankbook;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.webapp.biz.bankbook.AccountService;
import com.myproject.webapp.biz.bankbook.AccountVO;
import com.myproject.webapp.biz.user.UserService;
import com.myproject.webapp.biz.user.UserVO;
import com.myproject.webapp.util.AccountNumberDashFormat;

@Controller
public class BankbookController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/bankbook.do")
	public String getBankbook(Model model, HttpSession session) {
		if (session.getAttribute("user") == null)
			return "/WEB-INF/view/login.jsp";
		UserVO user = (UserVO) session.getAttribute("user");
		AccountVO account = accountService.getAccount(user);
		//model.addAttribute("account", account);
		model.addAttribute("accountNo", AccountNumberDashFormat.format(account.getAccountNo()));

		return "/WEB-INF/view/bankbook.jsp";
	}
}
