<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
<h2><%= "Hello World!" %></h2>
<a href="<%= request.getContextPath() %>/main/user?action=loginForm"><%= "로그인" %></a>
<a href="<%= request.getContextPath() %>/main/user?action=registForm"><%= "회원가입" %></a>
<a href="<%= request.getContextPath() %>/main/user?action=mypage"><%= "마이페이지" %></a>
</body>
</html>
