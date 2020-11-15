package order.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import common.action.Action;
import common.vo.ActionForward;
import order.svc.OrderListService;
import review.vo.ReviewPageInfo;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderListAction");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		// 페이징
		int page = 1;
		int limit = 5;

		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		OrderListService orderListService = new OrderListService();
		
		int listCount =orderListService.getOrderListCount((String)session.getAttribute("id"));
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		ReviewPageInfo pageInfo = new ReviewPageInfo(page, maxPage, startPage, endPage, listCount);

		
		// 주문 리스트 가져오기	
	
	
		JSONArray jsonArray = orderListService.getOrderList((String)session.getAttribute("id"), page, limit);
		
		System.out.println(jsonArray.size());
		
		request.setAttribute("list", jsonArray);
		request.setAttribute("pageInfo", pageInfo);
		
		forward = new ActionForward();
		
		forward.setPath("/order/order_list.jsp");
		
		return forward;
	}

}
