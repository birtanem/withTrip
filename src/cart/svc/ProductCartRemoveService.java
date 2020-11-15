package cart.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import cart.dao.CartDAO;




public class ProductCartRemoveService {

	public void cartRemove(String message) {
		
		String[] messageArr = message.split("-");		 
		 int[] messageNumArr = new int[messageArr.length];
		 
		 for(int i=0;i<messageArr.length; i++) {
		 	messageNumArr[i] = Integer.parseInt(messageArr[i]); 
		 }
		 
			Connection con = getConnection();
			
			// DAO 객체 생성
			CartDAO cdao = new CartDAO();
			
			cdao.setConnection(con);
			
			// 메서드 호출
//			cdao.cartRemove(c_num);
			 for(int i=0;i<messageNumArr.length;i++) {
					System.out.println("삭제처리: "+messageNumArr[i]);
				 	cdao.cartRemove(messageNumArr[i]);

			}
			
			// 커밋
			commit(con);
			
			close(con);	
			
		 }
	}


	

