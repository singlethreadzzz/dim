package com.singlethreadzzz.dim.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Goods implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -929574663990122616L;

	private String goodsId;
	
	private String goodsName;
	
	private String goodsCode;
	
	private String goodsTypeCode;
	
	private Integer goodsStock;
	
	private Float goodsPurchasePrice;
	
	private Float goodsSellPrice;
	
	private String goodsDescribe;
	
	private String goodsPictureId;
	
	private String goodsCreateUser;
	
	private Timestamp goodsCreateTime;
	
	private String goodsUpdateUser;
	
	private Timestamp goodsUpdateTime;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsTypeCode() {
		return goodsTypeCode;
	}

	public void setGoodsTypeCode(String goodsTypeCode) {
		this.goodsTypeCode = goodsTypeCode;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Float getGoodsPurchasePrice() {
		return goodsPurchasePrice;
	}

	public void setGoodsPurchasePrice(Float goodsPurchasePrice) {
		this.goodsPurchasePrice = goodsPurchasePrice;
	}

	public Float getGoodsSellPrice() {
		return goodsSellPrice;
	}

	public void setGoodsSellPrice(Float goodsSellPrice) {
		this.goodsSellPrice = goodsSellPrice;
	}

	public String getGoodsDescribe() {
		return goodsDescribe;
	}

	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}

	public String getGoodsPictureId() {
		return goodsPictureId;
	}

	public void setGoodsPictureId(String goodsPictureId) {
		this.goodsPictureId = goodsPictureId;
	}

	public String getGoodsCreateUser() {
		return goodsCreateUser;
	}

	public void setGoodsCreateUser(String goodsCreateUser) {
		this.goodsCreateUser = goodsCreateUser;
	}

	public Timestamp getGoodsCreateTime() {
		return goodsCreateTime;
	}

	public void setGoodsCreateTime(Timestamp goodsCreateTime) {
		this.goodsCreateTime = goodsCreateTime;
	}

	public String getGoodsUpdateUser() {
		return goodsUpdateUser;
	}

	public void setGoodsUpdateUser(String goodsUpdateUser) {
		this.goodsUpdateUser = goodsUpdateUser;
	}

	public Timestamp getGoodsUpdateTime() {
		return goodsUpdateTime;
	}

	public void setGoodsUpdateTime(Timestamp goodsUpdateTime) {
		this.goodsUpdateTime = goodsUpdateTime;
	}
	
}
