package com.singlethreadzzz.dim.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.singlethreadzzz.dim.exception.BeforePageException;
import com.singlethreadzzz.dim.pojo.LoginInfo;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping("/login")
	public ModelAndView login(LoginInfo loginInfo,HttpSession httpSession) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(loginInfo.getName() == null || loginInfo.getPassword() == null){
			throw new Exception("参数丢失");
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(loginInfo.getName(),loginInfo.getPassword());
			if(true == loginInfo.getIsRememberMe()) {
				token.setRememberMe(true);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loginInfo", new LoginInfo());
			try {
				currentUser.login(token);
				logger.info("登录成功");
			} catch (UnknownAccountException e0) {
				e0.printStackTrace();
				throw new BeforePageException("登录失败：账号不存在！", "login", map);
			} catch (IncorrectCredentialsException e1) {
				e1.printStackTrace();
				throw new BeforePageException("登录失败：密码错误！", "login", map);
			} catch (LockedAccountException e2) {
				e2.printStackTrace();
				throw new BeforePageException("登录失败：账号被锁定！", "login", map);
			} catch (AccountException e3) {
				e3.printStackTrace();
				throw new BeforePageException("登录失败：账号异常！", "login", map);
			} catch (CredentialsException e4) {
				e4.printStackTrace();
				throw new BeforePageException("登录失败：凭证异常！", "login", map);
			} catch (UnsupportedTokenException e5) {
				e5.printStackTrace();
				throw new BeforePageException("登录失败：不支持的token！", "login", map);
			} catch (AuthenticationException e6) {
				e6.printStackTrace();
				throw new BeforePageException("登录失败：未知的登录异常！", "login", map);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BeforePageException("登录失败：系统错误，请联系系统管理员", "login", map);
			}
		}
		
		Object redirectObj = httpSession.getAttribute("redirect");
		if(redirectObj != null) {
			mv.setViewName(redirectObj.toString());
			httpSession.removeAttribute("redirect");
		}else {
			mv.setViewName("redirect:/index");
		}
		return mv;
	}

}