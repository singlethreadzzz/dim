package com.singlethreadzzz.dim.exception;

import java.util.Map;

public class BeforePageException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7618756704531965481L;
	
	private String viewName;
	
	private Map<String, Object> viewObjectMap;

	public BeforePageException() {
		super();
	}
	
	public BeforePageException(String message) {
		super(message);
	}
	
	public BeforePageException(String message, String viewName) {
		super(message);
		this.viewName = viewName;
	}
	
	public BeforePageException(String message, String viewName, Map<String, Object> viewObjectMap) {
		super(message);
		this.viewName = viewName;
		this.viewObjectMap = viewObjectMap;
	}
	
	public String getViewName () {
		return viewName;
	}

	public Map<String, Object> getViewObjectMap() {
		return viewObjectMap;
	}
	
}
