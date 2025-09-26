package com.ssafy.ssafit.repository;

import java.util.List;

import com.ssafy.ssafit.model.Video;

public interface VideoRepository {
	
	// 모든 비디오 조회
	List<Video> findAll();
	
	// id로 비디오 조회
	Video findById(String id);
	
	// 영상 추가
	boolean add(Video video);
	
	// 영상 수정
	boolean update(Video video);
	
	// 영상 삭제
	boolean deleteById(String id);
}
