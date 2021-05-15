package nan.Entity;

import java.util.Date;

public class Doctor {
	private Integer did;	//主键
	private String dname;	//名字
	private String cardno;	//身份证
	private String phone;	//手机
	//性别 1男 0女
	private Integer gender;	
	private Integer age;	//年龄
	private Date birthday;	//生日
	private String email;	//邮箱
	//科室 1急诊科 2儿科 3妇科 4皮肤科 5内分泌科 6牙科
	private Integer department;	
	//学位 1专科 2本科 3研究生 4博士
	private Integer degree;	
	private String mark;	//简介
	
	
	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Doctor(String dname, String cardno, String phone, Integer gender, Integer age, Date birthday, String email,
			Integer department, Integer degree, String mark) {
		super();
		this.dname = dname;
		this.cardno = cardno;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
		this.degree = degree;
		this.mark = mark;
	}

	public Doctor(Integer did, String dname, String cardno, String phone, Integer gender, Integer age, Date birthday,
			String email, Integer department, Integer degree, String mark) {
		super();
		this.did = did;
		this.dname = dname;
		this.cardno = cardno;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
		this.degree = degree;
		this.mark = mark;
	}

	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Doctor [did=" + did + ", dname=" + dname + ", cardno=" + cardno + ", phone=" + phone + ", gender="
				+ gender + ", age=" + age + ", birthday=" + birthday + ", email=" + email + ", department=" + department
				+ ", degree=" + degree + ", mark=" + mark + "]";
	}
	
	
}
