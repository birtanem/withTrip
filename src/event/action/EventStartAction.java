package event.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import event.svc.EventPageService;
import event.svc.EventStartService;


public class EventStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventStartAction");
		
		ActionForward forward = null;
		
		String date = request.getParameter("date");
	
		// EventListService 클래스 인스턴스 생성
		EventStartService eventStartService = new EventStartService();

			boolean isSetDate = eventStartService.setEventSatrtDate(date);
			
			if(!isSetDate) {
				
				response.setContentType("text/html;charset=UTF-8");
				// 2. response 객체의 getWriter() 메서드를 호출하여
				//    출력스트림 객체(PrintWriter)를 리턴받음
				PrintWriter out = response.getWriter();
				// 3. PrintWriter 객체의 println() 메서드를 호출하여
				//    웹에서 수행할 작업(자바스크립트 출력 등)을 기술
				out.print("0"); // 자바스크립트 시작 태그	
				
			}else {
				
				
//				forward = new ActionForward();
//				
//				forward.setPath("/event.jsp");
//				
//			
				
			}

		
		return forward;
	}

}
