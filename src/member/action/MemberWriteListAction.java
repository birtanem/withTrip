package member.action;

import java.util.*;

import javax.servlet.http.*;

import common.action.*;
import common.vo.*;
import member.svc.MemberWriteListService;
import member.vo.MemberPageInfo;
import member.svc.*;
import review.vo.*;

public class MemberWriteListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("MemberWriteListAction");	
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int page = 1;
		int limit = 10;

		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		MemberWriteListService memberWriteListService = new MemberWriteListService();
		
		int listCount = memberWriteListService.getListCount(id);
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		MemberPageInfo pageinfo = new MemberPageInfo(page, maxPage, startPage, endPage, listCount);

		
// ------------------------------------------------------------------------------------------------------------
		
			ArrayList<ReviewBean> articleList = memberWriteListService.getArticleList(page, limit, id);

			request.setAttribute("pageinfo", pageinfo);
			request.setAttribute("articleList", articleList);

		
		forward = new ActionForward();
		forward.setPath("/member/member_WriteList.jsp");
		
		System.out.println("1");
		return forward;
	}

}
