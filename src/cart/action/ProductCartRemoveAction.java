package cart.action;

import static common.db.JdbcUtil.*;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dao.CartDAO;
import cart.svc.ProductCartRemoveService;
import common.action.Action;
import common.vo.ActionForward;

public class ProductCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductCartRemoveAction");
		
		ActionForward forward = null;
		// 요청한 클라이언트의 세션 영역 객체 가져오기
		HttpSession session = request.getSession();
				
		String id = (String)session.getAttribute("id"); 
		// id가 없으면 login 페이지로 돌아가기
		if(id == null) {
		forward.setRedirect(true);
		forward.setPath("MemberLogin.me");
		return forward;
		} 
		
		 String message = request.getParameter("message"); 
		 System.out.println(message);
		ProductCartRemoveService productCartRemoveSerivce = new ProductCartRemoveService();

		productCartRemoveSerivce.cartRemove(message);;

		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("productList.pr");
		
		return forward;
	}

}
