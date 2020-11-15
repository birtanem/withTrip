package event.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import common.vo.ActionForward;
import event.dao.EventDAO;
import member.vo.MemberBean;


public class EventPullService {

	public int pullEventBox() {
		
		System.out.println("EventPullService");
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int setPull = eventDAO.setPull();
		
		if(setPull == 30001 || setPull == 50001 || setPull == 100001 || setPull == 1) {
			
			commit(con);
			
		}else {
		
			rollback(con);
		}
		
		close(con);
		
		return setPull;
	}
	
	public int setPullCheck() {

		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int check = eventDAO.setPullCheck();
		
		close(con);
		
		return check;
	}
	

	public boolean minusPoint(String id) {
		
		System.out.println("minusPoint");
		boolean isMinusPointSuccess = false;
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int updateCount = eventDAO.updateMinusPoint(id);
		
		if(updateCount > 0) {
			
			MemberBean article = eventDAO.selectArticle(id);
			if(article.getPoint() < 0) {
				rollback(con);
			}else {
				commit(con);
				isMinusPointSuccess = true;
			}
			
		}else {
			rollback(con);
		}
		
		close(con);
		return isMinusPointSuccess;
	}
	
	public boolean setEventEndDate() {
		
		System.out.println("EventEndService");
		
		boolean isSetDate = false;
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int setDate = eventDAO.setEndDate();
		
		if(setDate > 0) {
			commit(con);
			isSetDate = true;
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isSetDate;
	}
	public boolean addWinCoupon(int point, String member_id) {
		System.out.println("EventWinService - insertWinList");
		Connection con = getConnection();
		
		boolean isInsertSuccess = false;
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int insertCount = eventDAO.updateCoupon(point, member_id);
		
		if(insertCount > 0) {
			
			commit(con);
			isInsertSuccess = true;

		}else {
			rollback(con);
		}
		
		close(con);
		
		
		return isInsertSuccess;
	}
	
	public boolean addWinList(int point, String member_id) {
		System.out.println("EventWinService - addWinList");
		Connection con = getConnection();
		
		boolean isInsertSuccess = false;
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int insertCount = eventDAO.insertWinArticle(point ,member_id);
		
		if(insertCount > 0) {
			
			commit(con);
			isInsertSuccess = true;

		}else {
			rollback(con);
		}
		
		close(con);
		
		
		return isInsertSuccess;
	}

	public int getCoupon(String id, int coupon) {
		System.out.println("EventWinService - getCoupon");
		Connection con = getConnection();
		
		boolean isInsertSuccess = false;
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		int couponCount = eventDAO.getCoupon(id, coupon);
		
		close(con);
		
		
		return couponCount;
		
	}

}
