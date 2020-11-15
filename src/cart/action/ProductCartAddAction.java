package cart.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.dao.CartDAO;
import cart.svc.ProductCartAddService;
import cart.vo.CartBean;
import common.action.Action;
import common.vo.ActionForward;

public class ProductCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ProductCartAddAction");

		// 리턴 잊지 않도록 미리 선언 해주기
		ActionForward forward = null;
		
		// 요청한 클라이언트의 세션 영역 객체 가져오기
		HttpSession session = request.getSession();
				
		String id = (String)session.getAttribute("id"); 
		// id가 없으면 login 페이지로 돌아가기
		if(id == null) {
		forward=new ActionForward();
		forward.setRedirect(true);
		forward.setPath("MemberLoginForm.me");
		return forward;
		} 
		
		// 장바구니 하나의 데이터를 저장할 CartBean 객체 생성
		CartBean cb = new CartBean();
		
		// 자바빈 저장
		cb.setC_p_num(Integer.parseInt(request.getParameter("p_num")));
		System.out.println(Integer.parseInt(request.getParameter("p_num")));
		System.out.println(Integer.parseInt(request.getParameter("p_amount")));
		cb.setC_p_amount(Integer.parseInt(request.getParameter("p_amount")));
	
		cb.setC_member_id(id);
//		cb.setC_member_id(id); // 멤버 생성되면 추가해주기
		
		// ProductCartAddService 클래스 생성
		// AddArticle() 메서드 호출하여 추가 요청하기
		// 파라미터 : CartBean 객체, 리턴타입 boolean
		ProductCartAddService productCartAddService = new ProductCartAddService();
		boolean isAddSuccess = productCartAddService.AddArticle(cb);
	
		
		// response 객체를 사용하여 문서 타입 및 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();	

		// 리턴 받아서 장바구니 추가 판별
		if(!isAddSuccess) {
		
		// getWriter() 메서드 호출
		out.println("<script>");
		out.println("alert('장바구니 수량이 추가되었습니다.')");
		out.println("location.href='ProductCartList.ca'");
		out.println("</script>");
		
		} else {
		
		System.out.println("장바구니에 추가되었습니다");
		out.println("<script>");
		out.println("alert('장바구니에 상품이 추가되었습니다.')");
		out.println("location.href='ProductCartList.ca'");
		out.println("</script>");
		
		}
		
//		// 현재에서 CartList.bo 서블릿을 요청하여 Redirect 방식 포워딩
//		forward = new ActionForward();
//		// 포워딩 방식 지정
//		forward.setRedirect(true);
		// 포워딩 주소
//		forward.setPath("ProductCartList.ca");
		
		return forward;
	}

}
