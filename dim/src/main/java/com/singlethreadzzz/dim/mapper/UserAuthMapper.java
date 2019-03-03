package com.singlethreadzzz.dim.mapper;

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

import com.singlethreadzzz.dim.domain.UserRole;

@Repository
public interface UserAuthMapper {
	
	@Results(id = "UserRole", value = 
		{ @Result(property = "roleId", column = "ROLE_ID"),
		  @Result(property = "roleName", column = "ROLE_NAME"), 
		  @Result(property = "roleCnname", column = "ROLE_CNNAME")})
	@Select("select a.ROLE_ID,a.ROLE_NAME,a.ROLE_CNNAME from dim_role a,dim_user b where B.ROLE_ID = A.ROLE_ID and b.USER_ID = #{userId}")
	public UserRole selectUserRoleByUserId(@Param("userId") String userId);
	
	@Insert("insert into dim_role(ROLE_ID,ROLE_NAME,ROLE_CNNAME) values(#{roleId}, #{roleName}, #{roleCnname})")
	public void insertUserRole(UserRole userRole);

	@Delete("delete from dim_role where ROLE_ID = #{roleId}")
	public void deleteUserRoleByRoleId(@Param("roleId") String roleId);

	@Update("update dim_role set ROLE_NAME = #{roleName},ROLE_CNNAME = #{roleCnname} where ROLE_ID = #{roleId}")
	public void updateUserByUserId(UserRole userRole);

    @ResultMap("UserRole")
	@Select("select ROLE_ID,ROLE_NAME,ROLE_CNNAME from dim_role")
	public List<UserRole> selectAllUserRoles();
   
    @ResultMap("UserRole")
	@Select("select ROLE_ID,ROLE_NAME,ROLE_CNNAME from dim_role where ROLE_ID = #{roleId}")
	public UserRole selectUserRoleByRoleId(@Param("roleId") String roleId);
    
    @ResultMap("UserRole")
	@Select("select ROLE_ID,ROLE_NAME,ROLE_CNNAME from dim_role where ROLE_NAME = #{roleName}")
	public UserRole selectUserRoleByRoleName(@Param("roleName") String roleName);

}
