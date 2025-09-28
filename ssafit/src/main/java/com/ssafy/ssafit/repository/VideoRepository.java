package com.ssafy.ssafit.repository;

import java.util.List;
import java.util.Map;

import com.ssafy.ssafit.model.Video;

/**
 * Video 도메인에 대한 Repository 인터페이스.
 */
public interface VideoRepository {
    
    /**
     * 모든 비디오 목록 조회.
     * 
     * @return 비디오 전체 리스트 List<Video>
     */
    List<Video> findAll();
    
    /**
     * ID로 단일 비디오 조회.
     *
     * @param id 비디오 고유 식별자
     * @return 해당 비디오 객체 (없으면 null 반환)
     */
    Video findById(String id);
    
    /**
     * 새 영상 등록.
     *
     * @param video 추가할 비디오
     * @return true: 정상적으로 추가됨, false: 이미 같은 id가 존재해 추가 실패
     */
    boolean add(Video video);
    
    /**
     * 기존 영상 수정.
     *
     * @param video 수정할 비디오 (id 기준으로 교체)
     * @return true: 수정 성공, false: 해당 id 없음
     */
    boolean update(Video video);
    
    /**
     * ID로 영상 삭제.
     *
     * @param id 삭제할 비디오의 고유 식별자
     * @return true: 삭제 성공, false: 해당 id 없음
     */
    boolean deleteById(String id);
}