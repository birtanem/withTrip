package member.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.http.*;

import static common.db.JdbcUtil.*;
import common.action.*;
import common.vo.*;
import member.svc.MemberMypageFormService;
import member.vo.MemberBean;
import suggestion.dao.SuggestionDAO;
import suggestion.svc.*;
import suggestion.vo.*;

public class MemberMypageFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberMypageFormAction");	
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('로그인이 필요한 페이지입니다!')");
			out.println("history.back()");
			out.println("</script>"); 
			
		} else {
			MemberMypageFormService memberMypageFormService = new MemberMypageFormService();
			
			MemberBean memberInfo = memberMypageFormService.getMemberInfo(id);
			
			request.setAttribute("memberInfo", memberInfo);
			
			forward = new ActionForward();
			forward.setPath("/member/member_Mypage.jsp");
		}
		
		return forward;
	}

}
