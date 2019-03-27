package com.singlethreadzzz.dim.service.userManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.mapper.userManage.UserManageMapper;
import com.singlethreadzzz.dim.pojo.UserInfo;
import com.singlethreadzzz.dim.service.userManage.UserManageService;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class UserManageServiceImpl implements UserManageService{
	
	@Autowired
	private UserManageMapper userMapper;
	
	@Override
	public List<User> getAllUsers() throws Exception {
		return userMapper.selectAllUsers();
	}
	
	public User selectUserByUserAccount(String userAccount) throws Exception {
		return this.userMapper.selectUserByUserAccount(userAccount);
	}
	
	public void addUser(User user) throws Exception {
		user.setUserId(UUIDUtils.getUUID());
		this.userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		this.userMapper.updateUserByUserId(user);	
	}

	@Override
	public void deleteUsers(List<String> userIdList) throws Exception {
		userIdList.forEach(x -> {
			User oldUser = new User();
			oldUser = this.userMapper.selectUserByUserId(x);
			this.userMapper.deleteUserByUserId(oldUser.getUserId());
		});
	}

	@Override
	public User selectUserByUserId(String userId) throws Exception {
		return this.userMapper.selectUserByUserId(userId);
	}
	
	@Override
	public List<UserInfo> getAllUsersInfo() throws Exception {
		return this.userMapper.selectAllUsersInfo();
	}

}
