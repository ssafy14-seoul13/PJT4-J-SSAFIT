package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.model.User;

public interface UserRepository {
	// 1. 사용차 등록
	abstract User addUser(User user);
	
	// 2. 사용자 전체 조회
	abstract User[] getUserList();
	
	// 3. userId로 사용자 조회 (중복 아이디 확인)
	abstract User searchByUserId(String userId);
	
	// 4. email로 사용자 조회 (이미 등록한 사용자인지 확인)
	abstract User searchByEmail(String email);
	
	// 5. userId, password로 사용자 조회 (로그인 성공 여부)
	abstract User searchByUserIdPassword(String userId, String userPw);
	
	// 6. 사용자 비밀번호만 수정
	abstract boolean updatePassword(String userId, String newUserPw);
	
	// 7. 사용자 이름만 수정
	abstract boolean updateName(String userId, String newName);
	
	// 8. 사용자 이메일만 수정
	abstract boolean updateEmail(String userId, String newEmail);
	// 3. 사용자 삭제
	abstract User deleteUser();
}
