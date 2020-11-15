package event.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import event.vo.EventBean;
import event.vo.EventWinBean;
import member.vo.MemberBean;

public class EventDAO {
	

	private EventDAO() {}
	
	private static EventDAO instance;

	public static EventDAO getInstance() {
		// BoardDAO 객체가 없을 경우에만 생성
		if(instance == null) {
			instance = new EventDAO();
		}	
		return instance;
	}

	Connection con; // Connection 객체 전달받아 저장할 변수 선언
	
	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int checkDate() {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int checkDate = 1;
		
		String sql = "SELECT e_round FROM event WHERE e_edate > now()";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				checkDate = 0;
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
			System.out.println("EventDAO - checkDate() 실패! : "+ e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return checkDate;
	}
	public int setStartDate(String date) { 
		PreparedStatement pstmt = null;
		int setDateCount = 0;
		System.out.println(date);
		int checkDate = checkDate();
		
		if(checkDate == 1) {
			
			try {
				
				String sql = "UPDATE event_box SET eb_pull = 0";
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
				
				
				sql = "INSERT INTO event VALUES(null, now(), ?)";
				pstmt  = con.prepareStatement(sql);
				pstmt.setString(1, date);
				setDateCount = pstmt.executeUpdate();
				


			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("EventDAO - setStartDate() 실패! : "+e.getMessage());
			}finally {
				close(pstmt);
			}
			
		}
		return setDateCount;
	}
	public int setEndDate() { 

		PreparedStatement pstmt = null;
		int setDate = 0;
		try {

			String sql = "UPDATE event SET e_edate = now() WHERE e_round = (SELECT MAX(e_round) FROM (SELECT e_round FROM event) as a)";
			pstmt  = con.prepareStatement(sql);
			setDate = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EventDAO - setEndDate() 실패! : "+e.getMessage());
		}finally {
			close(pstmt);
		}
		
		return setDate;
	}
	public MemberBean selectArticle(String member_id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean article = null;
			
		try {
			String sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				article = new MemberBean();
				article.setPoint(rs.getInt("point"));
				article.setCp_3(rs.getInt("cp_3"));
				article.setCp_5(rs.getInt("cp_5"));
				article.setCp_10(rs.getInt("cp_10"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("EventDAO - selectArticle() 실패! : "+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return article;
	}

	public Timestamp selectDate() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Timestamp date = null;
		
		String sql = "SELECT e_edate FROM event WHERE e_round = (SELECT MAX(e_round) FROM event) ";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				date = rs.getTimestamp("e_edate");			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - selectDate() 실패! : "+e.getMessage());
		}
		System.out.println("DAO date: "+date);
		return date;
	}
	
	public int setPull() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		int checkRank = 0;
		int checkPull = 0;
		
		try {
				
				String sql = "SELECT * FROM event_box WHERE eb_pull = 0 ORDER BY rand() LIMIT 1";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
	
				if(rs.next()) {
													
					num = rs.getInt("eb_num");
					
					checkRank = rs.getInt("eb_rank");
					
				}			
				
				checkPull = setPull2(num) + checkRank; // 30001 50001 100001	당첨, 1 꽝
				
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - setPull() 실패! : "+e.getMessage());
		}finally {
			
			close(rs);
			close(pstmt);
		}
		
		return checkPull;
	}
	public int setPull2(int num) {
		PreparedStatement pstmt2 = null;
		int checkPull = 0;
		
		try {			
				String sql = "UPDATE event_box SET eb_pull = 1 WHERE eb_num = ?";
				pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, num);			
				checkPull = pstmt2.executeUpdate(); // 30001 50001 100001	당첨, 1 꽝				
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - setPull2() 실패! : "+e.getMessage());
		}finally {
			close(pstmt2);
		}
		
		return checkPull;
	}
	
	public int setPullCheck() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;
		
		try {	
			String sql = "SELECT COUNT(DISTINCT eb_num) as 'num' FROM event_box WHERE eb_rank > 0 AND eb_pull = 0";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {					
				check = rs.getInt("num");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - setPullcheck() 실패! : "+e.getMessage());
		}finally {			
			close(rs);
			close(pstmt);
		}		
		return check;
	}

	public ArrayList<EventWinBean> getWinArticle() {
		
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		ArrayList<EventWinBean> articleList = new ArrayList<EventWinBean>();
		
		try {
			String sql = "SELECT * FROM event_win";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				EventWinBean article = new EventWinBean();
				article.setMember_id(rs.getString("member_id"));
				article.setEvent_round(rs.getInt("event_round"));
				article.setEw_date(rs.getDate("ew_date"));
				article.setEw_cp_3(rs.getInt("ew_cp_3"));
				article.setEw_cp_5(rs.getInt("ew_cp_5"));
				article.setEw_cp_10(rs.getInt("ew_cp_10"));
				articleList.add(article);
			}
		} catch (SQLException e) {
			System.out.println("EventDAO - getArticle() 실패! : "+e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	public ArrayList<EventBean> getArticle() {
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		ArrayList<EventBean> articleList = new ArrayList<EventBean>();
		
		try {
			String sql = "SELECT * FROM event";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				EventBean article = new EventBean();
				article.setE_round(rs.getInt("e_round"));
				article.setE_sdate(rs.getDate("e_sdate"));
				article.setE_edate(rs.getDate("e_edate"));
				articleList.add(article);
			}
		} catch (SQLException e) {
			System.out.println("EventDAO - getArticle() 실패! : "+e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return articleList;
	}
	
	public int selectPoint(String member_id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		
		
		try {
			String sql = "SELECT point FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				point = rs.getInt("point");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - selectPoint() 실패! : "+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
	
		
		return point;
	}

	public int updateMinusPoint(String memeber_id) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		
		try {
			String sql = "UPDATE member SET point = point - 100  WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memeber_id);
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - updateMinusPoint() 실패! : "+e.getMessage());
		}finally {
			close(pstmt);
		}
	
		
		return updateCount;
	}

	public int updateExchangePoint(int point, String member_id) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
		
		
		try {
			String sql = "UPDATE member SET point = point + ?, cp_? = cp_? - 1  WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			System.out.println(point);
			System.out.println(member_id);
			pstmt.setInt(1, point*10000);
			pstmt.setInt(2, point);
			pstmt.setInt(3, point);
			pstmt.setString(4, member_id);
			updateCount = pstmt.executeUpdate();
			System.out.println(updateCount);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - updateExchangePoint() 실패! : "+e.getMessage());
		}finally {
			close(pstmt);
		}
	
		
		return updateCount;
	}

	public int updateCoupon(int point, String member_id) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		
		try {
			String sql = "UPDATE member SET cp_? = cp_? + 1  WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setInt(2, point);
			pstmt.setString(3, member_id);
			insertCount = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - updateExchangePoint() 실패! : "+e.getMessage());
		}finally {
			close(pstmt);
		}
	
		
		return insertCount;
	}

	public int insertWinArticle(int point, String member_id) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		try {
			String sql = "INSERT INTO event_win(ew_num, member_id, event_round, ew_date, ew_cp_?) VALUES(null, ?, (SELECT MAX(e_round) FROM event), now(), 1)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, member_id);
			insertCount = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("EventDAO - insertWinArticle() 실패! : "+e.getMessage());
		}finally {
			close(pstmt);
		}
	
		
		return insertCount;
	}

	public JSONArray getChangeWinArticle(int sel) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jArr = new JSONArray();
		
		try {
			String sql = "SELECT * FROM event_win WHERE event_round = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sel);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				JSONObject obj = new JSONObject();
				obj.put("member_id", rs.getString("member_id"));
				obj.put("event_round", rs.getString("event_round"));
				obj.put("ew_date", rs.getString("ew_date"));
				obj.put("ew_cp_3", rs.getString("ew_cp_3"));
				obj.put("ew_cp_5", rs.getString("ew_cp_5"));
				obj.put("ew_cp_10", rs.getString("ew_cp_10"));
				jArr.add(obj);
			}
		} catch (SQLException e) {
			System.out.println("EventDAO - getChangeWinArticle() 실패! : "+e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return jArr;

	}

	public JSONObject getChangeArticle(int sel) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject obj = null;
		try {
			String sql = "SELECT * FROM event WHERE e_round = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sel);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				obj = new JSONObject();
				obj.put("e_round", rs.getString("e_round"));
				obj.put("e_sdate", rs.getString("e_sdate"));
				obj.put("e_edate", rs.getString("e_edate"));
			}
		} catch (SQLException e) {
			System.out.println("EventDAO - getChageArticle() 실패! : "+e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return obj;
	}

	public int getCoupon(String id, int coupon) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int couponCount = 0;
		try {
			String sql = "SELECT cp_? FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, coupon);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				couponCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("EventDAO - getCoupon() 실패! : "+e.getMessage());
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return couponCount;
	}
}
