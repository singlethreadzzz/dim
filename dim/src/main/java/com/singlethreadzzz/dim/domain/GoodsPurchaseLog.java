package com.singlethreadzzz.dim.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class GoodsPurchaseLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2329246343926635586L;
	
	private String goodsPurchaseLogId;
	
	private String goodsId;
	
	private Float goodsPurchasePrice;
	
	private Integer goodsPurchaseQuantity;
	
	private String goodsPurchaseBusinessId;
	
	private Timestamp goodsPurchaseTime;
	
	private String goodsPurchaseRemarks;
	
	private String operationUser;
	
	private Timestamp operationTime;

	public String getGoodsPurchaseLogId() {
		return goodsPurchaseLogId;
	}

	public void setGoodsPurchaseLogId(String goodsPurchaseLogId) {
		this.goodsPurchaseLogId = goodsPurchaseLogId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Float getGoodsPurchasePrice() {
		return goodsPurchasePrice;
	}

	public void setGoodsPurchasePrice(Float goodsPurchasePrice) {
		this.goodsPurchasePrice = goodsPurchasePrice;
	}

	public Integer getGoodsPurchaseQuantity() {
		return goodsPurchaseQuantity;
	}

	public void setGoodsPurchaseQuantity(Integer goodsPurchaseQuantity) {
		this.goodsPurchaseQuantity = goodsPurchaseQuantity;
	}

	public String getGoodsPurchaseBusinessId() {
		return goodsPurchaseBusinessId;
	}

	public void setGoodsPurchaseBusinessId(String goodsPurchaseBusinessId) {
		this.goodsPurchaseBusinessId = goodsPurchaseBusinessId;
	}

	public Timestamp getGoodsPurchaseTime() {
		return goodsPurchaseTime;
	}

	public void setGoodsPurchaseTime(Timestamp goodsPurchaseTime) {
		this.goodsPurchaseTime = goodsPurchaseTime;
	}

	public String getGoodsPurchaseRemarks() {
		return goodsPurchaseRemarks;
	}

	public void setGoodsPurchaseRemarks(String goodsPurchaseRemarks) {
		this.goodsPurchaseRemarks = goodsPurchaseRemarks;
	}

	public String getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}

	public Timestamp getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

}
