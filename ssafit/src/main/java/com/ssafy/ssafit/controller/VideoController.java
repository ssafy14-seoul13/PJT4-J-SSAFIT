package com.ssafy.ssafit.controller;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.ssafy.ssafit.model.Review;
import com.ssafy.ssafit.model.User;
import com.ssafy.ssafit.model.Video;
import com.ssafy.ssafit.repository.ReviewRepositoryImpl;
import com.ssafy.ssafit.service.review.ReviewServiceImpl;
import com.ssafy.ssafit.service.video.VideoService;
import com.ssafy.ssafit.service.review.ReviewService;
import com.ssafy.ssafit.service.video.VideoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/video")
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final VideoService service = new VideoServiceImpl();
	ReviewService reviewService = new ReviewServiceImpl(ReviewRepositoryImpl.getInstance());
	private final Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "list":
			handleList(request, response);
			break;

		case "get":
			handleGet(request, response);
			break;

		case "view":
			handleView(request, response);
			break;

		case "search":
			handleSearch(request, response);
			break;
			
		case "createForm":
			request.getRequestDispatcher("/WEB-INF/video/create.jsp").forward(request, response);
			break;
			
		case "updateForm":
			handleUpdateForm(request, response);
		    break;

		default:
			System.out.println("Error! : wrong request from client. at doGet() action : " + action);
			// TODO 잘못된 action 시 에러페이지 경로로 foward
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "create":
			handleCreate(request, response);
			break;

		case "update":
			handleUpdate(request, response);
			break;

		case "delete":
			handleDelete(request, response);
			break;

		default:
			System.out.println("Error! : wrong request from client. at doPost() action : " + action);
			// TODO 잘못된 action 시에러페이지 경로로 foward
		}
	}


	/**
	 * List<Video> videos
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Video> videos = service.getAllVideos();

		request.setAttribute("videos", videos);

		request.getRequestDispatcher("/WEB-INF/video/list.jsp").forward(request, response);
	}

	/**
	 * 조회수를 올리지 않고 상세페이지만 가져옴
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null || id.isBlank()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			request.setAttribute("message", "id가 비어 있습니다.");
			request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
		}

		Video video = service.getVideoById(id);

		// 해당 id 값의 video 없으면 404 페이지로 포워드
		if (video == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			request.setAttribute("message", "id가 비어 있습니다.");
			request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
			return;
		}

		request.setAttribute("video", video);
		request.getRequestDispatcher("/WEB-INF/video/detail.jsp").forward(request, response);

	}

	/**
	 * 
	 * 조회수를 올리고 상세 페이지로 이동. foward
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		if (id == null || id.isBlank()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			request.setAttribute("message", "id가 비어 있습니다.");
			request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
		}

		Video video = service.viewVideoById(id);

		// 해당 id 값의 video 없으면 404 페이지로 포워드
		if (video == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			request.setAttribute("message", "id가 비어 있습니다.");
			request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
			return;
		}
		
		System.out.println(id);
		List<Review> reviews = reviewService.searchByVideoId(id);
	    request.setAttribute("reviews", reviews);

		request.setAttribute("video", video);
		request.getRequestDispatcher("/WEB-INF/video/detail.jsp").forward(request, response);

	}

	private void handleSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");

		List<Video> videos = service.searchVideos(keyword);

		request.setAttribute("keyword", keyword); // 검색창에 값 유지용
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/WEB-INF/video/search.jsp").forward(request, response);
	}
	

	private void handleUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 권한 있는 사용자여야 페이지 받도록
		String id = request.getParameter("id");
	    if (id == null || id.isBlank()) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        request.setAttribute("message", "id 파라미터가 없습니다.");
	        request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
	        return;
	    }
	    Video v = service.getVideoById(id);
	    if (v == null) {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        request.setAttribute("message", "해당 영상이 존재하지 않습니다: id=" + id);
	        request.getRequestDispatcher("/WEB-INF/error/404.jsp").forward(request, response);
	        return;
	    }
	    request.setAttribute("video", v);
	    request.getRequestDispatcher("/WEB-INF/video/update.jsp").forward(request, response);
	    return;
	}


	private void handleCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loggedInUser") == null) {
			System.out.println("Issue! handleCreate(): 미 로그인 사용자 create 요청");
			
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.setAttribute("message", "권한 없음");
			request.getRequestDispatcher("/WEB-INF/error/403.jsp").forward(request, response);
			return;
		}
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		String title = request.getParameter("title");
		String channelName = request.getParameter("channelName");
		String author = loggedInUser.getId();
		String part = request.getParameter("part");
		String url = request.getParameter("url");

		String id = service.createVideo(title, channelName, author, part, url);
		response.sendRedirect(request.getContextPath() + "/video?action=view&id=" + id);
	}

	private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		System.out.println("id" + id);
		String title = request.getParameter("title");
		String channelName = request.getParameter("channelName");
//		String author = request.getParameter("userUID");
		String part = request.getParameter("part");
		String url = request.getParameter("url");
		
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String userUID = loggedInUser.getId();
		
		Video origin = service.getVideoById(id);
		
		//TODO 권한 검사는 필터로 빼기
		boolean isOwner = (userUID != null) && userUID.equals(origin.getAuthor());
		boolean isAdmin = (userUID != null) && userUID.equals("admin");
		if (!(isOwner || isAdmin)) {
			System.out.println("Issue! handleUpdate(): 권한 없는 사용자가 update 요청");
			
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.setAttribute("message", "권한 없음");
			request.getRequestDispatcher("/WEB-INF/error/403.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/video?action=get&id=" + id);

	}
	

	private void handleDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String userUID = loggedInUser.getId();
		
		Video origin = service.getVideoById(id);
		
		//TODO 권한 검사는 필터로 빼기
		boolean isOwner = (userUID != null) && userUID.equals(origin.getAuthor());
		boolean isAdmin = (userUID != null) && userUID.equals("admin");
		
		if (!(isOwner || isAdmin)) {
			System.out.println("Issue! handleDelete(): 권한 없는 사용자가 delete 요청");
			
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.setAttribute("message", "권한 없음");
			request.getRequestDispatcher("/WEB-INF/error/403.jsp").forward(request, response);
			return;
		}
		
	    boolean ok = service.deleteVideo(id);
	    if (ok) {
	        // 성공하면 목록으로
	        response.sendRedirect(request.getContextPath() + "/video?action=list");
	        return;
	    } else {
	        // 저장소 상태와 충돌 등
	        response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
			request.setAttribute("message", "삭제 실패");
			request.getRequestDispatcher("/WEB-INF/error/409.jsp").forward(request, response);
	        return;
	    }
		
		
	}

}
