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

public class SuggestionReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SuggestionReplyFormAction");	

		ActionForward forward = null;
		
		int su_num = Integer.parseInt(request.getParameter("su_num"));

		SuggestionDetailService suggestionDetailService = new SuggestionDetailService();
		SuggestionBean article = suggestionDetailService.getArticleList(su_num);
		request.setAttribute("article", article);

		forward = new ActionForward();
		forward.setPath("/suggestion/suggestion_Reply.jsp");
		
		return forward;
	}

}
