package com.singlethreadzzz.dim.exception;

public class BeforePageException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7618756704531965481L;
	
	private String viewName;

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
	
	public String getViewName () {
		return viewName;
	}

}
