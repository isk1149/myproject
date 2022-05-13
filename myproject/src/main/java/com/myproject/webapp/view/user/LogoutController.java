package com.myproject.webapp.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/WEB-INF/view/login.jsp";
	}
	
	@RequestMapping("/logoutExample.do")
	public String logouts(HttpSession session) {
		session.invalidate();
		return "loginExample.jsp";
	}
}
