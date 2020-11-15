package member.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;

import member.dao.MemberDAO;
import review.dao.ReviewDAO;
import review.vo.*;

public class MemberWriteListService {

	public int getListCount(String id) {
		System.out.println("MemberWriteListService - getListCount()");
		int listCount = 0;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectListCount(id);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<ReviewBean> getArticleList(int page, int limit, String id) {
		
		ArrayList<ReviewBean> articleList = null;
		
		System.out.println("MemberWriteListService -getArticleList()");
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		articleList = memberDAO.selectArticleList(page,limit,id);
		
		close(con);
		
		return articleList;
	}
}
