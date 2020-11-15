package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import product.svc.ProductListService;
import product.vo.ProductBean;
import review.vo.ReviewPageInfo;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		System.out.println("productListAction");
		
		
		int page = 1;
		int limit = 8;

		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		ProductListService productListService=new ProductListService();
		
		int listCount = productListService.getListCount();
		int maxPage = (int)((double)listCount/limit+0.95);
		int startPage = (((int)((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		ReviewPageInfo pageinfo = new ReviewPageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageinfo);

		
// ------------------------------------------------------------------------------------------------------------

		ArrayList<ProductBean> productList=productListService.getProductList(page, limit);
		System.out.println("tㅏ이즈"+productList.size());
	
		request.setAttribute("productList", productList);		
		forward=new ActionForward();
		forward.setPath("/product/product_list3.jsp");

		
		return forward;
	}

}
