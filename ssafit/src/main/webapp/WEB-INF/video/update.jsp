<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>영상 수정</title>
  <style>
    body { font-family: system-ui, -apple-system, sans-serif; margin: 24px; }
    .wrap { max-width: 720px; margin: 0 auto; }
    label { display:block; margin: 12px 0 6px; font-weight: 600; }
    input[type=text], select {
      width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 8px;
    }
    .actions { margin-top: 16px; display:flex; gap:8px; }
    button, .linkbtn {
      padding:10px 14px; border-radius:10px; border:0; cursor:pointer;
      background:#111; color:#fff; text-decoration:none;
    }
    .secondary { background:#e5e7eb; color:#111; }
  </style>
</head>
<body>
<div class="wrap">
  <h1>영상 수정</h1>

  <form action="<%=ctx%>/video?action=update" method="post">
    <input type="hidden" name="id" value="<c:out value='${video.id}'/>" />

    <label for="title">제목</label>
    <input type="text" id="title" name="title" value="<c:out value='${video.title}'/>" required />

    <label for="channelName">채널명</label>
    <input type="text" id="channelName" name="channelName" value="<c:out value='${video.channelName}'/>" required />

    <label for="part">운동 부위</label>
    <select id="part" name="part" required>
      <option value="전신" <c:if test="${video.part eq '전신'}">selected</c:if>>전신</option>
      <option value="상체" <c:if test="${video.part eq '상체'}">selected</c:if>>상체</option>
      <option value="하체" <c:if test="${video.part eq '하체'}">selected</c:if>>하체</option>
      <option value="복부" <c:if test="${video.part eq '복부'}">selected</c:if>>복부</option>
      <option value="코어" <c:if test="${video.part eq '코어'}">selected</c:if>>코어</option>
      <option value="스트레칭" <c:if test="${video.part eq '스트레칭'}">selected</c:if>>스트레칭</option>
    </select>

    <label for="url">유튜브 URL</label>
    <input type="text" id="url" name="url" value="<c:out value='${video.url}'/>" required />

    <div class="actions">
      <button type="submit">수정 저장</button>
      <a class="linkbtn secondary" href="<%=ctx%>/video?action=get&id=<c:out value='${video.id}'/>">취소</a>
    </div>
  </form>
</div>
</body>
</html>
