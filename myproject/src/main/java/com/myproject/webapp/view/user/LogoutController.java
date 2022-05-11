package com.myproject.webapp.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping("/logoutExample.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "loginExample.jsp";
	}
}
