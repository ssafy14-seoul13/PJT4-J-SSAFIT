package com.ssafy.ssafit.service.video;

import java.util.List;
import com.ssafy.ssafit.model.Video;

/**
 * Video 도메인에 대한 서비스 계층 인터페이스.
 */
public interface VideoService {

	/**
	 * 전체 영상 목록 조회.
	 *
	 * @return 모든 영상 리스트 List<Video>
	 */
	List<Video> getAllVideos();

	/**
	 * ID로 특정 영상 조회. (조회수 영향 x)
	 *
	 * @param id 영상 고유 식별자
	 * @return 해당 영상 객체 (없으면 null)
	 */
	Video getVideoById(String id);

	/**
	 * ID로 특정 영상 조회. (조회수 +1)
	 * 
	 * @param id
	 * @return
	 */
	Video viewVideoById(String id);

	/**
	 * 새로운 영상 등록.
	 *
	 * @return String id: 등록 성공, null: 실패. 이미 같은 ID 존재 등
	 */
	String createVideo(String title, String channelName, String author, String part, String url);

	/**
	 * 기존 영상 수정.
	 *
	 * @return true: 수정 성공, false: 해당 ID 없음
	 */
	boolean updateVideo(String id, String title, String channelName, String part, String url);

	/**
	 * 특정 영상 삭제.
	 *
	 * @param id 삭제할 영상 ID
	 * @return true: 삭제 성공, false: 해당 ID 없음
	 */
	boolean deleteVideo(String id);

	/**
	 * 키워드 기반 검색.
	 *
	 * @param keyword 제목, 설명, 채널명 등에 포함된 검색어
	 * @return 검색 결과 리스트. 없으면 null.
	 */
	List<Video> searchVideos(String keyword);
}
