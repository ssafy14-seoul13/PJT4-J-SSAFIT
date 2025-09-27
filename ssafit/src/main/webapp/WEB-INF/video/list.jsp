<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%-- 컨텍스트 루트 --%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>영상 목록</title>
</head>
<body>

  <h1>영상 목록</h1>

  <div class="toolbar">
    <form method="get" action="${cpath}/video">
      <input type="hidden" name="action" value="search"/>
      <input type="text"   name="keyword" placeholder="검색어 (예: 스쿼트)" value="${keyword}"/>
      <button type="submit">검색</button>
    </form>

    <a href="${cpath}/video?action=create">새 영상 등록</a>
  </div>

  <c:choose>
    <c:when test="${empty videos}">
      <div class="empty">등록된 영상이 없습니다.</div>
    </c:when>
    <c:otherwise>
      <table>
        <thead>
          <tr>
            <th style="width:14%">ID</th>
            <th style="width:26%">제목</th>
            <th style="width:14%">채널</th>
            <th style="width:10%">부위</th>
            <th style="width:8%">길이</th>
            <th style="width:8%">조회수</th>
            <th style="width:12%">생성시각</th>
            <th style="width:12%">수정시각</th>
            <th style="width:8%">작업</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="v" items="${videos}">
            <tr>
              <td><c:out value="${v.id}"/></td>

              <td>
                <a href="${cpath}/video?action=get&id=${v.id}">
                  <c:out value="${v.title}"/>
                </a>
              </td>

              <td><c:out value="${v.channelName}"/></td>
              <td><c:out value="${v.part}"/></td>

              <td><c:out value="${v.length}"/>초</td>
              <td><c:out value="${v.viewCount}"/></td>

              <td><c:out value="${v.createdAt}"/></td>
              <td><c:out value="${v.updatedAt}"/></td>

              <td class="actions">
                <a href="${cpath}/video?action=editForm&id=${v.id}">수정</a>

                <form method="post" action="${cpath}/video" style="display:inline" onsubmit="return confirm('삭제할까요?');">
                  <input type="hidden" name="action" value="delete"/>
                  <input type="hidden" name="id"     value="${v.id}"/>
                  <button type="submit">삭제</button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>

</body>
</html>
