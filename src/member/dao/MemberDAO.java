package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.vo.JoinException;
import member.vo.MemberBean;
import review.vo.ReviewBean;
import suggestion.vo.SuggestionBean;

import static common.db.JdbcUtil.*;

public class MemberDAO {
	
		public MemberDAO() {}
		
		private static MemberDAO instance;
		
//		private Connection getConnection() throws Exception{
//			// 예외처리를 메서드호출한 곳에서 처리하겠다
//			
////			// 1단계 드라이버로더
////			 Class.forName("com.mysql.jdbc.Driver");
////			 // 2단계 디비연결
////			 String dbUrl="jdbc:mysql://localhost:3306/jspdb1";
////			 String dbUser="jspid";
////			 String dbPass="jsppass";
////			 Connection con=DriverManager.getConnection(dbUrl, dbUser, dbPass);
////			 return con;
//			
//			Context init=new InitialContext();
//			DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
//			Connection con=(Connection) ds.getConnection();
//			return con;
//		}
		
		public static MemberDAO getInstance() {
			// BoardDAO 객체가 없을 경우에만 생성
			if(instance == null) {
				instance = new MemberDAO();
			}	
			return instance;
		}
		
		Connection con;
		
		public void setConnection(Connection con) {
			this.con = con;
		}
		
		public int insertMember(MemberBean memberBean) {
			PreparedStatement pstmt = null;
			int insertCount = 0;
			
			try {
				String sql = "insert into member(id,pass,name,age,gender,email,phone,date,point,type,cp_3,cp_5,cp_10) values(?,?,?,?,?,?,?,now(),0,?,0,0,0)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,memberBean.getId());
				pstmt.setString(2,memberBean.getPass());
				pstmt.setString(3,memberBean.getName());
				pstmt.setInt(4,memberBean.getAge());
				pstmt.setString(5,memberBean.getGender());
				pstmt.setString(6,memberBean.getEmail());
				pstmt.setString(7,memberBean.getPhone());
				pstmt.setString(8,memberBean.getType());
				insertCount = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return insertCount;
		}
		
		public int updateMember(MemberBean memberBean) {
			PreparedStatement pstmt = null;
			int UpdateCount = 0;
			
			try {
				String sql = "UPDATE member SET email=?, phone=?, type=? WHERE id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,memberBean.getEmail());
				pstmt.setString(2,memberBean.getPhone());
				pstmt.setString(3,memberBean.getType());
				pstmt.setString(4,memberBean.getId());
				UpdateCount = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return UpdateCount;
		}
		
		public int updatePass(MemberBean memberBean) {
			PreparedStatement pstmt = null;
			int UpdateCount = 0;
			
			try {
				String sql = "UPDATE member SET pass=? WHERE id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,memberBean.getPass());
				pstmt.setString(2,memberBean.getId());
				UpdateCount = pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return UpdateCount;
		}

		public int selectLoginMember(String id, String pass) {
			
			int loginResult = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//false라면 나오고
			//true라면 메인페이지로 이동
			
			//아이디에 해당하는 패스워드 존재여부
			//_db에서 확인해야한다. 
			// 1. 아이디와 패스워드가 일치하는 레코드 검색.
			
		    //_ 비교대상필요
		
				//  1. 아이디와 패스워드가 일치하는 레코드 검색
				// => 아이디, 패스워드 중 틀린 항목에 대한 구분이 불가능
				
				// 2. 아이디에 해당하는 패스워드 검색
				// String sql = "Select pass from member where =id? ";
				// 이렇게 하면 id가 맞아면 pass만 확인하면 되고 아예 id조차틀린경우
				// 아이디만 바로 판별가능
				
				try {
					String sql = "SELECT pass FROM member WHERE id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					
					if(rs.next()) { // 아이디가 존재하는 경우(패스워드가 조회되는 경우)
						// 패스워드 일치 여부 판별
						if(pass.equals(rs.getString(1))) { // 패스워드가 일치할 경우
							loginResult = 1;
						}else {
							loginResult = -1;
						}
					}
				
				} catch (SQLException e) { //조심해야하는데 sql 이 틀려도 튀어나감.. 
					e.printStackTrace();
				} finally {
					close(rs);
					close(pstmt);
				}
			return loginResult;
		}

		public boolean selectJoinMember(String id) throws Exception {
			
			boolean JoinResult = false;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			//그리고 db에서 아이디(고유값) 일치하는 회원이 있는지 확인하면 되잖아?
			//실제는 반복 가입 막기위해서 핸드폰을 인증하고 해야한
				try {
				String sql = "SELECT id FROM member WHERE id=?"; //기존의 회원 아이디 있는지 확인
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // 아이디가 존재하는 경우
					// 패스워드 일치 여부 판별
					if(id.equals(rs.getString(1))) { // 패스워드가 일치할 경우
						JoinResult = true;
					} else {
						//예외객체 직접 발생시켜서 예외메세지에
					//패스워드 오류 메세지 전달.
						
						throw new JoinException("회원 존재");//예외클래스 꼭대기 exception 쓰거나 우리가 만들거나, 
						//   이 과정이 예외 객체 생성
						// 정상적인 리턴이면 결과값 나오는데
						// 정상적 로그인이 안된다면, ? ? 트라이캐치하면 또,, 
						// 예외강제 throw ,,고 밖으로 던지는건 throws
					}
				}else {
					throw new JoinException("회원이 존재하지 않습니다.");
				}
				} catch (SQLException e) { //조심해야하는데 sql 이 틀려도 튀어나감.. 
					e.printStackTrace();
				} finally {
					close(rs);
					close(pstmt);
				}
				return JoinResult;
		}

		public boolean duplicateIdCheck(String id) {
			
			boolean isDuplicateMember = false;	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// board_num 에 해당하는 게시물을 조회하여 패스워드(board_pass) 일치 여부 확인
			try {
				String sql = "SELECT id FROM member WHERE id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 			
					isDuplicateMember = true;				
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return isDuplicateMember;
		}
		
		public boolean duplicateEmailCheck(String email) {
			
			boolean isDuplicateEmailCheck = false;	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// board_num 에 해당하는 게시물을 조회하여 패스워드(board_pass) 일치 여부 확인
			try {
				String sql = "SELECT email FROM member WHERE email = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 			
					isDuplicateEmailCheck = true;				
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return isDuplicateEmailCheck;
		}
		
		public boolean duplicatePhoneCheck(String phone) {
			
			boolean isDuplicatePhoneCheck = false;	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			// board_num 에 해당하는 게시물을 조회하여 패스워드(board_pass) 일치 여부 확인
			try {
				String sql = "SELECT phone FROM member WHERE phone = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, phone);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 			
					isDuplicatePhoneCheck = true;				
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}
			return isDuplicatePhoneCheck;
		}
		
		
		public MemberBean getMemberInfo(String id) {
			
			MemberBean memberInfo = new MemberBean();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				String sql = "select * from member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					// SuggestionBean 객체에 조회된 레코드(게시물) 정보를 저장
					memberInfo.setId(rs.getString("id"));
					memberInfo.setPass(rs.getString("pass"));
					memberInfo.setName(rs.getString("name"));
					memberInfo.setAge(rs.getInt("age"));
					memberInfo.setGender(rs.getString("gender"));
					memberInfo.setEmail(rs.getString("email"));
					memberInfo.setPhone(rs.getString("phone"));
					memberInfo.setDate(rs.getDate("date"));
					memberInfo.setPoint(rs.getInt("point"));
					memberInfo.setType(rs.getString("type"));					
				}
				
			} catch (SQLException e) {
				System.out.println("MemberDAO - getMemberInfo() 실패! : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			return memberInfo;
		}

//		내가쓴글 게시물 카운트
		
		public int selectListCount(String id) {

			int selectCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select count(r_num) from review where member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					selectCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("MemberDAO - selectListCount() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return selectCount;
		}
		
		// 내가쓴글 게시물  가져오기
		public ArrayList<ReviewBean> selectArticleList(int page, int limit, String id) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (page-1)*limit;
			
			ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
			
			String r_cnt = "(select count(rc_num) from review_comment where r_num = r_num)";
			String r_name = "(select region_name from region where region_code = region_region_code)";
			
			try {
				String sql = "select *, "+r_cnt+" as r_cnt,"+r_name+" as r_name "
						+ "from review where member_id=? order by r_num desc limit ?,?";
							// 댓글 개수와 해당 지역 이름값 받아오는 서브 쿼리문
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					ReviewBean reviewBean = new ReviewBean();
					
					reviewBean.setR_num(rs.getInt("r_num"));
					reviewBean.setR_id(rs.getString("member_id"));
					reviewBean.setR_subject(rs.getString("r_subject"));
					reviewBean.setR_content(rs.getString("r_content"));
					reviewBean.setR_readcount(rs.getInt("r_readcount"));
					reviewBean.setR_likecount(rs.getInt("r_likecount"));
					reviewBean.setR_date(rs.getDate("r_date"));
					reviewBean.setR_image(rs.getString("r_image"));
					reviewBean.setR_code(rs.getInt("region_region_code"));
					reviewBean.setR_name(rs.getString("r_name"));
					reviewBean.setR_cnt(rs.getInt("r_cnt"));
					
					articleList.add(reviewBean);
					
				}
			
			} catch (SQLException e) {
				System.out.println("MemberDAO - selectArticleList() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			return articleList;
		}
		
//		내가쓴 댓글 카운트
		
		public int selectReplyListCount(String id) {

			int selectCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select count(rc_num) from review_comment where member_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					selectCount = rs.getInt(1);
				}
				
			} catch (SQLException e) {
				System.out.println("MemberDAO - selectReplyListCount() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return selectCount;
		}
		
		// 내가쓴 댓글  가져오기 (review 댓글)
		public ArrayList<ReviewBean> selectReplyList(int page, int limit, String id) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (page-1)*limit;
			
			ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
			
			try {
				String sql = "select * from review_comment where member_id=? order by rc_num desc limit ?,?";
							// 댓글 개수와 해당 지역 이름값 받아오는 서브 쿼리문
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {

					ReviewBean reviewBean = new ReviewBean();
					
					reviewBean.setR_num(rs.getInt("rc_num"));
					reviewBean.setR_content(rs.getString("rc_content"));
					reviewBean.setR_readcount(rs.getInt("review_r_num"));
					reviewBean.setR_date(rs.getDate("rc_date"));

					articleList.add(reviewBean);
					
				}
			
			} catch (SQLException e) {
				System.out.println("MemberDAO - selectArticleList() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			return articleList;
		}

		
		//---------------------멤버 리스트------------------------------
		
		//멤버리스트 카운트
		public int memberListCount() {

			int selectCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				String sql = "select count(*) from member";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					selectCount += rs.getInt(1);;
				}
			} catch (SQLException e) {
				System.out.println("MemberDAO - memberListCount() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			
			return selectCount;
		}
		
		//멤버리스트 불러오기 (정렬기능 포함)
		public ArrayList<MemberBean> getMemberList(int page, int limit, String type) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (page-1)*limit;
			
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
			
			try {
				String sql = "select * from member order by " + type +" limit ?,?";	
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberBean memberBean = new MemberBean();
					memberBean.setId(rs.getString("id"));
					memberBean.setPass(rs.getString("pass"));
					memberBean.setName(rs.getString("name"));
					memberBean.setAge(rs.getInt("age"));
					memberBean.setGender(rs.getString("gender"));
					memberBean.setEmail(rs.getString("email"));
					memberBean.setPhone(rs.getString("phone"));
					memberBean.setDate(rs.getDate("date"));
					memberBean.setPoint(rs.getInt("point"));
					memberBean.setType(rs.getString("type"));

					memberList.add(memberBean);
					
				}
				
				System.out.println(type);
			
			} catch (SQLException e) {
				System.out.println("MemberDAO - getMemberList() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			return memberList;
		}
		
		//멤버리스트 아이디 검색용
		public ArrayList<MemberBean> getMemberListIdSearch(int page, int limit, String type) { 
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
			
			try {
				String sql = "select * from member where id=?";	
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, type);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberBean memberBean = new MemberBean();
					memberBean.setId(rs.getString("id"));
					memberBean.setPass(rs.getString("pass"));
					memberBean.setName(rs.getString("name"));
					memberBean.setAge(rs.getInt("age"));
					memberBean.setGender(rs.getString("gender"));
					memberBean.setEmail(rs.getString("email"));
					memberBean.setPhone(rs.getString("phone"));
					memberBean.setDate(rs.getDate("date"));
					memberBean.setPoint(rs.getInt("point"));
					memberBean.setType(rs.getString("type"));
					memberList.add(memberBean);
				}

				System.out.println(type);
			
			} catch (SQLException e) {
				System.out.println("MemberDAO - getMemberListIdSearch() 실패! : " + e.getMessage());
			}finally {
				close(rs);
				close(pstmt);
			}
			return memberList;
		}
		
		//멤버관리  포인트 변경
		public void memPointChange(String type, int cPoint) { 
			PreparedStatement pstmt = null;
			int UpdateCount = 0;
			
			try {
				String sql = "UPDATE member SET point=? WHERE id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cPoint);
				pstmt.setString(2,type);
				pstmt.executeUpdate();
				UpdateCount = pstmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("MemberDAO - memPointChange() 실패! : " + e.getMessage());
			}finally {
				close(pstmt);
			}

		}
		
		//멤버관리 아이디삭제
		public void deleteId(String type) { 
			PreparedStatement pstmt = null;
			
			try {
		 		String sql="delete from member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, type);
				pstmt.executeUpdate();
			}catch(Exception e) {
				System.out.println("MemberDAO - deleteId() 실패! : " + e.getMessage());
			}finally {
				close(pstmt);
			}
			
		}
}





















			
		