package com.singlethreadzzz.dim.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.singlethreadzzz.dim.domain.GoodsType;

@Repository
public interface GoodsManageMapper {
	
	@Results(id = "GoodsType", value = 
		{ @Result(property = "goodsTypeId", column = "GOODS_TYPE_ID"), 
		       @Result(property = "goodsTypeCode", column = "GOODS_TYPE_CODE"),
		       @Result(property = "goodsTypeName", column = "GOODS_TYPE_NAME")
		})
	@Select("select GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME from dim_goods_type")
	public List<GoodsType> selectAllGoodsTypeInfo();

	@Insert("insert into dim_goods_type (GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME) values(#{goodsTypeId}, #{goodsTypeCode}, #{goodsTypeName})")
	public void insertGoodsType(GoodsType goodsType);

	@Delete("delete from dim_goods_type where GOODS_TYPE_ID = #{goodsTypeId}")
	public void deleteGoodsTypeByGoodsTypeId(@Param("goodsTypeId") String goodsTypeId);

	@Update("update dim_goods_type set GOODS_TYPE_CODE = #{goodsTypeCode},GOODS_TYPE_NAME = #{goodsTypeName} where GOODS_TYPE_ID = #{goodsTypeId}")
	public void updateGoodsType(GoodsType goodsType);
    
    @ResultMap("User")
	@Select("select GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME from dim_goods_type where GOODS_TYPE_ID = #{goodsTypeId}")
	public GoodsType selectGoodsTypeInfoByGoodsTypeId(@Param("goodsTypeId") String goodsTypeId);
    
    @ResultMap("User")
	@Select("select GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME from dim_goods_type where GOODS_TYPE_CODE = #{goodsTypeCode}")
	public GoodsType selectGoodsTypeInfoByGoodsTypeCode(@Param("goodsTypeCode") Integer goodsTypeCode);

}