 package cart.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dao.CartDAO;
import cart.vo.CartBean;
import common.action.Action;
import common.vo.ActionForward;
import product.svc.ProductListService;
import product.vo.ProductBean;
import review.vo.ReviewPageInfo;

import static common.db.JdbcUtil.*;

public class ProductCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductCartListAction 도입부");
		
		ActionForward forward = new ActionForward();
		
		// 요청한 클라이언트의 세션 영역 객체 가져오기
		HttpSession session = request.getSession();
//		session.setAttribute("id", "nani");
		
		String id = (String)session.getAttribute("id"); 
		// id가 없으면 login 페이지로 돌아가기
		if(id == null) {
			forward.setRedirect(true);
			forward.setPath("MemberLoginForm.me");
			return forward;
		} 
		
		
		
		
		Connection con = getConnection();
		// CartDAO 객체 생성
		CartDAO cdao = new CartDAO();
		cdao.setConnection(con);
		// Vector vector 메서드 호출 getList(String id)
		Vector vector = cdao.getList(id); // ==> 로그인 완료되면 파라미터 id 추가하기 !!
		
		// List cartList = vector 첫번째 데이터
		ArrayList<CartBean> cartList = (ArrayList<CartBean>)vector.get(0);
		// List productList = vecotr 두번째 데이터
		ArrayList<ProductBean> productList = (ArrayList<ProductBean>)vector.get(1);
		
		// 저장
		close(con);
		
		
		request.setAttribute("cartList", cartList);
		request.setAttribute("productList", productList);
		
		System.out.println("ProductCartListAction 끝 부분");
		
		// 디스패쳐 방식으로 이동 
		forward.setPath("/product/cartList.jsp");
	
		return forward;
	}

}