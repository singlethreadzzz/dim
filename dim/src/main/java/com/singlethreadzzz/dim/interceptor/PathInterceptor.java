package com.singlethreadzzz.dim.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PathInterceptor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(PathInterceptor.class);

	/*/
	 * 方法之后，渲染视图之前
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	String path = request.getContextPath();
    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	modelAndView.addObject("path", path);
    	modelAndView.addObject("basePath", basePath);
    	logger.info("取得相对路径和绝对路径并传入页面");
    }

}
