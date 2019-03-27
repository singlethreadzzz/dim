package com.singlethreadzzz.dim.mapper.userManage;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.pojo.UserInfo;

@Repository
public interface UserManageMapper {
	
	@Results(id = "User", value = 
		{ @Result(property = "userId", column = "USER_ID"), 
		       @Result(property = "userAccount", column = "USER_ACCOUNT"),
		       @Result(property = "userName", column = "USER_NAME"),
		       @Result(property = "userPassword", column = "USER_PASSWORD"),
		       @Result(property = "roleId", column = "ROLE_ID")
		})
	@Select("select USER_ID,USER_ACCOUNT,USER_NAME,USER_PASSWORD,ROLE_ID from dim_user where USER_ID = #{userId}")
	public User selectUserByUserId(@Param("userId") String userId);

	@Insert("insert into dim_user(USER_ID,USER_ACCOUNT,USER_NAME,USER_PASSWORD,ROLE_ID) values(#{userId}, #{userAccount}, #{userName}, #{userPassword}, #{roleId})")
	public void insertUser(User user);

	@Delete("delete from dim_user where USER_ID = #{userId}")
	public void deleteUserByUserId(@Param("userId") String userId);

	@Update("update dim_user set USER_PASSWORD = #{userPassword},USER_NAME = #{userName} where USER_ID = #{userId}")
	public void updateUserByUserId(User managerInfo);

    @ResultMap("User")
	@Select("select USER_ID,USER_ACCOUNT,USER_NAME,USER_PASSWORD,ROLE_ID from dim_user")
	public List<User> selectAllUsers();
    
    @ResultMap("User")
	@Select("select USER_ID,USER_ACCOUNT,USER_NAME,USER_PASSWORD,ROLE_ID from dim_user where USER_ACCOUNT = #{userAccount}")
	public User selectUserByUserAccount(@Param("userAccount") String userAccount);
    
	@Results(id = "UserInfo", value =
		{ @Result(property = "userId", column = "USER_ID"),
		  @Result(property = "userAccount", column = "USER_ACCOUNT"), 
		  @Result(property = "userName", column = "USER_NAME"),
		  @Result(property = "roleName", column = "ROLE_NAME"),
		  @Result(property = "roleCnname", column = "ROLE_CNNAME")})
	@Select("select a.USER_ID,a.USER_ACCOUNT,a.USER_NAME,b.ROLE_NAME,b.ROLE_CNNAME from dim_user a,dim_role b where a.ROLE_ID = b.ROLE_ID and a.USER_ACCOUNT != 'admin'")
	public List<UserInfo> selectAllUsersInfo();

}