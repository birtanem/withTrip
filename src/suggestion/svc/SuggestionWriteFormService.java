package suggestion.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;
import static common.db.JdbcUtil.*;

import common.vo.*;
import suggestion.dao.SuggestionDAO;
import suggestion.vo.SuggestionBean;


public class SuggestionWriteFormService {

	public String getEmail(String id) { 
		String email = null;
		
		Connection con = getConnection();
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		suggestionDAO.setConnection(con);
		
		email = suggestionDAO.getEmail(id); 
		
		close(con);
		
		return email;
	}
}
