package com.myproject.webapp.biz.user.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.board.BoardVO;
import com.myproject.webapp.biz.user.UserVO;

@Repository
public class UserDAOJPA {
	@PersistenceContext
	private EntityManager em;
	
	public UserVO getUser(UserVO vo) {
		return (UserVO) em.find(UserVO.class, vo.getId());
	}
}
