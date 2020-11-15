package member.action;

import java.util.*;

import javax.servlet.http.*;

import common.action.*;
import common.vo.*;
import member.svc.MemberWriteListService;
import member.vo.MemberBean;
import member.vo.MemberPageInfo;
import member.svc.*;
import review.vo.*;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("MemberListAction");	
		
		int page = 1;
		int limit = 10;

		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		MemberListService memberListService = new MemberListService();
		
		int listCount = memberListService.memberListCount();
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		

		
// ------------------------------------------------------------------------------------------------------------
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();	
		String type = null;
		boolean idSearch = false;
		boolean pointChange = false;
		int cPoint = 0;
		String adminPass = null;
		
		if(request.getParameter("search") != null) {
			if(request.getParameter("search").equals("true")) {
				idSearch = true;
				type = request.getParameter("type");
				maxPage = 1;
				startPage = 1;
				endPage = 1;
			}
		} else {
			if(request.getParameter("type") == null) {
				type = "id ASC";
			} else {
				type = request.getParameter("type");
			}
		}
		
		memberList = memberListService.getMemberList(page, limit, type, idSearch, pointChange, cPoint, adminPass);
		forward = new ActionForward();
		forward.setPath("/member/member_MemberList.jsp?type="+type);

		MemberPageInfo pageinfo = new MemberPageInfo(page, maxPage, startPage, endPage, listCount); //멤버페이지 정보 저장 #아이디 검색시 페이지 정보들 변경해야해서 여기 위치해야함 자리변경 X	
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("articleList", memberList);
		
		System.out.println("1");
		return forward;
	}

}
