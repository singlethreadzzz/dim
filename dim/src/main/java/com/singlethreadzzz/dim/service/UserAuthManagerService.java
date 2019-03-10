package com.singlethreadzzz.dim.service;

import java.util.List;

import com.singlethreadzzz.dim.domain.Role;

public interface UserAuthManagerService {
	
	public Role selectUserRoleByUserId(String userId);
	
	public Role selectUserRoleByRoleId(String roleId);
	
	public Role selectUserRoleByRoleName(String roleName);
	
	public void addUserRole(Role userRole);
	
	public void updateUserRole(Role userRole);
	
	public void deleteUserRole(String roleId);
	
	public List<Role> getAllUserRoles();

}
