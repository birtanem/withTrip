package order.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import order.vo.OrderBean;
import order.vo.OrderDetailBean;
import product.vo.ProductBean;


public class OrderDAO {

private static OrderDAO instance;
	
	public OrderDAO() {}
	
	public static OrderDAO getInstance() {
		// CartDAO 객체가 없을 경우에만 생성
		if(instance==null) {
			instance=new OrderDAO();
		}
		return instance;
	}
	//-------------------------------------
	
	Connection con; // Connection 객체 전달받아서 저장할 변수 선언
	
	// Service 클래스로부터 Conncetion 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ProductBean selectOrderList(JSONObject obj) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBean pb = null;
		System.out.println("dao num : "+obj.get("num"));
		
		try {
			String sql = "SELECT * FROM product WHERE p_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt((String)obj.get("num")));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				pb = new ProductBean();
				pb.setP_num(rs.getInt("p_num"));
				pb.setP_name(rs.getString("p_name"));
				pb.setP_content(rs.getString("p_content"));
				pb.setP_image(rs.getString("p_image"));
				pb.setP_price(Integer.parseInt(String.valueOf(obj.get("price"))));
				pb.setP_amount(Integer.parseInt((String)obj.get("amount")));
				pb.setP_theme(rs.getString("p_theme"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {			
			close(rs);
			close(pstmt);		
		}
					
		return pb;
	}

	public String insertOrderList(OrderBean ob, int amount) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int insertCount = 0;
		String num = "";
		String orderNum = null;
		
		try {
			String sql = "SELECT LPAD(MAX(num),4,0) num FROM order_seq";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString(1));
				num = rs.getString(1);
			}
			
			orderNum = ob.getO_num() + num;
			System.out.println("price: "+ob.getO_price());
			
			sql = "INSERT INTO `order` VALUES(?,?,?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, Long.parseLong(orderNum));
			pstmt.setString(2, ob.getMember_id());
			pstmt.setInt(3, amount);
			pstmt.setInt(4, ob.getO_price());
			pstmt.setString(5, ob.getO_pay());
			pstmt.setInt(6, ob.getO_point());
			
			
			insertCount = pstmt.executeUpdate();
			
			if(insertCount <= 0) {
				orderNum = null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}

		return orderNum;
		
	}
	
	public int insertOrderDetailList(JSONObject obj, String orderNum) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		System.out.println("가격: "+obj.get("price"));
						
		try {
			
			String sql = "INSERT INTO order_detail VALUES(null,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, Long.parseLong(orderNum));
			pstmt.setString(2, (String)obj.get("name"));
			pstmt.setString(3, (String)obj.get("img"));
			pstmt.setInt(4, Integer.parseInt((String)obj.get("amount")));
			pstmt.setInt(5, Integer.parseInt((String)obj.get("price")));
			
			insertCount = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {

			close(pstmt);
		}
		return insertCount;
		
	}

	public void updateSequence() {
		PreparedStatement pstmt = null;
			
		try {
			String sql = "INSERT INTO order_seq VALUES(null)";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}	
	}

	public JSONArray getOrderList(String id, int page, int limit) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		
		int startRow = (page-1)*limit;

		try {
			// order, order_detail(이름, 이미지) 조인해서 JSON 에 조합
			// GROUP BY 절로 주문번호를 중복으로 가져오지 않게 함
			String sql = "SELECT * FROM `order` o JOIN order_detail od ON o.o_num = od.order_num WHERE member_id = ? GROUP BY od.order_num ORDER BY o.o_date desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("orderNum", rs.getLong("o.o_num"));
				obj.put("member_id", rs.getString("o.member_id"));
				obj.put("amount", rs.getInt("o.o_amount"));
				obj.put("price", rs.getInt("o.o_price"));
				obj.put("date", rs.getTimestamp("o.o_date"));
				obj.put("name", rs.getString("od.od_name"));
				obj.put("image", rs.getString("od.od_image"));
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
	

	public JSONArray getOrderSearchList(String id, String day, String day2, int page, int limit) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		int startRow = (page-1)*limit;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			// BETWEEN END 절로 날짜 범위 조회
			String sql = "SELECT * "
						+ "FROM `order` o JOIN order_detail od "
						+ "ON o.o_num = od.order_num "
						+ "WHERE member_id = ? AND "
						+ "o_date BETWEEN date(?) AND date(?)+1 "
						+ "GROUP BY od.order_num ORDER BY od.order_num DESC "
						+ "limit ?,?";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, day);
			pstmt.setString(3, day2);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, limit);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JSONObject obj = new JSONObject();
				obj.put("orderNum", rs.getLong("o.o_num"));
				obj.put("member_id", rs.getString("o.member_id"));
				obj.put("amount", rs.getInt("o.o_amount"));
				obj.put("price", rs.getInt("o.o_price"));
				obj.put("pay", rs.getString("o.o_pay"));
				obj.put("date", sdf.format(rs.getTimestamp("o.o_date")));
				obj.put("name", rs.getString("od.od_name"));
				obj.put("image", rs.getString("od.od_image"));
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

	public int getListCount(String id) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		
		try{
			String sql = "SELECT COUNT(o_num) FROM `order` WHERE member_id =?";
			
			pstmt = con.prepareStatement(sql);		
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}		
		return listCount;
	}

	public int getSearchListCount(String id, String day, String day2) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		
		try{
			String sql = "SELECT COUNT(o_num) FROM `order` WHERE member_id = ? AND o_date BETWEEN date(?) AND date(?)+1";
			
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, id);
			pstmt.setString(2, day);
			pstmt.setString(3, day2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}		
		return listCount;
	}

	public JSONArray getOrder(long orderNum) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONArray jsonArray = new JSONArray();
		
		try {
			// BETWEEN END 절로 날짜 범위 조회
			String sql = "SELECT * FROM `order` JOIN order_detail ON o_num = order_num WHERE o_num = ?";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, orderNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				JSONObject obj = new JSONObject();
				obj.put("orderNum", rs.getLong("o_num"));
				obj.put("member_id", rs.getString("member_id"));
				obj.put("amount", rs.getInt("od_amount"));
				obj.put("price", rs.getInt("od_price"));
				obj.put("total", rs.getInt("o_price"));
				obj.put("pay", rs.getString("o_pay"));
				obj.put("date", rs.getTimestamp("o_date")+"");
				obj.put("name", rs.getString("od_name"));
				obj.put("image", rs.getString("od_image"));
				obj.put("point",rs.getString("o_point"));

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

	public int updateSavePoint(String id, int point) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
	
		try {
			String sql = "UPDATE member SET point = point + ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return updateCount;
	}

	public int updateProduntAmount(int num, int amount) {
		PreparedStatement pstmt = null;
		int updateCount = 0;
	
		try {
			String sql = "UPDATE product SET p_amount = p_amount - ? WHERE p_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, num);
			
			updateCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return updateCount;
		
	}
	
	public int deleteCart(int num, String id) {
		PreparedStatement pstmt = null;
		int deleteCount = 0;
	
		try {
			String sql = "DELETE FROM cart WHERE c_p_num = ? AND c_member_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, id);
			
			deleteCount = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		return deleteCount;
		
	}
	
	public int getProductAmount(int num) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int amount = 0;
	
		try {
			String sql = "SELECT p_amount FROM product WHERE p_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				amount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			
		}		
		return amount;
		
	}
	
} 
 