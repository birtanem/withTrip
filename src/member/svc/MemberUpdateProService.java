package member.svc;

import java.sql.Connection;
import static common.db.JdbcUtil.*;

import member.dao.MemberDAO;
import member.vo.JoinException;
import member.vo.MemberBean;

public class MemberUpdateProService {

	public boolean UpdateMember(MemberBean memberBean) {
		
		System.out.println("MemberUpdateProService - UpdateMember()");
		
		boolean UpdateSuccess = false;
		
		// 커넥션 생성
		Connection con = getConnection();
		// MeberDAO 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		// MemberDAO 커넥션 연결
		memberDAO.setConnection(con);
		
		int UpdateCount = memberDAO.updateMember(memberBean);
		
		if(UpdateCount > 0) {
			UpdateSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return UpdateSuccess;
	}
	
	public boolean UpdatePass(MemberBean memberBean) {
		
		System.out.println("MemberUpdateProService - UpdatePass()");
		
		boolean UpdateSuccess = false;
		
		// 커넥션 생성
		Connection con = getConnection();
		// MeberDAO 생성
		MemberDAO memberDAO = MemberDAO.getInstance();
		// MemberDAO 커넥션 연결
		memberDAO.setConnection(con);
		
		int UpdateCount = memberDAO.updatePass(memberBean);
		
		if(UpdateCount > 0) {
			UpdateSuccess = true;
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return UpdateSuccess;
	}

}
