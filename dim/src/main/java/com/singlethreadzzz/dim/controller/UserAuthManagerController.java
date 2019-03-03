package com.singlethreadzzz.dim.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.UserManagerService;
import com.singlethreadzzz.dim.util.EncryptUtils;

@Controller
public class UserAuthManagerController {
	
	@Autowired
	private UserManagerService userManagerService;
	
	@GetMapping("/userManager")
	@ResponseBody
	public Result getAllUsers () throws Exception  {
		Result result = new Result();
		List<User> userList = new ArrayList<User>();
		try {
			userList = this.userManagerService.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询全部用户信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部用户信息成功");
		result.setData(userList);
		return result;
	}

	@RequiresRoles("admin")
	@PostMapping("/addUser")
	@ResponseBody
	public Result addUser(User user) throws Exception {
		
		Result result = new Result();
		
		User oldUser = new User();
		try {
			oldUser = this.userManagerService.selectUserByUserAccount(user.getUserAccount());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询用户信息失败");
		}
		
		if(oldUser != null) {
			throw new BeforeJsonException("用户已存在");
		}
		
		user.setUserPassword(EncryptUtils.shiroSHA256(user.getUserAccount(), user.getUserPassword()));
		try {
			this.userManagerService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("新增用户失败");
		}
		result.setCode(1);
		result.setMessage("新增用户成功");
		result.setData(null);
		return result;
	}
	
	@RequiresRoles("admin")
	@PostMapping("/updateUser")
	@ResponseBody
	public Result updateUser(User user) throws Exception {
		
		Result result = new Result();
		
		User oldUser = new User();
		try {
			oldUser = this.userManagerService.selectUserByUserAccount(user.getUserAccount());
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询用户信息失败");
		}
		
		if(oldUser == null) {
			throw new BeforeJsonException("用户不存在");
		}
		
		oldUser.setUserPassword(EncryptUtils.shiroSHA256(user.getUserAccount(), user.getUserPassword()));
		oldUser.setUserName(user.getUserName());
		oldUser.setRoleId(user.getRoleId());
		
		try {
			this.userManagerService.updateUser(oldUser);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("新增用户失败");
		}
		result.setCode(1);
		result.setMessage("新增用户成功");
		result.setData(null);
		return result;
	}
	
	@RequiresRoles("admin")
	@PostMapping("/deleteUser")
	@ResponseBody
	public Result deleteUser(String userId) throws Exception {
		
		Result result = new Result();
		
		User oldUser = new User();
		try {
			oldUser = this.userManagerService.selectUserByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询用户信息失败");
		}
		
		if(oldUser == null) {
			throw new BeforeJsonException("用户不存在");
		}
		
		try {
			this.userManagerService.deleteUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("删除用户失败");
		}
		result.setCode(1);
		result.setMessage("删除用户成功");
		result.setData(null);
		return result;
	}
}