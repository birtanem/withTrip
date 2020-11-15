package event.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Timestamp;

import event.dao.EventDAO;
import member.vo.MemberBean;


public class EventPageService {
	
	public MemberBean getArticle(String memeber_id) {

		System.out.println("EventPageService - getArticle()");
		MemberBean article = null;
		
		// DB 작업을 위한 준비 => Connection 객체, DAO 객체, DAO 객체의 메서드 호출
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		EventDAO eventDAO = EventDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		eventDAO.setConnection(con);
		
	
		article = eventDAO.selectArticle(memeber_id);
			
		if(article != null) {
			commit(con);
		}else {
			rollback(con);
		}
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		return article;
	}
	
	public Timestamp getDate() {
		
		System.out.println("EventPageService - getDate()");
		Timestamp date = null;
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		EventDAO eventDAO = EventDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		eventDAO.setConnection(con);
			
		date = eventDAO.selectDate();

		if(date != null) {
			commit(con);
		}else {
			rollback(con);
		}

		close(con);
		
		return date;
		
	}
	
	public int getPoint(String member_id) {
		
		System.out.println("EventPageService - getPoint()");
		int point = 0;
		
		// DB 작업을 위한 준비 => Connection 객체, DAO 객체, DAO 객체의 메서드 호출
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		EventDAO eventDAO = EventDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		eventDAO.setConnection(con);
		
	
		point = eventDAO.selectPoint(member_id);
			
		if(point >= 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		return point;
	}
}
