package review.svc;

import review.dao.CommentDAO;
import review.vo.CommentBean;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

public class CommentReplyService {

	public boolean insertArticle(CommentBean article) {

		boolean isReply = false;
		
		System.out.println("CommentReplyService - insertArticle");
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		int replyCount = commentDAO.ReplyArticl(article);
		
		if (replyCount > 0) {
			commit(con);
			isReply = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isReply;
	}

	public CommentBean getReplyArticle(int rc_num) {

		CommentBean article = null;
		
		System.out.println("CommentReplyService - getArticle");
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		article = commentDAO.getArticle(rc_num);
		
		close(con);
		
		return article;
	}

}
