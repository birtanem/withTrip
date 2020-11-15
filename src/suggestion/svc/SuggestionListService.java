package suggestion.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;

import suggestion.dao.SuggestionDAO;
import suggestion.vo.SuggestionBean;


public class SuggestionListService {

	public int	 getListCount() {
		System.out.println("SuggestionListService - getListCount()");
		int listCount = 0;
		
		Connection con = getConnection();
		
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		
		suggestionDAO.setConnection(con);
		
		listCount = suggestionDAO.selectListCount();
		
		close(con);
		
		return listCount;
	}
	
	public int	 getListCount1() {
		System.out.println("SuggestionListService - getListCount2()");
		int listCount1 = 0;
		
		Connection con = getConnection();
		
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		
		suggestionDAO.setConnection(con);
		
		listCount1 = suggestionDAO.selectListCount1();
		
		close(con);
		
		return listCount1;
	}
	
	public int	 getListCount2() {
		System.out.println("SuggestionListService - getListCount2()");
		int listCount2 = 0;
		
		Connection con = getConnection();
		
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		
		suggestionDAO.setConnection(con);
		
		listCount2 = suggestionDAO.selectListCount2();
		
		close(con);
		
		return listCount2;
	}

	public ArrayList<SuggestionBean> getArticleList(String id) {
		
		ArrayList<SuggestionBean> articleList = null;
		
		System.out.println("SuggestionListService -getArticleList()");
		
		Connection con = getConnection();
		
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		
		suggestionDAO.setConnection(con);
		
//		articleList = suggestionDAO.selectArticleList(page,limit);
		articleList = suggestionDAO.selectArticleList(id);
		
		close(con);
		
		return articleList;
		
	}
	
	public ArrayList<SuggestionBean> adminGetArticleList(String showStyle) {
		
		ArrayList<SuggestionBean> articleList = null;
		System.out.println("SuggestionListService -adminArticleList()");
		Connection con = getConnection();
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		suggestionDAO.setConnection(con);
		
		if(showStyle.equals("전체")) {
			articleList = suggestionDAO.adminselectArticleList();
		} else if(showStyle.equals("미완료")) {
			articleList = suggestionDAO.adminselectArticleList2();
		} else {
			articleList = suggestionDAO.adminselectArticleList();
		}
		
		close(con);
		
		return articleList;
	}
	
}
