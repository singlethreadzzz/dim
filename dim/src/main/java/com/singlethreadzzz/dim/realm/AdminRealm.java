package com.singlethreadzzz.dim.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.singlethreadzzz.dim.domain.Role;
import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.service.userManage.UserManageService;
import com.singlethreadzzz.dim.service.userManage.UserRoleManageService;

@Component
public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private UserManageService userManagerService;
	
	@Autowired
	private UserRoleManageService userAuthManagerService;
	
	/**
	 * <p>Method ：doGetAuthenticationInfo
	 * <p>Description : 用户名密码认证
	 *
	 * @param token
	 * @return
	 * @throws AuthenticationException 
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken UPtoken = (UsernamePasswordToken)token;
		String userAccount = UPtoken.getUsername();	
		User user = null;
		try {
			user = this.userManagerService.getUserByUserAccount(userAccount);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnknownAccountException();
		}
		
		if(user == null) {
			throw new UnknownAccountException();
		}
		
		String password = user.getUserPassword();

		if(password == null) {
		    throw new AccountException();
		}
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(userAccount + "LoveLive"), getName());
		return info;

	}

	/**
	 * <p>Method ：doGetAuthorizationInfo
	 * <p>Description : 权限认证
	 *
	 * @param principals
	 * @return 
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)  throws AuthenticationException {
		User principal = (User)principals.getPrimaryPrincipal();
		Role userRole = null;
		try {
			userRole = this.userAuthManagerService.getUserRoleByUserId(principal.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnknownAccountException();
		}
		Set<String> roles = new HashSet<>();
		if(userRole != null) {
			roles.add(userRole.getRoleName());
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
		return simpleAuthorizationInfo;
	}

}
