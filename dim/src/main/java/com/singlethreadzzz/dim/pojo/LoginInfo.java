package com.singlethreadzzz.dim.pojo;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class LoginInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String password;
	
	private Boolean isRememberMe;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsRememberMe() {
		return isRememberMe;
	}

	public void setIsRememberMe(Boolean isRememberMe) {
		this.isRememberMe = isRememberMe;
	}
	
	
}
