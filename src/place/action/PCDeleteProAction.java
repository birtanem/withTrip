package place.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PCDeleteProService;

public class PCDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("PCDeleteProAction");
		
		int pl_num = Integer.parseInt(request.getParameter("pl_num"));
		int pc_num = Integer.parseInt(request.getParameter("pc_num"));
		String page = request.getParameter("page");

		PCDeleteProService deleteProService = new PCDeleteProService();
		
		boolean DeleteSuccess = deleteProService.PCDelete(pc_num);
		
		if (!DeleteSuccess) {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
			
		}else {
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("PlaceDetail.pl?pl_num="+pl_num+"&page="+page);
			
		}
		
		return forward;
	}

}
