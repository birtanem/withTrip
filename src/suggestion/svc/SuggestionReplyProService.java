package suggestion.svc;
import static common.db.JdbcUtil.*;
import java.sql.*;
import suggestion.dao.SuggestionDAO;
import suggestion.vo.*;

public class SuggestionReplyProService {

	public boolean updateCheck(SuggestionBean suggestionBean) {
		boolean isReplySucces = false;
		
		Connection con = getConnection();
		SuggestionDAO suggestionDAO = SuggestionDAO.getInstance();
		suggestionDAO.setConnection(con);

//		답글 저장
		int insertReply = suggestionDAO.insertReply(suggestionBean);
		
		if(insertReply > 0) {
//			답글 저장완료 -> check상태 답변완료 수정
			int updateCount = suggestionDAO.updateCheck(suggestionBean);
			if (updateCount > 0) {
				commit(con);
				isReplySucces = true;
			}else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		return isReplySucces;
	}
}
