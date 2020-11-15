package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;


import place.dao.PlaceDAO;
import place.vo.PlaceBean;




public class PlaceListService {

	public int getListCount() {
//		System.out.println("BoardListService - getListCount()");
		int listCount = 0;
		
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		placeDAO.setConnection(con);
		
		// 4. BoardDAO 클래스의 selectListCount() 메서드를 호출하여 전체 게시물 수 가져오기
		listCount = placeDAO.selectListCount();
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		// 6. 작업 결과 리턴
		return listCount;
	}

	public ArrayList<PlaceBean> getArticleList(int page, int limit) {
//		System.out.println("BoardListService - getArticleList()");
		
		ArrayList<PlaceBean> articleList = null;
		
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		placeDAO.setConnection(con);
		
		// 4. BoardDAO 클래스의 selectArticleList() 메서드를 호출하여 
		//    => 페이지번호(page)와 글 갯수(limit) 를 사용하여 
		//       지정된 번호부터 지정된 게시물 갯수만큼 게시물 가져오기
		//    파라미터 : page, limit, 리턴타입 : ArrayList<BoardBean>
		articleList = placeDAO.selectArticleList(page, limit);
		
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		
		return articleList;
	}
	
	public ArrayList<PlaceBean> getList() {
		
		ArrayList<PlaceBean> articleList = null;
		
		Connection con = getConnection();
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		placeDAO.setConnection(con);
		
		articleList = placeDAO.selectList();
		
		close(con);
		
		
		return articleList;
	}

}
