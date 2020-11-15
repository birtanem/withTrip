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

public class SuggestionReplyProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = null;
		System.out.println("SuggestionReplyProAction");
		
		SuggestionBean suggestionBean = new SuggestionBean();
		suggestionBean.setNum(Integer.parseInt(request.getParameter("num")));
		suggestionBean.setId(request.getParameter("id"));
		suggestionBean.setEmail(request.getParameter("email"));
		suggestionBean.setSubject(request.getParameter("subject"));
		suggestionBean.setContent(request.getParameter("content"));
		//==============email 전송 시작===============================================
		String receiver = suggestionBean.getEmail();
		String sender = "lkj0511kr@gmail.com";
		String subject = "<WithTrip> - 답변";
		String content = suggestionBean.getId() +"님" + "<br>" + "글제목 : < " + suggestionBean.getSubject() + " > 에 대한 답변입니다." + "<br>" + "작성자 이메일 : " + suggestionBean.getEmail() + "<br>" + suggestionBean.getContent();
		
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
		String showStyle = (String)request.getParameter("showStyle");
		if(showStyle == null) {
			showStyle = "전체";
		}
		SuggestionReplyProService  suggestionReplyProService = new SuggestionReplyProService();
		boolean isReplySucces = suggestionReplyProService.updateCheck(suggestionBean);
		
		if (!isReplySucces) {
			response.setContentType("text/html:charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>"); 
			out.println("alert('건의사항 등록 실패!')");
			out.println("history.back()"); 
			out.println("</script>"); 
		}else {
			System.out.println("답변 등록 성공");
			System.out.println(showStyle);
			forward = new ActionForward();
//			forward.setRedirect(true);
			forward.setPath("adminSuggestion_List.su?showStyle="+showStyle+"");
		}
		return forward;
	}
}
