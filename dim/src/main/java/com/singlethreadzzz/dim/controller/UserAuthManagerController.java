package com.singlethreadzzz.dim.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.UserRole;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.UserAuthManagerService;

@Controller
public class UserAuthManagerController {
	
	@Autowired
	private UserAuthManagerService userAuthManagerService;
	
	@GetMapping("/userAuthManager")
	@ResponseBody
	public Result getAllUsers () throws Exception  {
		Result result = new Result();
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		try {
			userRoleList = this.userAuthManagerService.getAllUserRoles();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询全部角色信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部角色信息成功");
		result.setData(userRoleList);
		return result;
	}

	@RequiresRoles("admin")
	@PostMapping("/addUserRole")
	@ResponseBody
	public Result addUserRole(UserRole userRole) throws Exception {
		
		Result result = new Result();
		
		UserRole oldUserRole = new UserRole();
		try {
			oldUserRole = this.userAuthManagerService.selectUserRoleByRoleName(userRole.getRoleName());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询角色信息失败");
		}
		
		if(oldUserRole != null) {
			throw new BeforeJsonException("角色已存在");
		}
		
		try {
			this.userAuthManagerService.addUserRole(userRole);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("新增角色失败");
		}
		result.setCode(1);
		result.setMessage("新增角色成功");
		result.setData(null);
		return result;
	}
	
	@RequiresRoles("admin")
	@PostMapping("/updateUserRole")
	@ResponseBody
	public Result updateUserRole(UserRole userRole) throws Exception {
		
		Result result = new Result();
		
		UserRole oldUserRole = new UserRole();
		try {
			oldUserRole = this.userAuthManagerService.selectUserRoleByRoleId(userRole.getRoleId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询角色信息失败");
		}
		
		if(oldUserRole == null) {
			throw new BeforeJsonException("角色不存在");
		}
		
		oldUserRole.setRoleCnname(userRole.getRoleCnname());
		
		try {
			this.userAuthManagerService.updateUserRole(oldUserRole);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("新增角色失败");
		}
		result.setCode(1);
		result.setMessage("新增角色成功");
		result.setData(null);
		return result;
	}
	
	@RequiresRoles("admin")
	@PostMapping("/deleteUserRole")
	@ResponseBody
	public Result deleteUserRole(String roleId) throws Exception {
		
		Result result = new Result();
		
		UserRole oldUserRole = new UserRole();
		try {
			oldUserRole = this.userAuthManagerService.selectUserRoleByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询角色信息失败");
		}
		
		if(oldUserRole == null) {
			throw new BeforeJsonException("角色不存在");
		}
		
		try {
			this.userAuthManagerService.deleteUserRole(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("删除角色失败");
		}
		result.setCode(1);
		result.setMessage("删除角色成功");
		result.setData(null);
		return result;
	}
}
