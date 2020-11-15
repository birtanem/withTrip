package order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.action.ProductCartAddAction;
import cart.action.ProductCartListAction;
import cart.action.ProductCartRemoveAction;
import common.action.Action;
import common.vo.ActionForward;
import order.action.OrderAddAction;
import order.action.OrderAuthenticationAction;
import order.action.OrderDetailAction;
import order.action.OrderFrontAction;
import order.action.OrderListAction;
import order.action.OrderListSearchAction;


/**
 * Servlet implementation class OrderFrontController
 */
@WebServlet("*.or")
public class OrderFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("OrderFrontController - 도입부");
		
		// 서블릿 주소 가져오기
		String command = request.getServletPath();
		System.out.println("서블릿 확인 : "+ command);
		
		// 각 요청을 공통적으로 처리하기 위해 필요한 변수 선언
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/orderFront.or")) { // 주문페이지 시작
			action = new OrderFrontAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/orderAdd.or")) { // 주문 추가
			action = new OrderAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/orderForm.or")) { // 주문 추가
				forward = new ActionForward();
				// 요청한 클라이언트의 세션 영역 객체 가져오기
				HttpSession session = request.getSession();
				String id = (String)session.getAttribute("id"); 
				// id가 없으면 login 페이지로 돌아가기
				if(id == null) {
					forward.setRedirect(true);
					forward.setPath("MemberLoginForm.me");
				}else {
				forward.setPath("/order/order_buy.jsp");}
		}else if(command.equals("/orderResult.or")) { // 주문 추가
			forward = new ActionForward();
			forward.setPath("/order/order_result.jsp");

		}else if(command.equals("/orderList.or")) { // 주문 추가
			action = new OrderListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/orderDetail.or")) { // 주문 추가
			action = new OrderDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/orderListSearch.or")) { // 주문 추가
			action = new OrderListSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/orderAuthentication.or")) { // 주문 추가
			action = new OrderAuthenticationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if(command.equals("/orderEmailCheck.or")) { // 주문 추가
			forward = new ActionForward();
			forward.setPath("/order/email_check.jsp");


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
