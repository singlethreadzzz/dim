package com.singlethreadzzz.dim.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
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
			
			try {
				currentUser.login(token);
				logger.info("登录成功");
			}catch (Exception e) {
				e.printStackTrace();
                throw new BeforePageException("登录失败：" + e.getMessage(), "login");
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