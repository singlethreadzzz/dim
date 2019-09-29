package com.singlethreadzzz.dim.controller.userManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.Role;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.userManage.UserRoleManageService;

@Controller
@RequestMapping("/userManage")
public class UserRoleManageController {
	
	@Autowired
	private UserRoleManageService userRoleManageService;
	
	@GetMapping("/getAllUserRoles")
	@ResponseBody
	public Result getAllUserRoles () throws Exception  {
		Result result = new Result();
		List<Role> userRoleList = new ArrayList<Role>();
		try {
			userRoleList = this.userRoleManageService.getAllUserRoles();
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
	public Result addUserRole(Role userRole) throws Exception {
		
		Result result = new Result();
		
		try {
			this.userRoleManageService.addUserRole(userRole);
		}catch (BeforeJsonException e) {
			throw e;
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
	public Result updateUserRole(Role userRole) throws Exception {
		
		Result result = new Result();
		
		try {
			this.userRoleManageService.updateUserRole(userRole);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("修改角色失败");
		}
		result.setCode(1);
		result.setMessage("修改角色成功");
		result.setData(null);
		return result;
	}
	
	@RequiresRoles("admin")
	@PostMapping("/deleteUserRole")
	@ResponseBody
	public Result deleteUserRole(List<String> roleIdList) throws Exception {
		
		Result result = new Result();
		
		try {
			this.userRoleManageService.deleteUserRole(roleIdList);
		}catch (BeforeJsonException e) {
			throw e;
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
