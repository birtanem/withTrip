package suggestion.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;
import static common.db.JdbcUtil.*;

import suggestion.dao.SuggestionDAO;
import suggestion.vo.SuggestionBean;


public class SuggestionDetailService {

	public SuggestionBean getArticleList(int su_num) { //수정필요
		
		SuggestionBean article = null;
		
		Connection con = getConnection();
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		suggestionDAO.setConnection(con);
		
		boolean checked = suggestionDAO.successCheck(su_num); //답변 완료인지 확인  
		
		if(!checked) {
			article = suggestionDAO.selectArticle(su_num); //답변 미완료일경우 게시물만 출력 num_ref 조회 X
		} else {
			article = suggestionDAO.selectArticleR(su_num); //답변 완료일경우 게시물+답글 조회 출력
		}
		
		close(con);
		
		return article;
	}
}
