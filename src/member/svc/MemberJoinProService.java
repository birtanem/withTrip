package member.svc;

import java.sql.Connection;
import static common.db.JdbcUtil.*;

import member.dao.MemberDAO;
import member.vo.JoinException;
import member.vo.MemberBean;

public class MemberJoinProService {

	public boolean registMember(MemberBean memberBean) {
		
		System.out.println("MemberJoinProService - registMember()");
		
		boolean isJoinSuccess = false;
		
		// 커넥션 생성
		Connection con = getConnection();
		// MeberDAO 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		// MemberDAO 커넥션 연결
		memberDAO.setConnection(con);
		
		int insertCount = memberDAO.insertMember(memberBean);
		
		if(insertCount > 0) {
			isJoinSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return isJoinSuccess;
	}
}
