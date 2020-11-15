package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import review.vo.CommentBean;

import static common.db.JdbcUtil.*;

public class CommentDAO {

	private CommentDAO() {}
	
	private static CommentDAO instance;
	
	public static CommentDAO getInstance() {
		
		if (instance == null) {
			instance = new CommentDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int InsertArticle(CommentBean article) {

		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int rc_num = 0;
		
		try {
			
			String sql = "set foreign_key_checks=0";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			sql = "select max(rc_num) from review_comment";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rc_num = rs.getInt(1)+1;
			}
			
			sql = "insert into review_comment values(?,?,?,?,now(),?,?,?)";
			
			pstmt = con.prepareStatement(sql);
		
			pstmt.setInt(1, rc_num);
			pstmt.setInt(2, article.getR_num());
			pstmt.setString(3, article.getRc_id());
			pstmt.setString(4, article.getRc_content());
			pstmt.setInt(5, rc_num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			
			insertCount = pstmt.executeUpdate();
			
			if (insertCount > 0) {
				
				sql = "update member set point = point+50 where id = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, article.getRc_id());
				
				pstmt.executeUpdate();
			}
			
			sql = "set foreign_key_checks=1";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("CommentDAO - InsertArticle() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}

	public int getCountArticle(int r_num) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int articleCount = 0;
		
		try {
			String sql = "select count(rc_num) from review_comment where review_r_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				articleCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("CommentDAO - getCountArticle() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleCount;
	}

	public ArrayList<CommentBean> getArticleList(int r_num) {

		ArrayList<CommentBean> articleList = new ArrayList<CommentBean>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from review_comment where review_r_num = ?"
					+ " order by rc_ref desc, rct_lev asc";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, r_num);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				CommentBean article = new CommentBean();
				
				article.setRc_num(rs.getInt("rc_num"));
				article.setR_num(rs.getInt("review_r_num"));
				article.setRc_id(rs.getString("member_id"));
				article.setRc_content(rs.getString("rc_content"));
				article.setRc_date(rs.getDate("rc_date"));
				article.setRc_ref(rs.getInt("rc_ref"));
				article.setRc_seq(rs.getInt("rc_seq"));
				article.setRc_lev(rs.getInt("rct_lev"));
				
				articleList.add(article);
				
			}
			
		} catch (SQLException e) {
			System.out.println("CommentDAO - getArticleList() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	public int ReplyArticl(CommentBean article) {

		int replyCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int rc_num = 0;
		
		try {
			
			String sql = "select max(rc_num) from review_comment";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rc_num = rs.getInt(1)+1;
			}
			
			int re_ref = article.getRc_ref();
			int re_seq = article.getRc_seq();
			int re_lev = article.getRc_lev();
			
			sql = "update review_comment set rct_lev = rct_lev+1 where rc_ref = ? and rct_lev > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_lev);
			
			int updateCount = pstmt.executeUpdate();
			
			if (updateCount > 0) {
				commit(con);
			}
			
			re_seq++;
			re_lev++;
			
			sql = "insert into review_comment values(?,?,?,?,now(),?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, rc_num);
			pstmt.setInt(2, article.getR_num());
			pstmt.setString(3, article.getRc_id());
			pstmt.setString(4, article.getRc_content());
			pstmt.setInt(5, re_ref);
			pstmt.setInt(6, re_seq);
			pstmt.setInt(7, re_lev);
			
			replyCount = pstmt.executeUpdate();
			
			if (replyCount > 0) {
				sql = "update member set point = point+50 where id = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, article.getRc_id());
				
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			System.out.println("CommentDAO - ReplyArticl() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return replyCount;
	}

	public CommentBean getArticle(int rc_num) {

		CommentBean article = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = "select * from review_comment where rc_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, rc_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				article = new CommentBean();
				
				article.setRc_num(rs.getInt("rc_num"));
				article.setR_num(rs.getInt("review_r_num"));
				article.setRc_id(rs.getString("member_id"));
				article.setRc_content(rs.getString("rc_content"));
				article.setRc_date(rs.getDate("rc_date"));
				article.setRc_ref(rs.getInt("rc_ref"));
				article.setRc_seq(rs.getInt("rc_seq"));
				article.setRc_lev(rs.getInt("rct_lev"));
				
			}
			
		} catch (SQLException e) {
			System.out.println("CommentDAO - getArticle() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}

	public int updateArticle(CommentBean article) {

		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update review_comment set rc_content = ? where rc_num = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, article.getRc_content());
			pstmt.setInt(2, article.getRc_num());
			
			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("CommentDAO - updateArticle() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int deleteArticle(int rc_num) {

		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from review_comment where rc_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, rc_num);
			
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("CommentDAO - deleteArticle() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return deleteCount;
	}
	
}
