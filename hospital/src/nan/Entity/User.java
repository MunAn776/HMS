package nan.Entity;

import java.sql.Date;

public class User {
	private Integer uid;
	private String uname;
	private String password;
	private String username;
	private String email;
	private Date create_time;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public User(String uname, String password, String username, String email, Date create_time) {
		super();
		this.uname = uname;
		this.password = password;
		this.username = username;
		this.email = email;
		this.create_time = create_time;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(String password, String username) {
		super();
		this.password = password;
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", password=" + password + ", username=" + username
				+ ", email=" + email + ", create_time=" + create_time + "]";
	}
	
}
