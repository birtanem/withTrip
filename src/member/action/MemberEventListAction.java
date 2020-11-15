package member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.svc.AdminEventWinListService;
import common.action.Action;
import common.vo.ActionForward;
import event.vo.EventWinBean;
import member.svc.MemberReplyListService;
import member.vo.MemberPageInfo;

public class MemberEventListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberEventListAction");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int page = 1;
		int limit = 10;

		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		MemberReplyListService memberReplyListService = new MemberReplyListService();
		
		int listCount = memberReplyListService.getListCount(id);
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		MemberPageInfo pageinfo = new MemberPageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageinfo", pageinfo);
// ------------------------------------------------------------------------------------------------------------
		
		AdminEventWinListService adminEventWinListService = new AdminEventWinListService();
		
		ArrayList<EventWinBean> eventWinList =  adminEventWinListService.getWinArticleList();

		request.setAttribute("eventWinList", eventWinList);
		
		forward = new ActionForward();
		forward.setPath("/member/member_EventList.jsp");
		
		return forward;
	}

}
