package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.CommentDeleteService;

public class CommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("CommentDeleteAction");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		int rc_num = Integer.parseInt(request.getParameter("rc_num"));
		
		CommentDeleteService commentDeleteService = new CommentDeleteService();
		
		boolean isDelete = commentDeleteService.DeleteArticle(rc_num);
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("Review_Content.re?r_num="+r_num);
		
		return forward;
	}

}
