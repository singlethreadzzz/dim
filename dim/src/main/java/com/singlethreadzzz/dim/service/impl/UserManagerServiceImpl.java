package com.singlethreadzzz.dim.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.mapper.UserMapper;
import com.singlethreadzzz.dim.service.UserManagerService;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class UserManagerServiceImpl implements UserManagerService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getAllUsers() {
		return userMapper.selectAllUsers();
	}
	
	public User selectUserByUserAccount(String userAccount) {
		return this.userMapper.selectUserByUserAccount(userAccount);
	}
	
	public void addUser(User user) {
		user.setUserId(UUIDUtils.getUUID());
		this.userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		this.userMapper.updateUserByUserId(user);	
	}

	@Override
	public void deleteUser(String userId) {
		this.userMapper.deleteUserByUserId(userId);		
	}

	@Override
	public User selectUserByUserId(String userId) {
		return this.userMapper.selectUserByUserId(userId);
	}

}
