package com.myproject.webapp.view.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myproject.webapp.biz.user.UserService;
import com.myproject.webapp.biz.user.UserVO;
import com.myproject.webapp.biz.users.UsersService;
import com.myproject.webapp.biz.users.UsersVO;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo, HttpSession session) { 
		vo.setUser_id("hong");
		vo.setPwd("gildong");
		return "/WEB-INF/view/login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, HttpServletRequest request, HttpSession session) {
		
		Map<String, Boolean> error = new HashMap<>();
		request.setAttribute("error", error);
		
		if (vo.getUser_id() == null || vo.getUser_id().equals("")) {
			//throw new IllegalArgumentException("아이디는 필수 입력입니다.");
			error.put("emptyId", Boolean.TRUE);
			return "/WEB-INF/view/login.jsp";
		}
		
		UserVO user = userService.getUserVO(vo);
		if (user != vo) {
			session.setAttribute("user", user);
			return "main.jsp";
		}
		
		vo.setUser_id("");
		vo.setPwd("");
		error.put("failLogin", Boolean.TRUE);
		return "/WEB-INF/view/login.jsp";
	}
	
	// 게시판 예제용
	@Autowired
	private UsersService usersService;
	
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
		
		UsersVO user = usersService.getUser(vo);
			
		if (user != null) {
			session.setAttribute("userName", user.getName());
			return "getBoardListExample.do";
		}
		else return "loginExample.jsp";
	}
}
