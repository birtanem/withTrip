package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import place.dao.PlaceDAO;
import place.vo.PlaceCommentBean;

public class PCWiteProService {

	public boolean registComment(PlaceCommentBean pcb) {
		System.out.println("PCWriteProService - registArticle()");
		
		boolean isWriteSucces = false; // 글 등록 성공 여부를 리턴할 변수
		
		// DB 작업을 위한 준비 => Connection 객체, DAO 객체, DAO 객체의 메서드 호출
		// 공통작업-1. DB 작업에 필요한 Connection 객체 가져오기
//		Connection con = JdbcUtil.getConnection();
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		// 공통작업-2. DB 작업을 위한 BoardDAO 객체 생성 => 싱글톤 패턴으로 생성된 객체 가져오기
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		// 공통작업-3. BoardDAO 객체에 Connection 객체 전달
		placeDAO.setConnection(con);
		
		// 4. BoardDAO 객체의 insertArticle() 메서드를 호출하여 글 등록 처리
		// => 파라미터 : BoardBean 객체, 리턴타입 : int(insertCount)
		int insertCount = placeDAO.insertComment(pcb);
		
		// 5. 리턴받은 작업 결과 판별
		// => insertCount 가 0보다 크면 commit() 실행, isWriteSuccess 를 true 로 변경
		// => 아니면, rollback() 실행
		if(insertCount > 0) {
			commit(con);
			isWriteSucces = true;
		} else {
			rollback(con);
		}
		
		// 공통작업-6. Connection 객체 반환
		close(con);
		
		// 7. 작업 결과 리턴
		return isWriteSucces;
	}

}
