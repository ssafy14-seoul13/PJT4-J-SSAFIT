package com.ssafy.ssafit.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssafy.ssafit.model.Video;

public class VideoRepositoryImpl implements VideoRepository {
	
	// TODO videos.json 추가하고 경로 설정할것.
	private static final String FILE_PATH = "data/dev/videos.json";
	private final Gson gson = new Gson();
	
	private List<Video> loadVideos() {
        try {
            String json = Files.readString(Path.of(FILE_PATH));
            return gson.fromJson(json, new TypeToken<List<Video>>(){}.getType());
        } catch (IOException e) {
        	System.out.println("exception : loadVideos 실패!");
            return new ArrayList<>();
        }
    }
	
	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Video video) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Video video) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
