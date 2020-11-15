package admin.dao;

import static common.db.JdbcUtil.*;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AdminDAO {

	public AdminDAO() {}
	
	private static AdminDAO instance;

	public static AdminDAO getInstance() {
		// BoardDAO 객체가 없을 경우에만 생성
		if(instance == null) {
			instance = new AdminDAO();
		}	
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getTotalJoinCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int memberCount = 0;
		
		String sql = "SELECT COUNT(*) FROM member";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return memberCount;
	}

	public int getTotalReadCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardCount = 0;
		
		String sql = "SELECT SUM(pl_readcount) FROM place";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardCount;
	}
	
	public long getTotalRevenue() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long revenue = 0;
		
		String sql = "SELECT SUM(o_price) FROM `order`";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				revenue = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return revenue;
	}

	public JSONArray getDailyLog() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		
		String sql = "SELECT l2.l_date as date, truncate((l2.l_revenue - l.l_revenue)/l.l_revenue*100,0) as revrate, l2.l_revenue as revenue, truncate((l2.l_joincount - l.l_joincount)/l.l_joincount*100,0) as jrate, l2.l_joincount as joincount, truncate((l2.l_readcount - l.l_readcount)/l.l_readcount*100,0) as readrate, l2.l_readcount as readcount FROM log l JOIN log l2 ON l.l_num = l2.l_num-1 ORDER BY date DESC limit 7";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JSONObject obj = new JSONObject();
				
				obj.put("date",rs.getString("date"));
				obj.put("revrate",rs.getString("revrate"));
				obj.put("revenue",rs.getString("revenue"));
				obj.put("jrate",rs.getString("jrate"));
				obj.put("joincount",rs.getString("joincount"));
				obj.put("readrate",rs.getString("readrate"));
				obj.put("readcount",rs.getString("readcount"));
				
				jsonArray.add(obj);
								
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}


		return jsonArray;
	}


	public int[] getTypeCount() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] typeArr = new int[7];
		int i=0;
			
		try {
			String sql = "SELECT count(type) FROM member GROUP BY type ORDER BY type";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
					
				typeArr[i] = rs.getInt(1);
				i++;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(typeArr);
		return typeArr;
	}
}
