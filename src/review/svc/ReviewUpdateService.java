package review.svc;

import java.sql.Connection;

import review.dao.ReviewDAO;
import review.vo.ReviewBean;

import static common.db.JdbcUtil.*;

public class ReviewUpdateService {

	public ReviewBean SelectUpdateArticle(int r_num) {

		ReviewBean article = null;
		
		System.out.println("ReviewUpdateService");
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		article = reviewDAO.selectArticle(r_num);
		
		close(con);
		
		return article;
	}

	public boolean UpdateArticle(ReviewBean reviewBean) {

		boolean isupdate = false;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);

		int updateCount = reviewDAO.update(reviewBean);
		
		if (updateCount > 0) {
			commit(con);
			isupdate = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isupdate;
	}

}
