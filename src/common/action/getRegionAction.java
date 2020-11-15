package common.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.svc.getRegionService;
import common.vo.ActionForward;
import common.vo.regionCode;

public class getRegionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		System.out.println("rac");
		
		JSONArray jsonArray=new JSONArray();
		ArrayList<regionCode> regionarray=getRegionService.getRegion();
		
		for(int i=0; i < regionarray.size(); i++) {
			JSONObject obj=new JSONObject();
			obj.put("rCode", regionarray.get(i).getRegion_code());
			obj.put("rName", regionarray.get(i).getRegion_name());
			jsonArray.add(obj);
		}
		response.setContentType("text/html;charSet=UTF-8");
		PrintWriter out=response.getWriter();
		out.println(jsonArray);
		return forward;
	}

}
