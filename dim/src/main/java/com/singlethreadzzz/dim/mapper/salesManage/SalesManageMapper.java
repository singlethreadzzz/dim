package com.singlethreadzzz.dim.mapper.salesManage;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.singlethreadzzz.dim.domain.GoodsPurchaseLog;
import com.singlethreadzzz.dim.domain.GoodsSellLog;
import com.singlethreadzzz.dim.domain.GoodsType;

@Repository
public interface SalesManageMapper {
	
	@Results(id = "GoodsType", value = 
		{ @Result(property = "goodsTypeId", column = "GOODS_TYPE_ID"), 
		       @Result(property = "goodsTypeCode", column = "GOODS_TYPE_CODE"),
		       @Result(property = "goodsTypeName", column = "GOODS_TYPE_NAME")
		})
	@Select("select distinct a.GOODS_TYPE_ID,a.GOODS_TYPE_CODE,a.GOODS_TYPE_NAME from dim_goods_type a, dim_goods b where a.GOODS_TYPE_CODE = b.GOODS_TYPE_CODE")
	public List<GoodsType> selectAllUsedGoodsType();
	
	@Results(id = "GoodsPurchaseLog", value = 
		{ @Result(property = "goodsPurchaseLogId", column = "GOODS_PURCHASE_LOG_ID"), 
		       @Result(property = "goodsId", column = "GOODS_ID"),
		       @Result(property = "goodsPurchasePrice", column = "GOODS_PURCHASE_PRICE"),
		       @Result(property = "goodsPurchaseQuantity", column = "GOODS_PURCHASE_QUANTITY"),
		       @Result(property = "goodsPurchaseBusinessId", column = "GOODS_PURCHASE_BUSINESS_ID"),
		       @Result(property = "goodsPurchaseTime", column = "GOODS_PURCHASE_TIME"),
		       @Result(property = "goodsPurchaseRemarks", column = "GOODS_PURCHASE_REMARKS"),
		       @Result(property = "operationUser", column = "OPERATION_USER"),
		       @Result(property = "operationTime", column = "OPERATION_TIME")
		})
	@Insert("insert into dim_goods_purchase_log (GOODS_PURCHASE_LOG_ID,GOODS_ID,GOODS_PURCHASE_PRICE,GOODS_PURCHASE_QUANTITY,GOODS_PURCHASE_BUSINESS_ID,GOODS_PURCHASE_TIME,GOODS_PURCHASE_REMARKS,OPERATION_USER,OPERATION_TIME) values(#{goodsPurchaseLogId}, #{goodsId}, #{goodsPurchasePrice}, #{goodsPurchaseQuantity}, #{goodsPurchaseBusinessId}, #{goodsPurchaseTime}, #{goodsPurchaseRemarks}, #{operationUser}, #{operationTime})")
	public void insertGoodsPurchaseLog(GoodsPurchaseLog goodsPurchaseLog);
	
	@Results(id = "GoodsSellLog", value = 
		{ @Result(property = "goodsPurchaseLogId", column = "GOODS_SELL_LOG_ID"), 
		       @Result(property = "goodsId", column = "GOODS_ID"),
		       @Result(property = "goodsSellPrice", column = "GOODS_SELL_PRICE"),
		       @Result(property = "goodsSellQuantity", column = "GOODS_SELL_QUANTITY"),
		       @Result(property = "goodsSellMemberId", column = "GOODS_SELL_MEMBER_ID"),
		       @Result(property = "goodsSellTime", column = "GOODS_SELL_TIME"),
		       @Result(property = "goodsSellRemarks", column = "GOODS_SELL_REMARKS"),
		       @Result(property = "operationUser", column = "OPERATION_USER"),
		       @Result(property = "operationTime", column = "OPERATION_TIME")
		})
	@Insert("insert into dim_goods_sell_log (GOODS_SELL_LOG_ID,GOODS_ID,GOODS_SELL_PRICE,GOODS_SELL_QUANTITY,GOODS_SELL_MEMBER_ID,GOODS_SELL_TIME,GOODS_SELL_REMARKS,OPERATION_USER,OPERATION_TIME) values(#{goodsSellLogId}, #{goodsId}, #{goodsSellPrice}, #{goodsSellQuantity}, #{goodsSellMemberId}, #{goodsSellTime}, #{goodsSellRemarks}, #{operationUser}, #{operationTime})")
	public void insertGoodsSellLog(GoodsSellLog goodsSellLog);


}
