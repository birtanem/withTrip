package suggestion.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.*;
import common.vo.*;
import suggestion.action.SuggestionDetailAction;
import suggestion.action.SuggestionInfoAction;
import suggestion.action.SuggestionListAction;
import suggestion.action.SuggestionReplyFormAction;
import suggestion.action.SuggestionReplyProAction;
import suggestion.action.SuggestionWriteFormAction;
import suggestion.action.SuggestionWriteProAction;
import suggestion.action.adminSuggestionListAction;

@WebServlet("*.su")
public class SuggestionFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		System.out.println(command);
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/Suggestion_WriteForm.su")) {
			
			action = new SuggestionWriteFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
//-----------------------------------------------------------------------------------------------------------------			
		} else if(command.equals("/Suggestion_Menu.su")) {
			
			forward = new ActionForward();
			forward.setPath("/suggestion/suggestion_Menu.jsp"); // 이동할 view 페이지 경로 지정
			
		} else if(command.equals("/Suggestion_WritePro.su")) {
			
			action = new SuggestionWriteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Suggestion_List.su")) {
			
			action = new SuggestionListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Suggestion_Detail.su")) {
			
			action = new SuggestionDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Suggestion_ReplyForm.su")) {
			
			action = new SuggestionReplyFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/Suggestion_ReplyPro.su")) {
			
			action = new SuggestionReplyProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/adminSuggestion_List.su")) {
			
			action = new adminSuggestionListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}  else if(command.equals("/Suggestion_Info.su")) {
			
			action = new SuggestionInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		if (forward != null) {
			
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
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
