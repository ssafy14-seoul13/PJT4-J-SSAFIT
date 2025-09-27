<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
</head>
<body>
	<h2>로그인</h2>
	<hr>
	<p style="color:red;">${error}</p>
	<form method="POST" action="./user">
		<input type="hidden" name="action" value="login">
		<label for="userId">아이디</label>
		<input type="text" id="userId" name="userId"><br>
		<label for="userPw">비밀번호</label>
		<input type="text" id="userPw" name="userPw"><br>
		<input type="submit" value="로그인">
	</form>
	<a href="./">메인으로 →</a>
</body>
</html>