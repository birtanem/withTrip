package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import place.dao.PlaceDAO;


public class PlaceDeleteProService {

	public boolean removeArticle(int pl_num) {
boolean isDeleteSuccess = false;
		
		Connection con = getConnection();
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		placeDAO.setConnection(con);
		
		// BoardDAO 객체의 deleteArticle() 메서드를 호출하여 삭제 작업 수행
		// => 파라미터 : 글번호      리턴타입 : int(deleteCount)
		int deleteCount = placeDAO.deleteArticle(pl_num);
		
		// deleteCount > 0 일 경우 commit, isDeleteSuccess 를 true 로 변경
		// 아니면, rollback 수행
		if(deleteCount > 0) {
			commit(con);
			isDeleteSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isDeleteSuccess;
	}

}
