<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>SSAFIT</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<style>
:root{--bg:#fafafa;--fg:#111;--muted:#666;--line:#eaeaea;--btn:#111;--btnfg:#fff}
*{box-sizing:border-box}
body{margin:0;font-family:system-ui,-apple-system,Segoe UI,Roboto,sans-serif;background:var(--bg);color:var(--fg)}
main{max-width:400px;margin:80px auto;padding:0 20px}
.card{background:#fff;border:1px solid var(--line);border-radius:12px;padding:24px}
h2{text-align:center;margin-bottom:24px}
form label{display:block;margin:12px 0 6px;font-weight:600}
input[type=text],input[type=password]{width:100%;padding:10px 12px;border-radius:10px;border:1px solid var(--line);margin-bottom:12px}
input[type=submit]{width:100%;background:var(--btn);color:var(--btnfg);border:none;padding:12px;border-radius:10px;font-size:16px;cursor:pointer}
input[type=submit]:hover{background:#333}
.error{color:red;text-align:center;margin-bottom:16px}
.bottom-link{text-align:right;margin-top:16px}
.bottom-link a{color:var(--muted);text-decoration:none;font-size:14px}
footer{text-align:center;color:var(--muted);padding:24px}
</style>
</head>
<body>
	<main>
		<section class="card">
		<h2>로그인</h2>
		<p class="error">${error}</p>
		<form method="POST" action="./user">
			<input type="hidden" name="action" value="login">
			<label for="userId">아이디</label>
			<input type="text" id="userId" name="userId"><br>
			<label for="userPw">비밀번호</label>
			<input type="text" id="userPw" name="userPw"><br>
			<input type="submit" value="로그인">
		</form>
		<div class="bottom-link">
			<a href="./">메인으로 →</a>
		</div>
		</section>
	</main>
	<footer>
		© SSAFIT
	</footer>
</body>
</html>