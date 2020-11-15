package common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.action.AdminLogAction;
import admin.action.AdminEventListAction;
import admin.action.AdminEventWinListAction;
import admin.action.AdminProductAction;
import common.action.Action;
import common.vo.ActionForward;


@WebServlet("*.ad")
public class AdminFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		System.out.println("서블릿 확인:"+command);
		Action action =null;
		ActionForward forward=null;
		
		if(command.equals("/adminPage.ad")) {
			forward=new ActionForward();
			forward.setPath("/admin/adminPage.jsp");
			
		}else if(command.equals("/adminProduct.ad")) {
			try {
				action=new AdminProductAction();
				
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/adminEvent.ad")) {
			try {
				action=new AdminEventWinListAction();
				
				forward = action.execute(request, response);
				
				action=new AdminEventListAction();
				
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/adminLog.ad")) {
			try {
				action=new AdminLogAction();
				
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward !=null ) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
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
