package com.singlethreadzzz.dim.domain;

import java.io.Serializable;

public class GoodsPurchaseBusiness implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5706856346937466552L;
	
	private String GoodsPurchaseBusinessId;
	
	private String GoodsPurchaseBusinessName;
	
	private String GoodsPurchaseBusinessCellphoneNumber;

	public String getGoodsPurchaseBusinessId() {
		return GoodsPurchaseBusinessId;
	}

	public void setGoodsPurchaseBusinessId(String goodsPurchaseBusinessId) {
		GoodsPurchaseBusinessId = goodsPurchaseBusinessId;
	}

	public String getGoodsPurchaseBusinessName() {
		return GoodsPurchaseBusinessName;
	}

	public void setGoodsPurchaseBusinessName(String goodsPurchaseBusinessName) {
		GoodsPurchaseBusinessName = goodsPurchaseBusinessName;
	}

	public String getGoodsPurchaseBusinessCellphoneNumber() {
		return GoodsPurchaseBusinessCellphoneNumber;
	}

	public void setGoodsPurchaseBusinessCellphoneNumber(String goodsPurchaseBusinessCellphoneNumber) {
		GoodsPurchaseBusinessCellphoneNumber = goodsPurchaseBusinessCellphoneNumber;
	}
	
}
