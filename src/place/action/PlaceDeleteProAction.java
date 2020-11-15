package place.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PlaceDeleteProService;


public class PlaceDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("BoardDeleteProAction");
		
		ActionForward forward = null;
		
		// 전달받은 게시물 번호와 패스워드를 사용하여 적합한 사용자인지 판별
		int pl_num = Integer.parseInt(request.getParameter("pl_num"));
		
		// BoardDeleteProService 클래스의 인스턴스 생성
		PlaceDeleteProService boardDeleteProService = new PlaceDeleteProService();
		
			boolean isDeleteSuccess = boardDeleteProService.removeArticle(pl_num);
			
			// 만약, isDeleteSuccess 가 false 일 경우
			// 자바스크립트를 사용하여 "삭제 실패!" 출력 후 이전페이지로 돌아가기
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				// 아니면 ActionForward 객체를 생성하여 BoardList.bo 서블릿 주소 지정(Redirect)
				// => 포워딩 시 URL 주소 뒤에 page 파라미터 결합하여 전달
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("PlaceList.pl?page=" + request.getParameter("page"));
			}
			
		
		
		return forward;
	}

}
