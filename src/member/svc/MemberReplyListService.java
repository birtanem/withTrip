package member.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;

import member.dao.MemberDAO;
import review.dao.ReviewDAO;
import review.vo.*;

public class MemberReplyListService {

	public int getListCount(String id) {
		System.out.println("MemberReplyListService - getListCount()");
		int listCount = 0;
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		listCount = memberDAO.selectReplyListCount(id);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<ReviewBean> getReplyList(int page, int limit, String id) {
		
		ArrayList<ReviewBean> articleList = null;
		
		System.out.println("MemberReplyListService -getReplyList()");
		
		Connection con = getConnection();
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		memberDAO.setConnection(con);
		
		articleList = memberDAO.selectReplyList(page,limit,id);
		
		close(con);
		
		return articleList;
	}
}
