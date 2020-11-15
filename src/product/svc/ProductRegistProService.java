package product.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import product.dao.ProductDAO;
import product.vo.ProductBean;

public class ProductRegistProService {

	public boolean registArticle(ProductBean productBean) {
		System.out.println("서비스 - 상품 등록 메소드");
		
		boolean isRegistSucces = false;
		
		// DB 작업을 위한 준비
		// 커넥션 객체 가져오기
		Connection con = getConnection();
		
		// DB 작업을 위해 싱글톤 패턴으로 생성된 ProductDAO 가져오기.
		ProductDAO productDAO = ProductDAO.getInstance();
		
		// ProductDAO 객체에 Connection 객체 전달
		productDAO.setConnection(con);
		
		// ProductDAO의 insertArticle() 메서드 호출하여 글 등록 처리
		// => 파라미터: ProductBean 객체, 리턴 타입 int(insertCount)
		int insertCount = productDAO.insertArticle(productBean);
		
		// 리턴 받은 작업 결과 판별
		if(insertCount > 0) {
			commit(con);
			isRegistSucces = true;
		} else {
			rollback(con);
		}
		
		// 커넥션 객체 반환
		close(con);
		
		// 결과 리턴 
		return isRegistSucces;
	}

}
