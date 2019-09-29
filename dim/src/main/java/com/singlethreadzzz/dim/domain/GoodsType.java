package com.singlethreadzzz.dim.domain;

import java.io.Serializable;

public class GoodsType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4286579849215261563L;
	
	private String goodsTypeId;
	
	private String goodsTypeCode;
	
	private String goodsTypeName;

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeCode() {
		return goodsTypeCode;
	}

	public void setGoodsTypeCode(String goodsTypeCode) {
		this.goodsTypeCode = goodsTypeCode;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	
}
