package com.singlethreadzzz.dim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.Role;
import com.singlethreadzzz.dim.mapper.UserAuthMapper;
import com.singlethreadzzz.dim.service.UserAuthManagerService;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class UserAuthManagerServiceImpl implements UserAuthManagerService {
	
	@Autowired
	private UserAuthMapper userAuthMapper;

	@Override
	public Role selectUserRoleByUserId(String userId) {
		return this.userAuthMapper.selectUserRoleByUserId(userId);
	}

	@Override
	public void addUserRole(Role userRole) {
		userRole.setRoleId(UUIDUtils.getUUID());
		this.userAuthMapper.insertUserRole(userRole);
	}

	@Override
	public void updateUserRole(Role userRole) {
		this.userAuthMapper.updateUserByUserId(userRole);		
	}

	@Override
	public void deleteUserRole(String roleId) {
		this.userAuthMapper.deleteUserRoleByRoleId(roleId);		
	}

	@Override
	public List<Role> getAllUserRoles() {
		return this.userAuthMapper.selectAllUserRoles();
	}

	@Override
	public Role selectUserRoleByRoleId(String roleId) {
		return this.userAuthMapper.selectUserRoleByRoleId(roleId);
	}

	@Override
	public Role selectUserRoleByRoleName(String roleName) {
		return this.userAuthMapper.selectUserRoleByRoleName(roleName);
	}

}
