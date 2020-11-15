package review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.ReviewUpdateService;
import review.vo.ReviewBean;

public class ReviewUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		System.out.println("ReviewUpdateFormAction");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		ReviewUpdateService reviewUpdateService = new ReviewUpdateService();
		
		ReviewBean article = reviewUpdateService.SelectUpdateArticle(r_num);
		
		request.setAttribute("article", article);
		
		forward = new ActionForward();
		forward.setPath("/review/review_Update.jsp");
		
		return forward;
	}

}
