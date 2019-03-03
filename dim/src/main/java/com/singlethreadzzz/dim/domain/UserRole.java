package com.singlethreadzzz.dim.domain;

import java.io.Serializable;

public class UserRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roleId;
	
	private String roleName;
	
	private String roleCnname;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCnname() {
		return roleCnname;
	}

	public void setRoleCnname(String roleCnname) {
		this.roleCnname = roleCnname;
	}
	
}
