package com.singlethreadzzz.dim.service;

import java.util.List;

import com.singlethreadzzz.dim.domain.UserRole;

public interface UserAuthManagerService {
	
	public UserRole selectUserRoleByUserId(String userId);
	
	public UserRole selectUserRoleByRoleId(String roleId);
	
	public UserRole selectUserRoleByRoleName(String roleName);
	
	public void addUserRole(UserRole userRole);
	
	public void updateUserRole(UserRole userRole);
	
	public void deleteUserRole(String roleId);
	
	public List<UserRole> getAllUserRoles();

}
