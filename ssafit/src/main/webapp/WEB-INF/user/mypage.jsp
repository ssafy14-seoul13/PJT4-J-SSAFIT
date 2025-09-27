<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
</head>
<body>
	<h2>마이페이지</h2>
	<p style="color:green">${message}</p>
	<p style="color:red">${error}</p>
	<hr>
	<%
		if (session.getAttribute("loggedInUser") == null) {
			response.sendRedirect("user?action=loginForm");
			return;
		}
	%>
	<h3>회원 정보</h3>
	<ul>
		<li><strong>아이디 : </strong> ${loggedInUser.userId}</li>
		<li><strong>이름 : </strong> ${loggedInUser.name}</li>
		<li><strong>이메일 : </strong> ${loggedInUser.email}</li>
	</ul>
	<hr>
	<h3>회원 정보 수정</h3>
	<form method="POST" action="./user">
		<input type="hidden" name="action" value="modify">
		<label for="name">새 이름</label>
		<input type="text" id="name" name="name"><br>
		<label for="userPw">새 비밀번호</label>
		<input type="password" id="userPw" name="userPw"><br>
		<label for="userPwCheck">새 비밀번호 확인</label>
		<input type="password" id="userPwCheck" name="userPwCheck"><br>
		<label for="email">새 이메일</label>
		<input type="email" id="email" name="email"><br>
		<input type="submit" value="수정하기">
	</form>
	<hr>
	<a href="user?action=logout">😥 로그아웃</a>
	<a href="user?action=delete" style="color:red">🥵 회원탈퇴</a>
</body>
</html>