package order.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import order.svc.OrderListSearchService;
import order.svc.OrderListService;
import order.vo.OrderBean;
import review.vo.ReviewPageInfo;

public class OrderListSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderListSearchAction");
		ActionForward forward = null;
		

		
		
		
		
		HttpSession session = request.getSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance( );
		
		Calendar cal2 = Calendar.getInstance( );
		
		int betweenDay = Integer.parseInt(request.getParameter("betweenDay"));
		int betweenDay2 = Integer.parseInt(request.getParameter("betweenDay2"));
		
		cal.add (Calendar.DAY_OF_MONTH, - betweenDay); // 이전 일
		
		String day = sdf.format(cal.getTime());
		
		System.out.println(day);
		
		cal2.add (Calendar.DAY_OF_MONTH, - betweenDay2); // 이전 일
		
		String day2 = sdf.format(cal2.getTime());
		
		System.out.println(day2);
		
		// 페이징
		int page = 1;
		int limit = 5;

		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		OrderListSearchService orderListSearchService = new OrderListSearchService();
		
		int listCount =orderListSearchService.getOrderSearchListCount((String)session.getAttribute("id"), day, day2);
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		ReviewPageInfo pageInfo = new ReviewPageInfo(page, maxPage, startPage, endPage, listCount);
		
		JSONArray jsonArray = orderListSearchService.getOrderSearchList((String)session.getAttribute("id"), day, day2, page, limit);
		
		JSONObject obj = new JSONObject();
		
		obj.put("page", pageInfo.getPage());
		obj.put("maxPage", pageInfo.getMaxPage());
		obj.put("startPage", pageInfo.getStartPage());
		obj.put("endPage", pageInfo.getEndPage());
		obj.put("listCount", pageInfo.getListCount());
		
		jsonArray.add(obj);
		
		System.out.println("사이즈:"+jsonArray.size());


		System.out.println(jsonArray);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonArray);
		
		return forward;
	}

}
