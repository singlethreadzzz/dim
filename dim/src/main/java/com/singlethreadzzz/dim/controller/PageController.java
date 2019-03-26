package com.singlethreadzzz.dim.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@GetMapping("/login")
	public ModelAndView loginPage() {
		logger.info("跳转到登录页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		return mv;
	}
	
	@GetMapping("/index")
	public ModelAndView indexPage(HttpSession httpSession) {
		logger.info("跳转到首页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index/index");
		return mv;
	}
	
	@GetMapping("/error")
	public ModelAndView errorPage() {
		logger.info("跳转到错误页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/500");
		return mv;
	}
	
	@GetMapping("/goodsManage")
	public ModelAndView goodsManagePage() {
		logger.info("跳转到商品管理页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goodsManage/goodsManage");
		return mv;
	}
	
	@GetMapping("/dataDisplay")
	public ModelAndView dataDisplayPage() {
		logger.info("跳转到数据展示页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dataDisplay/dataDisplay");
		return mv;
	}
	
	@GetMapping("/userManage")
	public ModelAndView userManagePage() {
		logger.info("跳转到用户管理页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("userManage/userManage");
		return mv;
	}
	
	@GetMapping("/logQuery")
	public ModelAndView logQueryPage() {
		logger.info("跳转到日志查询页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("logQuery/logQuery");
		return mv;
	}
	
	@GetMapping("/salesManage")
	public ModelAndView salesManage() {
		logger.info("跳转到销售管理页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("salesManage/salesManage");
		return mv;
	}
	
	@GetMapping("/goodsTypeManage")
	public ModelAndView goodsTypeManage() {
		logger.info("跳转到商品类型管理页");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("goodsManage/goodsTypeManage");
		return mv;
	}

}