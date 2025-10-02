package com.ssafy.ssafit.service.video;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafit.model.Video;
import com.ssafy.ssafit.repository.VideoRepository;
import com.ssafy.ssafit.repository.VideoRepositoryImpl;
import com.ssafy.ssafit.util.IdGenerator;

public class VideoServiceImpl implements VideoService {

	private final VideoRepository videoRepository;

	public VideoServiceImpl() {
		this.videoRepository = VideoRepositoryImpl.getInstance();
	}

	@Override
	public List<Video> getAllVideos() {

		return videoRepository.findAll();
	}

	@Override
	public Video getVideoById(String id) {

		return videoRepository.findById(id);
	}
	
	@Override
	public Video viewVideoById(String id) {
		
		// @고민할점 동시성 고민하기 동시에 view 하면, 과연 제대로 + 1한거로 set 될까?
		Video video = videoRepository.findById(id);
		video.setViewCount(video.getViewCount() + 1);
		
		return video;
	}

	@Override
	public String createVideo(String title, String channelName, String author, String part, String url) {
		
		if (title.isBlank() || url.isBlank()) {
			System.out.println("Error! : at createVideo(). title or url is blank");
		}
		
		int viewCount = 0;
		int length = 0;
		
		//TODO youtube api로 url로 영상길이 받아오기
		// length = youTubeApi()
		
		String id = IdGenerator.newId();
		String createdAt = LocalDateTime.now().toString();
		Video video = new Video(id, title, channelName, author, part, url, length, createdAt);
		
		boolean succes = videoRepository.add(video);
		
		return succes ? video.getId() : null;
	}

	@Override
	public boolean updateVideo(String id, String title, String channelName, String part, String url) {
		Video origin = videoRepository.findById(id);
		
		if (origin == null) return false;
		
		//TODO dirty check
		origin.setTitle(title);
		origin.setChannelName(channelName);
		origin.setPart(part);
		origin.setUrl(url);
		origin.setUpdatedAt(LocalDateTime.now().toString());
		
		return videoRepository.update(origin);
	}

	@Override
	public boolean deleteVideo(String id) {

		return videoRepository.deleteById(id);
	}

	/**
	 * 키워드 검색
	 * 
	 * - 전처리: trim + toLowerCase
	 * - 토큰화: 공백(\\s) + 유니코드 문장부호(\\p{P}) 기준 분리
	 * - 매칭: 제목/채널/부위 텍스트에 대해 모든 토큰이 포함되어야 통과
	 * 
	 *
	 * @param keyword 사용자 입력 검색어(예: "스쿼트 하체")
	 * @return 모든 토큰을 포함하는 Video 목록 (없으면 빈 리스트)
	 *
	 * TODO: 검색 알고리즘 개선할것. 현재 전체 순회라 데이터가 많다면 오래걸림
	 */
	@Override
	public List<Video> searchVideos(String keyword) {
		List<Video> result = new ArrayList<>();

		if (keyword == null)
			return result;

		// 전처리
		String q = keyword.trim().toLowerCase();
		if (q.isEmpty())
			return result;

		// 토큰화
		// 공백(" ") 이나 구두점(".,!?;:...- 등") 으로 구분
		String[] tokens = q.split("[\\s\\p{P}]+");
		if (tokens.length == 0)
			return result;

		// 전체 목록 순회
		List<Video> all = videoRepository.findAll();

		for (Video v : all) {
			if (v == null)
				continue;

			StringBuilder sb = new StringBuilder();

			if (v.getTitle() != null)
				sb.append(v.getTitle().toLowerCase()).append(" ");
			
			if (v.getChannelName() != null)
				sb.append(v.getChannelName().toLowerCase()).append(" ");
			
			if (v.getPart() != null)
				sb.append(v.getPart().toLowerCase()).append(" ");

			String tokenTarget = sb.toString();

			boolean match = true;
			for (String token : tokens) {
				if (token.isBlank() || token.isEmpty())
					continue;

				match = tokenTarget.contains(token);

				if (!match)
					break;
			}

			if (match)
				result.add(v);
		}

		return result;
	}

}
