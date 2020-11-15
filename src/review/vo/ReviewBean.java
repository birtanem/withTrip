package review.vo;

import java.sql.*;

public class ReviewBean {

	private int r_num; // 리뷰 글 번호
	private String r_id; // 리뷰 작성 아이디
	private String r_subject; // 리뷰 글 제목
	private String r_content; // 리뷰 글 내용
	private int r_readcount; // 리뷰 글 조회수
	private int r_likecount; // 리뷰 글 좋아요 수
	private Date r_date; // 리뷰 글 작성 날짜
	private String r_image; // 리뷰 글 이미지
	private int r_code; // region 번호
	private String r_name; // region 이름
	private int r_cnt; // 댓글 개수
	
	
	public ReviewBean() {}

	public ReviewBean(int r_num, String r_id, String r_subject, String r_content, int r_readcount, int r_likecount,
			Date r_date, String r_image, int r_code, String r_name, int r_cnt) {
		super();
		this.r_num = r_num;
		this.r_id = r_id;
		this.r_subject = r_subject;
		this.r_content = r_content;
		this.r_readcount = r_readcount;
		this.r_likecount = r_likecount;
		this.r_date = r_date;
		this.r_image = r_image;
		this.r_code = r_code;
		this.r_name = r_name;
		this.r_cnt = r_cnt;
		
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public int getR_cnt() {
		return r_cnt;
	}

	public void setR_cnt(int r_cnt) {
		this.r_cnt = r_cnt;
	}

	public int getR_num() {
		return r_num;
	}

	public void setR_num(int r_num) {
		this.r_num = r_num;
	}

	public String getR_id() {
		return r_id;
	}

	public void setR_id(String r_id) {
		this.r_id = r_id;
	}

	public String getR_subject() {
		return r_subject;
	}

	public void setR_subject(String r_subject) {
		this.r_subject = r_subject;
	}

	public String getR_content() {
		return r_content;
	}

	public void setR_content(String r_content) {
		this.r_content = r_content;
	}

	public int getR_readcount() {
		return r_readcount;
	}

	public void setR_readcount(int r_readcount) {
		this.r_readcount = r_readcount;
	}

	public int getR_likecount() {
		return r_likecount;
	}

	public void setR_likecount(int r_likecount) {
		this.r_likecount = r_likecount;
	}

	public Date getR_date() {
		return r_date;
	}

	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}

	public String getR_image() {
		return r_image;
	}

	public void setR_image(String r_image) {
		this.r_image = r_image;
	}

	public int getR_code() {
		return r_code;
	}

	public void setR_code(int r_code) {
		this.r_code = r_code;
	}
	
}
