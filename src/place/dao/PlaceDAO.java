package place.dao;

import static common.db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import place.vo.PlaceBean;
import place.vo.PlaceCommentBean;



public class PlaceDAO {
	/*
	 * ------------ 싱글톤 디자인 패턴을 활용한 BoardDAO 인스턴스 작업 -------------
	 * 1. 외부에서 인스턴스 생성이 불가능하도록 private 접근제한자를 사용하여 생성자 정의
	 * 2. 직접 인스턴스를 생성하여 변수(instance)에 저장
	 * 3. 외부에서 인스턴스를 전달받을 수 있도록 Getter 메서드 정의
	 * 4. getInstance() 메서드에 인스턴스 생성없이 접근 가능하도록 static 메서드로 정의
	 *    => 메서드 내에서 접근하는 멤버변수 instance 도 static 변수로 정의
	 * 5. 인스턴스를 통해 instance 변수에 접근 불가능하도록 접근제한자 private 지정
	 */
	private PlaceDAO() {}
	
	private static PlaceDAO instance;

	public static PlaceDAO getInstance() {
		// BoardDAO 객체가 없을 경우에만 생성
		if(instance == null) {
			instance = new PlaceDAO();
		}
		
		return instance;
	}
	// ---------------------------------------------------------------------------------
	
	Connection con; // Connection 객체 전달받아 저장할 변수 선언
	
	// Service 클래스로부터 Connection 객체를 전달받는 메서드 setConnection() 정의
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// 게시물 등록
		public int insertArticle(PlaceBean pb) {
			// Service 클래스로부터 BoardBean 객체를 전달받아 DB 에 INSERT 작업 수행
			// => 수행 결과 값으로 int형 insertCount 를 리턴받아 다시 Service 클래스로 리턴
			int insertCount = 0;
			
			// DB 작업에 필요한 변수 선언(Connection 객체는 이미 외부로부터 전달받음)
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			System.out.println(pb.getPl_theme());
			try {
				
				int num = 1; // 새 게시물 번호를 저장할 변수(초기값으로 초기번호 1번 설정)
				
				// 현재 게시물의 번호 중 가장 큰 번호(최대값)를 조회하여 다음 새 글 번호 결정(+1)
				String sql = "SELECT MAX(pl_num) FROM place"; // 최대값 조회 쿼리문
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // 등록된 게시물이 하나라도 존재할 경우 게시물 번호 조회 성공
					// 조회된 번호 + 1 을 수행하여 새 글 번호로 저장
					num = rs.getInt(1) + 1;
				} 
				
				sql = "INSERT INTO place VALUES (?,?,?,?,?,?,?,?,now(),?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num); // 계산된 새 글 번호 사용
				pstmt.setInt(2, pb.getRegion_code());
				pstmt.setString(3, pb.getPl_name());
				pstmt.setString(4, pb.getPl_content());
				pstmt.setString(5, pb.getPl_address());
				pstmt.setString(6, pb.getPl_image());
				pstmt.setInt(7,0); //readCount
				pstmt.setInt(8, 0); // likeCount
				pstmt.setString(9, pb.getPl_theme()); 
			
				System.out.println(pb.getPl_content());
				// INSERT 구문 실행 후 리턴되는 결과값을 insertCount 변수에 저장
				insertCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
//				e.printStackTrace();
				System.out.println("PlaceDAO - insertArticle() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				// => 주의! Connection 객체는 Service 클래스에서 별도로 사용해야하므로 닫으면 안됨!
//				JdbcUtil.close(rs);
//				JdbcUtil.close(pstmt);
				// => static import 기능을 사용하여 db.JdbcUtil 클래스 내의 모든 static 멤버 import
				close(rs);
				close(pstmt);
			}
			
			return insertCount;
		}

		public int selectListCount() {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// board_num 컬럼의 전체 갯수를 조회하기(모든 컬럼을 뜻하는 * 기호 사용해도 됨)
				String sql = "SELECT COUNT(pl_num) FROM place"; // COUNT() 함수 사용
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectListCount() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}

		public ArrayList<PlaceBean> selectArticleList(int page, int limit) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산
			
			// 전체 게시물을 저장할 ArrayList 객체 생성 => 제네릭 타입 BoardBean 타입 지정
			ArrayList<PlaceBean> articleList = new ArrayList<PlaceBean>();
			
			String likeAvg = "(select round(avg(pc_rank),1) from place_comment where place_pl_num = pl_num)";
			
			try {
				// 게시물 갯수 조회할 SQL 구문 작성
				// => 정렬 : board_re_ref 기준 내림차순, board_re_seq 기준 오름차순
				// => limit : 시작 행 번호부터 지정된 게시물 갯수만큼 제한
				String sql = "SELECT * ,"+likeAvg+"as likeAvg FROM place ORDER BY pl_num desc LIMIT ?,?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, limit);
				rs = pstmt.executeQuery();
				
				// 조회된 레코드 만큼 반복
				while(rs.next()) {
					// 1개 레코드(게시물)를 저장할 BoardBean 객체 생성
					PlaceBean article = new PlaceBean();
					// BoardBean 객체에 조회된 레코드(게시물) 정보를 저장
					article.setPl_num(rs.getInt("pl_num"));
					article.setRegion_code(rs.getInt("region_code"));
					article.setPl_name(rs.getString("pl_name"));
					article.setPl_content(rs.getString("pl_content"));
					article.setPl_address(rs.getString("pl_address"));
					article.setPl_image(rs.getString("pl_image"));
					article.setPl_readcount(rs.getInt("pl_readcount"));
					article.setPl_likecount(rs.getInt("pl_likecount"));
					article.setPl_date(rs.getDate("pl_date"));
					article.setPl_theme(rs.getString("pl_theme"));
					article.setPl_likeAvg(rs.getDouble("likeAvg"));
					
					// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
					articleList.add(article);
					
				}
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectArticleList() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return articleList;
		}

		public PlaceBean selectArticle(int pl_num) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			PlaceBean article = null;
			
			String likeAvg = "(select round(avg(pc_rank),1) from place_comment where place_pl_num="+pl_num+")";
			
			// 게시물 번호(board_num)에 해당하는 게시물 상세 내용 조회 후 BoardBean 객체에 저장
			try {
				String sql = "SELECT * ,"+likeAvg+"as likeAvg  FROM place WHERE pl_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pl_num);
				rs = pstmt.executeQuery();
				
				// 게시물이 존재할 경우 BoardBean 객체에 모든 데이터 저장
				if(rs.next()) {
					article = new PlaceBean();
					article.setPl_num(rs.getInt("pl_num"));
					article.setRegion_code(rs.getInt("region_code"));
					article.setPl_name(rs.getString("pl_name"));
					article.setPl_content(rs.getString("pl_content"));
					article.setPl_address(rs.getString("pl_address"));
					article.setPl_image(rs.getString("pl_image"));
					article.setPl_readcount(rs.getInt("pl_readcount"));
					article.setPl_likecount(rs.getInt("pl_likecount"));
					article.setPl_date(rs.getDate("pl_date"));
					article.setPl_theme(rs.getString("pl_theme"));
					article.setPl_likeAvg(rs.getDouble("likeAvg"));
//					System.out.println(rs.getString("board_content"));
				}
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectArticle() 실패 : " + e.getMessage());
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return article;
		}

		public int updateReadcount(int pl_num) {
			int updateCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				// 게시물 번호(board_num) 에 해당하는 레코드의 board_readcount 값을 1 증가시킴
				String sql = "UPDATE place SET pl_readcount=pl_readcount+1 WHERE pl_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pl_num);
				updateCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - updateReadcount() 실패 : " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return updateCount;
		}

		public int deleteArticle(int pl_num) {
			int deleteCount = 0;
			
			PreparedStatement pstmt = null;
			
			try {
				String sql = "DELETE FROM place WHERE pl_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pl_num);
				deleteCount = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - deleteArticle() 오류 - " + e.getMessage());
			} finally {
				close(pstmt);
			}
			
			return deleteCount;
		}

		public int insertComment(PlaceCommentBean pcb) {
			int insertCount = 0;
			int updateCount = 0;
			
			// DB 작업에 필요한 변수 선언(Connection 객체는 이미 외부로부터 전달받음)
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				int pc_num = 1; // 새 게시물 번호를 저장할 변수(초기값으로 초기번호 1번 설정)
				
				// 현재 게시물의 번호 중 가장 큰 번호(최대값)를 조회하여 다음 새 글 번호 결정(+1)
				String sql = "SELECT MAX(pc_num) FROM place_comment"; // 최대값 조회 쿼리문
				
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // 등록된 게시물이 하나라도 존재할 경우 게시물 번호 조회 성공
					// 조회된 번호 + 1 을 수행하여 새 글 번호로 저장
					pc_num = rs.getInt(1) + 1;
				} 
				// 코멘트 작성 시 등록한 리뷰 점수 PALCE테이블에 합산 하는 구문
				sql= "UPDATE place SET pl_likecount=(pl_likecount+?) WHERE pl_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,pcb.getPc_rank()); //pc_rank
				pstmt.setInt(2, pcb.getPl_num());
				updateCount = pstmt.executeUpdate();
				
				sql = "INSERT INTO place_comment VALUES (?,?,now(),?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pc_num); // 계산된 새 글 번호 사용
				pstmt.setString(2, pcb.getPc_content());
				pstmt.setString(3, pcb.getMember_id());
				pstmt.setInt(4,pcb.getPc_rank()); //pc_rank
				pstmt.setInt(5, pcb.getPl_num()); 
				insertCount = pstmt.executeUpdate();
				
				if (insertCount > 0 ) {
					sql = "update member set point = point+50 where id = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, pcb.getMember_id());
					
					pstmt.executeUpdate();
				}
				
			} catch (SQLException e) {
//							e.printStackTrace();
				System.out.println("PlaceDAO - insertComment() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				// => 주의! Connection 객체는 Service 클래스에서 별도로 사용해야하므로 닫으면 안됨!
//							JdbcUtil.close(rs);
//							JdbcUtil.close(pstmt);
				// => static import 기능을 사용하여 db.JdbcUtil 클래스 내의 모든 static 멤버 import
				close(rs);
				close(pstmt);
			}
			
			return insertCount;
		}

		public int selectCommentCount(int pl_num) {
			int listCount = 0;
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				// board_num 컬럼의 전체 갯수를 조회하기(모든 컬럼을 뜻하는 * 기호 사용해도 됨)
				String sql = "SELECT COUNT(pc_num) FROM place_comment WHERE place_pl_num = ?"; // COUNT() 함수 사용
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pl_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectCommentCount() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}

		public ArrayList<PlaceCommentBean> selectCommentList(int pl_num, int page, int limit) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산
			
			// 전체 게시물을 저장할 ArrayList 객체 생성 => 제네릭 타입 BoardBean 타입 지정
			ArrayList<PlaceCommentBean> articleList = new ArrayList<PlaceCommentBean>();
			
			try {
				// 게시물 갯수 조회할 SQL 구문 작성
				// => 정렬 : board_re_ref 기준 내림차순, board_re_seq 기준 오름차순
				// => limit : 시작 행 번호부터 지정된 게시물 갯수만큼 제한
				
				String sql = "SELECT * FROM place_comment WHERE place_pl_num=? ORDER BY pc_date desc LIMIT ?,? ";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pl_num);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
				rs = pstmt.executeQuery();
				
				// 조회된 레코드 만큼 반복
				while(rs.next()) {
					// 1개 레코드(게시물)를 저장할 BoardBean 객체 생성
					PlaceCommentBean article = new PlaceCommentBean();
					// BoardBean 객체에 조회된 레코드(게시물) 정보를 저장
					article.setPc_num(rs.getInt("pc_num"));
					article.setPc_content(rs.getString("pc_content"));
					article.setPc_date(rs.getDate("pc_date"));
					article.setMember_id(rs.getString("member_id"));
					article.setPc_rank(rs.getInt("pc_rank"));
					article.setPl_num(rs.getInt("place_pl_num"));
										
					// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
					articleList.add(article);
					
				}
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectCommentList() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return articleList;
		}

		public int updateArticle(PlaceBean pb) {
						int insertCount = 0;
						PreparedStatement pstmt = null;
						String sql;
						try {
														
							sql = "UPDATE place SET region_code=?, pl_name=?, pl_content=?,pl_address=?,pl_image=?,pl_readcount=pl_readcount,pl_likecount=pl_likecount,pl_theme=? WHERE pl_num=?";
									
							pstmt = con.prepareStatement(sql);
							
							pstmt.setInt(1, pb.getRegion_code());
							pstmt.setString(2, pb.getPl_name());
							pstmt.setString(3, pb.getPl_content());
							pstmt.setString(4, pb.getPl_address());
							pstmt.setString(5, pb.getPl_image());
							pstmt.setString(6, pb.getPl_theme());
							pstmt.setInt(7, pb.getPl_num());
						
//							System.out.println("1 - pb.getRegion_code() : "+pb.getRegion_code());
//							System.out.println("2 - pb.getPl_name() : " + pb.getPl_name());
//							System.out.println("3 - pb.getPl_content() : " + pb.getPl_content());
//							System.out.println("4 - pb.getPl_address() : " + pb.getPl_address());
//							System.out.println("5 - pb.getPl_image() : " + pb.getPl_image());
//							System.out.println("6 - 6 pb.getPl_theme() : " + pb.getPl_theme());
//							System.out.println("7 - pb.getPl_num() : "+pb.getPl_num());
							
							insertCount = pstmt.executeUpdate();
							System.out.println(pb.getPl_image());
						} catch (SQLException e) {
							System.out.println("PlaceDAO - updateArticle() 실패! : " + e.getMessage());
						} finally {
							
						
							close(pstmt);
						}
						
						return insertCount;
		}

		public int selectSearchListCount(String search) {
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// board_num 컬럼의 전체 갯수를 조회하기(모든 컬럼을 뜻하는 * 기호 사용해도 됨)
				String sql = "SELECT COUNT(pl_num) FROM place WHERE pl_name like ? or pl_content like ? or pl_address like ? or pl_theme like ?"; // COUNT() 함수 사용
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setString(3, search);
				pstmt.setString(4, search);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectSearchListCount() 실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return listCount;
		}

		public ArrayList<PlaceBean> selectArticleList(int page, int limit, String search) {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int startRow = (page - 1) * limit; // 가져올 게시물에 대한 시작 행 번호 계산
			
			// 전체 게시물을 저장할 ArrayList 객체 생성 => 제네릭 타입 BoardBean 타입 지정
			ArrayList<PlaceBean> articleList = new ArrayList<PlaceBean>();
			
			try {
				// 게시물 갯수 조회할 SQL 구문 작성
				// => 정렬 : board_re_ref 기준 내림차순, board_re_seq 기준 오름차순
				// => limit : 시작 행 번호부터 지정된 게시물 갯수만큼 제한
//				String sql = "SELECT * FROM place WHERE pl_name like ? or pl_content like ? or pl_address like ? or pl_theme like ? ORDER BY pl_num desc LIMIT ?,?";
				String sql = "SELECT * FROM place WHERE pl_theme like ? ORDER BY pl_num desc LIMIT ?,?";
				
				pstmt = con.prepareStatement(sql);
//				pstmt.setString(1, search);
//				pstmt.setString(2, search);
//				pstmt.setString(3, search);
//				pstmt.setString(4, search);
//				pstmt.setInt(5, startRow);
//				pstmt.setInt(6, limit);
				pstmt.setString(1, search);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, limit);
				rs = pstmt.executeQuery();
				
				// 조회된 레코드 만큼 반복
				while(rs.next()) {
					// 1개 레코드(게시물)를 저장할 BoardBean 객체 생성
					PlaceBean article = new PlaceBean();
					// BoardBean 객체에 조회된 레코드(게시물) 정보를 저장
					article.setPl_num(rs.getInt("pl_num"));
					article.setRegion_code(rs.getInt("region_code"));
					article.setPl_name(rs.getString("pl_name"));
					article.setPl_content(rs.getString("pl_content"));
					article.setPl_address(rs.getString("pl_address"));
					article.setPl_image(rs.getString("pl_image"));
					article.setPl_readcount(rs.getInt("pl_readcount"));
					article.setPl_likecount(rs.getInt("pl_likecount"));
					article.setPl_date(rs.getDate("pl_date"));
					article.setPl_theme(rs.getString("pl_theme"));
					
					// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
					articleList.add(article);
					
				}
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - selectArticleList() (search)실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return articleList;
		}

		public ArrayList<PlaceBean> selectList() {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList<PlaceBean> articleList = new ArrayList<PlaceBean>();
			
			String likeAvg = "(select round(avg(pc_rank),1) from place_comment where place_pl_num = pl_num)";
			try {
				// 인기여행지(like순) 추출하기 위해서 ORDER BY 추가
				String sql = "SELECT *,"+likeAvg+" as likeAvg FROM place ORDER BY likeAvg DESC";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					// 1개 레코드(게시물)를 저장할 BoardBean 객체 생성
					PlaceBean article = new PlaceBean();
					// BoardBean 객체에 조회된 레코드(게시물) 정보를 저장
					article.setPl_num(rs.getInt("pl_num"));
					article.setRegion_code(rs.getInt("region_code"));
					article.setPl_name(rs.getString("pl_name"));
					article.setPl_content(rs.getString("pl_content"));
					article.setPl_address(rs.getString("pl_address"));
					article.setPl_image(rs.getString("pl_image"));
					article.setPl_readcount(rs.getInt("pl_readcount"));
					article.setPl_likecount(rs.getInt("pl_likecount"));
					article.setPl_date(rs.getDate("pl_date"));
					article.setPl_theme(rs.getString("pl_theme"));
					article.setPl_likeAvg(rs.getDouble("likeAvg"));
					
					// 전체 레코드 저장하는 ArrayList 객체에 1개 레코드를 저장한 BoardBean 객체 전달
					articleList.add(article);}
				
			} catch (Exception e) {
				System.out.println("PlaceDAO - selectArticleList() (search)실패! : " + e.getMessage());
			} finally {
				// DB 자원 반환
				close(rs);
				close(pstmt);
			}
			
			return articleList;
		}

		public int pc_delete(int pc_num) {
			
			PreparedStatement pstmt = null;
			
			int deleteCount = 0;
			
			try {
				
				String sql = "delete from place_comment where pc_num = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, pc_num);
				
				deleteCount = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				System.out.println("PlaceDAO - pc_delete() : " + e.getMessage());
			}finally {
				close(pstmt);
			}
			
			return deleteCount;
		}
	
}
