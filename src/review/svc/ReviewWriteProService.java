package review.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;

import review.dao.ReviewDAO;
import review.vo.*;

public class ReviewWriteProService {

	public boolean registArticle(ReviewBean reviewBean) {
		
		System.out.println("ReviewWriteProService");
		
		boolean isWriteSucces = false;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		int insertCount = reviewDAO.insertArticle(reviewBean);
		
		if (insertCount > 0) {
			commit(con);
			isWriteSucces = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return isWriteSucces;
	}
	
}
