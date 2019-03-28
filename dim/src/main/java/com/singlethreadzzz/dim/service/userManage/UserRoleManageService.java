package com.singlethreadzzz.dim.service.userManage;

import java.util.List;

import com.singlethreadzzz.dim.domain.Role;

public interface UserRoleManageService {
	
	public Role getUserRoleByUserId(String userId) throws Exception;
	
	public Role getUserRoleByRoleId(String roleId) throws Exception;
	
	public Role getUserRoleByRoleName(String roleName) throws Exception;
	
	public void addUserRole(Role userRole) throws Exception;
	
	public void updateUserRole(Role userRole) throws Exception;
	
	public void deleteUserRole(List<String> roleIdList) throws Exception;
	
	public List<Role> getAllUserRoles() throws Exception;

}
