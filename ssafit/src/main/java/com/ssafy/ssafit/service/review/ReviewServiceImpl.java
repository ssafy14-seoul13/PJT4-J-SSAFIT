package com.ssafy.ssafit.service.review;

import java.util.List;

import com.ssafy.ssafit.model.Review;
import com.ssafy.ssafit.repository.ReviewRepository;

public class ReviewServiceImpl implements ReviewService{

	private final ReviewRepository reviewRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@Override
	// 1. 리뷰 등록
	public Review addReview(String videoId, String userId, String content) {
		return reviewRepository.addReview(userId, videoId, content);
	}

	@Override
	// 2. 리뷰 조회 (영상별)
	public List<Review> searchByVideoId(String videoId) {
		return reviewRepository.searchByVideoId(videoId);
	}

	@Override
	// 3. 리뷰 조회 (사용자별)
	public List<Review> searchByUserId(String userId) {
		return reviewRepository.searchByUserId(userId);
	}

	@Override
	// 4. 개별 리뷰 조회
	public Review getReview(String id) {
		return reviewRepository.getReview(id);
	}

	@Override
	// 5. 리뷰 수정
	public boolean updateReview(String id, String userId, String content) {
		return reviewRepository.updateReview(id, userId, content);
	}

	@Override
	// 6. 리뷰 삭제
	public boolean deleteReview(String id, String userId) {
		return reviewRepository.deleteReview(id, userId);
	}

}
