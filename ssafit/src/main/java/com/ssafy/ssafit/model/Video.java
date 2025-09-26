package com.ssafy.ssafit.model;


import java.time.LocalDateTime;

import com.ssafy.ssafit.util.IdGenerator;

public class Video {
	private String id;				// 고유 id
	private String title;			// 유튜브 제목
	private String channelName;		// 유튜브 영상 채널 명
	private String author;			// 비디오 등록한 user id
	private String part;			// 운동 부위
	private String url;				// 유튜브 영상 링크
	private int length;				// 영상 길이 (초)
	
	private LocalDateTime createAt;	// 생성 시간	
	private LocalDateTime updatedAt;// 수정 시간
	
	private int viewCount;			// 조회수
	
	public Video(String title, String channelName, String author, String part, String url, int length) {
		this.id = IdGenerator.newId();
		this.title = title;
		this.channelName = channelName;
		this.author = author;
		this.part = part;
		this.url = url;
		this.length = length;
		
		this.createAt = this.updatedAt =  LocalDateTime.now();
		
		this.viewCount = 0;
	}

	//////////////////////////////////////////////////
	// getter setters
	
	public String getId() {
		return id;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

//	public void setCreateAt(LocalDateTime createAt) {
//		this.createAt = createAt;
//	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	///////////////////////////////////////////////////////////////
	// methods
	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", channelName=" + channelName + ", author=" + author
				+ ", part=" + part + ", url=" + url + ", length=" + length + ", createAt=" + createAt + ", updatedAt="
				+ updatedAt + "]";
	}
}
