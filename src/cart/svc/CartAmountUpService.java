package cart.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cart.action.ProductCartListAction;
import cart.dao.CartDAO;
import cart.vo.CartBean;
import product.vo.ProductBean;

public class CartAmountUpService {

	public void upCart(String id,int c_num) {
		System.out.println("CartAmountUpService");

		Connection con = getConnection();
		// CartDAO 객체 생성
		CartDAO cdao = new CartDAO();
		cdao.setConnection(con);
		
		Vector vector = cdao.getList(id); 
		
		// List cartList = vector 첫번째 데이터
		ArrayList<CartBean> cartList = (ArrayList<CartBean>)vector.get(0);
		// List productList = vecotr 두번째 데이터
		ArrayList<ProductBean> productList = (ArrayList<ProductBean>)vector.get(1);
		
		for(int i = 0; i < cartList.size(); i++) {
			if(cartList.get(i).getC_num() == c_num) {
				cartList.get(i).setC_p_amount(cartList.get(i).getC_p_amount()+1);
			}
		}
		
		
		close(con);	
		
	}
	
}
