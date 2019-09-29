package com.singlethreadzzz.dim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	/*/
	 * 方法之前
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("登录拦截器拦截成功");
		HttpSession httpSession = request.getSession();
		if(httpSession == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			logger.info("跳转到登录界面");
	        return false;
		}
		Object userId = httpSession.getAttribute("userId");
		if(userId != null) {
			logger.info("用户已登录");
			return true;
		}else {
			String redirectUrl = request.getRequestURI();
			httpSession.setAttribute("redirect", redirectUrl);
			response.sendRedirect(request.getContextPath() + "/login");
			logger.info("用户未登录，跳转到登录界面");
	        return false;
		}
    }

}