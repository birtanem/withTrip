package review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.CommentReplyService;
import review.vo.CommentBean;

public class CommentReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("CommentReplyForm");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		int rc_num = Integer.parseInt(request.getParameter("rc_num"));
		int rc_ref = Integer.parseInt(request.getParameter("rc_ref"));
		int rc_lev = Integer.parseInt(request.getParameter("rc_lev"));
		int rc_seq = Integer.parseInt(request.getParameter("rc_seq"));
		
		CommentBean article = new CommentBean();
		
		article.setRc_num(rc_num);
		article.setR_num(r_num);
		article.setRc_id(request.getParameter("id"));
		article.setRc_content(request.getParameter("rc_content"));
		article.setRc_ref(rc_ref);
		article.setRc_lev(rc_lev);
		article.setRc_seq(rc_seq);
		
		CommentReplyService commentReplyService = new CommentReplyService();
		
		boolean isReply = commentReplyService.insertArticle(article);
		
		if (!isReply) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('글작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}else {

			System.out.println("글작성 완료");
			
			forward = new ActionForward();
			
			forward.setPath("/Review_Content.re?&r_num="+r_num);
		}
		return forward;
	}

}
