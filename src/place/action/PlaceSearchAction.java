package place.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import place.svc.PlaceListService;
import place.svc.PlaceSearchService;
import place.vo.PlaceBean;
import place.vo.PlacePageInfo;
import product.svc.ProductListService;
import product.vo.ProductBean;
import review.svc.ReviewContentService;
import review.vo.ReviewBean;

public class PlaceSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PlaceSearchAction");
		ActionForward forward = null;
		String search = "%"+ request.getParameter("search") + "%";
		System.out.println(search);
		
		int page = 1; // 현재 페이지 번호를 저장할 변수
		int limit = 3; // 한 페이지 당 출력할 게시물 수 지정
		
		// 파라미터로 전달받은 페이지 번호가 있을 경우 가져와서 page 변수에 저장
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page")); // String -> int 변환
		}
		
		// BoardListService 클래스 인스턴스 생성
		// 게시물 전체 갯수를 조회하기 위해 getListCount() 메서드 호출하여 갯수 리턴받기
		PlaceSearchService placeSearchService = new PlaceSearchService();
		int listCount = placeSearchService.getListCount(search);
//		System.out.println("전체 게시물 수 : " + listCount);
		
		// 지정한 갯수 만큼의 게시물 가져오기
		// BoardListService 클래스의 getArticleList() 메서드를 호출하여 게시물 목록 가져오기
		// => 파라미터 : 현재페이지번호(page), 한 번에 가져올 게시물 최대 갯수(limit)
		// => 리턴타입 : ArrayList<BoardBean> => 게시물 1개 저장할 BoardBean 제네릭 타입으로 지정
		ArrayList<PlaceBean> articleList = placeSearchService.getArticleList(page, limit, search);
		
//		for(BoardBean article : articleList) {
//			System.out.println(article.getBoard_subject());
//		}
		
		// 페이징 처리를 위해 페이지 수 계산
		// 1. 최대 페이지 번호 계산 : 전체 게시물 수 / limit 결과를 반올림 처리 위해 0.95 더함
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
		
		// 페이징 정보를 저장할 PageInfo 객체 생성 및 데이터 저장
		PlacePageInfo pageInfo = new PlacePageInfo(page, maxPage, startPage, endPage, listCount);
		
		
		//--------------------------------------------------------------------
		// 추천 리뷰 리스트 
		
		ReviewContentService reviewContentService = new ReviewContentService();
		
		ArrayList<ReviewBean> arrayList = reviewContentService.getArrayList();
		
		request.setAttribute("reviewList",arrayList);
		
		//--------------------------------------------------------------------
		
		
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
		
		
		
		// request 객체에 PageInfo 객체와 ArrayList 객체 저장
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		request.setAttribute("listCount", listCount);
		
		
		forward = new ActionForward();
		forward.setPath("/place/place_search_list.jsp");
		
		
		return forward;
	}

}
