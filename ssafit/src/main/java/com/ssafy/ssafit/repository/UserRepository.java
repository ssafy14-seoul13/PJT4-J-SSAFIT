package com.ssafy.ssafit.repository;

import com.ssafy.ssafit.model.User;

public interface UserRepository {
	// 1. 사용차 등록
	abstract User addUser();
	
	// 2. 사용자 전체 조회
	abstract User[] getUserList();
	
	// 3. 사용자 삭제
	abstract User deleteUser();
}
