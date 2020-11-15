package suggestion.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;

import member.dao.MemberDAO;
import place.dao.PlaceDAO;
import product.dao.ProductDAO;
import review.dao.ReviewDAO;
import suggestion.dao.SuggestionDAO;
import suggestion.vo.SuggestionBean;


public class SuggestionInfoService {

	public int getPlaceCount() {
		System.out.println("SuggestionInfoService - getPlaceCount()");
		int listCount1 = 0;
		
		Connection con = getConnection();
		
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		placeDAO.setConnection(con);
		
		listCount1 = placeDAO.selectListCount();
		
		close(con);
		
		return listCount1;
	}
	
	public int getReviewCount() {
		System.out.println("SuggestionInfoService - getReviewCount()");
		int listCount2 = 0;
		
		Connection con = getConnection();
		
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		
		reviewDAO.setConnection(con);
		
		listCount2 = reviewDAO.selectListCount();
		
		close(con);
		
		return listCount2;
	}
	
	public int getProductCount() {
		System.out.println("SuggestionInfoService - getProductCount()");
		int listCount3 = 0;
		
		Connection con = getConnection();
		
		ProductDAO productDAO = ProductDAO.getInstance();
		
		productDAO.setConnection(con);
		
		listCount3 = productDAO.selectListCount();
		
		close(con);
		
		return listCount3;
	}
	
	public int getMemberCount() {
		System.out.println("SuggestionInfoService - getMemberCount()");
		int listCount4 = 0;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		listCount4 = memberDAO.memberListCount();
		
		close(con);
		
		return listCount4;
	}

}
