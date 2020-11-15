package product.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import product.dao.ProductDAO;
import product.vo.ProductBean;

public class ProductListService {
	public ArrayList<ProductBean> getProductList(int page, int limit){
		ArrayList<ProductBean> productList=null;
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		productList=productDAO.getList(page, limit);
		
		System.out.println(productList);
		close(con);
		
		return productList;
	}
	public int getListCount() {
		int ListCount=0;
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		ListCount=productDAO.selectListCount();
		System.out.println(ListCount);
		close(con);
		
		return ListCount;
	}
	public ArrayList<ProductBean> getProductList() {
		ArrayList<ProductBean> productList=null;
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		productList=productDAO.getList();
		
		System.out.println(productList);
		close(con);
		
		return productList;
	}
	
	// 추천상품리스트 4개 
	public ArrayList<ProductBean> getProductList(String theme) {
		ArrayList<ProductBean> productList=null;
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		productList=productDAO.getList(theme);
		
		System.out.println(productList);
		close(con);
		
		return productList;
	}
	
}
