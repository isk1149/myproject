package com.myproject.webapp.biz.users.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.board.BoardVO;
import com.myproject.webapp.biz.users.UsersVO;

@Repository
public class UsersDAOJPA {
	@PersistenceContext
	private EntityManager em;
	
	public UsersVO getUser(UsersVO vo) {
		return (UsersVO) em.find(UsersVO.class, vo.getId());
	}
}
