package order.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.action.MailServlet;
import common.action.SHA256;
import common.vo.ActionForward;

public class OrderAuthenticationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderAuthenficationAction");
		ActionForward forward = null;

		
		String host = "http://192.168.1.10:8080/withTrip/";

		String receiver = request.getParameter("email");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("email", receiver);
		
		String code = SHA256.getEncrypt(receiver);

		String subject = "withTrip 이메일 인증 메일입니다.";
		String content = "다음 링크에 접속하여 이메일 인증을 진행해주세요. " 
		        + "<a href='" + host + "orderEmailCheck.or?code=" + code
				+ "'>이메일 인증하기</a>";

		String[] mailArr = {receiver, subject, content};

		MailServlet.main(mailArr);
		
		
		return forward;
	}

}
