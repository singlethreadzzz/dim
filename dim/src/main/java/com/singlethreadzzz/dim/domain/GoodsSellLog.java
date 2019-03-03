package com.singlethreadzzz.dim.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class GoodsSellLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2270195135150314075L;
	
	private String goodsSellLogId;
	
	private String goodsId;
	
	private Float goodsSellPrice;
	
	private Integer goodsSellQuantity;
	
	private Timestamp goodsSellTime;
	
	private String goodsSellRemarks;
	
	private String operationUser;
	
	private Timestamp operationTime;

	public String getGoodsSellLogId() {
		return goodsSellLogId;
	}

	public void setGoodsSellLogId(String goodsSellLogId) {
		this.goodsSellLogId = goodsSellLogId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Float getGoodsSellPrice() {
		return goodsSellPrice;
	}

	public void setGoodsSellPrice(Float goodsSellPrice) {
		this.goodsSellPrice = goodsSellPrice;
	}

	public Integer getGoodsSellQuantity() {
		return goodsSellQuantity;
	}

	public void setGoodsSellQuantity(Integer goodsSellQuantity) {
		this.goodsSellQuantity = goodsSellQuantity;
	}

	public Timestamp getGoodsSellTime() {
		return goodsSellTime;
	}

	public void setGoodsSellTime(Timestamp goodsSellTime) {
		this.goodsSellTime = goodsSellTime;
	}

	public String getGoodsSellRemarks() {
		return goodsSellRemarks;
	}

	public void setGoodsSellRemarks(String goodsSellRemarks) {
		this.goodsSellRemarks = goodsSellRemarks;
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
