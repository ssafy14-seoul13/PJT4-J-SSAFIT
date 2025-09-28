package com.ssafy.ssafit.model;

import com.ssafy.ssafit.util.IdGenerator;

public class User {
	// 변수
	private String id;
	private String userId;
	private String userPw;
	private String name;
	private String email;
	
	// 기본 생성자
	private User() {
	}
	
	// 생성자
	public User(String userId, String userPw, String name, String email) {
		this.id = IdGenerator.newId(); // 가입 시, 새로운 유저 넘버 부여
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.email = email;
	}
	
	// getter&setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// toString
	@Override
	public String toString() {
		return "User[id : " + id + ", userId : " + userId + ", userPw : " + userPw + ", name : " + name + ", email : " + email + "]";
	}
}
