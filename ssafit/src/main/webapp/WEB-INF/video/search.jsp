<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>영상 검색</title>
  <style>
    body { font-family: system-ui, -apple-system, sans-serif; margin: 24px; }
    table { border-collapse: collapse; width: 100%; }
    th, td { border-bottom: 1px solid #eee; padding: 8px 10px; text-align: left; }
    .empty { color: #777; padding: 16px 0; }
    .keyword { color: #333; }
  </style>
</head>
<body>
  <h1>검색 결과</h1>

  <form action="<%=ctx%>/video" method="get" style="margin: 12px 0;">
    <input type="hidden" name="action" value="search"/>
    <input type="text" name="keyword" placeholder="제목/채널/부위 검색" value="<c:out value='${keyword}'/>"/>
    <button type="submit">검색</button>
    <a href="<%=ctx%>/video?action=list">목록</a>
  </form>

  <c:choose>
    <c:when test="${empty videos}">
      <div class="empty">"<span class="keyword"><c:out value="${keyword}"/></span>" 에 대한 결과가 없습니다.</div>
    </c:when>
    <c:otherwise>
      <table>
        <thead>
          <tr>
            <th>제목</th>
            <th>채널</th>
            <th>부위</th>
            <th>길이</th>
            <th>조회수</th>
            <th>생성</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="v" items="${videos}">
          <tr>
            <td>
              <a href="<%=ctx%>/video?action=get&id=<c:out value='${v.id}'/>">
                <c:out value="${v.title}"/>
              </a>
            </td>
            <td><c:out value="${v.channelName}"/></td>
            <td><c:out value="${v.part}"/></td>
            <td><c:out value="${v.length}"/>초</td>
            <td><c:out value="${v.viewCount}"/></td>
            <td><c:out value="${v.createdAt}"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</body>
</html>
