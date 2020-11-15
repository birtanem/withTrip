package member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import member.svc.MemberUpdateProService;
import member.vo.MemberBean;

// 회원정보 업데이트, 비밀번호 업데이트 하나의 action, service 사용 판별은 파라미터 where 로 판별함

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("MemberUpdateProAction");
		
		ActionForward forward =null;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		request.getParameter("where");

		if(request.getParameter("where").equals("1")) { //where = 1(회원정보 수정 페이지에서 넘어오면)  
			
			// 폼에서 입력받은 데이터  MemberBean 저장
			MemberBean memberBean = new MemberBean();
			memberBean.setId(request.getParameter("nowId"));
			memberBean.setEmail(request.getParameter("email"));
			memberBean.setPhone(request.getParameter("phone"));
			memberBean.setType(request.getParameter("type"));

			MemberUpdateProService memberUpdateProService = new MemberUpdateProService();

						boolean UpdateSuccess = memberUpdateProService.UpdateMember(memberBean);
						
						if(!UpdateSuccess) { 
							out.println("<script>"); 
							out.println("alert('정보 업데이트 실패!')");
							out.println("history.back()");
							out.println("</script>"); 
						}else { 
							HttpSession session = request.getSession();
							session.setAttribute("alertOn", "true");
							session.setAttribute("session", request.getParameter("type") );
							forward = new ActionForward();
							forward.setRedirect(true);
							forward.setPath("./MemberMypage.me");
						}
			
			
		} else if(request.getParameter("where").equals("2")) { //where = 2(비밀번호 수정 페이지에서 넘어오면)  
			
			// 폼에서 입력받은 데이터  MemberBean 저장
			MemberBean memberBean = new MemberBean();
			memberBean.setId(request.getParameter("nowId"));
			memberBean.setPass(request.getParameter("pass"));

			MemberUpdateProService memberUpdateProService = new MemberUpdateProService();

						boolean UpdateSuccess = memberUpdateProService.UpdatePass(memberBean);
						
						if(!UpdateSuccess) { 
							out.println("<script>"); 
							out.println("alert('비밀번호 변경 실패!')");
							out.println("history.back()");
							out.println("</script>"); 
						}else { 
							HttpSession session = request.getSession();
							session.setAttribute("alertOn", "pass");
							forward = new ActionForward();
							forward.setRedirect(true);
							forward.setPath("./MemberMypage.me");
						}
			
		} else {
			System.out.println(request.getParameter("where") + "0");
		}
		


        return forward;
	}
}
