package member.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.servlet.http.*;

import static common.db.JdbcUtil.*;
import common.action.*;
import common.vo.*;
import member.svc.MemberMypageFormService;
import member.svc.MemberUpdateFormService;
import member.vo.MemberBean;
import suggestion.dao.SuggestionDAO;
import suggestion.svc.*;
import suggestion.vo.*;

public class MemberUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberMypageFormAction");	
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(request.getParameter("where").equals("1")) {
			System.out.println(request.getParameter("where").equals("1"));
			if(id == null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>"); 
				out.println("alert('로그인이 필요한 페이지입니다!')");
				out.println("history.back()");
				out.println("</script>"); 
				
			} else {
				MemberUpdateFormService memberUpdateFormService = new MemberUpdateFormService();
				
				MemberBean memberInfo = memberUpdateFormService.getMemberInfo(id);
				
				request.setAttribute("memberInfo", memberInfo);
				
				forward = new ActionForward();
				forward.setPath("/member/member_UpdateForm.jsp");
			}
		} else if(request.getParameter("where").equals("2")) {
				System.out.println(request.getParameter("where").equals("2"));
				if(id == null) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>"); 
					out.println("alert('로그인이 필요한 페이지입니다!')");
					out.println("history.back()");
					out.println("</script>"); 
					
				} else {
					MemberUpdateFormService memberUpdateFormService = new MemberUpdateFormService();
					
					MemberBean memberInfo = memberUpdateFormService.getMemberInfo(id);
					
					request.setAttribute("memberInfo", memberInfo);
					
					forward = new ActionForward();
					forward.setPath("/member/member_UpdatePassForm.jsp");
				}
				
		} else {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('접근오류!')");
			out.println("history.back()");
			out.println("</script>"); 
		}
		
		return forward;
	}

}
