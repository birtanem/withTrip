package member.action;

import java.io.PrintWriter;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import member.svc.MemberLoginProService;


public class MemberLogoutProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		//메인페이지로 포워딩
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<script>");
//		out.println("alert('로그아웃 완료!");
//		out.println("</script>");             ->>>>>>>>>script alert 출력 안먹힘 왜인지모름 일단 skip
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("main.co");
	
		return forward;     
	}
}
