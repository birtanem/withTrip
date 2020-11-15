package order.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import org.json.simple.JSONArray;

import order.dao.OrderDAO;

public class OrderDetailActionService {

	public JSONArray getOrder(long orderNum) {
		
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
		
		JSONArray jsonArray = orderDAO.getOrder(orderNum);
		
		commit(con);
	
		close(con);
		
		return jsonArray;
	}

}
