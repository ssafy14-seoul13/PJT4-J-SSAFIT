<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<style>:root{--bg:#fafafa;--fg:#111;--muted:#666;--line:#eaeaea;--btn:#111;--btnfg:#fff;}
*{box-sizing:border-box;}
body{margin:0;font-family:system-ui,-apple-system,Segoe UI,Roboto,sans-serif;background:var(--bg);color:var(--fg);max-width:400px;margin:80px auto 40px;padding:0 20px;}
h2{text-align:center;margin-bottom:24px;}
p.message{color:green;text-align:center;margin:8px 0;font-size:0.9rem;}
p.error{color:red;text-align:center;margin:8px 0 16px 0;font-size:0.9rem;}
hr{border:none;border-top:1px solid var(--line);margin:24px 0;}
form label{display:block;margin:12px 0 6px;font-weight:600;}
input[type="text"],input[type="password"],input[type="email"]{width:100%;padding:10px 12px;border-radius:10px;border:1px solid var(--line);margin-bottom:12px;font-size:1rem;}
input[type="submit"],input[type="reset"]{width:48%;padding:12px;border-radius:10px;border:none;font-size:16px;font-weight:600;cursor:pointer;margin-top:12px;}
input[type="submit"]{background:var(--btn);color:var(--btnfg);}
input[type="submit"]:hover{background:#333;}
input[type="reset"]{background:#e5e7eb;color:var(--fg);}
input[type="reset"]:hover{background:#ccc;}
a{display:inline-block;margin-top:16px;color:var(--muted);text-decoration:none;font-size:14px;}
a + a{margin-left:12px;}
footer{text-align:center;color:var(--muted);padding:24px 0 12px 0;}</style>
</head>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
	<p class="message">${message}</p>
	<p class="error">${error}</p>
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
	<a href="./">메인으로</a>
	<a href="./user?action=loginForm">로그인 페이지로</a>
	<footer>© SSAFIT</footer>
</body>
</html>