package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.action.Action;
import common.vo.ActionForward;
import product.svc.productDeleteService;

public class productDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		boolean isDelete = false;
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		productDeleteService productDeleteService=new productDeleteService();
		isDelete=productDeleteService.delete(p_num);
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (!isDelete) {
			out.println("<script>");
			out.println("alert('delete실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('삭제완료')");
			out.println("location.href='adminProduct.ad'");
			out.println("</script>");
			
		}
		
		return forward;
	}

}
