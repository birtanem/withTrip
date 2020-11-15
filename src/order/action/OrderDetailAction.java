package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import order.svc.OrderDetailActionService;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("OrderDetailAction");
		
		ActionForward forward = null;
		
		OrderDetailActionService orderDetailActionService = new OrderDetailActionService();
		
		JSONArray jsonArray = orderDetailActionService.getOrder(Long.parseLong(request.getParameter("num")));
		
		JSONObject obj = (JSONObject)jsonArray.get(0);
		
		
		request.setAttribute("obj", obj);
		request.setAttribute("list", jsonArray);
		
		forward = new ActionForward();
		
		forward.setPath("/order/order_detail.jsp");
		
		return forward;
	}

}
