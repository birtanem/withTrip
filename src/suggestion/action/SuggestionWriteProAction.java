package suggestion.action;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import suggestion.action.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.*;
import suggestion.svc.*;
import suggestion.vo.*;
import common.vo.*;

public class SuggestionWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		System.out.println("SuggestionSendEmailAction");
		
		SuggestionBean suggestionBean = new SuggestionBean();
		suggestionBean.setId(request.getParameter("id"));
		suggestionBean.setEmail(request.getParameter("email"));
		suggestionBean.setSubject(request.getParameter("subject"));
		suggestionBean.setContent(request.getParameter("content"));
		
		//==============email 전송 시작===============================================		
		String receiver = "lkj0511kr@gmail.com";
		String sender = "lkj0511kr@gmail.com";
		String subject = suggestionBean.getSubject() + "<건의사항>";
		String content = "작성자 아이디 : " + suggestionBean.getId() + "<br>" + "작성자 이메일 : " + suggestionBean.getEmail() + "<br>" + suggestionBean.getContent();
		
		try {
			Properties properties = System.getProperties();
			properties.put("mail.smtp.starttls.enable", "true"); // gmail은 무조건 true 고정
			properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp 서버 주소
			properties.put("mail.smtp.auth", "true"); // gmail은 무조건 true 고정
			properties.put("mail.smtp.port", "587"); // gmail 포트
			Authenticator auth = new GoogleAuthentication();
			Session s = Session.getDefaultInstance(properties, auth);
			//Session s = Session.getdefultInstance(properties, auth);
			Message message = new MimeMessage(s);
			Address sender_address = new InternetAddress(sender);
			Address receiver_address = new InternetAddress(receiver);
			message.setHeader("content-type", "text/html;charset=UTF-8");
			message.setFrom(sender_address);
			message.addRecipient(Message.RecipientType.TO, receiver_address);
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=UTF-8");
			message.setSentDate(new java.util.Date());
			Transport.send(message);	
		} catch (Exception e) {
//			out.println("SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다. 또는 잘못된 형식의 이메일입니다.");
			e.printStackTrace();
		}
		//==============email 전송 끝===============================================
		SuggestionWriteProService suggestionWriteProService = new SuggestionWriteProService();
		boolean isWriteSucces = suggestionWriteProService.registSuggestion(suggestionBean);//수정필요22222222222222
		
		if (!isWriteSucces) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('건의사항 등록 실패!')");
			out.println("history.back()");
			out.println("</script>"); 
		} else {
			System.out.println("건의사항 등록 성공");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String resultMsg = ""; 
			resultMsg += "<script type='text/javascript'>"; 
			resultMsg += "alert('건의사항 등록 완료!');";	
			resultMsg += "</script>"; 
			out.print(resultMsg);
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("Suggestion_Menu.su");
		}
		
		return forward;
	}
}
