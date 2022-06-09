package com.example.demo.entity;


public class User {
	
	private Integer userNo;
	private String userId;
	private String userName;
	private String password;
	
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {
		
	}
	
	public User(Integer userNo, String userId, String password) {
		this.userNo =userNo;
		this.userId = userId;
		this.password = password;
	}
	
}
