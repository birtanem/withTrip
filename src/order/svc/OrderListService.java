package order.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;

import org.json.simple.JSONArray;

import order.dao.OrderDAO;

public class OrderListService {

	public JSONArray getOrderList(String id, int page, int limit) {
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);

		JSONArray jsonArray = orderDAO.getOrderList(id, page, limit);
				
		close(con);
		
		return jsonArray;
	}
	
	public int getOrderListCount(String id) {
	
		int listCount = 0;
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
		
		listCount = orderDAO.getListCount(id);
		
		close(con);
		
		return listCount;
	}

	
}
