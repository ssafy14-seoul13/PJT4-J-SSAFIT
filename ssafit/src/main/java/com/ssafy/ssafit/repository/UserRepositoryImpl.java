package com.ssafy.ssafit.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.util.DBUtil;

public class UserRepositoryImpl implements UserRepository{
	
	// DB 연결하기 위한 세팅
	private DBUtil util = DBUtil.getInstance();
	///////////////////////////////////////////////////////////////
	private static UserRepositoryImpl instance = new UserRepositoryImpl();


    private UserRepositoryImpl() {}

    public static UserRepositoryImpl getInstance() {
        return instance;
    }
    ////////////////////////////////////////////////////////////////////

	@Override
	// 1. 사용자 추가
	public User addUser(User user) {
		// 1-a. 이미 존재하는 아이디일 경우 -> null
		if (userMap.containsKey(user.getUserId())) {
	        return null;
	    }
		// 1-b. 존재하지 않을 경우 -> 등록
	    userMap.put(user.getUserId(), user);
	    return user;
	}

	@Override
	// 2. 등록된 모든 사용자 정보 반환
	public User[] getUserList() {
		List<User> list = new ArrayList<>();
		String sql = "SELECT * FROM board";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	}
	
	@Override
	// 3. UserId로 사용자 찾기 (중복 id 찾기)
	public User searchByUserId(String userId) {
		return userMap.get(userId);
	}
	
	@Override
	// 4. 이메일로 사용자 찾기 (이미 등록한 사용자인지 확인)
	public User searchByEmail(String email) {
		for (User user : userMap.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
	}
	
	@Override
	// 5. 아이디, 패스워드로 사용자 찾기 (로그인 여부 확인)
	public User searchByUserIdPassword(String userId, String userPw) {
		User user = userMap.get(userId);
        if (user != null && user.getUserPw().equals(userPw)) {
            return user;
        }
        return null;
	}

	@Override
	public boolean updatePassword(String userId, String newUserPw) {
		User user = userMap.get(userId);
        if (user != null) {
            user.setUserPw(newUserPw);
            return true;
        }
        return false;
	}

	@Override
	public boolean updateName(String userId, String newName) {
		User user = userMap.get(userId);
        if (user != null) {
            user.setName(newName);
            return true;
        }
        return false;
	}

	@Override
	public boolean updateEmail(String userId, String newEmail) {
		User user = userMap.get(userId);
        if (user != null) {
            user.setEmail(newEmail);
            return true;
        }
        return false;
	}
	
	@Override
	// 10. 사용자 삭제
	public boolean deleteUser(String userId) {
		return userMap.remove(userId) != null;
	}

}
