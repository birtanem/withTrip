package cart.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cart.vo.CartBean;
import product.vo.ProductBean;

public class CartDAO {
	
	private static CartDAO instance;
	
	public CartDAO() {}
	
	public static CartDAO getInstance() {
		// CartDAO 객체가 없을 경우에만 생성
		if(instance==null) {
			instance=new CartDAO();
		}
		return instance;
	}
	//-------------------------------------
	
	Connection con; // Connection 객체 전달받아서 저장할 변수 선언
	
	// Service 클래스로부터 Conncetion 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}

	// 장바구니 중복 확인
	public int checkProduct(CartBean cb) {
		System.out.println("checkProduct");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;

		try { // 멤버 아이디와 상품명이 중복되는 상품 확인하기
			String sql = "select * from cart where c_member_id=? and c_p_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, cb.getC_member_id());
			pstmt.setInt(2, cb.getC_p_num());
			System.out.println(cb.getC_p_num());
			// rs 실행
			rs=pstmt.executeQuery();
			// 데이터가 있으면 check = 1
			// c_m_id와 c_p_num
			// c_amount 업데이트 
			if(rs.next()) {
				check = 1;
				sql = "update cart set c_p_amount = c_p_amount+? where c_member_id=? and c_p_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cb.getC_p_amount());
				pstmt.setString(2, cb.getC_member_id());
				pstmt.setInt(3, cb.getC_p_num());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("CartDAO - checkProduct() 실패 ! : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return check;
	
	} // 장바구니 중복 확인 메서드 끝

	// 장바구니 추가 시작
	public void cartAdd(CartBean cb) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int c_num = 0;
		
		try {
			// 장바구니 max num 구하기
			String sql = "select max(c_num) from cart";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				c_num = rs.getInt(1)+1;
			} else {
				c_num = 1;
			}
			// 장바구니 추가
			sql = "insert into cart values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.setInt(2, cb.getC_p_num());
			pstmt.setString(3, cb.getC_member_id());
			pstmt.setInt(4, cb.getC_p_amount()); 
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("CartDAO - cartAdd() 실패 ! : " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

	} // 장바구니 추가 메서드 끝


	
	// 장바구니 목록 메서드 시작
	public Vector getList(String id) { 
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector vector = new Vector();
		ArrayList cartList = new ArrayList();
		ArrayList productList = new ArrayList();
	

		
		try {
			String sql = "select * from cart c join product p on c.c_p_num = p.p_num where c_member_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartBean cb = new CartBean();
				ProductBean pb = new ProductBean();
				cb.setC_num(rs.getInt("c_num"));
				cb.setC_p_num(rs.getInt("c_p_num"));
				cb.setC_member_id(rs.getString("c_member_id"));
				cb.setC_p_amount(rs.getInt("c_p_amount"));
				pb.setP_num(rs.getInt("p_num"));
				pb.setP_name(rs.getString("p_name"));
				pb.setP_content(rs.getString("p_content"));
				pb.setP_image(rs.getString("p_image"));
				pb.setP_price(rs.getInt("p_price"));
				pb.setP_amount(rs.getInt("p_amount"));
				pb.setP_theme(rs.getString("p_theme"));
				cartList.add(cb);
				productList.add(pb);
			}
			// 벡터 첫번째 칸에 장바구니 목록
			// 벡터 두번째 칸에 상품 목록
			vector.add(cartList);
			vector.add(productList);
			
		} catch (SQLException e) {
			System.out.println(("CartDAO - getList실패" + e.getMessage()));
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return vector;
	} // 장바구니 목록 메서드 끝

	
	// 장바구니 부분삭제 메서드
	public void cartRemove(int c_num) {
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from cart where c_p_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			// 실행하기
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(("CartDAO - cartRemove 실패" + e.getMessage()));
		} finally {
			close(pstmt);
		}
		
	} // 장바구니 부분삭제 메서드 끝
	
	

	
}// 클래스 끝
