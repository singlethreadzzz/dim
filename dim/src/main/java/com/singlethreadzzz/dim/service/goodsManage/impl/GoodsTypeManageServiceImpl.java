package com.singlethreadzzz.dim.service.goodsManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.mapper.goodsManage.GoodsTypeManageMapper;
import com.singlethreadzzz.dim.service.goodsManage.GoodsTypeManageService;
import com.singlethreadzzz.dim.util.UUIDUtils;

/**
 * <p>Class       : com.singlethreadzzz.dim.service.goodsManage.impl.GoodsTypeManageServiceImpl
 * <p>Descdription: 商品类型管理逻辑层实现类
 *
 * @author  単スレッド-singlethreadzzz@gmail.com
 * @version 1.0.0
 *<p>
 *--------------------------------------------------------------<br>
 * 修改履历：<br>
 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建文件；<br>
 *--------------------------------------------------------------<br>
 *</p>
 */
@Service
public class GoodsTypeManageServiceImpl implements GoodsTypeManageService{
	
	@Autowired
	private GoodsTypeManageMapper goodsTypeManageMapper;

	@Override
	public List<GoodsType> getAllGoodsTypeInfo() throws Exception {
		return this.goodsTypeManageMapper.selectAllGoodsTypeInfo();
	}

	@Override
	public void saveGoodsType(GoodsType goodsType) throws Exception {
		GoodsType oldGoodsType = new GoodsType();
		oldGoodsType = this.getGoodsTypeInfoByGoodsTypeCode(goodsType.getGoodsTypeCode());
		if(oldGoodsType != null) {
			throw new BeforeJsonException("商品类型编码已存在");
		}
		goodsType.setGoodsTypeId(UUIDUtils.getUUID());
		this.goodsTypeManageMapper.insertGoodsType(goodsType);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteGoodsTypeByGoodsTypeId(List<String> goodsTypeIdList) throws Exception {
		for(String goodsTypeId : goodsTypeIdList) {
			GoodsType oldGoodsType = new GoodsType();
			oldGoodsType = this.goodsTypeManageMapper.selectGoodsTypeInfoByGoodsTypeId(goodsTypeId);
			if(oldGoodsType == null) {
				throw new BeforeJsonException("商品类型信息不存在");
			}
			this.goodsTypeManageMapper.deleteGoodsTypeByGoodsTypeId(goodsTypeId);
		};
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
		
		this.goodsTypeManageMapper.updateGoodsType(oldGoodsType);
	}

	@Override
	public GoodsType getGoodsTypeInfoByGoodsTypeId(String goodsTypeId) throws Exception {
		return this.goodsTypeManageMapper.selectGoodsTypeInfoByGoodsTypeId(goodsTypeId);
	}

	@Override
	public GoodsType getGoodsTypeInfoByGoodsTypeCode(Integer goodsTypeCode) throws Exception {
		return this.goodsTypeManageMapper.selectGoodsTypeInfoByGoodsTypeCode(goodsTypeCode);
	}

}
