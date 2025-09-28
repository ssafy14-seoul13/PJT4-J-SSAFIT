package com.ssafy.ssafit.service.user;

import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.repository.UserRepository;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	// 1. 사용자 정보 추가 (회원가입) -> repo로 이동
	public boolean addUser(String userId, String userPw, String userPwCheck, String name, String email) {
		// 1-a. 모든 정보를 기입하지 않았을 경우 -> false
		if (userId == null || userPw == null || userPwCheck == null || name == null || email == null) return false;
		// 1-b. 이미 존재하는 아이디인 경우
		if (userRepository.searchByUserId(userId) != null) return false;
		// 1-c. 이미 등록한 사용자인 경우 (일치하는 이메일이 있을 경우)
		if (userRepository.searchByEmail(email) != null) return false;
		// 1-d. 비밀번호를 확인했을 때, 잘못 입력한 경우 -> false;
		if (!userPw.equals(userPwCheck)) return false;
		
		// 1-e. 모든 검증을 통과한 경우 
		User user = new User(userId, userPw, name, email);
		userRepository.addUser(user);
		return true;
	}

	@Override
	// 2. 사용자 로그인 -> repo로 이동
	public User login(String userId, String userPw) {
		// 2-a. 모든 정보를 기입하지 않았을 경우 -> null
		if (userId == null || userPw == null) return null;
		// 2-b. 아이디와 비밀번호가 일치하지 않는 경우 -> null
		User user = userRepository.searchByUserIdPassword(userId, userPw);
		if (user == null) return null;
		return user;
	}

	@Override
	public User searchByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
		
	}

	
	@Override
	// 4. 비밀번호 수정 (마이페이지)
	public boolean updatePassword(String userId, String oldPw, String newPw, String newPwCheck) {
		// 4-a. 기존 비밀번호 확인
		User user = userRepository.searchByUserIdPassword(userId, oldPw);
		if (user == null) return false;
		// 4-b. 새 비밀번호와 확인용 비밀번호 일치 여부
		if (!newPw.equals(newPwCheck)) return false;
		// 4-c. 비밀번호 수정
		boolean result = userRepository.updatePassword(userId, newPw);
		return result;
	}

	@Override
	// 5. 이름 수정 (마이페이지)
	public boolean updateName(String userId, String newName) {
		// 5-a. 유효성 검사
		if (userId == null || newName == null || newName.isEmpty()) return false;
		// 5-b. Repository를 통해 이름만 수정
		boolean result = userRepository.updateName(userId, newName);
		return result;
	}

	@Override
	// 6. 이메일 수정 (마이페이지)
	public boolean updateEmail(String userId, String newEmail) {
		// 6-a. 유효성 검사 및 중복 이메일 확인
		if (userId == null || newEmail == null || newEmail.isEmpty()) return false;
		// 6-b. 동일한 이메일이 이미 존재하는지 확인
		if (userRepository.searchByEmail(newEmail) != null) return false;
		// 6-c. Repository를 통해 이메일만 수정
		boolean result = userRepository.updateEmail(userId, newEmail);
		return result;
	}

	@Override
	public boolean deleteUser(String userId) {
		return userRepository.deleteUser(userId);
	}


}
