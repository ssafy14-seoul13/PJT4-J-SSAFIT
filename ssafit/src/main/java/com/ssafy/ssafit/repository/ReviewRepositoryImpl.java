package com.ssafy.ssafit.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.Review;

public class ReviewRepositoryImpl implements ReviewRepository{

	// 리뷰 저장 메모리
	private Map<String, Review> reviewMap = new HashMap<>();

	@Override
	// 1. 리뷰 등록
	public Review addReview(String userId, String videoId, String content) {
		Review review = new Review(userId, videoId, content);
		reviewMap.put(review.getId(), review);
		return review;
	}

	@Override
	// 2. 리뷰 조회 (영상별)
	public List<Review> searchByVideoId(String videoId) {
		// 2-a. 영상 id와 일치하는 리뷰만 따로 리스트에 저장
		List<Review> result = new ArrayList<>();
	    for (Review r : reviewMap.values()) {
	        if (r.getVideoId().equals(videoId)) {
	            result.add(r);
	        }
	    }
	    // 2-b. 최신순 정렬
	    Collections.sort(result, (r1, r2) -> r2.getDate().compareTo(r1.getDate()));
	    return result;
	}

	@Override
	// 3. 리뷰 조회 (사용자별)
	public List<Review> searchByUserId(String userId) {
		// 3-a. 사용자 id와 일치하는 리뷰만 따로 리스트에 저장
		List<Review> result = new ArrayList<>();
	    for (Review r : reviewMap.values()) {
	        if (r.getUserId().equals(userId)) {
	            result.add(r);
	        }
	    }
	    // 3-b. 최신순 정렬
	    Collections.sort(result, (r1, r2) -> r2.getDate().compareTo(r1.getDate()));
	    return result;
	}

	@Override
	// 4. 개별 리뷰 조회
	public Review getReview(String id) {
		return reviewMap.get(id);
	}

	@Override
	// 5. 리뷰 수정
	public boolean updateReview(String id, String userId, String content) {
		Review review = reviewMap.get(id);
		if (review != null && review.getUserId().equals(userId)) {
			review.setContent(content);
			return true;
		}
		return false;
	}

	@Override
	// 6. 리뷰 삭제
	public boolean deleteReview(String id, String userId) {
		Review review = reviewMap.get(id);
		if (review != null && review.getUserId().equals(userId)) {
			reviewMap.remove(id);
			return true;
		}
		return false;
	}
	
	

}
