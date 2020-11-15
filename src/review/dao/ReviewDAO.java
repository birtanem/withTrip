package review.dao;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;

import common.vo.*;
import review.vo.*;

public class ReviewDAO {

	private ReviewDAO() {}
	
	private static ReviewDAO instance;
	
	public static ReviewDAO getInstance() {
		
		if (instance == null) {
			instance = new ReviewDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertArticle(ReviewBean reviewBean) {

		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 0;
		
		try {
			
			String sql = "select max(r_num) from review";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				num = rs.getInt(1)+1;
			}
			
			sql = "insert into review values(?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, reviewBean.getR_id());
			pstmt.setString(3, reviewBean.getR_subject());
			pstmt.setString(4, reviewBean.getR_content());
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setString(7, reviewBean.getR_image());
			pstmt.setInt(8, reviewBean.getR_code());
			
			insertCount = pstmt.executeUpdate();
			
			if (insertCount > 0) {
				sql = "update member set point = point+100 where id = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, reviewBean.getR_id());
				
				pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - insertArticle() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}

	public int selectListCount() {

		int selectCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select count(r_num) from review";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				selectCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectListCount() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return selectCount;
	}
	//---------------------------------------------------------------------------------------
	// 게시글 5개 가져오기
	
	public ArrayList<ReviewBean> getArticleList() {

		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from review order by r_likecount desc limit 5";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				ReviewBean reviewBean = new ReviewBean();
				
				reviewBean.setR_num(rs.getInt("r_num"));
				reviewBean.setR_id(rs.getString("member_id"));
				reviewBean.setR_subject(rs.getString("r_subject"));
				reviewBean.setR_content(rs.getString("r_content"));
				reviewBean.setR_readcount(rs.getInt("r_readcount"));
				reviewBean.setR_likecount(rs.getInt("r_likecount"));
				reviewBean.setR_date(rs.getDate("r_date"));
				reviewBean.setR_image(rs.getString("r_image"));
				reviewBean.setR_code(rs.getInt("region_region_code"));
				
				articleList.add(reviewBean);
				
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - getArticleList() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	//---------------------------------------------------------------------------------------
	// 게시글 가져오기
	public ArrayList<ReviewBean> selectArticleList(int page, int limit) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page-1)*limit;
		
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		
		String r_cnt = "(select count(rc_num) from review_comment where review_r_num = r_num)";
		String r_name = "(select region_name from region where region_code = region_region_code)";
		
		try {
			String sql = "select *, "+r_cnt+" as r_cnt,"+r_name+" as r_name "
					+ "from review order by r_num desc limit ?,?";
						// 댓글 개수와 해당 지역 이름값 받아오는 서브 쿼리문
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				ReviewBean reviewBean = new ReviewBean();
				
				reviewBean.setR_num(rs.getInt("r_num"));
				reviewBean.setR_id(rs.getString("member_id"));
				reviewBean.setR_subject(rs.getString("r_subject"));
				reviewBean.setR_content(rs.getString("r_content"));
				reviewBean.setR_readcount(rs.getInt("r_readcount"));
				reviewBean.setR_likecount(rs.getInt("r_likecount"));
				reviewBean.setR_date(rs.getDate("r_date"));
				reviewBean.setR_image(rs.getString("r_image"));
				reviewBean.setR_code(rs.getInt("region_region_code"));
				reviewBean.setR_name(rs.getString("r_name"));
				reviewBean.setR_cnt(rs.getInt("r_cnt"));
				
				articleList.add(reviewBean);
				
			}
		
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticleList() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	//============================================================================================================
	// 지역 코드로 게시글 가져오기
	public ArrayList<ReviewBean> selectArticleList(int page, int limit, int r_code) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page-1)*limit;
		
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		
		String r_cnt = "(select count(rc_num) from review_comment where review_r_num = r_num)";
		String r_name = "(select region_name from region where region_code = region_region_code)";
		
		try {
			String sql = "select *, "+r_cnt+" as r_cnt,"+r_name+" as r_name "
					+ "from review where region_region_code = ? order by r_num desc limit ?,?";
						// 댓글 개수와 해당 지역 이름값 받아오는 서브 쿼리문
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, r_code);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				ReviewBean reviewBean = new ReviewBean();
				
				reviewBean.setR_num(rs.getInt("r_num"));
				reviewBean.setR_id(rs.getString("member_id"));
				reviewBean.setR_subject(rs.getString("r_subject"));
				reviewBean.setR_content(rs.getString("r_content"));
				reviewBean.setR_readcount(rs.getInt("r_readcount"));
				reviewBean.setR_likecount(rs.getInt("r_likecount"));
				reviewBean.setR_date(rs.getDate("r_date"));
				reviewBean.setR_image(rs.getString("r_image"));
				reviewBean.setR_code(rs.getInt("region_region_code"));
				reviewBean.setR_name(rs.getString("r_name"));
				reviewBean.setR_cnt(rs.getInt("r_cnt"));
				
				articleList.add(reviewBean);
				
			}
		
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticleList() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	//============================================================================================================
	// Search로 게시글 가져오기
	public ArrayList<ReviewBean> selectArticleList(int page, int limit, String r_search) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int startRow = (page-1)*limit;
		
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		
		String r_cnt = "(select count(rc_num) from review_comment where review_r_num = r_num)";
		String r_name = "(select region_name from region where region_code = region_region_code)";
		
		try {
			String sql = "select *, "+r_cnt+" as r_cnt,"+r_name+" as r_name "
					+ "from review where r_subject like ? or r_content like ? or member_id like ? "
					+ "or "+r_name+" like ?"
					+ "order by r_num desc limit ?,?";
					// 댓글 개수와 해당 지역 이름값 받아오는 서브 쿼리문
					// 리뷰 게시판 검색
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "%"+r_search+"%");
			pstmt.setString(2, "%"+r_search+"%");
			pstmt.setString(3, "%"+r_search+"%");
			pstmt.setString(4, "%"+r_search+"%"); // r_name 추가시 Unknown column 'r_name' in 'where clause'
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, limit);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				ReviewBean reviewBean = new ReviewBean();
				
				reviewBean.setR_num(rs.getInt("r_num"));
				reviewBean.setR_id(rs.getString("member_id"));
				reviewBean.setR_subject(rs.getString("r_subject"));
				reviewBean.setR_content(rs.getString("r_content"));
				reviewBean.setR_readcount(rs.getInt("r_readcount"));
				reviewBean.setR_likecount(rs.getInt("r_likecount"));
				reviewBean.setR_date(rs.getDate("r_date"));
				reviewBean.setR_image(rs.getString("r_image"));
				reviewBean.setR_code(rs.getInt("region_region_code"));
				reviewBean.setR_name(rs.getString("r_name"));
				reviewBean.setR_cnt(rs.getInt("r_cnt"));
				
				articleList.add(reviewBean);
				
			}
		
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticleList() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return articleList;
	}
	
	public ReviewBean selectArticle(int r_num) {

		ReviewBean article = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String r_name = "(select region_name from region where region_code = region_region_code)";
		
		try {
			
			String sql = "select *,"+r_name+" as r_name "
							+ "from review where r_num = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, r_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				article = new ReviewBean();
				
				article.setR_num(rs.getInt("r_num"));
				article.setR_id(rs.getString("member_id"));
				article.setR_subject(rs.getString("r_subject"));
				article.setR_content(rs.getString("r_content"));
				article.setR_readcount(rs.getInt("r_readcount"));
				article.setR_likecount(rs.getInt("r_likecount"));
				article.setR_date(rs.getDate("r_date"));
				article.setR_image(rs.getString("r_image"));
				article.setR_code(rs.getInt("region_region_code"));
				article.setR_name(rs.getString("r_name"));
				
			}
		
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticle() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return article;
	}

	public int updateCount(int r_num) {

		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update review set r_readcount = r_readcount+1 where r_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - updateCount() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int update(ReviewBean reviewBean) {

		int updateCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
		
			String sql = "update review set r_subject = ? , r_content = ?, region_region_code = ? where r_num = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, reviewBean.getR_subject());
			pstmt.setString(2, reviewBean.getR_content());
			pstmt.setInt(3, reviewBean.getR_code());
			pstmt.setInt(4, reviewBean.getR_num());
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - update() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}

	public int Delete(int r_num) {

		int deleteCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "delete from review where r_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			
			deleteCount = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - Delete() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}

	public void UpdateLikeCount(int r_num) {

		PreparedStatement pstmt = null;
		
		try {
			String sql = "update review set r_likecount = r_likecount+1 where r_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - UpdateLikeCount() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		
	}

	public int UserCheck(int r_num, String r_id) {

		int userCheck = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select member_id from review where r_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (r_id.equals(rs.getString(1))) {
					userCheck = 1;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - UserCheck() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return userCheck;
	}

	public int likeCheck(int r_num, String id) {
		System.out.println("BoardDAO - likeCheck");
		int article = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from like_check where review_r_num = ? and review_member_member_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (id.equals(rs.getString(2))) {
					
					article =1;
				}else {
					article = 0;
				}
			}else {
				article = -1;
			}
		} catch (SQLException e) {
			System.out.println("BoardDAO - likeCheck() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return article;
	}

	public int insertLike(int r_num, String id) {
		
		int insertCount = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "set foreign_key_checks=0";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.executeUpdate();
			
			sql = "insert into like_check values(?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, r_num);
			pstmt.setString(2, id);
			
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - insertLike() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		return insertCount;
	}

}
