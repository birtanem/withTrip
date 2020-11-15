package admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import admin.svc.AdminLogActionService;
import common.action.Action;
import common.vo.ActionForward;

public class AdminLogAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminLogAction");
		ActionForward forward = null;
		
		AdminLogActionService adminLogActionService = new AdminLogActionService();
		
		int totalJoinCount = adminLogActionService.geTtotalJoinCount();		
		int totalReadCount = adminLogActionService.getTotalReadCount();		
		long totalRevenue = adminLogActionService.getTotalRevenue();
		
		JSONArray jsonArray = adminLogActionService.getDailyLog();
		
		int[] typeArr = adminLogActionService.getTypeCount();
		
		for(int i=0;i<typeArr.length;i++) {
			System.out.println(typeArr[i]);
		}

		
		JSONObject totalCount = new JSONObject();
				
		System.out.println(totalJoinCount+", "+totalReadCount+", "+totalRevenue);
		
		totalCount.put("joinCount", totalJoinCount);
		totalCount.put("readCount", totalReadCount);
		totalCount.put("revenue", totalRevenue);
		
		
		request.setAttribute("type", typeArr);
		request.setAttribute("list", jsonArray);
		request.setAttribute("total", totalCount);
		
		forward = new ActionForward();
		
		forward.setPath("/admin/adminLog.jsp");
		
			
		return forward;
	}

}
