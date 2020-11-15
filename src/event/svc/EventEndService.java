package event.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import event.dao.EventDAO;



public class EventEndService {
	
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
}
