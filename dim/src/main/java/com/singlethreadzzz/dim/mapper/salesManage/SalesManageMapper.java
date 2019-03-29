package com.singlethreadzzz.dim.mapper.salesManage;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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

}
