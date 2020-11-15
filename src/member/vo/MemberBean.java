package member.vo;

import java.sql.*;

public class MemberBean {
	
	public MemberBean() {}

	private String id;
	private String pass;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String phone;
	private Date date;
	private int point;
	private String type;
	private int cp_3;
	private int cp_5;
	private int cp_10;
	
	public MemberBean(String id, String pass, String name, int age, String gender, String email, String phone,
			Date date, int point, String type, int cp_3, int cp_5, int cp_10) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.date = date;
		this.point = point;
		this.type = type;
		this.cp_3 = cp_3;
		this.cp_5 = cp_5;
		this.cp_10 = cp_10;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCp_3() {
		return cp_3;
	}

	public void setCp_3(int cp_3) {
		this.cp_3 = cp_3;
	}

	public int getCp_5() {
		return cp_5;
	}

	public void setCp_5(int cp_5) {
		this.cp_5 = cp_5;
	}

	public int getCp_10() {
		return cp_10;
	}

	public void setCp_10(int cp_10) {
		this.cp_10 = cp_10;
	}
}