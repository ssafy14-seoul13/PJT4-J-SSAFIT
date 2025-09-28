<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<style>
:root{--bg:#fafafa;--fg:#111;--muted:#666;--line:#eaeaea;--btn:#111;--btnfg:#fff;}
*{box-sizing:border-box;}
body{margin:0;font-family:system-ui,-apple-system,Segoe UI,Roboto,sans-serif;background:var(--bg);color:var(--fg);max-width:480px;margin-left:auto;margin-right:auto;padding:24px 20px;}
h2{text-align:center;margin-bottom:16px;}
p.message{color:green;text-align:center;margin:8px 0;font-size:0.9rem;}
p.error{color:red;text-align:center;margin:8px 0 16px 0;font-size:0.9rem;}
hr{border:none;border-top:1px solid var(--line);margin:24px 0;}
h3{margin-bottom:12px;border-bottom:1px solid var(--line);padding-bottom:6px;font-weight:600;}
ul{list-style:none;padding-left:0;margin:0 0 24px 0;}
ul li{margin-bottom:8px;font-size:1rem;}
ul li strong{font-weight:600;}
form label{display:block;margin-bottom:6px;font-weight:600;margin-top:12px;}
form input[type="text"],form input[type="password"],form input[type="email"]{width:100%;padding:10px 12px;border-radius:10px;border:1px solid var(--line);font-size:1rem;}
form input[type="submit"]{margin-top:20px;width:100%;background:var(--btn);color:var(--btnfg);border:none;padding:14px;border-radius:10px;font-size:16px;cursor:pointer;font-weight:600;}
form input[type="submit"]:hover{background:#333;}
.actions{margin-top:20px;display:flex;justify-content:space-between;gap:12px;flex-wrap:wrap;}
.actions a{text-decoration:none;padding:12px 18px;border-radius:10px;background:var(--btn);color:var(--btnfg);font-weight:600;text-align:center;flex:1 1 45%;cursor:pointer;transition:background-color 0.2s;}
.actions a.logout{background:#555;}
.actions a.logout:hover{background:#333;}
.actions a.delete{background:#e53e3e;color:#fff;}
.actions a.delete:hover{background:#b53030;}
footer{text-align:center;color:var(--muted);padding:24px 0 12px 0;}
</style>
</head>
<body>
	<h2>마이페이지</h2>
	<p class="message">${message}</p>
	<p class="error">${error}</p>
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
	<div class="actions">
	<a href="user?action=logout">로그아웃</a>
	<a href="user?action=delete" style="color:red">회원탈퇴</a>
	</div>
	<br/>
	<a href="./" style="color: var(--muted); font-size: 14px; display: block; text-align: right; margin-top: 24px;">메인으로</a>
	<footer>© SSAFIT</footer>
</body>
</html>