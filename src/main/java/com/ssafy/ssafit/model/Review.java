package com.ssafy.ssafit.model;

import java.util.Date;

import com.ssafy.ssafit.util.IdGenerator;

public class Review {
	// 변수
	private String id;
	private String userId;
	private String name;
	private String videoId;
	private String content;
	private Date date;
	
	// 기본 생성자
	private Review() {
	}
	
	// 생성자
	public Review(String userId, String name, String videoId, String content) {
		this.id = IdGenerator.newId(); // 등록 시, 새로운 리뷰 id 부여
		this.userId = userId;
		this.name = name;
		this.videoId = videoId;
		this.content = content;
		this.date = new Date();
	}
	
	// getter&setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	// toString
	@Override
	public String toString() {
		return "Review[id : " + id + ", userId : " + userId + ", videoId : " + videoId + ", content : " + content + ", date : " + date + "]";
	}
}
