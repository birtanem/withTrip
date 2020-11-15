package event.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.Date;

import com.sun.org.apache.regexp.internal.recompile;

import event.dao.EventDAO;


public class EventStartService {
	
	public boolean setEventSatrtDate(String date) {

		System.out.println("EventStartService");
		
		boolean isSetDate = false;
		// DB 작업을 위한 준비 => Connection 객체, DAO 객체, DAO 객체의 메서드 호출
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		EventDAO eventDAO = EventDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		eventDAO.setConnection(con);
		
		int setDateCount = eventDAO.setStartDate(date);
		
		if(setDateCount > 0) {
			commit(con);
			isSetDate = true;
		}else {
			rollback(con);
		}
		
		
		// 공통작업-5. Connection 객체 반환
		close(con);
		
		return isSetDate;
	}
}
