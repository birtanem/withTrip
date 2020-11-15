package product.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import product.svc.ProductDetailService;
import product.svc.productUpdateService;
import product.vo.ProductBean;

public class ProductUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("productUpdateAction도착!");

		ActionForward forward = null;

		boolean isUpdate = false;

		ProductBean productBean = new ProductBean();
		ProductDetailService productDetailService = new ProductDetailService();
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		productBean = productDetailService.getProductDetail(p_num);
		productUpdateService productUpdateServie = new productUpdateService();
		productBean.setP_num(p_num);

		if (request.getParameter("region_region_code") == null
				|| request.getParameter("region_region_code").equals("0")) {
			productBean.setRegion_region_code(productBean.getRegion_region_code());
		} else {
			productBean.setRegion_region_code(Integer.parseInt(request.getParameter("region_region_code")));
		}
		if (request.getParameter("p_theme") == null) {
			productBean.setP_theme(productBean.getP_theme());
		} else {
			productBean.setP_theme(request.getParameter("p_theme"));
		}
		if (request.getParameter("name") == null) {
			productBean.setP_name(productBean.getP_name());

		} else {
			productBean.setP_name(request.getParameter("name"));
		}
		if (request.getParameter("price") == null || request.getParameter("price") == "") {
			productBean.setP_price(productBean.getP_price());

		} else {
			productBean.setP_price(Integer.parseInt(request.getParameter("price")));
		}
		if (request.getParameter("amount") == null) {
			productBean.setP_amount(productBean.getP_amount());
		} else {

			productBean.setP_amount(Integer.parseInt(request.getParameter("amount")));
		}
		

		isUpdate = productUpdateServie.productUpdate(productBean);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (!isUpdate) {
			out.println("<script>");
			out.println("alert('update실패')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("adminProduct.ad");
		}

		return forward;
	}

}
