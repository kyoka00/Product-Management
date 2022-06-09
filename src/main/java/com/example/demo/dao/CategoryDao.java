package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Categories;

@Repository
public class CategoryDao {
public static final String SQL_SELECT = "select * from category";
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Categories> select() {
		String sql = SQL_SELECT;
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		List<Categories> result = jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<Categories>(Categories.class));
		return result.isEmpty()? null: result;
	}
}