package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public class ProductDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SQL_SELECT_NOTFINISHED ="select p.product_id, p.product_name, p.brand_name, p.category_id, c.category_name, p.purchase_date, p.starting_date, p.expiration_date, p.favorite\n"
			+ "from products p join category c on p.category_id = c.category_id where p.user_no = :userNo and p.finished = FALSE";
	private static final String SQL_SELECT_ALL ="select p.product_id, p.product_name, p.brand_name, p.category_id, c.category_name, p.purchase_date, p.starting_date, p.expiration_date, p.favorite\n"
			+ "from products p join category c on p.category_id = c.category_id where p.user_no = :userNo and (p.product_name like :keyword or p.brand_name like :keyword or c.category_name like :keyword)";
	private static final String SQL_INSERT ="insert into products (user_no, product_name, brand_name, category_id,  purchase_date, starting_date, expiration_date, favorite, finished)\n"
			+ "values (:userNo,:productName,:brandName,:categoryId,:purchaseDate, :startingDate,:expirationDate,:favorite, :finished)";
	
	
	public List<Product> notFinished(Integer userNo){
		String sql = SQL_SELECT_NOTFINISHED;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userNo", userNo);
		
		List<Product> result = jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<Product>(Product.class));
		return result.isEmpty()? null: result;
	}
	
	public List<Product> search(Integer userNo, String searchKey){
		String sql = SQL_SELECT_ALL;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userNo", userNo);
		param.addValue("keyword", '%'+searchKey+ '%');
		
		List<Product> result = jdbcTemplate.query(sql,param, new BeanPropertyRowMapper<Product>(Product.class));
		return result.isEmpty()? null: result;
	}
	
	public String insert(Integer userNo, Product p) {
		String sql= SQL_INSERT;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userNo", userNo);
		param.addValue("productName", p.getProductName());
		param.addValue("brandName", p.getBrandName());
		param.addValue("categoryId", p.getCategoryId());
		param.addValue("purchaseDate", p.getPurchaseDate());
		param.addValue("startingDate", p.getStartingDate());
		param.addValue("expirationDate", p.getExpirationDate());
		param.addValue("favorite", p.isFavorite());
		param.addValue("finished", p.isFinished());
		
		int count = jdbcTemplate.update(sql, param);
		
		if(count==0) {
			return "登録できませんでした。";
		}
		
		 return count+"件登録しました。";

	}
	
	
}
