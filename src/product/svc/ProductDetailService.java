package product.svc;
import static common.db.JdbcUtil.*;

import java.sql.Connection;

import product.dao.ProductDAO;
import product.vo.ProductBean;

public class ProductDetailService {

	public ProductBean getProductDetail(int p_num) {
		
		Connection con=getConnection();
		ProductBean productBean=new ProductBean();
		ProductDAO productDAO=ProductDAO.getInstance();
		productDAO.setConnection(con);
		
		productBean=productDAO.getProductDetail(p_num);
		System.out.println("pBean:"+productBean);
		close(con);
		
		return productBean;
	}

}
