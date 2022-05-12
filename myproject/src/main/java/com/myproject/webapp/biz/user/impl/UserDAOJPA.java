package com.myproject.webapp.biz.user.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.user.UserVO;

@Repository
public class UserDAOJPA {
	@PersistenceContext
	private EntityManager em;
	
	public UserVO getUser(UserVO vo) {
		UserVO user = (UserVO) em.find(UserVO.class, vo.getUser_id());
		if (user == null || !user.getPwd().equals(vo.getPwd())) {
			return vo;
		}
		return user;
	}
}

