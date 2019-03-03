package com.singlethreadzzz.dim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.UserRole;
import com.singlethreadzzz.dim.mapper.UserAuthMapper;
import com.singlethreadzzz.dim.service.UserAuthManagerService;

@Service
public class UserAuthManagerServiceImpl implements UserAuthManagerService {
	
	@Autowired
	private UserAuthMapper userAuthMapper;

	@Override
	public UserRole selectUserRoleByUserId(String userId) {
		return this.userAuthMapper.selectUserRoleByUserId(userId);
	}

	@Override
	public void addUserRole(UserRole userRole) {
		this.userAuthMapper.insertUserRole(userRole);
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		this.userAuthMapper.updateUserByUserId(userRole);		
	}

	@Override
	public void deleteUserRole(String roleId) {
		this.userAuthMapper.deleteUserRoleByRoleId(roleId);		
	}

	@Override
	public List<UserRole> getAllUserRoles() {
		return this.userAuthMapper.selectAllUserRoles();
	}

	@Override
	public UserRole selectUserRoleByRoleId(String roleId) {
		return this.userAuthMapper.selectUserRoleByRoleId(roleId);
	}

	@Override
	public UserRole selectUserRoleByRoleName(String roleName) {
		return this.userAuthMapper.selectUserRoleByRoleName(roleName);
	}

}
