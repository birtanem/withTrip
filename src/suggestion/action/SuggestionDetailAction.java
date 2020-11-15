package suggestion.action;

import java.sql.Connection;
import java.util.*;

import javax.servlet.http.*;

import static common.db.JdbcUtil.*;
import common.action.*;
import common.vo.*;
import suggestion.dao.SuggestionDAO;
import suggestion.svc.*;
import suggestion.vo.*;

public class SuggestionDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SuggestionDetailAction");	
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");

		ActionForward forward = null;
		
		int su_num = Integer.parseInt(request.getParameter("su_num"));
		
		SuggestionDetailService suggestionDetailService = new SuggestionDetailService();
//		suggestionDetailService.
//		ArrayList<SuggestionBean> articleList = suggestionListService.getArticleList(id);
//		request.setAttribute("articleList", articleList);
		SuggestionBean article = suggestionDetailService.getArticleList(su_num);
		
		request.setAttribute("article", article);
		request.setAttribute("id", id);
		
		if(id.equals("admin")) {
			forward = new ActionForward();
			forward.setPath("/suggestion/adminSuggestion_Detail.jsp");
		} else {
			forward = new ActionForward();
			forward.setPath("/suggestion/suggestion_Detail.jsp");
		}
		return forward;
	}

}
