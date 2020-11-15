package event.svc;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import event.dao.EventDAO;
import event.vo.EventBean;
import event.vo.EventWinBean;
import static common.db.JdbcUtil.*;

public class EventChangeListService {

	public JSONArray changeEventWinList(int sel) {
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		JSONArray articleList = eventDAO.getChangeWinArticle(sel);
		
		close(con);
		return articleList;
	}

	public JSONObject changeEventList(int sel) {
		
		System.out.println("adminEventWinListService");
		
		Connection con = getConnection();
		
		EventDAO eventDAO = EventDAO.getInstance();
		
		eventDAO.setConnection(con);
		
		JSONObject event = eventDAO.getChangeArticle(sel);
		
		
		close(con);
		
		return event;
		
	}
}
