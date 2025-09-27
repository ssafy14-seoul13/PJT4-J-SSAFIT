package com.ssafy.ssafit.repository;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafit.model.User;

public class UserRepositoryImpl implements UserRepository{

	// 사용자를 저장할 저장소
	private static final List<User> userList = new ArrayList<>();
	
	@Override
	// 1. 사용자 추가
	public User addUser(User user) {
		userList.add(user);
		return user;
	}

	@Override
	// 2. 등록된 모든 사용자 정보 반환
	public User[] getUserList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	// 3. UserId로 사용자 찾기 (중복 id 찾기)
	public User searchByUserId(String userId) {
		// 3-a. 등록된 사용자가 없을 경우 -> null 반환
		if (userList.isEmpty()) return null;
		// 3-b. 같은 아이디가 있을 경우 -> 해당 유저 정보 반환
		for (User u : userList) {
			if (u.getUserId().equals(userId)) return u;
		}
		return null;
	}
	
	@Override
	// 4. 이메일로 사용자 찾기 (이미 등록한 사용자인지 확인)
	public User searchByEmail(String email) {
		// 4-a. 등록된 사용자가 없을 경우 -> null 반횐
		if (userList.isEmpty()) return null;
		// 4-b. 같은 이메일이 있을 경우 -> 해당 유저 정보 반환
		for (User u : userList) {
			if (u.getEmail().equals(email)) return u;
		}
		return null;
	}
	
	@Override
	// 5. 아이디, 패스워드로 사용자 찾기 (로그인 여부 확인)
	public User searchByUserIdPassword(String userId, String userPw) {
		// 5-a. 등록된 사용자가 없을 경우 -> null 반환
		if (userList.isEmpty()) return null;
		// 5-b. 같은 아이디와 비밀번호가 있을 경우 -> 해당 유저 정보 반환
		for (User u : userList) {
			if (u.getUserId().equals(userId) && u.getUserPw().equals(userPw)) return u;
		}
		return null;
	}

	@Override
	public boolean updatePassword(String userId, String newUserPw) {
		User user = searchByUserId(userId);
		if (user != null) {
			user.setUserPw(newUserPw);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateName(String userId, String newName) {
		User user = searchByUserId(userId);
		if (user != null) {
			user.setName(newName);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEmail(String userId, String newEmail) {
		User user = searchByUserId(userId);
		if (user != null) {
			user.setEmail(newEmail);
			return true;
		}
		return false;
	}
	
	@Override
	public User deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
