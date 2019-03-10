package com.singlethreadzzz.dim.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.exception.BeforePageException;
import com.singlethreadzzz.dim.pojo.Result;

public class MyExceptionResolver implements HandlerExceptionResolver {
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		
		    e.printStackTrace();
		
	       	ModelAndView mv = new ModelAndView();
	        
	        if(e instanceof BeforeJsonException){
	        	Result result = new Result();
	        	result.setCode(0);
	        	result.setMessage(e.getMessage());
	        	result.setData(null);
	        	MappingJackson2JsonView view = new MappingJackson2JsonView();
	        	mv.addObject("result", result);
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
	        	mv.setViewName("error500");
	        }
	        
	        return mv;
	}

}
