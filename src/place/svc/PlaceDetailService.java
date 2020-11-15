package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import place.dao.PlaceDAO;
import place.vo.PlaceBean;
import place.vo.PlaceCommentBean;


public class PlaceDetailService {

	public PlaceBean getArticle(int pl_num) {
//		System.out.println("BoardDetailService - getArticle()");
//		System.out.println("board_num = " + board_num);
		
		PlaceBean article = null;
		
		// DB 작업을 위한 준비 => Connection 객체, DAO 객체, DAO 객체의 메서드 호출
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		PlaceDAO boardDAO = PlaceDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		boardDAO.setConnection(con);
		
		// 4. 게시물 상세 내용 조회 및 조회수 증가
		article = boardDAO.selectArticle(pl_num);
		
		// article 객체가 null 이 아닐 때 조회수 증가
		if(article != null) {
			int updateCount = boardDAO.updateReadcount(pl_num);
			// 조회수 증가 성공 시 commit, 실패 시 rollback 수행
			if(updateCount > 0) {
				commit(con);
			} else {
				rollback(con);
			}
		}
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		return article;
	}

	public int getCommentListCount(int pl_num) {
		int listCount = 0;
		
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection();
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		placeDAO.setConnection(con);
		
		// 4. BoardDAO 클래스의 selectListCount() 메서드를 호출하여 전체 게시물 수 가져오기
		listCount = placeDAO.selectCommentCount(pl_num);
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		// 6. 작업 결과 리턴
		return listCount;
	}

	public ArrayList<PlaceCommentBean> getCommentList(int pl_num, int page, int limit) {
		
		ArrayList<PlaceCommentBean> commentList = null;
		
		Connection con = getConnection();
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		placeDAO.setConnection(con);
		commentList=placeDAO.selectCommentList(pl_num, page, limit);
		close(con);
		
		return commentList;
			
	}

}
