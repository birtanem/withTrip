package order.action;



import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



import common.action.Action;
import common.vo.ActionForward;
import order.svc.OrderAddService;
import order.vo.OrderBean;



public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderAddAction");
		//한글처리
		request.setCharacterEncoding("utf-8");
		// 리턴 잊지 않도록 미리 선언 해주기
		ActionForward forward = null;
		// insert 성공 여부 판별 변수선언
		
	 
		
		
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray)parser.parse(request.getParameter("jsonData"));
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd");
		
		Date date = new Date();
		
		String strDate = sdf.format(date);
		
		HttpSession session = request.getSession();
		
		OrderBean ob = new OrderBean();
		
		ob.setMember_id((String)session.getAttribute("id"));
		ob.setO_price(Integer.parseInt(request.getParameter("total")));
		ob.setO_pay(request.getParameter("pay"));
		ob.setO_num(Integer.parseInt(strDate));
		ob.setO_point(Integer.parseInt(request.getParameter("point")));
						
		OrderAddService orderAddService = new OrderAddService();

		String orderNum = orderAddService.insertOrderList(ob, jsonArray);
		
		if(orderNum.contains("문제") || orderNum.contains("품절")) {
			System.out.println("여기냐설마");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(orderNum);
			
		}else {
						System.out.println("넘와와라");
			session.setAttribute("orderNum", orderNum);
			session.setAttribute("ob", ob);
		}
		

		return forward;
	}

}
