package com.singlethreadzzz.dim.service;

import java.util.List;

import com.singlethreadzzz.dim.domain.User;

public interface UserManagerService {
	
	public User selectUserByUserAccount(String userAccount);
	
	public User selectUserByUserId (String userId);
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(String userId);
	
	public List<User> getAllUsers();

}
