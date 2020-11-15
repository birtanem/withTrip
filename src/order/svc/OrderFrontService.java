package order.svc;

import java.sql.Connection;
import static  common.db.JdbcUtil.*;
import org.json.simple.JSONObject;

import order.dao.OrderDAO;
import product.vo.ProductBean;

public class OrderFrontService {

	public ProductBean selectOrderList(JSONObject obj) {
		System.out.println(obj.get("num"));
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
		
		
		ProductBean pb = orderDAO.selectOrderList(obj);
		
		
		close(con);
		
		return pb;
		
		
	}

}
