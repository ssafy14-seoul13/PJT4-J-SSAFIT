package com.ssafy.ssafit.app;

import java.io.IOException;

import com.ssafy.ssafit.controller.UserController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main/*")
public class FrontController extends HttpServlet{
	private final UserController userController = new UserController();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		
		System.out.println("똥나와");
		
		if (pathInfo != null && pathInfo.startsWith("/user")) {
			System.out.println("똥싸");
			userController.process(request, response);
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
