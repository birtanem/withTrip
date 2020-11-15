package event.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import event.action.EventChangeListAction;
import event.action.EventEndAction;
import event.action.EventExchangeAction;
import event.action.EventPageAction;
import event.action.EventPullAction;
import event.action.EventStartAction;

@WebServlet("*.ev") // 서블릿 주소 중 XXX.bo 주소에 대한 요청을 전달받음
public class EventFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식 한글 처리
		request.setCharacterEncoding("UTF-8");
		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println(command);
		// 각 요청을 공통적으로 처리하기 위해 필요한 변수 선언
		Action action = null;
		ActionForward forward = null;
		// if문을 사용하여 서블릿 주소 판별 및 각 요청 처리를 위한 작업 요청
		
		if(command.equals("/event.ev")) { // 이벤트 페이지 접속
			
			action = new EventPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/eventStart.ev")) { // 관리자 이벤트 시작

			action = new EventStartAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/eventEnd.ev")) { // 관리자 이벤트 종료

			action = new EventEndAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if(command.equals("/eventAdminPop.ev")) {
			
			forward = new ActionForward();	
			forward.setPath("/event/eventAdminPop.jsp");


		}else if(command.equals("/eventPull.ev")) {

			action = new EventPullAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/eventExchangePoint.ev")) {

			action = new EventExchangeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/eventChangeList.ev")) {

			action = new EventChangeListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
			
			
		// ActionForward 객체 내의 포워딩 방식에 따라 각각의 포워딩 작업 수행
		if(forward != null) {
			// ActionForward 객체 내의 포워딩 방식(Dispatcher or Redirect) 판별
			if(forward.isRedirect()) { // Redirect 방식
				// Redirect 방식으로 포워딩(주소표시줄 변경 O, request 객체가 공유되지 않음)
				// => response 객체의 sendRedirect() 메서드를 호출하여 포워딩 할 페이지 전달
				response.sendRedirect(forward.getPath());
			} else { // Dispatcher 방식
				// Dispatcher 방식으로 포워딩(주소표시줄 변경 X, request 객체가 공유됨)
				// => request 객체의 getRequestDispatcher() 메서드를 호출하여 포워딩 할 페이지 전달
				//    => RequestDispatcher 객체가 리턴됨
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				// => RequestDispatcher 객체의 forward() 메서드를 호출하여 request, response 객체 전달
				dispatcher.forward(request, response);
			}
		}	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}