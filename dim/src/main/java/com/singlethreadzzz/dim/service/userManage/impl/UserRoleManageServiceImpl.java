package com.singlethreadzzz.dim.service.userManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.Role;
import com.singlethreadzzz.dim.mapper.userManage.UseRoleManageMapper;
import com.singlethreadzzz.dim.service.userManage.UserRoleManageService;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class UserRoleManageServiceImpl implements UserRoleManageService {
	
	@Autowired
	private UseRoleManageMapper useRoleManageMapper;

	@Override
	public Role selectUserRoleByUserId(String userId) {
		return this.useRoleManageMapper.selectUserRoleByUserId(userId);
	}

	@Override
	public void addUserRole(Role userRole) {
		userRole.setRoleId(UUIDUtils.getUUID());
		this.useRoleManageMapper.insertUserRole(userRole);
	}

	@Override
	public void updateUserRole(Role userRole) {
		this.useRoleManageMapper.updateUserByUserId(userRole);		
	}

	@Override
	public void deleteUserRole(String roleId) {
		this.useRoleManageMapper.deleteUserRoleByRoleId(roleId);		
	}

	@Override
	public List<Role> getAllUserRoles() {
		return this.useRoleManageMapper.selectAllUserRoles();
	}

	@Override
	public Role selectUserRoleByRoleId(String roleId) {
		return this.useRoleManageMapper.selectUserRoleByRoleId(roleId);
	}

	@Override
	public Role selectUserRoleByRoleName(String roleName) {
		return this.useRoleManageMapper.selectUserRoleByRoleName(roleName);
	}

}
