package suggestion.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.http.*;

import static common.db.JdbcUtil.*;
import common.action.*;
import common.vo.*;
import suggestion.dao.SuggestionDAO;
import suggestion.svc.*;
import suggestion.vo.*;

public class SuggestionInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SuggestionInfoAction");	
		ActionForward forward = null;
			
		
			SuggestionInfoService suggestionInfoService = new SuggestionInfoService();
			int listCount1 = suggestionInfoService.getPlaceCount();
			int listCount2 = suggestionInfoService.getReviewCount();
			int listCount3 = suggestionInfoService.getProductCount();
			int listCount4 = suggestionInfoService.getMemberCount();
			
			request.setAttribute("listCount1", listCount1);
			request.setAttribute("listCount2", listCount2);
			request.setAttribute("listCount3", listCount3);
			request.setAttribute("listCount4", listCount4);
			
			forward = new ActionForward();
			forward.setPath("/suggestion/suggestion_Info.jsp");
		
		return forward;
	}

}
