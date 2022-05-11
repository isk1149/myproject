package com.myproject.webapp.biz.users.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.myproject.webapp.biz.board.BoardVO;
import com.myproject.webapp.biz.users.UsersVO;

@Repository
public class UsersDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String USER_GET  ="select * from users where id= ? and password = ?";
	
	public UsersVO getUser(UsersVO vo) {
		Object[] args = {vo.getId(), vo.getPassword()};
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}
}

class UserRowMapper implements RowMapper<UsersVO> {
	public UsersVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsersVO user = new UsersVO();
		user.setId(rs.getString("id"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setRole(rs.getString("role"));
		return user;
	}
}