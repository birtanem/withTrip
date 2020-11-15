package cart.vo;

public class CartBean {
	private int c_num; // 장바구니 번호
	private String c_member_id; // 사용자 아이디 // 값 가지고 다니기
	private int c_p_num; // 상품 번호 // 상품테이블 외래키 생성
	private int c_p_amount; // 수량
	
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_member_id() {
		return c_member_id;
	}
	public void setC_member_id(String c_member_id) {
		this.c_member_id = c_member_id;
	}
	public int getC_p_num() {
		return c_p_num;
	}
	public void setC_p_num(int c_p_num) {
		this.c_p_num = c_p_num;
	}
	public int getC_p_amount() {
		return c_p_amount;
	}
	public void setC_p_amount(int c_p_amount) {
		this.c_p_amount = c_p_amount;
	}
	


	
	
	
	

	
	
}
