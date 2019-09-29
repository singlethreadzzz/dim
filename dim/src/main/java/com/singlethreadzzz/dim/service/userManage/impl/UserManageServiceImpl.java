package com.singlethreadzzz.dim.service.userManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.mapper.userManage.UserManageMapper;
import com.singlethreadzzz.dim.pojo.UserInfo;
import com.singlethreadzzz.dim.service.userManage.UserManageService;
import com.singlethreadzzz.dim.util.EncryptUtils;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class UserManageServiceImpl implements UserManageService{
	
	@Autowired
	private UserManageMapper userManageMapper;
	
	@Override
	public List<User> getAllUsers() throws Exception {
		return userManageMapper.selectAllUsers();
	}
	
	public User getUserByUserAccount(String userAccount) throws Exception {
		return this.userManageMapper.selectUserByUserAccount(userAccount);
	}
	
	public void addUser(User user) throws Exception {
		User oldUser = new User();
		oldUser = this.userManageMapper.selectUserByUserAccount(user.getUserAccount());
		if(oldUser != null) {
			throw new BeforeJsonException("用户已存在");
		}
		user.setUserId(UUIDUtils.getUUID());
		user.setUserPassword(EncryptUtils.shiroSHA256(user.getUserAccount(), user.getUserPassword()));
		this.userManageMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) throws Exception {
		User oldUser = new User();
		oldUser = this.userManageMapper.selectUserByUserId(user.getUserId());
		if(oldUser == null) {
			throw new BeforeJsonException("用户不存在");
		}
		oldUser.setUserPassword(EncryptUtils.shiroSHA256(user.getUserAccount(), user.getUserPassword()));
		oldUser.setUserName(user.getUserName());
		oldUser.setRoleId(user.getRoleId());
		this.userManageMapper.updateUserByUserId(oldUser);	
	}

	@Override
	@Transactional
	public void deleteUsers(List<String> userIdList) throws Exception {
		for(String userId : userIdList) {
			User oldUser = new User();
			oldUser = this.userManageMapper.selectUserByUserId(userId);
			if(oldUser == null) {
				throw new BeforeJsonException("用户不存在");
			}
			this.userManageMapper.deleteUserByUserId(userId);
		}
	}

	@Override
	public User getUserByUserId(String userId) throws Exception {
		return this.userManageMapper.selectUserByUserId(userId);
	}
	
	@Override
	public List<UserInfo> getAllUsersInfo() throws Exception {
		return this.userManageMapper.selectAllUsersInfo();
	}

}
