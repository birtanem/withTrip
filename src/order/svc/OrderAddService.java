package order.svc;

import static common.db.JdbcUtil.*;

import java.sql.Connection;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import com.sun.org.apache.regexp.internal.recompile;

import order.dao.OrderDAO;
import order.vo.OrderBean;
import order.vo.OrderDetailBean;

public class OrderAddService {
	
	/*
	 *  insertOrderList() 메서드 실행 시 주문에 필요한 모든 작업이 처리됨
	 *  모든 작업이 성공하면 커밋
	 *  하나라도 실패하면 전체 롤백
	 *  
	 *  1. order 저장 (oder_seq 업뎃)
	 *  2. order_detail 저장
	 *  3. product 수량 빼기
	 *  4. cart 삭제
	 *  5. 포인트 적립
	 *  
	 */
	public String insertOrderList(OrderBean ob, JSONArray jsonArray) {
		
		boolean insertSuccess = false;
		boolean updateSuccess = false;
		boolean deleteSuccess = false;
		boolean savePointSuccess = false;
		int amount = 0;
		
		Connection con = getConnection();
		
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		orderDAO.setConnection(con);
		
		// order 저장
		String orderNum = orderDAO.insertOrderList(ob, jsonArray.size());
		
		if(orderNum != null) {
			
			// order_seq 업데이트
			orderDAO.updateSequence();
			
			// order_detail 저장
			for(int i=0;i<jsonArray.size();i++) {
				
				JSONObject obj = (JSONObject)jsonArray.get(i);

				int insertCount = orderDAO.insertOrderDetailList(obj, orderNum);
				
				if(insertCount > 0) {
					insertSuccess = true;					
				}else {
					insertSuccess = false;
					break;
				}
			}		
			
			if(!insertSuccess) { // order_detail 저장 실패!
				orderNum = "주문상세에서 문제가 발생하였습니다!";
				rollback(con);
			}else { // order_detail 저장 성공!

				// product 저장
				for(int i=0;i<jsonArray.size();i++) {
					JSONObject obj = (JSONObject)jsonArray.get(i);
					int updateCount2 = orderDAO.updateProduntAmount(Integer.parseInt((String)obj.get("num")), Integer.parseInt((String)obj.get("amount")));
					if(updateCount2 > 0) {						
						amount = orderDAO.getProductAmount(Integer.parseInt((String)obj.get("num")));		
						updateSuccess = true;
					}else {
						updateSuccess = false;
						break;
					}					
				}
			if(amount < 0) {
				orderNum = "품절된 상품이 포함되었습니다!";
				rollback(con);
			}else if(!updateSuccess) {
				orderNum = "상품수량에서 문제가 발생하였습니다!";
				rollback(con);
			}else { // product 저장 성공!
					
				
					// cart 삭제
					for(int i=0;i<jsonArray.size();i++) {
						JSONObject obj = (JSONObject)jsonArray.get(i);
												
							int deleteCount = orderDAO.deleteCart(Integer.parseInt((String)obj.get("num")), ob.getMember_id());
							if(deleteCount > 0) {
								deleteSuccess = true;
							}else {					
								deleteSuccess = true;
//								break;
							}

					}
					
					if(!deleteSuccess) { // cart 삭제 실패!
						
						orderNum = "장바구니에서 문제가 발생하였습니다!";
						rollback(con);
					}else { // cart 삭제 성공!
						
						// 포인트 적립
						//  사용포인트 차감, 구매금액 1퍼 적립						
						int savePoint = (int)((ob.getO_price()-ob.getO_point())*0.01);
						// sql 이 적립한다고 + 되어있음.  차감 위해서 - 로 전달?
						int minusPoint = (int)(ob.getO_point()*-1);					
						int updateCount =  orderDAO.updateSavePoint(ob.getMember_id(), minusPoint);

						if(updateCount > 0) {							
							int updateCount2 = orderDAO.updateSavePoint(ob.getMember_id(), savePoint);								
							if(updateCount2 > 0) {								
								savePointSuccess =  true;
							}																
						}						
						if(!savePointSuccess) { // 포인트 적립 실패!
							orderNum = "포인트적립에서 문제가 발생하였습니다!";
							rollback(con);
						}else { // 포인트 적립 성공!
							
							// 모든 과정이 에러없이 실행되면 커밋
							commit(con);
						}
					}					
				}
			}
		}
		System.out.println("orderNum : "+orderNum);
		close(con);
		return orderNum;
	}
}
