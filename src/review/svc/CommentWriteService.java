package review.svc;

import java.sql.Connection;

import static common.db.JdbcUtil.*;

import review.dao.CommentDAO;
import review.vo.CommentBean;

public class CommentWriteService {

	public boolean isWriteComment(CommentBean article) {
		System.out.println("CommentWriteService");
		boolean isComment = false;
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		int insertCount = commentDAO.InsertArticle(article);
		
		if (insertCount > 0) {
			commit(con);
			isComment = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isComment;
	}

}
