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

import com.singlethreadzzz.dim.domain.Role;

@Repository
public interface UserAuthMapper {
	
	@Results(id = "Role", value = 
		{ @Result(property = "roleId", column = "ROLE_ID"),
		  @Result(property = "roleName", column = "ROLE_NAME"), 
		  @Result(property = "roleCnname", column = "ROLE_CNNAME")})
	@Select("select a.ROLE_ID,a.ROLE_NAME,a.ROLE_CNNAME from dim_role a,dim_user b where B.ROLE_ID = A.ROLE_ID and b.USER_ID = #{userId}")
	public Role selectUserRoleByUserId(@Param("userId") String userId);
	
	@Insert("insert into dim_role(ROLE_ID,ROLE_NAME,ROLE_CNNAME) values(#{roleId}, #{roleName}, #{roleCnname})")
	public void insertUserRole(Role userRole);

	@Delete("delete from dim_role where ROLE_ID = #{roleId}")
	public void deleteUserRoleByRoleId(@Param("roleId") String roleId);

	@Update("update dim_role set ROLE_NAME = #{roleName},ROLE_CNNAME = #{roleCnname} where ROLE_ID = #{roleId}")
	public void updateUserByUserId(Role userRole);

    @ResultMap("Role")
	@Select("select ROLE_ID,ROLE_NAME,ROLE_CNNAME from dim_role")
	public List<Role> selectAllUserRoles();
   
    @ResultMap("Role")
	@Select("select ROLE_ID,ROLE_NAME,ROLE_CNNAME from dim_role where ROLE_ID = #{roleId}")
	public Role selectUserRoleByRoleId(@Param("roleId") String roleId);
    
    @ResultMap("Role")
	@Select("select ROLE_ID,ROLE_NAME,ROLE_CNNAME from dim_role where ROLE_NAME = #{roleName}")
	public Role selectUserRoleByRoleName(@Param("roleName") String roleName);

}
