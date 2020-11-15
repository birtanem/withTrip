package event.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import event.svc.EventPageService;
import member.vo.MemberBean;



public class EventPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventPageAction");
		
		ActionForward forward = null;
		MemberBean article = null;
		// EventListService 클래스 인스턴스 생성
		EventPageService eventPageService = new EventPageService();
		
		HttpSession session = request.getSession();
				
		String id = (String)session.getAttribute("id");
		
		if(id != null) {
			
			article = eventPageService.getArticle(id);
			request.setAttribute("article", article);
		}
					
							
		Date date = eventPageService.getDate();
	
		
		request.setAttribute("date", date);

			
		forward = new ActionForward();
		
		forward.setPath("/event/event.jsp");
			
		return forward;
	}

}
