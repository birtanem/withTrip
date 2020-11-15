package product.svc;

import static common.db.JdbcUtil.*;  
import java.sql.Connection;

import product.dao.ProductDAO;
import product.vo.ProductBean;

public class productUpdateService {

	public boolean productUpdate(ProductBean productBean) {
		System.out.println("productUpdateService도착!");
		
		boolean isUpdate=false;
		
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int updateCount=productDAO.productUpdate(productBean);
		if(updateCount>0) {
			isUpdate=true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return isUpdate;
	}
	
	
}
