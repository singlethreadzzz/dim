package com.singlethreadzzz.dim.service.userManage.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singlethreadzzz.dim.domain.Role;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.mapper.userManage.UseRoleManageMapper;
import com.singlethreadzzz.dim.service.userManage.UserRoleManageService;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class UserRoleManageServiceImpl implements UserRoleManageService {
	
	@Autowired
	private UseRoleManageMapper useRoleManageMapper;

	@Override
	public Role getUserRoleByUserId(String userId) throws Exception {
		return this.useRoleManageMapper.selectUserRoleByUserId(userId);
	}

	@Override
	@RequiresRoles("admin")
	public void addUserRole(Role userRole) throws Exception {
		Role oldUserRole = new Role();
		oldUserRole = this.useRoleManageMapper.selectUserRoleByRoleName(userRole.getRoleName());
		if(oldUserRole != null) {
			throw new BeforeJsonException("角色已存在");
		}
		userRole.setRoleId(UUIDUtils.getUUID());
		this.useRoleManageMapper.insertUserRole(userRole);
	}

	@Override
	@RequiresRoles("admin")
	public void updateUserRole(Role userRole) throws Exception {
		Role oldUserRole = new Role();
		oldUserRole = this.useRoleManageMapper.selectUserRoleByRoleId(userRole.getRoleId());
		if(oldUserRole == null) {
			throw new BeforeJsonException("角色不存在");
		}
		oldUserRole.setRoleCnname(userRole.getRoleCnname());
		this.useRoleManageMapper.updateUserByUserId(oldUserRole);		
	}

	@Override
	@RequiresRoles("admin")
	@Transactional
	public void deleteUserRole(List<String> roleIdList) throws Exception {
		for(String roleId : roleIdList) {
			Role oldUserRole = new Role();
			oldUserRole = this.useRoleManageMapper.selectUserRoleByRoleId(roleId);
			if(oldUserRole == null) {
				throw new BeforeJsonException("角色不存在");
			}
			this.useRoleManageMapper.deleteUserRoleByRoleId(roleId);		
		}
	}

	@Override
	public List<Role> getAllUserRoles() throws Exception {
		return this.useRoleManageMapper.selectAllUserRoles();
	}

	@Override
	public Role getUserRoleByRoleId(String roleId) throws Exception {
		return this.useRoleManageMapper.selectUserRoleByRoleId(roleId);
	}

	@Override
	public Role getUserRoleByRoleName(String roleName) throws Exception {
		return this.useRoleManageMapper.selectUserRoleByRoleName(roleName);
	}

}
