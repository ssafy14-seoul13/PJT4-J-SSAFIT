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
private final ReviewService reviewService = new ReviewServiceImpl(new ReviewRepositoryImpl());
	
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
				String videoId = request.getParameter("videoId");
				String content = request.getParameter("content");
				reviewService.addReview(videoId, user.getId(), content);
				break;
			// 2. 리뷰 조회 (영상별)
			case "list" :
				videoId = request.getParameter("videoId");
				List<Review> reviews = reviewService.searchByVideoId(videoId);
				request.setAttribute("reviews", reviews);
                request.setAttribute("videoId", videoId);
                request.getRequestDispatcher("/WEB-INF/review/list.jsp").forward(request, response);
                break;
            // 3. 리뷰 삭제
			case "delete" :
				String id = request.getParameter("id");
                reviewService.deleteReview(id, user.getId());
                response.sendRedirect("review?action=list&videoId=" + request.getParameter("videoId"));
                break;
            // 4. 리뷰 수정
			case "update":
                String updateId = request.getParameter("id");
                String newContent = request.getParameter("content");
                reviewService.updateReview(updateId, user.getId(), newContent);
                response.sendRedirect("review?action=list&videoId=" + request.getParameter("videoId"));
                break;
		}
	}
}
