package review.action;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import common.action.*;
import common.vo.*;
import review.svc.*;
import review.vo.*;

public class ReviewWriteProAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("ReviewWriteProAction");
		ServletContext context = request.getServletContext();
		
		String saveFolder = "/reviewUpload";
		
		String realFolder = context.getRealPath(saveFolder);
		
		int fileSize = 1024*1024*10;
		
		MultipartRequest multi = new MultipartRequest(
				request, 
				realFolder, 
				fileSize, 
				"UTF-8", 
				new DefaultFileRenamePolicy());
		
		int region = Integer.parseInt(multi.getParameter("r_code"));
		
		ReviewBean reviewBean = new ReviewBean();
		
		reviewBean.setR_id(multi.getParameter("r_id"));
		reviewBean.setR_code(region);
		reviewBean.setR_subject(multi.getParameter("r_subject"));
		reviewBean.setR_content(multi.getParameter("r_content"));
		
		reviewBean.setR_image(
				multi.getOriginalFileName( (String)multi.getFileNames().nextElement() ));
		ReviewWriteProService reviewWriteProService = new ReviewWriteProService();
		
		boolean isWriteSucces = reviewWriteProService.registArticle(reviewBean);
		
		if (!isWriteSucces) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('글작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
			
		}else {
			System.out.println("글 등록 성공");

			forward = new ActionForward();
			
			forward.setRedirect(true);
			
			forward.setPath("Review_List.re");
		}
		
		return forward;
	}

}
