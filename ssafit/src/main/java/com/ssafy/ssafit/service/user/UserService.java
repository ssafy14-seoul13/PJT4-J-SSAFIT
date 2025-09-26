package com.ssafy.ssafit.service.user;

import com.ssafy.ssafit.model.User;

public interface UserService {
	// 1. 사용자 추가 (회원가입)
	abstract boolean addUser(String userId, String userPw, String userPwCheck, String name, String email);
	
	// 2. 사용자 확인 (로그인)
	abstract boolean login();
	
	// 2. 로그인한 사용자(나) 정보 조회 (마이페이지)
	abstract User searchByUserId();
	
	// 3. 사용자 정보 수정 (마이페이지)
	abstract User updateUser();
	
	// 4. 사용자 정보 삭제 (마이페이지)
	abstract User deleteUser();
}
