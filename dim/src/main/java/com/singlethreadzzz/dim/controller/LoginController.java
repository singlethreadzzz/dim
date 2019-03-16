package com.singlethreadzzz.dim.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.exception.BeforePageException;
import com.singlethreadzzz.dim.pojo.LoginInfo;
import com.singlethreadzzz.dim.pojo.Result;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping("/login")
	@ResponseBody
	public Result login(@RequestBody LoginInfo loginInfo) throws Exception {
		
		Result result = new Result();
		
		if(loginInfo.getName() == null || loginInfo.getPassword() == null){
			throw new Exception("参数丢失");
		}
		
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(loginInfo.getName(),loginInfo.getPassword());
			if(loginInfo.getIsRememberMe()) {
				token.setRememberMe(true);
			}
			try {
				currentUser.login(token);
				logger.info("登录成功");
			} catch (UnknownAccountException e0) {
				e0.printStackTrace();
				throw new BeforeJsonException("登录失败：账号不存在！");
			} catch (IncorrectCredentialsException e1) {
				e1.printStackTrace();
				throw new BeforeJsonException("登录失败：密码错误！");
			} catch (LockedAccountException e2) {
				e2.printStackTrace();
				throw new BeforeJsonException("登录失败：账号被锁定！");
			} catch (AccountException e3) {
				e3.printStackTrace();
				throw new BeforeJsonException("登录失败：账号异常！");
			} catch (CredentialsException e4) {
				e4.printStackTrace();
				throw new BeforeJsonException("登录失败：凭证异常！");
			} catch (UnsupportedTokenException e5) {
				e5.printStackTrace();
				throw new BeforeJsonException("登录失败：不支持的token！");
			} catch (AuthenticationException e6) {
				e6.printStackTrace();
				throw new BeforeJsonException("登录失败：未知的登录异常！");
			} catch (Exception e) {
				e.printStackTrace();
				throw new BeforeJsonException("登录失败：系统错误，请联系系统管理员");
			}
		}
		
		result.setCode(1);
		result.setData(null);
		result.setMessage("登录成功");
		return result;
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public ModelAndView logout() throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		}catch (Exception e) {
			throw new BeforePageException(e.getMessage(), "500");
		}
		
		modelAndView.setViewName("login");
		return modelAndView;

	}

}