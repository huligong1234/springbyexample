package org.jeedevframework.web.domain;

import java.io.Serializable;

public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String userName;
	private String password;
	
	public SysUser() {
		super();
	}
	
	public SysUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public SysUser(Integer id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", userName=" + userName + ", password="
				+ password + "]";
	}
}
