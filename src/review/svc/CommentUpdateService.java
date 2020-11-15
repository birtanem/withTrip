package review.svc;

import java.sql.Connection;

import review.dao.CommentDAO;
import review.vo.CommentBean;

import static common.db.JdbcUtil.*;

public class CommentUpdateService {

	public CommentBean getArticle(int rc_num) {

		System.out.println("CommentUpdateService - getArticle");
		
		CommentBean article = null;
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		article = commentDAO.getArticle(rc_num);
		
		close(con);
		
		return article;
	}

	public boolean UpdateArticle(CommentBean article) {

		boolean isArticle = false;
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		int updateCount = commentDAO.updateArticle(article);
		
		if (updateCount > 0) {
			commit(con);
			isArticle = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isArticle;
	}

}
