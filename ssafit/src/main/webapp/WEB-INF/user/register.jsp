<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<form method="POST" action="./user">
		<input type="hidden" name="action" value="register">
		<label for="userId">아이디</label>
		<input type="text" id="userId" name="userId"><br>
		<label for="userPw">비밀번호</label>
		<input type="password" id="userPw" name="userPw"><br>
		<label for="userPwCheck">비밀번호 확인</label>
		<input type="password" id="userPwCheck" name="userPwCheck"><br>
		<label for="name">이름</label>
		<input type="text" id="name" name="name"><br>
		<label for="email">이메일</label>
		<input type="email" id="email" name="email"><br>
		<input type="submit" value="회원가입">
		<input type="reset" value="초기화">
	</form>
</body>
</html>