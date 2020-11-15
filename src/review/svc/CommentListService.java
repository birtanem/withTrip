package review.svc;

import review.dao.CommentDAO;
import review.vo.CommentBean;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

public class CommentListService {

	public int getArticle(int r_num) {

		System.out.println("CommentListService - getArticle");
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		listCount = commentDAO.getCountArticle(r_num);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<CommentBean> getArticleList(int r_num) {

		ArrayList<CommentBean> ArticleList = null;
		
		System.out.println("CommentListService - getArticleList");
		
		Connection con = getConnection();
		
		CommentDAO commentDAO = CommentDAO.getInstance();
		
		commentDAO.setConnection(con);
		
		ArticleList = commentDAO.getArticleList(r_num);
		
		close(con);
		
		return ArticleList;
	}

}
