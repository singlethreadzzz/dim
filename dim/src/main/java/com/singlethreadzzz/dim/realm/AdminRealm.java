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
import com.singlethreadzzz.dim.service.UserAuthManageService;
import com.singlethreadzzz.dim.service.UserManageService;

@Component
public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private UserManageService userManagerService;
	
	@Autowired
	private UserAuthManageService userAuthManagerService;
	
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
		user = this.userManagerService.selectUserByUserAccount(userAccount);
		
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
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User principal = (User)principals.getPrimaryPrincipal();
		Role userRole = this.userAuthManagerService.selectUserRoleByUserId(principal.getUserId());
		Set<String> roles = new HashSet<>();
		if(userRole != null) {
			roles.add(userRole.getRoleName());
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
		return simpleAuthorizationInfo;
	}

}
