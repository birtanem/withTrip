package place.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PlaceDetailService;
import place.vo.PlaceBean;

public class PlaceUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 파라미터로 전달된 게시물 번호(board_num) 가져오기
		int pl_num = Integer.parseInt(request.getParameter("pl_num"));
		
		// BoardDetailService 인스턴스 생성 후 getArticle() 메서드 호출하여 상세내용 가져오기
		// => 파라미터 : 게시물 번호(board_num), 리턴타입 : BoardBean(article)
		PlaceDetailService placeDetailService = new PlaceDetailService();
		PlaceBean article = placeDetailService.getArticle(pl_num);
		request.setAttribute("article", article);
		
		
		forward = new ActionForward();
		
		forward.setPath("/place/place_modify.jsp");
		return forward;
	}

}
