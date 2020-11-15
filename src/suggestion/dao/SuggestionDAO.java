package suggestion.dao;

import java.sql.*;
import java.util.*;

import suggestion.vo.*;

import static common.db.JdbcUtil.*;

public class SuggestionDAO {

	private SuggestionDAO() {}
	
	private static SuggestionDAO instance;
	
	public static SuggestionDAO getInstance() { //?????
		
		if (instance == null) {
			instance = new SuggestionDAO();
		}
		
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertSuggestion(SuggestionBean suggestionBean) {

		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 0;
		String check="미완료";
		
		try {
			
			String sql = "select max(sg_num) from suggestion";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1)+1;
			}
			
			sql = "insert into suggestion values(?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, suggestionBean.getId());
			pstmt.setString(3, suggestionBean.getContent());
			pstmt.setString(4, suggestionBean.getEmail());
			pstmt.setString(5, check);
			pstmt.setString(6, suggestionBean.getSubject());
			pstmt.setInt(7, 0);
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SuggestionDAO - insertSuggestion() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}

	public int selectListCount() {
		int listCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// board_num 컬럼의 전체 갯수를 조회하기(모든 컬럼을 뜻하는 * 기호 사용해도 됨)
		String sql = "SELECT COUNT(sg_num) FROM suggestion";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SuggestionDAO - selectListCount() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(con);
			close(pstmt);
		}
		
		
		return listCount;
	}
	
//	public ArrayList<SuggestionBean> selectArticleList(int page, int limit) {
	public ArrayList<SuggestionBean> selectArticleList(String id) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		int startRow = (page-1)*limit;
		
		ArrayList<SuggestionBean> articleList = new ArrayList<SuggestionBean>();
		
		try {
			String sql = "select * from suggestion where member_member_id=? order by sg_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// 조회된 레코드 만큼 반복
			while(rs.next()) {
				// 1개 레코드(게시물)를 저장할 SuggestionBean 객체 생성
				SuggestionBean article = new SuggestionBean();
				
				// SuggestionBean 객체에 조회된 레코드(게시물) 정보를 저장
				article.setNum(rs.getInt("sg_num"));
				article.setId(rs.getString("member_member_id"));
				article.setSubject(rs.getString("sg_subject"));
				article.setEmail(rs.getString("sg_email"));
				article.setDate(rs.getDate("sg_date"));
				article.setCheck(rs.getString("sg_check"));

				// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
				articleList.add(article);
				
			}
			
		} catch (SQLException e) {
			System.out.println("SuggestionDAO - selectArticleList() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}

	public SuggestionBean selectArticle(int su_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SuggestionBean article = null;
		
		try {
			String sql = "SELECT * FROM suggestion WHERE sg_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, su_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new SuggestionBean();
				article.setNum(rs.getInt("sg_num"));
				article.setId(rs.getString("member_member_id"));
				article.setEmail(rs.getString("sg_email"));
				article.setSubject(rs.getString("sg_subject"));
				article.setContent(rs.getString("sg_content"));
				article.setDate(rs.getDate("sg_date"));
				article.setCheck(rs.getString("sg_check"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return article;
	}
	
	public SuggestionBean selectArticleR(int su_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SuggestionBean article = null;
		
		try {
			String sql = "SELECT * FROM suggestion WHERE sg_num_ref=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, su_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new SuggestionBean();
				article.setContent_r(rs.getString("sg_content"));
				article.setDate_r(rs.getDate("sg_date"));
			}
			
			sql = "SELECT * FROM suggestion WHERE sg_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, su_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article.setNum(rs.getInt("sg_num"));
				article.setId(rs.getString("member_member_id"));
				article.setEmail(rs.getString("sg_email"));
				article.setSubject(rs.getString("sg_subject"));
				article.setContent(rs.getString("sg_content"));
				article.setDate(rs.getDate("sg_date"));
				article.setCheck(rs.getString("sg_check"));
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return article;
	}
	
	public boolean successCheck(int su_num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean checked = false;
		
		try {
			String sql = "SELECT * FROM suggestion WHERE sg_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, su_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("sg_check").equals("답변완료")) {
					checked = true;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("BoardDAO - selectArticle() 실패 : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return checked;
	}
	
	public int updateCheck(SuggestionBean suggestionBean) {
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String check="답변완료";
		
		try {
			String sql = "UPDATE suggestion SET sg_check=? WHERE sg_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, check);
			pstmt.setInt(2, suggestionBean.getNum());
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SuggestionDAO - updateCheckSuggestion() 실패! : " + e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return updateCount;
	}
	
//	getEmail
	public String getEmail(String id) {
		String email = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select email from member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				email = rs.getString("email");
			}
			
		} catch (SQLException e) {
			System.out.println("SuggestionDAO - getEmail() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return email;
	}
	
	//=============================================admin==================================
	
	public int insertReply(SuggestionBean suggestionBean) {

		int insertCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int num = 0;
		String check="답변글";
		
		try {
			
			String sql = "select max(sg_num) from suggestion";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1)+1;
			}
			
			sql = "insert into suggestion values(?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, "admin");
			pstmt.setString(3, suggestionBean.getContent());
			pstmt.setString(4, "관리자 이메일");
			pstmt.setString(5, check);
			pstmt.setString(6, "관리자 답글");
			pstmt.setInt(7, suggestionBean.getNum());
			insertCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SuggestionDAO - insertReply() 실패! : " + e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return insertCount;
	}
	
	public ArrayList<SuggestionBean> adminselectArticleList() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<SuggestionBean> articleList = new ArrayList<SuggestionBean>();
		
		try {
			String sql = "select * from suggestion where sg_num_ref=? order by sg_num desc"; //답변글 제외한 모든글 보기 (답변완료글 포함)
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();
			
			// 조회된 레코드 만큼 반복
			while(rs.next()) {
				// 1개 레코드(게시물)를 저장할 SuggestionBean 객체 생성
				SuggestionBean article = new SuggestionBean();
				
				// SuggestionBean 객체에 조회된 레코드(게시물) 정보를 저장
				article.setNum(rs.getInt("sg_num"));
				article.setId(rs.getString("member_member_id"));
				article.setSubject(rs.getString("sg_subject"));
				article.setEmail(rs.getString("sg_email"));
				article.setDate(rs.getDate("sg_date"));
				article.setCheck(rs.getString("sg_check"));

				// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
				articleList.add(article);
				
			}
			
		} catch (SQLException e) {
			System.out.println("SuggestionDAO - adminselectArticleList() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	public ArrayList<SuggestionBean> adminselectArticleList2() {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<SuggestionBean> articleList = new ArrayList<SuggestionBean>();
		
		try {
			String sql = "select * from suggestion where sg_check=? order by sg_num desc"; //답변글 제외한 모든글 보기 (답변완료글 포함)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "미완료");
			rs = pstmt.executeQuery();
			
			// 조회된 레코드 만큼 반복
			while(rs.next()) {
				// 1개 레코드(게시물)를 저장할 SuggestionBean 객체 생성
				SuggestionBean article = new SuggestionBean();
				
				// SuggestionBean 객체에 조회된 레코드(게시물) 정보를 저장
				article.setNum(rs.getInt("sg_num"));
				article.setId(rs.getString("member_member_id"));
				article.setSubject(rs.getString("sg_subject"));
				article.setEmail(rs.getString("sg_email"));
				article.setDate(rs.getDate("sg_date"));
				article.setCheck(rs.getString("sg_check"));

				// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
				articleList.add(article);
				
			}
			
		} catch (SQLException e) {
			System.out.println("SuggestionDAO - adminselectArticleList2() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	public int selectListCount1() {      //답변 미완료 글숫자 가져오기
		int listCount1 = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT COUNT(sg_num) FROM suggestion WHERE sg_num_ref=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount1 = rs.getInt(1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SuggestionDAO - selectListCount2() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(con);
			close(pstmt);
		}
		
		
		return listCount1;
	}
	
	public int selectListCount2() {      //답변 미완료 글숫자 가져오기
		int listCount2 = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			String sql = "SELECT COUNT(sg_num) FROM suggestion WHERE sg_check=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "미완료");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount2 = rs.getInt(1);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SuggestionDAO - selectListCount2() 실패! : " + e.getMessage());
		} finally {
			// DB 자원 반환
			close(con);
			close(pstmt);
		}
		
		
		return listCount2;
	}
	
}
