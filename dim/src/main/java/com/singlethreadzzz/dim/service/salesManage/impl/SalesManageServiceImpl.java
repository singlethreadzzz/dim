package com.singlethreadzzz.dim.service.salesManage.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.Goods;
import com.singlethreadzzz.dim.domain.GoodsPurchaseLog;
import com.singlethreadzzz.dim.domain.GoodsSellLog;
import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.domain.User;
import com.singlethreadzzz.dim.mapper.goodsManage.GoodsManageMapper;
import com.singlethreadzzz.dim.mapper.salesManage.SalesManageMapper;
import com.singlethreadzzz.dim.service.salesManage.SalesManageService;
import com.singlethreadzzz.dim.util.UUIDUtils;

@Service
public class SalesManageServiceImpl implements SalesManageService{
	
	@Autowired
	private SalesManageMapper salesManageMapper;
	
	private GoodsManageMapper goodsManageMapper;

	@Override
	public List<GoodsType> getAllUsedGoodsType() throws Exception {
		return this.salesManageMapper.selectAllUsedGoodsType();
	}

	@Override
	public void saveGoodsPurchaseLog(GoodsPurchaseLog goodsPurchaseLog) throws Exception {
		Goods goods = this.goodsManageMapper.selectGoodsByGoodsId(goodsPurchaseLog.getGoodsId());
		int oldGoodsStock = goods.getGoodsStock();
		goods.setGoodsStock(oldGoodsStock - goodsPurchaseLog.getGoodsPurchaseQuantity());
		this.goodsManageMapper.updateGoods(goods);
		User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
		goodsPurchaseLog.setGoodsPurchaseLogId(UUIDUtils.getUUID());
		goodsPurchaseLog.setGoodsPurchaseBusinessId("1");
		goodsPurchaseLog.setOperationTime(Timestamp.valueOf(LocalDateTime.now()));
		goodsPurchaseLog.setOperationUser(currentUser.getUserAccount());
		this.salesManageMapper.insertGoodsPurchaseLog(goodsPurchaseLog);
	}

	@Override
	public void saveGoodsSellLog(GoodsSellLog goodsSellLog) throws Exception {
		Goods goods = this.goodsManageMapper.selectGoodsByGoodsId(goodsSellLog.getGoodsId());
		int oldGoodsStock = goods.getGoodsStock();
		goods.setGoodsStock(oldGoodsStock - goodsSellLog.getGoodsSellQuantity());
		this.goodsManageMapper.updateGoods(goods);
		User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
		goodsSellLog.setGoodsSellLogId(UUIDUtils.getUUID());
		goodsSellLog.setGoodsSellMemberId("1");
		goodsSellLog.setOperationTime(Timestamp.valueOf(LocalDateTime.now()));
		goodsSellLog.setOperationUser(currentUser.getUserAccount());
		this.salesManageMapper.insertGoodsSellLog(goodsSellLog);
	}

}
