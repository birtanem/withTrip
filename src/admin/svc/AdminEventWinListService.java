package admin.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import event.dao.EventDAO;
import event.vo.EventWinBean;

public class AdminEventWinListService {

	public ArrayList<EventWinBean> getWinArticleList() {
		
		System.out.println("adminEventWinListService");
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		ArrayList<EventWinBean> eventWinList = eventDAO.getWinArticle();
		
		
		close(con);
		
		return eventWinList;
		
	}
	

}
