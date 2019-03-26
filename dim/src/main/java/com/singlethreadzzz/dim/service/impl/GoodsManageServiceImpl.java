package com.singlethreadzzz.dim.service.impl;

import java.util.List;

import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.mapper.GoodsManageMapper;
import com.singlethreadzzz.dim.service.GoodsManageService;

public class GoodsManageServiceImpl implements GoodsManageService{
	
	private GoodsManageMapper goodsManageMapper;

	@Override
	public List<GoodsType> getAllGoodsTypeInfo() throws Exception {
		return this.goodsManageMapper.selectAllGoodsTypeInfo();
	}

	@Override
	public void saveGoodsType(GoodsType goodsType) throws Exception {
		GoodsType oldGoodsType = new GoodsType();
		oldGoodsType = this.getGoodsTypeInfoByGoodsTypeCode(goodsType.getGoodsTypeCode());
		if(oldGoodsType != null) {
			throw new BeforeJsonException("商品类型编码已存在");
		}
		this.goodsManageMapper.insertGoodsType(goodsType);
	}

	@Override
	public void deleteGoodsTypeByGoodsTypeId(String goodsTypeId) throws Exception {
		GoodsType oldGoodsType = new GoodsType();
		oldGoodsType = this.getGoodsTypeInfoByGoodsTypeId(goodsTypeId);
		if(oldGoodsType == null) {
			throw new BeforeJsonException("商品类型信息不存在");
		}
		this.goodsManageMapper.deleteGoodsTypeByGoodsTypeId(goodsTypeId);
	}

	@Override
	public void updateGoodsType(GoodsType goodsType) throws Exception {
		GoodsType oldGoodsType = new GoodsType();
		
		oldGoodsType = this.getGoodsTypeInfoByGoodsTypeCode(goodsType.getGoodsTypeCode());
		if(oldGoodsType != null) {
			throw new BeforeJsonException("商品类型编码已存在");
		}
		
		oldGoodsType = this.getGoodsTypeInfoByGoodsTypeId(goodsType.getGoodsTypeId());
		if(oldGoodsType == null) {
			throw new BeforeJsonException("商品类型信息不存在");
		}
		
		oldGoodsType.setGoodsTypeCode(goodsType.getGoodsTypeCode());
		oldGoodsType.setGoodsTypeName(goodsType.getGoodsTypeName());
		
		this.goodsManageMapper.updateGoodsType(goodsType);
	}

	@Override
	public GoodsType getGoodsTypeInfoByGoodsTypeId(String goodsTypeId) throws Exception {
		return this.goodsManageMapper.selectGoodsTypeInfoByGoodsTypeId(goodsTypeId);
	}

	@Override
	public GoodsType getGoodsTypeInfoByGoodsTypeCode(Integer goodsTypeCode) throws Exception {
		return this.goodsManageMapper.selectGoodsTypeInfoByGoodsTypeCode(goodsTypeCode);
	}

}
