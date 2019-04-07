package com.singlethreadzzz.dim.mapper.dataDisplay;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.singlethreadzzz.dim.pojo.SalesVolumeInfo;
import com.singlethreadzzz.dim.pojo.SalesVolumeTypeInfo;

@Repository
public interface DataDisplayMapper {
	
	@Results(id = "SalesVolumeInfo", value = 
		{ @Result(property = "goodsId", column = "GOODS_ID"), 
			   @Result(property = "goodsName", column = "GOODS_NAME"),
		       @Result(property = "salesVolume", column = "SALES_VOLUME"),
		})
	@Select("select b.GOODS_ID,b.GOODS_NAME,a.SALES_VOLUME FROM (select t.GOODS_ID,sum(t.GOODS_SELL_PRICE) as SALES_VOLUME from dim_goods_sell_log t where date_format( t.GOODS_SELL_TIME, '%Y%m' ) = date_format( curdate() , '%Y%m' ) group by t.GOODS_ID) a,dim_goods b where a.GOODS_ID = b.GOODS_ID order by a.SALES_VOLUME desc limit 10")
	public List<SalesVolumeInfo> selectGoodsSalesVolumeTop10();
	
	@Results(id = "SalesVolumeTypeInfo", value = 
		{ @Result(property = "goodsTypeCode", column = "GOODS_TYPE_CODE"), 
			   @Result(property = "goodsTypeName", column = "GOODS_TYPE_NAME"),
		       @Result(property = "salesTypeVolume", column = "SALES_TYPE_VOLUME"),
		})
	@Select("select t.GOODS_TYPE_CODE,t.GOODS_TYPE_NAME,sum(t.GOODS_SELL_QUANTITY) as SALES_TYPE_VOLUME from (select c.GOODS_TYPE_CODE,c.GOODS_TYPE_NAME,a.GOODS_SELL_PRICE,a.GOODS_SELL_QUANTITY,a.GOODS_SELL_TIME from dim_goods_sell_log a,dim_goods b, dim_goods_type c where a.GOODS_ID = b.GOODS_ID and b.GOODS_TYPE_CODE = c.GOODS_TYPE_CODE) t where date_format( t.GOODS_SELL_TIME, '%Y%m' ) = date_format( curdate() , '%Y%m' ) group by t.GOODS_TYPE_CODE,t.GOODS_TYPE_NAME")
	public List<SalesVolumeTypeInfo> selectGoodsSalesVolumeTypeTop10();

}