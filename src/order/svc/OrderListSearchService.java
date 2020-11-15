package order.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import order.dao.OrderDAO;
import order.vo.OrderBean;
import product.vo.ProductBean;

public class OrderListSearchService {

	public JSONArray getOrderSearchList(String id, String day, String day2, int page, int limit) {
		
		System.out.println("OrderListSearchService");
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
			
		JSONArray jsonArray = orderDAO.getOrderSearchList(id, day, day2, page, limit);
		
		close(con);
		
		return jsonArray;
	}
	
	public int getOrderSearchListCount(String id, String day, String day2) {
		
		int listCount = 0;
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
		
		listCount = orderDAO.getSearchListCount(id, day, day2);
		
		close(con);
		
		return listCount;
	}
}
