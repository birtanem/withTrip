package place.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import place.dao.PlaceDAO;

public class PCDeleteProService {

	public boolean PCDelete(int pc_num) {

		boolean DeleteSuccess = false;
		
		System.out.println("PCDeleteProService - PCDelete");
		
		Connection con = getConnection();
		
		PlaceDAO placeDAO = PlaceDAO.getInstance();
		
		placeDAO.setConnection(con);
		
		int DeleteCount = placeDAO.pc_delete(pc_num);
		
		if (DeleteCount > 0) {
			
			DeleteSuccess = true;
			commit(con);
			
		}else {
			rollback(con);
		}
		close(con);
		
		return DeleteSuccess;
	}

	
}
