package com.ssafy.ssafit.controller;

import java.io.IOException;

import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.repository.UserRepositoryImpl;
import com.ssafy.ssafit.service.user.UserService;
import com.ssafy.ssafit.service.user.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController extends HttpServlet{
	private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
	
	public UserController() {
		super();
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("loginForm")) {
	        request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
	        return;
	    }
		
		switch (action) {
			case "loginForm" :
				request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
		        break;
			case "login" :
				String userId = request.getParameter("userId");
				String userPw = request.getParameter("userPw");
				
				User user = userService.login(userId, userPw);
				if (user != null) {
					response.sendRedirect("");
				} else {
					request.setAttribute("error", "아이디 또는 비밀번호가 틀립니다.");
					request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
				}
				break;
			case "registForm" :
				request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
		        break;
			case "register" :
				String userId1 = request.getParameter("userId");
				String userPw1 = request.getParameter("userPw");
				String userPwCheck = request.getParameter("userPwCheck");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				
				boolean result = userService.addUser(userId1, userPw1, userPwCheck, name, email);
				
				if (result) {
					request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
				} else {
					request.setAttribute("error", "아이디 또는 비밀번호가 틀립니다.");
					request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
				}
				
		}
	}
}
