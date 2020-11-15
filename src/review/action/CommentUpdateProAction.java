package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.CommentUpdateService;
import review.vo.CommentBean;

public class CommentUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("CommentUpdateProAction");
		
		ActionForward forward = null;

		int r_num = Integer.parseInt(request.getParameter("r_num"));
		int rc_num = Integer.parseInt(request.getParameter("rc_num"));
		int rc_ref = Integer.parseInt(request.getParameter("rc_ref"));
		int rc_lev = Integer.parseInt(request.getParameter("rc_lev"));
		int rc_seq = Integer.parseInt(request.getParameter("rc_seq"));
		
		System.out.println(r_num);
		System.out.println(rc_num);
		System.out.println(rc_ref);
		System.out.println(rc_lev);
		System.out.println(rc_seq);
		System.out.println(request.getParameter("rc_id"));
		System.out.println(request.getParameter("rc_content"));
		
		CommentBean article = new CommentBean();

		article.setRc_num(rc_num);
		article.setR_num(r_num);
		article.setRc_id(request.getParameter("rc_id"));
		article.setRc_content(request.getParameter("rc_content"));
		article.setRc_ref(rc_ref);
		article.setRc_lev(rc_lev);
		article.setRc_seq(rc_seq);
		
		CommentUpdateService commentUpdateService = new CommentUpdateService();
		
		boolean isUpdate = commentUpdateService.UpdateArticle(article);
		
		forward = new ActionForward();
		forward.setPath("/Review_Content.re?&r_num="+r_num);
		
		return forward;
	}

}
