package com.singlethreadzzz.dim.service.userManage;

import java.util.List;

import com.singlethreadzzz.dim.domain.Role;

public interface UserRoleManageService {
	
	public Role selectUserRoleByUserId(String userId);
	
	public Role selectUserRoleByRoleId(String roleId);
	
	public Role selectUserRoleByRoleName(String roleName);
	
	public void addUserRole(Role userRole);
	
	public void updateUserRole(Role userRole);
	
	public void deleteUserRole(String roleId);
	
	public List<Role> getAllUserRoles();

}
