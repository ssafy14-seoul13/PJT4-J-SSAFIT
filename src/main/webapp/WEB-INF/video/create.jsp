<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String ctx = request.getContextPath();
    String userUID = (String) session.getAttribute("userUID");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>영상 등록</title>
  <style>
    body { font-family: system-ui, -apple-system, sans-serif; margin: 24px; }
    .wrap { max-width: 720px; margin: 0 auto; }
    label { display:block; margin: 12px 0 6px; font-weight: 600; }
    input[type=text], select, textarea {
      width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 8px;
    }
    .actions { margin-top: 16px; display:flex; gap:8px; align-items:center; }
    .hint { color:#666; font-size: 0.9rem; }
    .warn { background:#fff3cd; padding:10px 12px; border-radius:8px; margin-bottom:16px; }
    button, .linkbtn {
      padding:10px 14px; border-radius:10px; border:0; cursor:pointer;
      background:#111; color:#fff; text-decoration:none; display:inline-block;
    }
    .secondary { background:#e5e7eb; color:#111; }
    .disabled { opacity:.6; pointer-events:none; }
  </style>
  <script>
    function validateForm(e){
      const title = document.querySelector('[name=title]').value.trim();
      const channel = document.querySelector('[name=channelName]').value.trim();
      const url = document.querySelector('[name=url]').value.trim();
      if(!title){ alert('제목을 입력하세요.'); e.preventDefault(); return false; }
      if(!channel){ alert('채널명을 입력하세요.'); e.preventDefault(); return false; }
      // 유튜브 링크 간단 검증 (watch?v= 또는 embed/)
      const yt = /^(https?:\/\/)?(www\.)?youtube\.com\/(watch\?v=|embed\/).+/i.test(url);
      if(!yt){ if(!confirm('유튜브 주소가 아닌 것 같습니다. 그래도 저장할까요?')) { e.preventDefault(); return false; } }
      return true;
    }
    document.addEventListener('DOMContentLoaded', () => {
      const form = document.getElementById('createForm');
      if(form){ form.addEventListener('submit', validateForm); }
    });
  </script>
</head>
<body>
<div class="wrap">
  <h1>영상 등록</h1>

  <c:if test="${empty sessionScope.loggedInUser}">
    <div class="warn">로그인 후 등록 가능합니다.</div>
  </c:if>

  <form id="createForm" action="<%=ctx%>/video?action=create" method="post" class="<c:out value='${empty sessionScope.loggedInUser ? "disabled" : ""}'/>">
    <!-- 컨트롤러에서 author는 세션 userUID로 읽으므로 폼에는 넣지 않음 -->

    <label for="title">제목</label>
    <input type="text" id="title" name="title" placeholder="영상 제목을 입력하세요" maxlength="200" required />

    <label for="channelName">채널명</label>
    <input type="text" id="channelName" name="channelName" placeholder="예) ThankyouBUBU, 김계란" maxlength="100" required />

    <label for="part">운동 부위</label>
    <select id="part" name="part" required>
      <option value="">선택하세요</option>
      <option value="전신">전신</option>
      <option value="상체">상체</option>
      <option value="하체">하체</option>
      <option value="복부">복부</option>
      <option value="코어">코어</option>
      <option value="스트레칭">스트레칭</option>
    </select>

    <label for="url">유튜브 URL</label>
    <input type="text" id="url" name="url" placeholder="https://www.youtube.com/embed/..." required />
    <div class="hint">가능하면 <code>https://www.youtube.com/embed/VIDEO_ID</code> 형식으로 입력하세요.</div>

    <div class="actions">
      <button type="submit" class="<c:out value='${empty sessionScope.loggedInUser ? "disabled" : ""}'/>">등록</button>
      <a class="linkbtn secondary" href="<%=ctx%>/video?action=list">목록</a>
    </div>
  </form>
</div>
</body>
</html>
