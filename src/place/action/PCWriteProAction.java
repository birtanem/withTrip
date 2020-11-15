package place.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PCWiteProService;
import place.vo.PlaceCommentBean;

public class PCWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PCWriteProAction");
		ActionForward forward = null;
		int pl_num=Integer.parseInt(request.getParameter("pl_num"));
		int nowPage=Integer.parseInt(request.getParameter("page"));
		String id = request.getParameter("id");
		String pc_content = request.getParameter("pc_content");
		int pc_rank=Integer.parseInt(request.getParameter("pc_rank"));
		
//		System.out.println("작성자 : "+id);
//		System.out.println("게시글번 : "+pl_num);
//		System.out.println("내용 : "+pc_content);
//		System.out.println("페이지 : "+nowPage);
		System.out.println("점수입력 : "+pc_rank);
		

		PlaceCommentBean pcb = new PlaceCommentBean();
		pcb.setMember_id(id);
		pcb.setPc_content(pc_content);
		pcb.setPl_num(pl_num);
		pcb.setPc_rank(pc_rank);
		
		PCWiteProService pcws = new PCWiteProService();
		boolean isWriteSuccess = pcws.registComment(pcb);
		
		// 리턴받은 결과를 사용하여 글 등록 결과 판별
		if(!isWriteSuccess) {
//			System.out.println("글 등록 실패!");
			// 자바스크립트를 사용하여 다이얼로그를 통해 실패 메세지 출력 후 이전 페이지로 이동
			// 1. response 객체를 사용하여 문서 타입 및 인코딩 설정
			response.setContentType("text/html;charset=UTF-8");
			// 2. response 객체의 getWriter() 메서드를 호출하여
			//    출력스트림 객체(PrintWriter)를 리턴받음
			PrintWriter out = response.getWriter();
			// 3. PrintWriter 객체의 println() 메서드를 호출하여
			//    웹에서 수행할 작업(자바스크립트 출력 등)을 기술
			out.println("<script>"); // 자바스크립트 시작 태그
			out.println("alert('댓글 등록 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			System.out.println("댓글 등록 성공!");
			
			// 현재 BoardWritePro.bo 에서 BoardList.bo 서블릿 주소를 요청하여 Redirect 방식으로 포워딩
			// 1. ActionForward 객체 생성
			forward = new ActionForward();
			// 2. 포워딩 방식 지정 => Redirect 방식이므로 파라미터에 true 전달(필수)
			forward.setRedirect(true);
			// 3. 포워딩 할 주소 지정 => 서블릿 주소 BoardList.bo 요청
			forward.setPath("PlaceDetail.pl?&pl_num="+pl_num+"&page="+nowPage);
		}
		
		
		// 4. ActionForward 객체 리턴 
		return forward;
		
		
	}

}
