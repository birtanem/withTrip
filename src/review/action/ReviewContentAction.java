package review.action;

import java.util.ArrayList;

import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.*;
import common.vo.*;
import place.svc.PlaceListService;
import place.vo.PlaceBean;
import product.svc.ProductListService;
import product.vo.ProductBean;
import review.svc.*;
import review.vo.*;

public class ReviewContentAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		
		System.out.println("ReviewContentAction");
		
		int r_num = Integer.parseInt(request.getParameter("r_num"));
		String page = request.getParameter("page");
		
		ReviewContentService reviewContentService = new ReviewContentService();
		
		ReviewBean article = reviewContentService.getArticle(r_num);

		ArrayList<ReviewBean> arrayList = reviewContentService.getArrayList();

		CommentListService commentListService = new CommentListService();
		
		ArrayList<CommentBean> articleList = commentListService.getArticleList(r_num);
		
		int commentCount = commentListService.getArticle(r_num);
		
		ReviewPageInfo pageinfo = new ReviewPageInfo(commentCount);
		
		
		// ---------- 추천 상품 리스트 ------------------
		
		// 비로그인 시 "0" 전달 -> 랜덤 4개
		
		// 로그인 시 theme(session) 전달 -> 테마에서 랜덤 4개 
		
		ProductListService productListService = new ProductListService();
		
		HttpSession session = request.getSession();
		
		ArrayList<ProductBean> productList = null;
		
		if((String)session.getAttribute("session") == null) {
			
			productList = productListService.getProductList("0");
			System.out.println("0");
			
		}else {
			
			productList = productListService.getProductList((String)session.getAttribute("session"));
			System.out.println("theme");
		}
			
		request.setAttribute("productList", productList);
		
		// -------------------------------------------------
		
		// ---------- 여행지 추천 리스트 ------------------
		
		PlaceListService placeListService = new PlaceListService();
		ArrayList<PlaceBean> list = placeListService.getList();
		request.setAttribute("placeList", list);
		
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		request.setAttribute("pageinfo", pageinfo);
		request.setAttribute("arrayList",arrayList);
		request.setAttribute("articleList",articleList);
		
		forward = new ActionForward();
		forward.setPath("/review/review_Content.jsp");
		
		return forward;
	}

}
