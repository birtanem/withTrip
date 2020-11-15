package common.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.vo.regionCode;

public class RegionDAO {
	private static RegionDAO instance;

	private RegionDAO() {
	}

	public static RegionDAO getInstance() {
		if (instance == null) {
			instance = new RegionDAO();
		}
		return instance;
	}

	Connection con;

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<regionCode> getRegion() {
		ArrayList<regionCode> regionarray = new ArrayList<regionCode>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from region";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				regionCode code = new regionCode();
				code.setRegion_code(rs.getInt(1));
				code.setRegion_name(rs.getString(2));
				regionarray.add(code);
			}
		} catch (SQLException e) {
			System.out.println("getRegionDAO err: " + e.getMessage());
		} finally {
			close(rs);
			close(pstmt);
		}

		return regionarray;
	}

}
