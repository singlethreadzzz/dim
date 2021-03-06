package com.singlethreadzzz.dim.controller.userManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.pojo.UserInfo;
import com.singlethreadzzz.dim.service.userManage.UserManageService;

@Controller
@RequestMapping("/userManage")
public class UserManageController {
	
	@Autowired
	private UserManageService userManagerService;
	
	@GetMapping("/getAllUsers")
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
	
	@GetMapping("/getAllUsersInfo")
	@ResponseBody
	public Result getAllUsersInfo () throws Exception  {
		Result result = new Result();
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		try {
			userInfoList = this.userManagerService.getAllUsersInfo();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询全部用户信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部用户信息成功");
		result.setData(userInfoList);
		return result;
	}

	@RequiresRoles("admin")
	@PostMapping("/addUser")
	@ResponseBody
	public Result addUser(@RequestBody User user) throws Exception {
		
		Result result = new Result();
		
		try {
			this.userManagerService.addUser(user);
		}catch (BeforeJsonException e) {
			throw e;
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
	public Result updateUser(@RequestBody User user) throws Exception {
		
		Result result = new Result();
		
		try {
			this.userManagerService.updateUser(user);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("修改用户失败");
		}
		result.setCode(1);
		result.setMessage("修改用户成功");
		result.setData(null);
		return result;
	}
	
	@RequiresRoles("admin")
	@DeleteMapping("/deleteUser")
	@ResponseBody
	public Result deleteUser(@RequestBody List<String> userIdList) throws Exception {
		
		Result result = new Result();
		
		try {
			this.userManagerService.deleteUsers(userIdList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("删除用户失败");
		}
		result.setCode(1);
		result.setMessage("删除用户成功");
		result.setData(null);
		return result;
	}
	
	@GetMapping("/getUserByUserId")
	@ResponseBody
	public Result getUserByUserId (@RequestParam String userId) throws Exception  {
		Result result = new Result();
		User user = new User();
		try {
			user = this.userManagerService.getUserByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询用户信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询用户信息成功");
		result.setData(user);
		return result;
	}
}
