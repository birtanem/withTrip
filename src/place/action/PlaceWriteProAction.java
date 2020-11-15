package place.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PlaceUpdateProService;
import place.svc.PlaceWriteProService;
import place.vo.PlaceBean;

public class PlaceWriteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PlaceWriteProAction");
		
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		
		String saveFolder = "/placeUpload"; 
		
		String realFolder = context.getRealPath(saveFolder);
		
		int fileSize = 1024 * 1024 * 10; // 1024byte = 1KByte * 1024 = 1MB * 10 = 10MB 
		
		// MultiPartRequest 객체 생성 => cos.jar 필요
		MultipartRequest multi = new MultipartRequest(
				request, // request 객체
				realFolder,  // 파일이 업로드 될 실제 폴더
				fileSize, // 한 번에 업로드 가능한 파일 최대 크기
				"UTF-8", // 파일명에 대한 인코딩 방식
				new DefaultFileRenamePolicy()); // 파일명 중복 시 중복 파일명을 처리할 객체
		
		PlaceBean pb = new PlaceBean();
		pb.setPl_name(multi.getParameter("pl_name"));
		pb.setPl_content(multi.getParameter("pl_content"));
		pb.setPl_address(multi.getParameter("pl_address"));
		pb.setPl_theme(multi.getParameter("pl_theme"));
		pb.setRegion_code(Integer.parseInt(multi.getParameter("region_code")));
		
		pb.setPl_image(
				multi.getOriginalFileName( (String)multi.getFileNames().nextElement() ));
		
		PlaceWriteProService prps = new PlaceWriteProService();
		boolean isWriteSuccess = prps.registArticle(pb);
		
		
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
			out.println("alert('글 등록 실패!')"); // 다이얼로그 메세지 출력
			out.println("history.back()"); // 이전 페이지로 돌아가기
			out.println("</script>"); // 자바스크립트 끝 태그
		} else {
			System.out.println("글 등록 성공!");
			
			// 현재 BoardWritePro.bo 에서 BoardList.bo 서블릿 주소를 요청하여 Redirect 방식으로 포워딩
			// 1. ActionForward 객체 생성
			forward = new ActionForward();
			// 2. 포워딩 방식 지정 => Redirect 방식이므로 파라미터에 true 전달(필수)
			forward.setRedirect(true);
			// 3. 포워딩 할 주소 지정 => 서블릿 주소 BoardList.bo 요청
			forward.setPath("PlaceList.pl?check=2");
		}
		
		
		// 4. ActionForward 객체 리턴 
		return forward; // => BoardFrontController 로 전달
	}

}
