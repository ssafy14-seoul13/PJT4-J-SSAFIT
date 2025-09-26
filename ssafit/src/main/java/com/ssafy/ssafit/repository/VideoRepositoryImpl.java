package com.ssafy.ssafit.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssafy.ssafit.model.Video;

/**
 * VideoRepository 구현체.
 * 
 * - 데이터는 JSON 파일(data/videos.json)에 저장되고, 실행 중에는 Map<String, Video>로 메모리에 로드하여
 * 관리한다. - ID 기반 CRUD가 빠르도록 Map 구조 사용. - 애플리케이션 종료 시점에 변경 내용을 json 파일로 저장한다.
 */
public class VideoRepositoryImpl implements VideoRepository {

	// 메모리 내 영상 저장소
	Map<String, Video> videos = new HashMap<>();

	// 개발시 "data/dev" 경로 사용
//	private static final String FILE_PATH = "data/videos.json";
	private static final String FILE_PATH = "data/dev/videos.json";
	private final Gson gson = new Gson();

	public VideoRepositoryImpl() {
		List<Video> list = loadVideos();
		for (Video video : list) {
			videos.put(video.getId(), video);
		}

		Runtime.getRuntime().addShutdownHook(new Thread(this::saveVideos));
	}
	
    /**
     * JSON 파일에서 영상 목록을 로드한다.
     * 파일이 없거나 읽기 실패 시 빈 리스트 반환.
     */
	private List<Video> loadVideos() {
		try {
			String json = Files.readString(Path.of(FILE_PATH));
			return gson.fromJson(json, new TypeToken<List<Video>>() {
			}.getType());
		} catch (IOException e) {
			System.out.println("exception : loadVideos 실패!");
			return new ArrayList<>();
		}
	}
	
    /**
     * 현재 메모리에 있는 영상 데이터를 JSON 파일로 저장한다.
     */
	private void saveVideos() {
		try {
			String json = gson.toJson(new ArrayList<>(videos.values()));
			Files.writeString(Path.of(FILE_PATH), json);
		} catch (IOException e) {
			System.out.println("exception : saveVideos 실패!");
		}
	}
	

    /**
     * 모든 영상 목록 조회
     */
	@Override
	public List<Video> findAll() {
		
		return new ArrayList<>(videos.values());
	}

    /**
     * ID로 영상 단건 조회
     * @return 성공시 Video 객체 반환, 해당 id 를 가진 영상이 없을 시 null 반환
     */
	@Override
	public Video findById(String id) {

		return videos.get(id);
	}
	
    /**
     * 신규 영상 추가
     * @return true: 정상 추가됨, false: 이미 같은 id 존재
     */
	@Override
	public boolean add(Video video) {

		return videos.putIfAbsent(video.getId(), video) == null;
	}

	/**
	 * 기존 영상 수정
	 * 
	 * @return true: 성공적으로 수정됨, false: 해당 id 없음
	 */
	@Override
	public boolean update(Video video) {

		return videos.replace(video.getId(), video) != null;
	}

	/**
	 * ID로 영상 삭제
	 * 
	 * @return true: 삭제 성공, false: 해당 id 없음
	 */
	@Override
	public boolean deleteById(String id) {

		return videos.remove(id) != null;
	}

}
