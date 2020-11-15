package member.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.svc.MemberJoinCheckService;
import member.svc.MemberJoinProService;
import member.vo.MemberBean;


public class MemberJoinCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("MemberJoinCheckAction");
		
		ActionForward forward =null;

		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		System.out.println(id);
		System.out.println(email);
		System.out.println(phone);
		


		// 회원가입 서비스 MemberJoinProService 생성하고
		// 회원가입 처리할 registMember() 메서드 실행
		MemberJoinCheckService memberJoinCheckService = new MemberJoinCheckService();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// dupCheck
		boolean isDuplicateCheck = false;
		
		if(request.getParameter("id") != null) {
			isDuplicateCheck = memberJoinCheckService.duplicateIdCheck(id);
		} 
		
		if (request.getParameter("email") != null) {
			isDuplicateCheck = memberJoinCheckService.duplicateEmailCheck(email);
		} 
		
		if (request.getParameter("phone") != null) {
			isDuplicateCheck = memberJoinCheckService.duplicatePhoneCheck(phone);
		}

		if(!isDuplicateCheck) { //false 인 경우
//			out.println(request.getParameter("id"));
			out.println(0);
		}else { // id 중복 true 인 경우
			out.println(1);						
		}  
		
        return forward;

	}

}
