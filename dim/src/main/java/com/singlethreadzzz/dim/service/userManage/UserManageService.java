package com.singlethreadzzz.dim.service.userManage;

import java.util.List;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.pojo.UserInfo;

public interface UserManageService {
	
	public User selectUserByUserAccount(String userAccount) throws Exception;
	
	public User selectUserByUserId (String userId) throws Exception;
	
	public void addUser(User user) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void deleteUsers(List<String> userIdList) throws Exception;
	
	public List<User> getAllUsers() throws Exception;

	public List<UserInfo> getAllUsersInfo() throws Exception;

}
