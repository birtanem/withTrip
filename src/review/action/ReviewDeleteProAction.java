package review.action;

import java.io.*;

import javax.mail.Session;
import javax.servlet.http.*;

import common.action.*;
import common.vo.*;
import review.svc.*;
import review.vo.*;

public class ReviewDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		System.out.println("ReviewDeleteProAction");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		ReviewDeleteService reviewDeleteService = new ReviewDeleteService();
		
		HttpSession session = request.getSession();
		
		String r_id = (String) session.getAttribute("id");
		
		System.out.println(r_id);
		
		boolean article = reviewDeleteService.DeleteArticle(r_num,r_id);
		
		if (!article) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("Review_List.re");
		}
		
		return forward;
	}

}
