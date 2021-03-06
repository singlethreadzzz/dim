package com.singlethreadzzz.dim.service.salesManage;

import java.util.List;

import com.singlethreadzzz.dim.domain.GoodsPurchaseLog;
import com.singlethreadzzz.dim.domain.GoodsSellLog;
import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.pojo.GoodsInfo;

public interface SalesManageService {
	
	public List<GoodsType> getAllUsedGoodsType() throws Exception ;
	
	public void saveGoodsPurchaseLog(GoodsPurchaseLog goodsPurchaseLog) throws Exception ;
	
	public void saveGoodsSellLog(GoodsSellLog goodsSellLog) throws Exception ;
	
	public List<GoodsInfo> getFuzzyGoodsInfoByGoodsName(String goodsName) throws Exception ;

}
