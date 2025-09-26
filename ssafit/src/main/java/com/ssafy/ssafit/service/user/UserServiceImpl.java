package com.ssafy.ssafit.service.user;

import com.ssafy.ssafit.model.User;

public class UserServiceImpl implements UserService {

	@Override
	// 1. 사용자 정보 추가 (회원가입) -> repo로 이동
	public boolean addUser(String userId, String userPw, String userPwCheck, String name, String email) {
		// 1-A. 모든 정보를 기입하지 않았을 경우 -> false
		if (userId == null || userPw == null || userPwCheck == null || name == null || email == null) {
			return false;
		}
		// 1-B. 이미 존재하는 아이디거나, 기존에 등록된 사용자일 경우
		// 1-B-a. repo에서 현재까지 가입한 사용자 정보 가져오기
		// 1-B-b. 일치하는 아이디 있으면 false
		// 1-B-c. 일치하는 이메일이 있으면 false
		// 1-C. 비밀번호를 확인했을 때, 잘못 입력한 경우 -> false;
		if (!userPw.equals(userPwCheck)) {
			return false;
		}
		return true;
	}

	@Override
	// 2. 사용자 로그인 -> repo로 이동
	public boolean login() {
		// 2-A. 모든 정보를 기입하지 않았을 경우 -> false
		// 2-B. 아이디와 비밀번호가 일치하지 않는 경우
		return true;
	}

	@Override
	public User searchByUserId() {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public User updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}


}
