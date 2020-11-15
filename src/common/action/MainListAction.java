package common.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.vo.ActionForward;
import member.svc.MemberMypageFormService;
import member.vo.MemberBean;
import place.action.PlaceListAction;
import place.svc.PlaceListService;
import place.vo.PlaceBean;
import product.svc.ProductListService;
import product.svc.productUpdateService;
import product.vo.ProductBean;

public class MainListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MainListAction");
		ActionForward forward = null;
		PlaceListService placeListService = new PlaceListService();
		ArrayList<PlaceBean> list = placeListService.getList();
		request.setAttribute("list", list);

		
		
		
		//--메인list--
		ProductListService productListService=new ProductListService();
		ArrayList<ProductBean> pdList=productListService.getProductList();

		
		request.setAttribute("pdList", pdList);
		forward = new ActionForward();
		
		forward.setPath("/index.jsp?#main-map");
		return forward;
	}

}
