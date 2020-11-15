package common.svc;

import static common.db.JdbcUtil.close;
import static common.db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import common.dao.RegionDAO;
import common.vo.regionCode;

public class getRegionService {

	public static ArrayList<regionCode> getRegion() {
		ArrayList<regionCode> regionarray=null;
		Connection con=getConnection();
		RegionDAO regionDAO=RegionDAO.getInstance();
		regionDAO.setConnection(con);
		regionarray=regionDAO.getRegion();
			close(con);
		
		return regionarray;
	}
	
}
