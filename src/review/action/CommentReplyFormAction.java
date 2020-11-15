package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.CommentReplyService;
import review.vo.CommentBean;

public class CommentReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("CommentReplyFormAction");
		
//		int r_num = Integer.parseInt(request.getParameter("r_num"));
		int rc_num = Integer.parseInt(request.getParameter("rc_num"));
//		String page = request.getParameter("page");
//		
		CommentReplyService commentReplyService = new CommentReplyService();
		
		CommentBean article = commentReplyService.getReplyArticle(rc_num);
//		
//		System.out.println(article.getRc_ref());
//		System.out.println(article.getRc_content());
//		
		request.setAttribute("article", article);
//		request.setAttribute("page", page);
		
		forward = new ActionForward();
		forward.setPath("/review/replyForm.jsp");
		
		return forward;
	}

}
