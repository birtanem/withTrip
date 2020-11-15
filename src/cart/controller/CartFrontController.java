package cart.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cart.action.ProductCartAddAction;
import cart.action.ProductCartListAction;
import cart.action.ProductCartRemoveAction;
import common.action.Action;
import common.vo.ActionForward;


@WebServlet("*.ca")
public class CartFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST 방식 한글 처리
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("프론트 컨트롤러 도입부");
		
		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println("서블릿 확인 : "+ command);
		
		// 각 요청을 공통적으로 처리하기 위해 필요한 변수 선언
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/ProductCartAdd.ca")) { // 장바구니 추가 서블릿
			// ProductCartAdd 클래스 인스턴스 생성 => Action 타입으로 업캐스팅
			action = new ProductCartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ProductCartList.ca")) { // 장바구니 목록 서블릿
			action = new ProductCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ProductCartRemove.ca")) { // 장바구니 삭제
			action = new ProductCartRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		// 포워딩
		if(forward != null) {
		// ActionForward 객체 내의 포워딩 방식 (Dispatcher or Redirect) 판별
		if(forward.isRedirect()) { // Redirect 방식
			// Redirect 방식으로 포워딩 (주소표시줄 변경 O, request 객체가 공유되지 않음)
			// => response 객체의 sendRedirect() 메서드를 호출하여 포워딩 할 페이지 전달
			response.sendRedirect(forward.getPath());
		} else { // Dispatcher 방식
			// Dispatcher 방식으로 포워딩(주소표시줄 변경 X, request 객체가 공유됨)
			// => request 객체의 getRequestDispatcher() 메서드를 호출하여 포워딩 할 페이지 전달
			// => RequestDispatcher 객체가 리턴됨
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
