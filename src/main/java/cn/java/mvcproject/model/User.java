package cn.java.mvcproject.model;

import java.util.Date;
/**
 * 用户类
 * @author Administrator
 *
 */
public class User {
	/**
	 * 用户编号
	 */
	private int id;
	/**
	 * 用户账号
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户电话
	 */
	private String phone;
	/**
	 * 用户地址
	 */
	private String address;
	/**
	 * 用户注册日期
	 */
	private Date regDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password, String phone, String address, Date regDate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
	} 
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", username=" + username + ", password=" + password
				+ ", phone=" + phone + ", address=" + address + ", regDate=" + regDate + "]\n";
	}
	
}
