package com.ssafy.ssafit.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.util.DBUtil;

public class UserRepositoryImpl implements UserRepository{
	
	// DB연결을 위한 세팅
	private DBUtil util = DBUtil.getInstance();
	private static UserRepositoryImpl instance = new UserRepositoryImpl();

    private UserRepositoryImpl() {}

    public static UserRepositoryImpl getInstance() {
        return instance;
    }
	// 사용자를 저장할 저장소
	private static final Map<String, User> userMap = new HashMap<>();
	
	@Override
	// 1. 사용자 추가
	public User addUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO user (id, user_id, user_pwd, name, email) VALUES (?, ?, ?, ?, ?)";
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getUserId());
			pstmt.setString(3, user.getUserPw());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getEmail());
			
			int result = pstmt.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
	    return user;
	}

	@Override
	// 2. 등록된 모든 사용자 정보 반환
	public User[] getUserList() {
		Collection<User> values = userMap.values();
        return values.toArray(new User[0]);
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql = "SELECT * FROM board WHERE user_id = ? AND user_pwd = ?";
		User user = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user = new User();
				user.setId(rs.getString("id"));
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pwd"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		return user;
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
