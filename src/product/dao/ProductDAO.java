package product.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import product.vo.ProductBean;

public class ProductDAO {
	private static ProductDAO instance;

	private ProductDAO() {
	}

	public static ProductDAO getInstance() {
		if (instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	// 상품 등록 메서드
	public int insertArticle(ProductBean productBean) {

		int insertCount = 0;

		// DB 작업에 필요한 변수 선언(Connection 객체는 이미 외부로부터 전달받음)
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			int num = 1;

			// 새 글 번호 결정
			String sql = "SELECT MAX(p_num) FROM product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			// 게시글 등록
			sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, productBean.getP_name());
			pstmt.setString(3, productBean.getP_content());
			pstmt.setString(4, productBean.getP_image());
			pstmt.setInt(5, productBean.getP_price());
			pstmt.setInt(6, productBean.getP_amount());
			pstmt.setInt(7, productBean.getRegion_region_code());
			pstmt.setString(8, productBean.getP_theme());

			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("productDAO - insertArticle메서드 실패ㅠㅠ " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return insertCount;
	} // 상품 등록 메서드 끝

	public ArrayList<ProductBean> getList(int page, int limit) {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int startRow = (page-1)*limit;

		try {
	
			String sql = "select p.p_num, p.p_name, p.p_content,p.p_image, p.p_price,"
					+ "p.p_amount, p.p_theme, p.region_region_code,r.region_name"
					+ " from product p join region r on p.region_region_code = r.region_code order by p_num limit ?,?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBean productBean = new ProductBean();
				productBean.setP_num(rs.getInt(1));
				productBean.setP_name(rs.getString(2));
				productBean.setP_content(rs.getString(3));
				productBean.setP_image(rs.getString(4));
				productBean.setP_price(rs.getInt(5));
				productBean.setP_amount(rs.getInt(6));
				productBean.setP_theme(rs.getString(7));
				productBean.setRegion_region_code(rs.getInt(8));
				productBean.setRegion_name(rs.getString(9));
				productList.add(productBean);

			}
		} catch (SQLException e) {
			System.out.println("ProductDAO getList실패" + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}
	public ArrayList<ProductBean> getList() {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
	
			String sql = "select p.p_num, p.p_name, p.p_content,p.p_image, p.p_price,"
					+ "p.p_amount, p.p_theme, p.region_region_code,r.region_name"
					+ " from product p join region r on p.region_region_code = r.region_code";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBean productBean = new ProductBean();
				productBean.setP_num(rs.getInt(1));
				productBean.setP_name(rs.getString(2));
				productBean.setP_content(rs.getString(3));
				productBean.setP_image(rs.getString(4));
				productBean.setP_price(rs.getInt(5));
				productBean.setP_amount(rs.getInt(6));
				productBean.setP_theme(rs.getString(7));
				productBean.setRegion_region_code(rs.getInt(8));
				productBean.setRegion_name(rs.getString(9));
				productList.add(productBean);
			}
		} catch (SQLException e) {
			System.out.println("ProductDAO getList실패" + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}
	
	// 추천상품리스트 4개 뽑아감
	public ArrayList<ProductBean> getList(String theme) {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			
			if(theme == "0") { // 비로그인
				
				sql = "SELECT * FROM product ORDER BY rand() LIMIT 4";
				pstmt = con.prepareStatement(sql);
				
			}else { // 로그인
				
				sql = "SELECT * FROM product WHERE p_theme = ? ORDER BY rand() LIMIT 4";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, theme);
			}
						
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductBean productBean = new ProductBean();
				productBean.setP_num(rs.getInt(1));
				productBean.setP_name(rs.getString(2));
				productBean.setP_content(rs.getString(3));
				productBean.setP_image(rs.getString(4));
				productBean.setP_price(rs.getInt(5));
				productBean.setP_amount(rs.getInt(6));
				productBean.setRegion_region_code(rs.getInt(7));
				productBean.setP_theme(rs.getString(8));
				productList.add(productBean);
			}
		} catch (SQLException e) {
			System.out.println("ProductDAO getList실패" + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return productList;
	}

	public int selectListCount() {
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select count(p_num) from product";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("ProductDAO - selectListCount 실패" + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}//selectListCount

	public ProductBean getProductDetail(int p_num) {
		ProductBean productBean =null;
		System.out.println("dao pnum:"+p_num);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select p.p_num, p.p_name, p.p_content,p.p_image, p.p_price,"
					+ "p.p_amount, p.p_theme, p.region_region_code,r.region_name"
					+ " from product p join region r on p.region_region_code = r.region_code where p.p_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				productBean=new ProductBean();
				productBean.setP_num(rs.getInt(1));
				productBean.setP_name(rs.getString(2));
				productBean.setP_content(rs.getString(3));
				productBean.setP_image(rs.getString(4));
				productBean.setP_price(rs.getInt(5));
				productBean.setP_amount(rs.getInt(6));
				productBean.setP_theme(rs.getString(7));
				productBean.setRegion_region_code(rs.getInt(8));
				productBean.setRegion_name(rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("ProductDAO-getProductDetail에러: 	"+e.getMessage());
		}finally {
			close(rs);
			close(pstmt);
		}
		return productBean;
	}//getProductDetail
	
	
	public int productUpdate(ProductBean pb) {
		PreparedStatement pstmt=null;
		int updateCount=0;
		try {
			String sql="update product set p_name=?, p_content=?, p_image=?, p_price=?,p_amount=?,"
					+ "p_theme=?, region_region_code=? where p_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pb.getP_name());
			pstmt.setString(2, pb.getP_content());
			pstmt.setString(3, pb.getP_image());
			System.out.println("daoprice:"+pb.getP_price());
			pstmt.setInt(4, pb.getP_price());
			pstmt.setInt(5, pb.getP_amount());
			pstmt.setString(6, pb.getP_theme());
			pstmt.setInt(7, pb.getRegion_region_code());
			pstmt.setInt(8, pb.getP_num());
			updateCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DAO-productUPdate에러: "+e.getMessage());
		}finally {
			close(pstmt);
		}return updateCount;
	}

	public int productImgUpdate(ProductBean pb) {
		PreparedStatement pstmt=null;
		int updateCount=0;
		try {
			String sql="update product set p_image=? where p_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pb.getP_image());
			pstmt.setInt(2, pb.getP_num());
			updateCount=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DAO-productImgUPdate에러: "+e.getMessage());
		}finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int productDelete(int p_num) {
		int deleteCount=0;
		PreparedStatement pstmt=null;
		try {
			String sql = "set foreign_key_checks=0";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = "delete from product where p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			deleteCount = pstmt.executeUpdate();
			sql = "set foreign_key_checks=1";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DAO-productDelete에러: "+e.getMessage());
		}finally {
			close(pstmt);
		}
		return deleteCount;
	}
	
	
	

}
