package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import place.dao.PlaceDAO;
import place.vo.PlaceBean;

public class PlaceUpdateProService {

	public boolean registArticle(PlaceBean pb) {
		System.out.println("PlaceWriteProService - registArticle()");
		
		boolean isWriteSucces = false; // 글 등록 성공 여부를 리턴할 변수
		
		
		Connection con = getConnection(); // static import 로 지정된 메서드 호출
		
		
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		
		placeDAO.setConnection(con);
		
		int insertCount = placeDAO.updateArticle(pb);
		
		System.out.println("잘 돌아옴??");
		
		if(insertCount > 0) {
			commit(con);
			isWriteSucces = true;
		} else {
			rollback(con);
		}
		

		close(con);
		

		return isWriteSucces;
	}

}
