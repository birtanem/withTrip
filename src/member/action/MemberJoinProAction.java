package member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import member.svc.MemberJoinProService;
import member.vo.MemberBean;


public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("MemberJoinProAction");
		
		ActionForward forward =null;
		    	
		// 폼에서 입력받은 데이터  MemberBean 저장
		MemberBean memberBean = new MemberBean();

		
		memberBean.setId(request.getParameter("id"));
		memberBean.setPass(request.getParameter("pass"));
		memberBean.setName(request.getParameter("name"));
		memberBean.setAge(Integer.parseInt(request.getParameter("age")));
		memberBean.setGender(request.getParameter("gender"));
		memberBean.setEmail(request.getParameter("email"));
		memberBean.setPhone(request.getParameter("phone"));
		memberBean.setType(request.getParameter("type"));

		
		// 회원가입 서비스 MemberJoinProService 생성하고
		// 회원가입 처리할 registMember() 메서드 실행
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

					boolean isJoinSuccess = memberJoinProService.registMember(memberBean);
					
					if(!isJoinSuccess) { // 회원가입 실패
						out.println("<script>"); 
						out.println("alert('회원가입 실패!')");
						out.println("history.back()");
						out.println("</script>"); 
					}else { // 회원가입 성공
						
						// 회원가입 성공하면 바로 로그인처리
						
						//loginAcion은 loginForm <-> ajax 로 반응하기 때문에  joinAction 에서 바로 날리면 페이지 이동이 안뎀.
//						request.setAttribute("id", request.getParameter("id"));
//						request.setAttribute("pass", request.getParameter("passwd"));
//						forward = new ActionForward();
////						forward.setRedirect(true);
//						forward.setPath("MemberLoginPro.me");
						
//						//바로 세션값주고 로그인시킴######## 회원가입 완료라는 창을 띄우기 위해 자동 로그인 해제함
//						HttpSession session = request.getSession();
//						session.setAttribute("id", request.getParameter("id"));
//						forward = new ActionForward();
//						forward.setPath("./");
						
						//alert 창 띄우기위해 세션값 주고 로그인 페이지로 전송
						HttpSession session = request.getSession();
						session.setAttribute("alertOn", "true");
						forward = new ActionForward();
						forward.setPath("./MemberLoginForm.me");
						
					}
					
					

        return forward;

	}

}
