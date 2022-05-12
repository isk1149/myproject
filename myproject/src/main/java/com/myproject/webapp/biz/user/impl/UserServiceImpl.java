package com.myproject.webapp.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.webapp.biz.user.UserService;
import com.myproject.webapp.biz.user.UserVO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAOJPA userDAO;
	
	@Override
	public UserVO getUserVO(UserVO vo) {
		return userDAO.getUser(vo);
	}
}
