package review.svc;

import java.sql.*;

import review.dao.ReviewDAO;

import static common.db.JdbcUtil.*;

public class ReviewDeleteService {

	public boolean DeleteArticle(int r_num, String r_id) {
		System.out.println("ReviewDeleteService");
		boolean article = false;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		int UserCheck = reviewDAO.UserCheck(r_num, r_id);
		
		if (UserCheck > 0) {
			
			int deleteCount = reviewDAO.Delete(r_num);
			
			if (deleteCount > 0) {
				commit(con);
				article = true;
			}else {
				rollback(con);
			}
		}else {
			rollback(con);
		}
		
		close(con);
		
		return article;
	}



}
