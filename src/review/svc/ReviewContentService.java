package review.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.ArrayList;

import review.dao.ReviewDAO;
import review.vo.*;

public class ReviewContentService {

	public ReviewBean  getArticle(int r_num) {
		
		ReviewBean article = null;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		article = reviewDAO.selectArticle(r_num);
		
		if (article != null) {
			int updateCount = reviewDAO.updateCount(r_num);
			if (updateCount > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		}
		close(con);
		
		return article;
		
	}

	public boolean LikeArticle(int r_num, String id) {

		boolean likeArticle = false;
		System.out.println("ReviewContentService - LikeArticle");
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		int likeCheck = reviewDAO.likeCheck(r_num, id);

		if (likeCheck == 0 || likeCheck == -1) {
			
		int insertCount = reviewDAO.insertLike(r_num, id);
		
			if (insertCount > 0) {
					
				reviewDAO.UpdateLikeCount(r_num);
				likeArticle = true;
				commit(con);
			}
			
		}else {
			rollback(con);
		}
			close(con);
			
		return likeArticle;
	}

	public ArrayList<ReviewBean> getArrayList() {

		ArrayList<ReviewBean> arrayList = null;
		
		System.out.println("ReviewContentService - getArrayList");
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		arrayList = reviewDAO.getArticleList();
		
		close(con);
		
		return arrayList;
	}
	
}
