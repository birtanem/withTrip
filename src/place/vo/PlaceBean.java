package place.vo;

import java.sql.Date;

public class PlaceBean {

	private int pl_num;
	private int region_code;
	private String pl_name;
	private String pl_content;
	private String pl_address;
	private String pl_image;
	private int pl_readcount;
	private int pl_likecount;
	private Date pl_date;
	private String pl_theme;
	private double pl_likeAvg;
	
	public double getPl_likeAvg() {
		return pl_likeAvg;
	}
	public void setPl_likeAvg(double pl_likeAvg) {
		this.pl_likeAvg = pl_likeAvg;
	}
	public int getPl_num() {
		return pl_num;
	}
	public void setPl_num(int pl_num) {
		this.pl_num = pl_num;
	}
	public int getRegion_code() {
		return region_code;
	}
	public void setRegion_code(int region_code) {
		this.region_code = region_code;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public String getPl_content() {
		return pl_content;
	}
	public void setPl_content(String pl_content) {
		this.pl_content = pl_content;
	}
	public String getPl_address() {
		return pl_address;
	}
	public void setPl_address(String pl_address) {
		this.pl_address = pl_address;
	}
	public String getPl_image() {
		return pl_image;
	}
	public void setPl_image(String pl_image) {
		this.pl_image = pl_image;
	}
	public int getPl_readcount() {
		return pl_readcount;
	}
	public void setPl_readcount(int pl_readcount) {
		this.pl_readcount = pl_readcount;
	}
	public int getPl_likecount() {
		return pl_likecount;
	}
	public void setPl_likecount(int pl_likecount) {
		this.pl_likecount = pl_likecount;
	}
	public Date getPl_date() {
		return pl_date;
	}
	public void setPl_date(Date pl_date) {
		this.pl_date = pl_date;
	}
	public String getPl_theme() {
		return pl_theme;
	}
	public void setPl_theme(String pl_theme) {
		this.pl_theme = pl_theme;
	}
	
	
}