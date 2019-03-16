package com.singlethreadzzz.dim.resolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.exception.BeforePageException;

public class MyExceptionResolver implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		
		    e.printStackTrace();
		
	       	ModelAndView mv = new ModelAndView();
	        
	        if(e instanceof BeforeJsonException){
	        	MappingJackson2JsonView view = new MappingJackson2JsonView();
	        	mv.addObject("code", 0);
	        	mv.addObject("message", e.getMessage());
	        	mv.addObject("data", null);
	            mv.setView(view);
	        }else if(e instanceof BeforePageException){
	        	mv.addObject("message", e.getMessage());
	            mv.setViewName(((BeforePageException) e).getViewName());
	            Map<String, Object> map = ((BeforePageException) e).getViewObjectMap();
	            if(map != null && !map.isEmpty()) {
	            	map.keySet().forEach(x -> {
	            		mv.addObject(x, map.get(x));
	            	});
	            }
	        }else {
	        	mv.setViewName("500");
	        }
	        
	        return mv;
	}

}
