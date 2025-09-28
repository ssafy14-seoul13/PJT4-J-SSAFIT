<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>SSAFIT 홈</title>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <style>
    :root { --bg:#fafafa; --fg:#111; --muted:#666; --line:#eaeaea; --btn:#111; --btnfg:#fff; }
    * { box-sizing: border-box; }
    body { margin:0; font-family: system-ui, -apple-system, Segoe UI, Roboto, sans-serif; background:var(--bg); color:var(--fg); }
    header { display:flex; align-items:center; justify-content:space-between; padding:16px 20px; border-bottom:1px solid var(--line); background:#fff; position:sticky; top:0; }
    .brand { font-weight:800; letter-spacing:.2px; }
    nav a { margin-left:12px; text-decoration:none; color:var(--fg); }
    main { max-width:960px; margin:24px auto; padding:0 20px; }
    .grid { display:grid; grid-template-columns: repeat(auto-fit, minmax(240px,1fr)); gap:16px; margin-top:16px; }
    .card { background:#fff; border:1px solid var(--line); border-radius:12px; padding:16px; }
    .card h3 { margin:0 0 8px; font-size:1.1rem; }
    .card p { margin:0 0 12px; color:var(--muted); }
    .actions a, .actions button { display:inline-block; margin-right:8px; margin-top:4px; padding:10px 14px; border-radius:10px; text-decoration:none; border:0; cursor:pointer; }
    .btn { background:var(--btn); color:var(--btnfg); }
    .btn.secondary { background:#e5e7eb; color:var(--fg); }
    form.search { display:flex; gap:8px; margin-top:8px; }
    form.search input[type=text]{ flex:1; padding:10px 12px; border:1px solid var(--line); border-radius:10px; }
    form.search button{ padding:10px 14px; border-radius:10px; border:0; background:var(--btn); color:#fff; cursor:pointer; }
    footer { text-align:center; color:var(--muted); padding:24px; }
  </style>
</head>
<body>

<header>
  <div class="brand">SSAFIT</div>
  <nav>
    <a href="<%= request.getContextPath() %>/main/user?action=loginForm">로그인</a>
    <a href="<%= request.getContextPath() %>/main/user?action=registForm">회원가입</a>
    <a href="<%= request.getContextPath() %>/main/user?action=mypage">마이페이지</a>
  </nav>
</header>

<main>
  <section class="card">
    <h3>빠른 검색</h3>
    <p>제목·채널명·운동 부위로 영상을 찾아보세요.</p>
    <form class="search" action="<%= request.getContextPath() %>/video" method="get">
      <input type="hidden" name="action" value="search"/>
      <input type="text" name="keyword" placeholder="예) 전신, 상체, ThankyouBUBU" />
      <button type="submit">검색</button>
    </form>
  </section>

  <div class="grid">
    <section class="card">
      <h3>영상 목록</h3>
      <p>전체 영상을 최신 순으로 확인합니다.</p>
      <div class="actions">
        <a class="btn" href="<%= request.getContextPath() %>/video?action=list">목록 보기</a>
      </div>
    </section>

    <section class="card">
      <h3>영상 등록</h3>
      <p>유튜브 URL과 정보를 입력해 신규 영상을 추가합니다.</p>
      <div class="actions">
        <a class="btn" href="<%= request.getContextPath() %>/video?action=createForm">등록하기</a>
      </div>
    </section>

    <section class="card">
      <h3>빠른 이동</h3>
      <p>자주 쓰는 메뉴로 바로 이동합니다.</p>
      <div class="actions">
        <a class="btn secondary" href="<%= request.getContextPath() %>/main/user?action=loginForm">로그인</a>
        <a class="btn secondary" href="<%= request.getContextPath() %>/main/user?action=registForm">회원가입</a>
        <a class="btn secondary" href="<%= request.getContextPath() %>/main/user?action=mypage">마이페이지</a>
      </div>
    </section>
  </div>
</main>

<footer>
  © SSAFIT
</footer>

</body>
</html>
