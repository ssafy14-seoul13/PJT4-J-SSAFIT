<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
String ctx = request.getContextPath();
%>
<%@ page import="com.ssafy.ssafit.model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영상 상세</title>
<style>
body {
	font-family: system-ui, -apple-system, sans-serif;
	margin: 24px;
}

.wrap {
	max-width: 960px;
	margin: 0 auto;
}

.meta {
	margin: 12px 0;
	color: #555;
}

.actions a, .actions form button {
	margin-right: 8px;
}

iframe {
	width: 100%;
	height: 480px;
	border: 0;
	margin: 12px 0;
}

table.info td {
	padding: 6px 8px;
	vertical-align: top;
}
</style>
</head>
<body>
	<div class="wrap">
		<h1>
			<c:out value="${video.title}" />
		</h1>
		<div class="meta">
			채널:
			<c:out value="${video.channelName}" />
			· 부위:
			<c:out value="${video.part}" />
			· 길이:
			<c:out value="${video.length}" />
			초 · 조회수:
			<c:out value="${video.viewCount}" />
			<br /> 등록자:
			<c:out value="${video.author}" />
			· 생성:
			<c:out value="${video.createdAt}" />
			· 수정:
			<c:out value="${video.updatedAt}" />
		</div>

		<iframe src="<c:out value='${video.url}'/>" allowfullscreen></iframe>

		<table class="info">
			<tr>
				<td>ID</td>
				<td><code>
						<c:out value="${video.id}" />
					</code></td>
			</tr>
			<tr>
				<td>URL</td>
				<td><a href="<c:out value='${video.url}'/>" target="_blank"
					rel="noreferrer noopener"><c:out value="${video.url}" /></a></td>
			</tr>
		</table>

		<div class="actions" style="margin-top: 12px;">
			<a href="<%=ctx%>/video?action=list">목록</a>
			<!-- 수정/삭제는 컨트롤러 권한 검사 통과 시에만 성공 -->
			<a
				href="<%=ctx%>/video?action=updateForm&id=<c:out value='${video.id}'/>">수정</a>

			<form action="<%=ctx%>/video?action=delete" method="post"
				style="display: inline;" onsubmit="return confirm('정말 삭제할까요?');">
				<input type="hidden" name="id" value="<c:out value='${video.id}'/>" />
				<button type="submit">삭제</button>
			</form>
		</div>
	</div>
	<div class="wrap">
		<br>
		<hr />
		<h2>리뷰</h2>

		<%
		User user = (User) session.getAttribute("loggedInUser");
		String userId = (user != null) ? user.getUserId() : null;
		request.setAttribute("userId", userId);
		%>

		<!-- 리뷰 작성 폼 -->
		<form method="post"
			action="${pageContext.request.contextPath}/main/review">
			<input type="hidden" name="action" value="regist" /> <input
				type="hidden" name="videoId" value="${video.id}" />
			<textarea name="content" rows="3" cols="80" placeholder="리뷰를 작성하세요"></textarea>
			<br />
			<button type="submit">등록</button>
		</form>

		<!-- 리뷰 목록 -->
		<c:if test="${empty reviews}">
			<p>아직 리뷰가 없습니다. 첫 리뷰를 작성해보세요!</p>
		</c:if>
		<c:if test="${not empty reviews}">
			<c:forEach var="r" items="${reviews}">
				<div
					style="border: 1px solid #ccc; padding: 10px; margin-top: 10px;">
					<strong>${r.name}</strong> - ${r.date}<br />

					<!-- 읽기용 영역 -->
					<div id="reviewContent_${r.id}">
						${r.content}<br />
					</div>

					<!-- 수정용 폼 -->
					<form id="editForm_${r.id}" method="post"
						action="${pageContext.request.contextPath}/main/review"
						style="display: none;">
						<input type="hidden" name="action" value="update" /> <input
							type="hidden" name="id" value="${r.id}" /> <input type="hidden"
							name="videoId" value="${video.id}" />
						<textarea name="content" rows="3" cols="80">${r.content}</textarea>
						<br />
						<button type="submit">수정 완료</button>
						<button type="button" onclick="toggleEditMode('${r.id}');">취소</button>
					</form>

					<!-- 수정/삭제 버튼: 로그인한 유저 == 작성자일 때만 -->
					<c:if test="${userId == r.userId}">
						<div id="actionButtons_${r.id}" style="margin-top: 5px;">
							<button onclick="toggleEditMode('${r.id}');">수정</button>

							<form method="post"
								action="${pageContext.request.contextPath}/main/review"
								style="display: inline;">
								<input type="hidden" name="action" value="delete" /> <input
									type="hidden" name="id" value="${r.id}" /> <input
									type="hidden" name="videoId" value="${video.id}" />
								<button type="submit">삭제</button>
							</form>
						</div>
					</c:if>

				</div>
			</c:forEach>
		</c:if>
	</div>

	<script>
		function toggleEditMode(reviewId) {
			const contentDiv = document.getElementById('reviewContent_'
					+ reviewId);
			const editForm = document.getElementById('editForm_' + reviewId);
			const actionButtons = document.getElementById('actionButtons_'
					+ reviewId);

			if (editForm.style.display === 'none') {
				// 수정 폼 열기
				contentDiv.style.display = 'none';
				editForm.style.display = 'block';
				if (actionButtons)
					actionButtons.style.display = 'none';
			} else {
				// 수정 폼 닫기
				contentDiv.style.display = 'block';
				editForm.style.display = 'none';
				if (actionButtons)
					actionButtons.style.display = 'block';
			}
		}
	</script>
</body>
</html>
