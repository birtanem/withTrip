package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import com.sun.xml.internal.ws.Closeable;

import product.dao.ProductDAO;
import product.vo.ProductBean;

public class productImgUpdateService {

	public boolean productImgUpdate(ProductBean productBean) {
		System.out.println("productImgSvcㄷ착");
		boolean isUpdate=false;
		
		Connection con=getConnection();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		int updateCount=productDAO.productImgUpdate(productBean);
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
