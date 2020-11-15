package place.vo;

import java.sql.Date;

public class PlaceCommentBean {

	private int pc_num;
	private String pc_content;
	private Date pc_date;
	private String member_id;
	private int pc_rank;
	private int pl_num;
	
	public int getPc_num() {
		return pc_num;
	}
	public void setPc_num(int pc_num) {
		this.pc_num = pc_num;
	}
	public String getPc_content() {
		return pc_content;
	}
	public void setPc_content(String pc_content) {
		this.pc_content = pc_content;
	}
	public Date getPc_date() {
		return pc_date;
	}
	public void setPc_date(Date pc_date) {
		this.pc_date = pc_date;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPc_rank() {
		return pc_rank;
	}
	public void setPc_rank(int pc_rank) {
		this.pc_rank = pc_rank;
	}
	public int getPl_num() {
		return pl_num;
	}
	public void setPl_num(int pl_num) {
		this.pl_num = pl_num;
	}
	
	
}
