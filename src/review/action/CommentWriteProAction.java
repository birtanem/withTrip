package review.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import review.svc.CommentListService;
import review.svc.CommentWriteService;
import review.vo.CommentBean;

public class CommentWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("CommentWriteProAction");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		System.out.println(r_num);
		CommentBean article = new CommentBean();

		article.setR_num(r_num);
		article.setRc_id(request.getParameter("id"));
		article.setRc_content(request.getParameter("rc_content"));
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		CommentWriteService commentWriteService = new CommentWriteService();
		
		boolean isComment = commentWriteService.isWriteComment(article);
		
		
		CommentListService commentListService = new CommentListService();
		
		ArrayList<CommentBean> articleList = commentListService.getArticleList(r_num);
		
		int commentCount = commentListService.getArticle(r_num);
		
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < articleList.size(); i++) {
			
			JSONObject jObject = new JSONObject();
			
			jObject.put("rc_id", articleList.get(i).getRc_id());
			jObject.put("rc_content", articleList.get(i).getRc_content());
			jObject.put("rc_date", articleList.get(i).getRc_date()+"");
			jObject.put("commentCount", commentCount);
			jObject.put("rc_num", articleList.get(i).getRc_num());
//			jObject.put("rc_lev", articleList.get(i).getRc_lev());
//			jObject.put("rc_ref", articleList.get(i).getRc_ref());
//			jObject.put("rc_seq", articleList.get(i).getRc_seq());
			
			jsonArray.add(jObject);
		}
		
//		JSONObject jObject = new JSONObject();
//			
//		jObject.put("commentCount", commentCount);
		
		out.print(jsonArray);
//		out.print(jObject);
		System.out.println(jsonArray);
//		System.out.println(jObject);
//		forward = new ActionFoRWARD();
//		FORWARD.SETPATH("/REVIEW_Content.re?r_num="+r_num+"&page="+page);
		
		return forward;
	}

}
