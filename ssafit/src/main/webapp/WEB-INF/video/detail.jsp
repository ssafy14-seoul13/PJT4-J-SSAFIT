<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>영상 상세</title>
  <style>
    body { font-family: system-ui, -apple-system, sans-serif; margin: 24px; }
    .wrap { max-width: 960px; margin: 0 auto; }
    .meta { margin: 12px 0; color: #555; }
    .actions a, .actions form button { margin-right: 8px; }
    iframe { width: 100%; height: 480px; border: 0; margin: 12px 0; }
    table.info td { padding: 6px 8px; vertical-align: top; }
  </style>
</head>
<body>
<div class="wrap">
  <h1><c:out value="${video.title}"/></h1>
  <div class="meta">
    채널: <c:out value="${video.channelName}"/> · 부위: <c:out value="${video.part}"/> · 길이: <c:out value="${video.length}"/>초 · 조회수: <c:out value="${video.viewCount}"/>
    <br/>
    등록자: <c:out value="${video.author}"/> · 생성: <c:out value="${video.createdAt}"/> · 수정: <c:out value="${video.updatedAt}"/>
  </div>

  <iframe src="<c:out value='${video.url}'/>" allowfullscreen></iframe>

  <table class="info">
    <tr><td>ID</td><td><code><c:out value="${video.id}"/></code></td></tr>
    <tr><td>URL</td><td><a href="<c:out value='${video.url}'/>" target="_blank" rel="noreferrer noopener"><c:out value="${video.url}"/></a></td></tr>
  </table>

  <div class="actions" style="margin-top:12px;">
    <a href="<%=ctx%>/video?action=list">목록</a>
    <!-- 수정/삭제는 컨트롤러 권한 검사 통과 시에만 성공 -->
    <a href="<%=ctx%>/video?action=updateForm&id=<c:out value='${video.id}'/>">수정</a>

    <form action="<%=ctx%>/video?action=delete" method="post" style="display:inline;" onsubmit="return confirm('정말 삭제할까요?');">
      <input type="hidden" name="id" value="<c:out value='${video.id}'/>"/>
      <button type="submit">삭제</button>
    </form>
  </div>
</div>
</body>
</html>
