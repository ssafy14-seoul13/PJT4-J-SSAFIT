package com.ssafy.ssafit.service.user;

import com.ssafy.ssafit.model.User;

public interface UserService {
	// 1. 사용자 추가 (회원가입)
	abstract boolean addUser(String userId, String userPw, String userPwCheck, String name, String email);
	
	// 2. 사용자 확인 (로그인)
	abstract User login(String userId, String userPw);
	
	// 2. 로그인한 사용자(나) 정보 조회 (마이페이지)
	abstract User searchByUserId(String userId);
	
	// 3. 사용자 비밀번호 수정
	abstract boolean updatePassword(String userId, String oldPw, String newPw, String newPwCheck);
	
	// 4. 사용자 이름 수정
	abstract boolean updateName(String userId, String newName);
	
	// 5. 사용자 이메일 수정
	abstract boolean updateEmail(String userId, String newEmail);
	
	// 4. 사용자 정보 삭제 (마이페이지)
	abstract User deleteUser();
}
