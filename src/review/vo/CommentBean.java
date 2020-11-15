package review.vo;

import java.sql.Date;

public class CommentBean {

	private int rc_num;
	private int r_num;
	private String rc_id;
	private String rc_content;
	private Date rc_date;
	private int rc_ref;
	private int rc_lev;
	private int rc_seq;
	
	public CommentBean() {}

	public CommentBean(int rc_num, int r_num, String rc_id, String rc_content, Date rc_date, int rc_ref, int rc_lev,
			int rc_seq) {
		super();
		this.rc_num = rc_num;
		this.r_num = r_num;
		this.rc_id = rc_id;
		this.rc_content = rc_content;
		this.rc_date = rc_date;
		this.rc_ref = rc_ref;
		this.rc_lev = rc_lev;
		this.rc_seq = rc_seq;
	}

	public int getRc_num() {
		return rc_num;
	}

	public void setRc_num(int rc_num) {
		this.rc_num = rc_num;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getRc_id() {
		return rc_id;
	}

	public void setRc_id(String rc_id) {
		this.rc_id = rc_id;
	}

	public String getRc_content() {
		return rc_content;
	}

	public void setRc_content(String rc_content) {
		this.rc_content = rc_content;
	}

	public Date getRc_date() {
		return rc_date;
	}

	public void setRc_date(Date rc_date) {
		this.rc_date = rc_date;
	}

	public int getRc_ref() {
		return rc_ref;
	}

	public void setRc_ref(int rc_ref) {
		this.rc_ref = rc_ref;
	}

	public int getRc_lev() {
		return rc_lev;
	}

	public void setRc_lev(int rc_lev) {
		this.rc_lev = rc_lev;
	}

	public int getRc_seq() {
		return rc_seq;
	}

	public void setRc_seq(int rc_seq) {
		this.rc_seq = rc_seq;
	}

}
