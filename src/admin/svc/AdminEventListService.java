package admin.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import event.dao.EventDAO;
import event.vo.EventBean;


public class AdminEventListService {

	public ArrayList<EventBean> getArticleList() {
		
		System.out.println("adminEventWinListService");
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		ArrayList<EventBean> eventList = eventDAO.getArticle();	
		
		close(con);
		
		return eventList;
		
	}
}
