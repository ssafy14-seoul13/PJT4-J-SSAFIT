package com.ssafy.ssafit.controller;

import java.io.IOException;

import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.repository.UserRepositoryImpl;
import com.ssafy.ssafit.service.user.UserService;
import com.ssafy.ssafit.service.user.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserController {
	private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
	
	public UserController() {
		super();
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		System.out.println("똥");
		switch (action) {
			// 1. 로그인 폼 페이지 열기
			case "loginForm" :
				System.out.println("똥2");
				// 1-a. 로그인한 사용자일 경우 -> 마이페이지
				if (session.getAttribute("loggedInUser") != null) {
					response.sendRedirect("user?action=mypage");
				// 1-b. 로그인 안한 사용자일 경우 -> 로그인페이지
				} else {
					request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
					System.out.println("똥5");
				}
		        break;
		    // 2. 로그인 폼 제출
			case "login" :
				String userId = request.getParameter("userId");
				String userPw = request.getParameter("userPw");
				
				User user = userService.login(userId, userPw);
				if (user != null) {
					// 로그인 성공 시 세션에 사용자 정보 저장
			        request.getSession().setAttribute("loggedInUser", user);
					response.sendRedirect("./");
				} else {
					request.setAttribute("error", "아이디 또는 비밀번호가 틀립니다.");
					request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
				}
				break;
			// 3. 회원가입 폼 페이지 열기
			case "registForm" :
				// 3-a. 로그인한 사용자일 경우 -> 마이페이지
				if (session.getAttribute("loggedInUser") != null) {
					response.sendRedirect("user?action=mypage");
				// 3-b. 로그인 안한 사용자일 경우 -> 회원가입 페이지
				} else {
					request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
				}
		        break;
		    // 4. 회원가입 폼 제출
			case "register" :
				String userId1 = request.getParameter("userId");
				String userPw1 = request.getParameter("userPw");
				String userPwCheck = request.getParameter("userPwCheck");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				
				boolean result = userService.addUser(userId1, userPw1, userPwCheck, name, email);
				
				if (result) {
					request.setAttribute("message", "성공적으로 가입되었습니다.");
				} else {
					request.setAttribute("error", "이미 가입했거나, 아이디, 비밀번호가 잘못되었습니다.");
				}
				request.getRequestDispatcher("/WEB-INF/user/register.jsp").forward(request, response);
				break;
			// 5. 마이페이지 폼 열기
			case "mypage" :
				User user2 = (User) session.getAttribute("loggedInUser");
				// 5-a. 로그인 안 했을 경우 -> 로그인 폼
				if (user2 == null) {
					request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
				// 5-b. 로그인 했을 경우 -> 회원가입 폼
				} else {
					request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
				}
				break;
			// 6. 마이페이지 내 정보 수정 폼 제출
			case "modify" :
				User loggedInUser = (User) session.getAttribute("loggedInUser");
				String loggedUserId = loggedInUser.getUserId();
				
				String newName = request.getParameter("name");
				String newUserPw = request.getParameter("userPw");
				String newUserPwCheck = request.getParameter("userPwCheck");
				String newEmail = request.getParameter("email");
				
				boolean success = true;
				// 6-a. 이름 변경
				if (!newName.isEmpty() && !(newName.equals(loggedInUser.getName()))) {
					if (!userService.updateName(loggedUserId, newName)) {
						success = false;
					} else {
						loggedInUser.setName(newName);
					}
				}
				// 6-b. 비밀번호 변경
				if (!newUserPw.isEmpty() && !newUserPwCheck.isEmpty() && !(newUserPw.equals(loggedInUser.getUserPw()))) {
					if (!userService.updatePassword(loggedUserId, loggedInUser.getUserPw(), newUserPw, newUserPwCheck)) {
						success = false;
					} else {
						loggedInUser.setUserPw(newUserPw);
					}
				}
				// 6-c. 이메일 변경
				if (!newEmail.isEmpty() && !(newEmail.equals(loggedInUser.getEmail()))) {
					if (!(userService.updateEmail(loggedUserId, newEmail))) {
						success = false;
					} else {
						loggedInUser.setEmail(newEmail);
					}
				}
				
				if (success) {
					request.setAttribute("message", "회원정보가 성공적으로 수정되었습니다.");
				} else {
					request.setAttribute("error", "회원정보 수정에 실패했습니다.");
				}
				
				request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
				break;
			// 7. 로그아웃
			case "logout" :				
				if (session.getAttribute("loggedInUser") != null) {
					session.invalidate();
				}
				response.sendRedirect("./");
				break;
			// 8. 회원 탈퇴
			case "delete" :
		        if (session.getAttribute("loggedInUser") != null) {
		        	User user3 = (User) session.getAttribute("loggedInUser");
		            if (user3 != null) {
		                String userId2 = user3.getUserId();
		                userService.deleteUser(userId2);
		                
		                session.invalidate();
		            }
		        }
		        response.sendRedirect("./");
		        break;
		    default :
		    	response.sendRedirect("./");
		    	break;
		}
	}
}
