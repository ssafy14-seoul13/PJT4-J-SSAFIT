package com.ssafy.ssafit.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafit.model.Video;
import com.ssafy.ssafit.util.DBUtil;

/**
 * VideoRepository 구현체.
 * 
 * - 데이터는 JSON 파일(ssafit/data/videos.json)에 저장되고, 실행 중에는 Map<String, Video>로
 * 메모리에 로드하여 관리한다. - ID 기반 CRUD가 빠르도록 Map 구조 사용. - 애플리케이션 종료 시점에 변경 내용을 json 파일로
 * 저장한다.
 */
public class VideoRepositoryImpl implements VideoRepository {
	
	private static DBUtil util = DBUtil.getInstance();
	
	private static VideoRepository repo = new VideoRepositoryImpl();
	
	private VideoRepositoryImpl() {
		
	}
	
	public static VideoRepository getInstance() {
		return repo;
	}


	/**
	 * 모든 영상 목록 조회
	 */
	@Override
	public List<Video> findAll() {
		List<Video> list = new ArrayList<>();
		String sql = "SELECT * FROM video";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Video video = new Video();
				video.setId(rs.getString("id"));
				video.setTitle(rs.getString("title"));
				video.setChannelName(rs.getString("channel_name"));
				video.setAuthor(rs.getString("author"));
				video.setUrl(rs.getString("url"));
				video.setLength(rs.getInt("length"));
				video.setCreatedAt(rs.getString("created_at"));
				video.setUpdatedAt(rs.getString("updated_at"));
				video.setViewCount(rs.getInt("view_cnt"));
				list.add(video);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn);
		}
		return list;
	}

	/**
	 * ID로 영상 단건 조회
	 * 
	 * @return 성공시 Video 객체 반환, 해당 id 를 가진 영상이 없을 시 null 반환
	 */
	@Override
	public Video findById(String id) {

//		return videos.get(id);
		return null;
	}

	/**
	 * 신규 영상 추가
	 * 
	 * @return true: 정상 추가됨, false: 이미 같은 id 존재
	 */
	@Override
	public boolean add(Video video) {

//		return videos.putIfAbsent(video.getId(), video) == null;
		return false;
	}

	/**
	 * 기존 영상 수정
	 * 
	 * @return true: 성공적으로 수정됨, false: 해당 id 없음
	 */
	@Override
	public boolean update(Video video) {

//		return videos.replace(video.getId(), video) != null;
		return false;
	}

	/**
	 * ID로 영상 삭제
	 * 
	 * @return true: 삭제 성공, false: 해당 id 없음
	 */
	@Override
	public boolean deleteById(String id) {

//		return videos.remove(id) != null;
		return false;
	}

}
