package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import product.svc.ProductDetailService;
import product.svc.ProductListService;
import product.vo.ProductBean;

public class ProductUpdateDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		System.out.println("productDetailAction");
		
		int p_num=Integer.parseInt(request.getParameter("p_num"));
		System.out.println("act pnum"+p_num);
		ProductDetailService productDetilService=new ProductDetailService();
		ProductBean productDetail=new ProductBean();
		productDetail=productDetilService.getProductDetail(p_num);
		
		request.setAttribute("productDetail", productDetail);
		
		forward=new ActionForward();
		forward.setPath("/product/productUpdateDetail.jsp");
		
		return forward;
	}

}
