package event.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import event.svc.EventPageService;
import event.svc.EventPullService;

public class EventPullAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("EventPullAction");
		ActionForward forward = null;
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = (String)session.getAttribute("id");
		// {"point", "check"}
		JSONObject obj = new JSONObject();
		
		EventPullService eventPullService = new EventPullService();
		EventPageService eventPageService = new EventPageService();
		
		
		// event_box 체크
		int check = eventPullService.setPullCheck();
		
		
		if(check != 0) { // 뽑기 가능 -> 진행
			
			// 뽑기 시작 전 포인트 차감
			boolean minusPointSuccess = eventPullService.minusPoint(id);
			
			if(!minusPointSuccess) { // 포인트 부족
				
				out.print("0");
			
			}else {
				
				// 차감된 포인트 가져와서 JSON 저장
				int point = eventPageService.getPoint(id);
				obj.put("point", point);
				// 뽑기 시작
				int setPullSuccess = eventPullService.pullEventBox();
				
				if(setPullSuccess == 30001) { // 30000 당첨
					// 쿠폰 추가
					eventPullService.addWinCoupon(3, id);
					// 당첨 리스트 추가
					eventPullService.addWinList(3 ,id);
					// 쿠폰 수 가져오기
					int coupon = eventPullService.getCoupon(id, 3);
					System.out.println(coupon);
					// 쿠폰 수 JSON 저장
					obj.put("coupon", coupon);
					// 당첨값 JSON 저장
					obj.put("check", "30000");
					// ajax 리턴 {"point","check"}	
					out.print(obj);	
					
				}else if(setPullSuccess == 50001){ // 50000 당첨
					eventPullService.addWinCoupon(5, id);
					eventPullService.addWinList(5 ,id);
					int coupon = eventPullService.getCoupon(id, 5);
					System.out.println(coupon);
					obj.put("coupon", coupon);
					obj.put("check", "50000");		
					out.print(obj);
					
				}else if(setPullSuccess == 100001){ // 100000 당첨
					eventPullService.addWinCoupon(10, id);
					eventPullService.addWinList(10 ,id);
					int coupon = eventPullService.getCoupon(id, 10);
					obj.put("coupon", coupon);
					obj.put("check", "100000");
					out.print(obj);
					
				}else if(setPullSuccess == 1) { // 꽝
					obj.put("check", "1");
					out.print(obj);							
				}			
				
			}


			
		}else { // 뽑기불가 -> 종료
			
			boolean isEndSuccess =  eventPullService.setEventEndDate();
			obj.put("check", "0");
			out.print(obj);	
		}
		
		
		return forward;
	}

}
