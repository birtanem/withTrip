package product.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import product.svc.ProductRegistProService;
import product.vo.ProductBean;

public class ProductRegistProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("글쓰기 프로 액션 페이지");
		
		ActionForward forward = null;
		
		// 현재 컨텍스트(객체) 정보고 가져오기
		ServletContext context = request.getServletContext();
		
		// 가상 폴더 위치
		String saveFolder = "product/productUpload";
		
		// 실제 업로드 폴더 위치
		String realFolder = context.getRealPath(saveFolder);
		
		// 파일 사이즈 50M
		int fileSize = 1024 * 1024 * 50;
		
		// MultiPareRequest 객체 생성 
		MultipartRequest multi = new MultipartRequest(
				request, 
				realFolder, 
				fileSize, 
				"UTF-8", 
				new DefaultFileRenamePolicy()); //
		
		// 입력받은 데이터를 저장할 ProductBean 객체 생성
		// => MultiPartRequest 객체로부터 입력받은 데이터를 가져와서 ProductBean 객체에 저장
		ProductBean productBean = new ProductBean();
		productBean.setP_name(multi.getParameter("p_name"));
		productBean.setP_content(multi.getParameter("p_content"));
		productBean.setP_image(multi.getParameter("p_image"));
		productBean.setP_price(Integer.parseInt(multi.getParameter("p_price")));
		productBean.setRegion_region_code(Integer.parseInt(multi.getParameter("region_region_code")));
		productBean.setP_amount(Integer.parseInt(multi.getParameter("p_amount")));
		productBean.setP_theme(multi.getParameter("p_theme"));
		// 업로드할 파일명 전달
		productBean.setP_image(
				multi.getOriginalFileName( (String)multi.getFileNames().nextElement()));
		
	
		// ProductRegistProService 클래스 인스턴스 생성
		// registArticle() 메서드 호출하여 글 등록 요청하기
		// => 파라미터 : ProductBean 객체, 리턴타입 boolean
		ProductRegistProService productRegistProService = new ProductRegistProService();
		boolean isRegistSuccess = productRegistProService.registArticle(productBean);
		
		
		// 리턴 받아서 글 등록 결과 판별
		// response 객체를 사용하여 문서 타입 및 인코딩 설정
		response.setContentType("text/html;charset=UTF-8");
		// getWriter() 메서드 호출
		PrintWriter out = response.getWriter();
		if(!isRegistSuccess) {
			out.println("<script>");
			out.println("alert('글 등록이 실패되었습니다.')");
			out.println("hilstory.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("window.opener.reload()");
			out.println("window.close()");
			out.println("</script>");
		}
		
		return forward;
	}

}
