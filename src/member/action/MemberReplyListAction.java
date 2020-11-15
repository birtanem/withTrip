package member.action;

import java.util.*;

import javax.servlet.http.*;

import common.action.*;
import common.vo.*;
import member.svc.MemberWriteListService;
import member.vo.MemberPageInfo;
import member.svc.*;
import review.vo.*;

public class MemberReplyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("MemberReplyListAction");	
		
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

		
// ------------------------------------------------------------------------------------------------------------
			
		//replyBean 을 하나 만들어서 사용해야하지만 그냥 reviewBean 에 값 담음 필요하면 만들어야뎀
			ArrayList<ReviewBean> articleList = memberReplyListService.getReplyList(page, limit, id);
			
			request.setAttribute("pageinfo", pageinfo);
			request.setAttribute("articleList", articleList);

		
		forward = new ActionForward();
		forward.setPath("/member/member_ReplyList.jsp");
		
		System.out.println("1");
		return forward;
	}

}
