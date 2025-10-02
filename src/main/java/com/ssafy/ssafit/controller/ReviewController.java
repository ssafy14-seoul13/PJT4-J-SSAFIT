package com.ssafy.ssafit.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.ssafit.model.Review;
import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.repository.ReviewRepositoryImpl;
import com.ssafy.ssafit.service.review.ReviewService;
import com.ssafy.ssafit.service.review.ReviewServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReviewController {
private final ReviewService reviewService = new ReviewServiceImpl(ReviewRepositoryImpl.getInstance());
	
	public ReviewController() {
		super();
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loggedInUser");
		
		switch (action) {
			// 1. 리뷰 등록
			case "regist" :
				System.out.println("웅");
				// 1-a. 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
	            if (user == null) {
	            	System.out.println("이잉");
	                response.sendRedirect(request.getContextPath() + "/main/user?action=loginForm");
	                return;
	            }

				String videoId = request.getParameter("videoId");
				String content = request.getParameter("content");
				// 1-b. 로그인되어 있으면, 작성된거 보여주기
				if (reviewService.addReview(user.getUserId(), user.getName(),videoId, content) != null ) {
					System.out.println("기래기래");
				}
				response.sendRedirect(request.getContextPath() + "/video?action=view&id=" + videoId);
				break;
            // 3. 리뷰 삭제
			case "delete" :
				String id = request.getParameter("id");
                reviewService.deleteReview(id, user.getUserId());
                response.sendRedirect(request.getContextPath() + "/video?action=view&id=" + request.getParameter("videoId"));
                break;
             // 4. 리뷰 수정 폼 열기 (GET 요청 처리)
            case "editForm":
                // 4-a. 로그인 여부 체크
                if (user == null) {
                    response.sendRedirect(request.getContextPath() + "/main/user?action=loginForm");
                    return;
                }
                // 4-b. 수정할 리뷰 정보 가져오기
                String reviewId = request.getParameter("id");
                Review reviewToEdit = reviewService.getReview(reviewId);
                
                // 4-c. 권한 체크 (로그인 사용자와 리뷰 작성자가 같은지)
                if (!user.getUserId().equals(reviewToEdit.getUserId())) {
                    // 권한이 없으면 에러 메시지를 포함하여 상세 페이지로 리다이렉트
                    request.setAttribute("error", "수정 권한이 없습니다.");
                    response.sendRedirect(request.getContextPath() + "/video?action=view&id=" + request.getParameter("videoId"));
                    return;
                }
                
                // 4-d. 리뷰 정보를 request에 담고 수정 폼 페이지로 포워드
                request.setAttribute("reviewToEdit", reviewToEdit);
                request.getRequestDispatcher("WEB-INF/review/editForm.jsp").forward(request, response);
                break;
             // 5. 리뷰 수정 (POST 요청 처리)
            case "update":
                // 5-a. 로그인 여부 체크
                if (user == null) {
                    response.sendRedirect(request.getContextPath() + "/main/user?action=loginForm");
                    return;
                }
                // 5-b. 수정 로직 실행
                String updateId = request.getParameter("id");
                String newContent = request.getParameter("content");
                reviewService.updateReview(updateId, user.getUserId(), newContent); // Authorization check inside service
                response.sendRedirect(request.getContextPath() + "/video?action=view&id=" + request.getParameter("videoId"));
                break;
		}
	}
}
