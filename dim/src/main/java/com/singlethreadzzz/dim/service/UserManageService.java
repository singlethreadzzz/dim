package com.singlethreadzzz.dim.service;

import java.util.List;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.pojo.UserInfo;

public interface UserManageService {
	
	public User selectUserByUserAccount(String userAccount);
	
	public User selectUserByUserId (String userId);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUsers(List<String> userIdList);
	
	public List<User> getAllUsers();

	public List<UserInfo> getAllUsersInfo();

}
