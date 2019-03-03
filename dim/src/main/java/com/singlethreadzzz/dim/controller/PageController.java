package com.singlethreadzzz.dim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.pojo.LoginInfo;

@Controller
public class PageController {
	
	@GetMapping("/login")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		LoginInfo LoginInfo = new LoginInfo();
		mv.addObject(LoginInfo);
		return mv;
	}
	
	@GetMapping("/index")
	public ModelAndView indexPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("index", "你好");
		return mv;
	}
	
	@GetMapping("/error")
	public ModelAndView errorPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		return mv;
	}
	
	@GetMapping("/managerInfo")
	public ModelAndView managerInfoPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("managerInfo");
		User managerInfo = new User();
		mv.addObject(managerInfo);
		return mv;
	}

}
