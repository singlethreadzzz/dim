package com.singlethreadzzz.dim.service;

import java.util.List;

import com.singlethreadzzz.dim.domain.GoodsType;

public interface GoodsManageService {
	
	public List<GoodsType> getAllGoodsTypeInfo() throws Exception ;
		
	public void saveGoodsType(GoodsType goodsType) throws Exception;
	
	public void deleteGoodsTypeByGoodsTypeId(String goodsTypeId) throws Exception ;
	
	public void updateGoodsType(GoodsType goodsType) throws Exception ;
	
	public GoodsType getGoodsTypeInfoByGoodsTypeId(String goodsTypeId) throws Exception ;
	
	public GoodsType getGoodsTypeInfoByGoodsTypeCode(Integer goodsTypeCode) throws Exception ;

}
