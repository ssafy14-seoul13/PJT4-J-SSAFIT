<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>403 Forbidden</title></head>
<body>
  <h1>403 Forbidden</h1>
  <p>권한이 없습니다.</p>
  <p>${message}</p>
  <p><a href="${pageContext.request.contextPath}/video?action=list">목록으로</a></p>
</body></html>
