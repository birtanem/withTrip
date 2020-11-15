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
import review.vo.CommentBean;

public class GetCommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("GetCommentListAction");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		System.out.println(r_num);
		
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		CommentListService commentListService = new CommentListService();
		
		ArrayList<CommentBean> articleList = commentListService.getArticleList(r_num);
		
		JSONArray jsonArray = new JSONArray();
		
		for (int i = 0; i < articleList.size(); i++) {
			
			JSONObject jObject = new JSONObject();
			
			jObject.put("rc_id", articleList.get(i).getRc_id());
			jObject.put("rc_content", articleList.get(i).getRc_content());
			jObject.put("rc_date", articleList.get(i).getRc_date()+"");
			jObject.put("rc_num", articleList.get(i).getRc_num());
//			jObject.put("rc_lev", articleList.get(i).getRc_lev());
//			jObject.put("rc_ref", articleList.get(i).getRc_ref());
//			jObject.put("rc_seq", articleList.get(i).getRc_seq());
			
			jsonArray.add(jObject);
		}
		out.print(jsonArray);
		System.out.println(jsonArray);
		
		return forward;
	}

}
