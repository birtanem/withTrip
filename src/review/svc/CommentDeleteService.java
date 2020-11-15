package review.svc;

import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;

import review.dao.CommentDAO;

import static common.db.JdbcUtil.*;

public class CommentDeleteService {

	public boolean DeleteArticle(int rc_num) {

		boolean deleteArticle = false;
		
		System.out.println("CommentDeleteService - DeleteArticle");
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		int deleteCount = commentDAO.deleteArticle(rc_num);
		
		if (deleteCount > 0) {
			commit(con);
			deleteArticle = true;
		}else {
			rollback(con);
		}
		close(con);
		
		return deleteArticle;
	}

}
