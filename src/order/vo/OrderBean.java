package order.vo;

import java.sql.Timestamp;

public class OrderBean {
	private long o_num;
	private String member_id;
	private int o_amount;
	private int o_price;
	private String o_pay;
	private Timestamp o_date;
	private int o_point;
	
	public long getO_num() {
		return o_num;
	}
	public void setO_num(long o_num) {
		this.o_num = o_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}	
	public int getO_amount() {
		return o_amount;
	}
	public void setO_amount(int o_amount) {
		this.o_amount = o_amount;
	}
	public int getO_price() {
		return o_price;
	}
	public void setO_price(int o_price) {
		this.o_price = o_price;
	}
	public String getO_pay() {
		return o_pay;
	}
	public void setO_pay(String o_pay) {
		this.o_pay = o_pay;
	}
	public Timestamp getO_date() {
		return o_date;
	}
	public void setO_date(Timestamp o_date) {
		this.o_date = o_date;
	}
	public int getO_point() {
		return o_point;
	}
	public void setO_point(int o_point) {
		this.o_point = o_point;
	}
	
	
	
	
	

	

	
	
	



}
