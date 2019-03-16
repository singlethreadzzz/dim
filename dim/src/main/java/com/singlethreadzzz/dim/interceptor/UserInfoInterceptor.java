package com.singlethreadzzz.dim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.singlethreadzzz.dim.domain.Role;
import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.exception.BeforePageException;
import com.singlethreadzzz.dim.service.UserAuthManagerService;

@Component
public class UserInfoInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserAuthManagerService userAuthManagerService;
	
	Logger logger = LoggerFactory.getLogger(UserInfoInterceptor.class);

	/*/
	 * 方法之前
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
    }

	/*/
	 * 方法之后，渲染视图之前
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	if(modelAndView != null) {
			User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
			Role role = new Role();
			try {
				role = this.userAuthManagerService.selectUserRoleByRoleId(currentUser.getRoleId());
			} catch (Exception e) {
				throw new BeforePageException("权限查询错误", "500");
			}
			modelAndView.addObject("userId", currentUser.getUserId());
			modelAndView.addObject("userName", currentUser.getUserName());
			modelAndView.addObject("userAccount", currentUser.getUserAccount());
			modelAndView.addObject("roleName", role.getRoleName());
			logger.info("用户信息成功赋值");
        }
    }

	/*/
	 * 渲染视图之后
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    
    }

}
