package com.myproject.webapp.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.webapp.biz.users.UsersService;
import com.myproject.webapp.biz.users.UsersVO;

@Controller
public class LoginController {
	@Autowired
	private UsersService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String signIn() {
		return "";
	}
	
	@RequestMapping(value="/loginExample.do", method=RequestMethod.GET)
	public String loginView(UsersVO vo) {
		vo.setId("test");
		vo.setPassword("test123");
		return "loginExample.jsp";
	}
	
	@RequestMapping(value="/loginExample.do", method=RequestMethod.POST)
	public String login(UsersVO vo, HttpSession session) {
		if (vo.getId() == null || vo.getId().equals(""))
			throw new IllegalArgumentException("아이디는 필수 입력입니다.");
		
		UsersVO user = userService.getUser(vo);
		if (user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardListExample.do";
		}
		else return "loginExample.jsp";
	}
}
