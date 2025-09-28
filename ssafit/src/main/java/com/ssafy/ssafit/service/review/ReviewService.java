package com.ssafy.ssafit.service.review;

import java.util.List;

import com.ssafy.ssafit.model.Review;

public interface ReviewService {
	// 1. 리뷰 등록
	abstract Review addReview(String userId, String name, String videoId, String content);
	
	// 2. 리뷰 조회 (영상별)
	abstract List<Review> searchByVideoId(String videoId);
	
	// 3. 리뷰 조회 (사용자별)
	abstract List<Review> searchByUserId(String userId);
	
	// 4. 개별 리뷰 조회
	abstract Review getReview(String id);
	
	// 5. 리뷰 수정
	abstract boolean updateReview(String id, String userId, String content);
	
	// 6. 리뷰 삭제
	abstract boolean deleteReview(String id, String userId);
}
