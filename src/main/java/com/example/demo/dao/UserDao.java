package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public class UserDao {
	
	public static final String SQL_SELECT = "select * from users where user_id = :id and password = :pass";
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public User login(String loginId, String password) {
		String sql = SQL_SELECT;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", loginId);
		param.addValue("pass", password);
		
		List<User> result = jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<User>(User.class));
		return result.isEmpty()? null: result.get(0);
	}
}
