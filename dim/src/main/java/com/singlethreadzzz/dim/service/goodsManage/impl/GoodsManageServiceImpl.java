package com.singlethreadzzz.dim.service.goodsManage.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singlethreadzzz.dim.domain.Goods;
import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.mapper.goodsManage.GoodsManageMapper;
import com.singlethreadzzz.dim.pojo.GoodsInfo;
import com.singlethreadzzz.dim.service.goodsManage.GoodsManageService;
import com.singlethreadzzz.dim.util.UUIDUtils;

/**
 * <p>Class       : com.singlethreadzzz.dim.service.goodsManage.impl.GoodsManageServiceImpl
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
public class GoodsManageServiceImpl implements GoodsManageService{
	
	@Autowired
	private GoodsManageMapper goodsManageMapper;

	@Override
	public List<GoodsInfo> getAllGoodsInfo() throws Exception {
		return this.goodsManageMapper.selectAllGoodsInfo();
	}

	@Override
	public void saveGoods(Goods goods) throws Exception {
		Goods oldGoods = new Goods();
		oldGoods = this.getGoodsByGoodsCode(goods.getGoodsCode());
		if(oldGoods != null) {
			throw new BeforeJsonException("商品类型编码已存在");
		}
		goods.setGoodsId(UUIDUtils.getUUID());
		goods.setGoodsStock(0);

		User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		goods.setGoodsCreateUser(currentUser.getUserAccount());
		goods.setGoodsCreateTime(timestamp);
		goods.setGoodsUpdateTime(timestamp);
		goods.setGoodsUpdateUser(currentUser.getUserAccount());
		this.goodsManageMapper.insertGoods(goods);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteGoodsByGoodsId(List<String> goodsIdList) throws Exception {
		for(String goodsId : goodsIdList) {
			Goods oldGoods = new Goods();
			oldGoods = this.goodsManageMapper.selectGoodsByGoodsId(goodsId);
			if(oldGoods == null) {
				throw new BeforeJsonException("商品类型信息不存在");
			}
			this.goodsManageMapper.deleteGoodsByGoodsId(goodsId);
		};
	}

	@Override
	public void updateGoods(Goods goods) throws Exception {
		Goods oldGoods = new Goods();
		
		oldGoods = this.getGoodsByGoodsId(goods.getGoodsId());
		if(oldGoods == null) {
			throw new BeforeJsonException("商品类型信息不存在");
		}
		
		oldGoods.setGoodsCode(goods.getGoodsCode());
		oldGoods.setGoodsName(goods.getGoodsName());
		oldGoods.setGoodsTypeCode(goods.getGoodsTypeCode());
		oldGoods.setGoodsPurchasePrice(goods.getGoodsPurchasePrice());
		oldGoods.setGoodsSellPrice(goods.getGoodsSellPrice());
		oldGoods.setGoodsDescribe(goods.getGoodsDescribe());
		
		User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		oldGoods.setGoodsUpdateTime(timestamp);
		oldGoods.setGoodsUpdateUser(currentUser.getUserAccount());
		
		this.goodsManageMapper.updateGoods(oldGoods);
	}

	@Override
	public Goods getGoodsByGoodsId(String goodsId) throws Exception {
		return this.goodsManageMapper.selectGoodsByGoodsId(goodsId);
	}

	@Override
	public Goods getGoodsByGoodsCode(String goodsCode) throws Exception {
		return this.goodsManageMapper.selectGoodsByGoodsCode(goodsCode);
	}

}
