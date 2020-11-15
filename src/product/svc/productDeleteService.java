package product.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.commit;
import static common.db.JdbcUtil.getConnection;
import static common.db.JdbcUtil.rollback;

import java.sql.Connection;

import product.dao.ProductDAO;

public class productDeleteService {

	
	
	public boolean delete(int p_num) {
		boolean isDelete=false;
		
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int deleteCount=productDAO.productDelete(p_num);
		if(deleteCount>0) {
			isDelete=true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isDelete;
	}

	
	
	
	
}
