package product.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.productImgUpdateService;
import product.svc.ProductDetailService;
import product.svc.productUpdateService;
import product.vo.ProductBean;

public class ProductImgUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("productimgUpdateAction도착!");
		
		ActionForward forward = null;
		
		ServletContext context = request.getServletContext();
		String saveFolder = "product/productUpload";
		String realFolder = context.getRealPath(saveFolder);
		int fileSize = 1024 * 1024 * 50;
		MultipartRequest multi = new MultipartRequest(
				request, 
				realFolder, 
				fileSize, 
				"UTF-8", 
				new DefaultFileRenamePolicy());
		
		boolean isUpdate = false;
		
		ProductBean productBean = new ProductBean();
		int p_num=Integer.parseInt(multi.getParameter("p_num"));
	
		productImgUpdateService productImgUpdateServie = new productImgUpdateService();
		productBean.setP_num(p_num);
		productBean.setP_image(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		System.out.println("file:"+multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		isUpdate = productImgUpdateServie.productImgUpdate(productBean);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (!isUpdate) {
			out.println("<script>");
			out.println("alert('update실패')");
			out.println("history.back()");
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
