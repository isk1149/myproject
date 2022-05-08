package com.myproject.webapp.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.webapp.biz.user.UserService;
import com.myproject.webapp.biz.user.UserVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo) {
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpSession session) {
		UserVO user = userService.getUser(vo);
		if (user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		}
		else return "login.jsp";
	}
}
/*
@RequestMapping(value="/login.do", method=RequestMethod.GET)
public String loginView(UserVO vo, UserDAO userDAO) {
	vo.setId("test");
	vo.setPassword("test123");
	return "login.jsp";
}

@RequestMapping(value="/login.do", method=RequestMethod.POST)
public String login(UserVO vo, UserDAO userDAO, HttpSession session) {
	UserVO user = userDAO.getUser(vo);
	if (user != null) {
		session.setAttribute("userName", user.getName());
		return "getBoardList.do";
	}
	else return "login.jsp";
}
*/