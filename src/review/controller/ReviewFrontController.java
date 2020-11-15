package review.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.*;
import common.vo.*;
import place.action.ImageCallbackAction;
import review.action.*;

@WebServlet("*.re")
public class ReviewFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String command = request.getServletPath();
		
		System.out.println(command);
		
		Action action = null;
		
		ActionForward forward = null;
		
		if (command.equals("/Review_List.re")) {
			
			action = new ReviewListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Review_WriteForm.re")) {
			
			forward = new ActionForward();
			
			forward.setPath("/review/review_Write.jsp");
			
		}else if(command.equals("/Review_WritePro.re")) {
			
			action = new ReviewWriteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/ImageCallback.re")) {
			action = new review.action.ImageCallbackAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Review_Content.re")) {
			
			action = new ReviewContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Review_UpdateForm.re")) {
			
			action = new ReviewUpdateFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Review_UpdatePro.re")) {
			
			action = new ReviewUpdateProAction();
			
			try {
				forward  = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Review_DeleteForm.re")) {
			
			action = new ReviewDeleteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Comment_WriteForm.re")){
			
			forward = new ActionForward();
			
			forward.setPath("/review/commentForm.jsp");
			
		}else if(command.equals("/Comment_WritePro.re")) {
			
			action = new CommentWriteProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Review_ContentLike.re")) {
			
			action = new ReviewLikeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Comment_ReplyForm.re")) {
			
			action = new CommentReplyFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/Comment_ReplyPro.re")) {
			
			action = new CommentReplyProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/Comment_UpdateForm.re")) {
			
			action = new CommentUpdateFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/Comment_UpdatePro.re")) {
			
			action = new CommentUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if (command.equals("/Comment_Delete.re")) {
			
			action = new CommentDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/GetCommentList.re")) {
			
			action = new GetCommentListAction();
			
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
