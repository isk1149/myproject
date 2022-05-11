package com.myproject.webapp.biz.users.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.users.UsersService;
import com.myproject.webapp.biz.users.UsersVO;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	//private UserDAOSpring userDAO;
	private UsersDAOJPA userDAO;
	
	@Override
	public UsersVO getUser(UsersVO vo) {
		return userDAO.getUser(vo);
	}
}
