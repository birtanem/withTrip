package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.CommentUpdateService;
import review.vo.CommentBean;

public class CommentUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("CommentUpdateFormAction");
		
		ActionForward forward = null;
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		
		int rc_num = Integer.parseInt(request.getParameter("rc_num"));
		
		CommentUpdateService commentUpdateService = new CommentUpdateService();
		
		CommentBean article = commentUpdateService.getArticle(rc_num);
		
		request.setAttribute("article", article);
		
		forward = new ActionForward();
		forward.setPath("/review/commentUpdateForm.jsp?r_num="+r_num+"&rc_num="+rc_num);
		
		return forward;
	}

}
