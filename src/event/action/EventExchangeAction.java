package event.action;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import event.svc.EventExchangeService;


public class EventExchangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventExchangeAction");
		ActionForward forward = null;
		
		EventExchangeService eventExchangeService = new EventExchangeService();
		
		HttpSession session = request.getSession();
	
		int point = Integer.parseInt(request.getParameter("point"));

			
			String id = (String)session.getAttribute("id");
			boolean isExchageSuccess =  eventExchangeService.exchangePoint(point, id);
			
			if(!isExchageSuccess) {
				
		
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('교환이 실패하였습니다!')");
				out.println("history.back()");
				out.println("</script>");
				
				
			}else {

				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('"+point*10000+"P 교환되었습니다!')");
				out.println("location.href='event.ev'");
				out.println("</script>");
			}		

		return forward;
	}

}
