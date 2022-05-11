package com.myproject.webapp.view.bankbook;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myproject.webapp.biz.bankbook.BankbookVO;

@Controller
public class BankbookController {

	@RequestMapping("/bankbook.do")
	public String getBankbook(BankbookVO vo, Model model) {
		//if (session.getAttribute("userName") == null)
			//return "login.jsp";
		return "/WEB-INF/view/bankbook.jsp";
	}
}
