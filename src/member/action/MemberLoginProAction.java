package member.action;

import java.io.PrintWriter;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import member.svc.MemberLoginProService;
import member.svc.MemberMypageFormService;
import member.vo.MemberBean;


public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
			System.out.println("MemberLoginProAction");
					
			ActionForward forward = null;
					
			 // 아이디와 비밀번호를 가져온다.
	        String id = request.getParameter("id");
	        String pass = request.getParameter("pass");
	        
	        System.out.println(id+pass);
	        
	        //---------메인페이지를 위한 세션 작업
	        MemberMypageFormService info=new MemberMypageFormService();
	        MemberBean mb=info.getMemberInfo(id);
	        
	        
	        
	        // MemberLoginProService 클래스 인스턴스 생성 
	        MemberLoginProService mlps = new MemberLoginProService();
	        

	        	
				int isMember = mlps.isLoginMember(id, pass);
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				// LoginException 예외가 발생하지 않으면 로그인 성공 처리
				// ajax =>  출력된 값을 리턴으로 가져감
				
				if(isMember == 1) {
					
					// 세션 객체를 사용하여 로그인에 성공한 아이디를 저장
					// => request 객체로부터 HttpSession 객체 가져와서 setAttribute() 호출하여 저장
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("session", mb.getType());
					out.println("1");
				}else if(isMember == -1) {					
					// 비밀번호 틀림
					out.println("-1");
					
				}else {					
					// 아이디 없음
					out.println("0");				
				}
				
				
			
				// LoginException 예외가 발생하여 전달되면 catch 구문 실행됨
				// => 전달받은 예외 객체의 메세지를 자바스크립트로 출력하면
				//    로그인 실패에 대한 결과 메세지 출력 구분 가능

	        return forward;      
	}
}
