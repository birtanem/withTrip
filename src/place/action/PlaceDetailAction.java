package place.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PlaceDetailService;
import place.vo.PCpageInfo;
import place.vo.PlaceBean;
import place.vo.PlaceCommentBean;
import product.svc.ProductListService;
import product.vo.ProductBean;
import review.svc.ReviewContentService;
import review.vo.ReviewBean;

public class PlaceDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("BoardDetailAction");
		
		ActionForward forward = null;
		
		// 파라미터로 전달된 게시물 번호(board_num) 가져오기
		int pl_num = Integer.parseInt(request.getParameter("pl_num"));
		
		// BoardDetailService 인스턴스 생성 후 getArticle() 메서드 호출하여 상세내용 가져오기
		// => 파라미터 : 게시물 번호(board_num), 리턴타입 : BoardBean(article)
		PlaceDetailService placeDetailService = new PlaceDetailService();
		PlaceBean article = placeDetailService.getArticle(pl_num);
		//PlaceComment_List 가져오는 코드
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 4; // 한 페이지 당 출력할 게시물 수 지정
		
		// 파라미터로 전달받은 페이지 번호가 있을 경우 가져와서 page 변수에 저장
		if(request.getParameter("cpage") != null) {
			page = Integer.parseInt(request.getParameter("cpage")); // String -> int 변환
		}
		int listCount = placeDetailService.getCommentListCount(pl_num);
		ArrayList<PlaceCommentBean> commentList = placeDetailService.getCommentList(pl_num,page,limit);
		int maxPage = (int)((double)listCount / limit + 0.95);
		// 2. 현재 페이지에서 표시할 시작 페이지 번호 계산(1, 11, 21 등)
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		// 3. 현재 페이지에서 표시할 끝 페이지 번호 계산(10, 20, 30 등)
		int endPage = startPage + 10 - 1;
		// 단, 끝 페이지 번호가 최대 페이지 번호보다 클 경우 
		// => 최대 페이지 번호를 끝 페이지 번호로 사용
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PCpageInfo pageInfo = new PCpageInfo(page, maxPage, startPage, endPage, listCount);
		
		
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
		
		
		// ---------- 추천 리뷰 리스트 ------------------
				
		ReviewContentService reviewContentService = new ReviewContentService();		
		ArrayList<ReviewBean> arrayList = reviewContentService.getArrayList();		
		request.setAttribute("reviewList", arrayList);
	
		// ----------------------------------------------
		
		request.setAttribute("cpageInfo", pageInfo);
		request.setAttribute("commentList", commentList);
		request.setAttribute("article", article);
		
		
		forward = new ActionForward();
		forward.setPath("/place/place_Content.jsp");
		
		return forward;
	}

}
