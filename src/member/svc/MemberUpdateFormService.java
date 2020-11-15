package member.svc;

import static common.db.JdbcUtil.*;

import java.sql.*;
import java.util.*;
import static common.db.JdbcUtil.*;

import common.vo.*;
import member.dao.MemberDAO;
import member.vo.MemberBean;
import suggestion.dao.SuggestionDAO;
import suggestion.vo.SuggestionBean;


public class MemberUpdateFormService {

	public MemberBean getMemberInfo(String id) {
		MemberBean memberInfo = null;
		
		System.out.println("MemberUpdateFormService -getMemberInfo()");
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		memberInfo = memberDAO.getMemberInfo(id);
		
		close(con);
		
		return memberInfo;
	}
}
