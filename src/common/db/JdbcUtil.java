package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	// DB 관련 기본 기능(연결, 자원반환, commit, rollback 등)을 담당하는 클래스
	// 정의하는 메서드 모두 인스턴스 생성없이 호출하도록 static 메서드로 정의
	// 1. DBCP 기능을 활용한 Connection 객체를 가져오는 getConnection() 메서드
	public static Connection getConnection() {
		
		Connection con = null; // DBCP 로부터 전달받은 Connection 객체를 저장할 변수 선언
		
		try {
			// context.xml 의 <Context> 태그 설정을 가져오기
			Context initCtx = new InitialContext();
			// context.xml 의 <Context> 태그 내의 <Resource> 태그 부분 가져오기
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// context.xml 의 JNDI(name 속성) 가져오기
			DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQL");
			// DataSource 객체의 getConnection() 메서드를 호출하여 Connection 객체 가져오기
			con = ds.getConnection();
			
			// DB 작업에 대한 Auto Commit 기능 해제
			con.setAutoCommit(false); // 기본값은 true (=Auto Commit 활성화)
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return con; // Connection 객체 리턴
	}
	
	// 메서드 오버로딩을 활용하여 Connection, PreparedStatement, ResultSet 객체 자원 반환 구현
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Auto Commit 해제로 인해 Commit, Rollback 기능을 수행하는 각각의 메서드 정의
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
