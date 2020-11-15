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

public class adminSuggestionListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("adminSuggestionListAction");	

		ActionForward forward = null;
		
		SuggestionListService suggestionListService = new SuggestionListService();
		
		int listCount = suggestionListService.getListCount();
		int listCount1 = suggestionListService.getListCount1();
		int listCount2 = suggestionListService.getListCount2();
		System.out.println("전체 게시물 수 : " + listCount); //게시물수 
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		String showStyle = (String)request.getParameter("showStyle");
		System.out.println(showStyle);
		System.out.println(listCount2);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(showStyle == null) {
			showStyle = "전체";
		}
		System.out.println(showStyle);
		if(id == null) {
			out.println("<script>"); 
			out.println("alert('관리자전용 페이지입니다')");
			out.println("history.back()");
			out.println("</script>"); 
		}
		if(id.equals("admin")) {
			
			forward = new ActionForward();
			ArrayList<SuggestionBean> articleList = suggestionListService.adminGetArticleList(showStyle);
			request.setAttribute("articleList", articleList);
			request.setAttribute("showStyle", showStyle);
			request.setAttribute("listCount1", listCount1);
			request.setAttribute("listCount2", listCount2);
			forward.setPath("/suggestion/adminSuggestion_List.jsp");
			
		} else {
			out.println("<script>"); 
			out.println("alert('관리자전용 페이지입니다')");
			out.println("history.back()");
			out.println("</script>"); 
		}
		return forward;
	}

}
