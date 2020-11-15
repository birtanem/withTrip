package place.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import common.action.Action;
import common.vo.ActionForward;
import place.action.ImageCallbackAction;
import place.action.PCDeleteProAction;
import place.action.PCWriteProAction;
import place.action.PlaceDeleteProAction;
import place.action.PlaceDetailAction;
import place.action.PlaceListAction;
import place.action.PlaceSearchAction;
import place.action.PlaceUpdateFormAction;
import place.action.PlaceUpdateProAction;
import place.action.PlaceWriteProAction;



@WebServlet("*.pl") // 서블릿 주소 중 XXX.pl 주소에 대한 요청을 전달받음
public class PlaceFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식 한글 처리
		request.setCharacterEncoding("UTF-8");


		String command = request.getServletPath();
		System.out.println(command);
		

		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/PlaceWriteForm.pl")) {
			forward = new ActionForward();
			forward.setPath("/place/place_write.jsp"); 
			
		} else if(command.equals("/PlaceWritePro.pl")) {
			// BoardWriteProAction 클래스 인스턴스 생성 => Action 타입으로 업캐스팅
			action = new PlaceWriteProAction();
			// 공통 메서드인 execute() 메서드를 호출하여 request, response 객체 전달
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/PlaceList.pl")) {
			// 글 목록 조회를 위해 BoardListAction 클래스 인스턴스 생성 => Action 타입 업캐스팅
			action = new PlaceListAction();
			// 공통 메서드인 execute() 메서드를 호출하여 request, response 객체 전달
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		} else if(command.equals("/PlaceDetail.pl")) {
			// 글 상세내용 표시를 위해 BoardDetailAction 클래스 인스턴스 생성 => Action 타입 업캐스팅
			action = new PlaceDetailAction();
			// 공통 메서드인 execute() 메서드를 호출하여 request, response 객체 전달
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 else if(command.equals("/PlaceDeletePro.pl")) {
			// 글 삭제를 위해 BoardDeleteProAction 클래스 인스턴스 생성
			action = new PlaceDeleteProAction();
			// 공통 메서드인 execute() 메서드를 호출하여 request, response 객체 전달
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		
		 else if(command.equals("/PC_WritePro.pl")) {
				action = new PCWriteProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			 }
		
		 else if (command.equals("/ImageCallback.pl")) {
				action = new ImageCallbackAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
		 else if (command.equals("/PlaceUpdateForm.pl")) {
				action = new PlaceUpdateFormAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
		 else if (command.equals("/PlaceUpdatePro.pl")) {
				action = new PlaceUpdateProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 else if (command.equals("/PlaceSearch.pl")) {
				action = new PlaceSearchAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 else if (command.equals("/PC_DeletePro.pl")) {
				action = new PCDeleteProAction();
				
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
