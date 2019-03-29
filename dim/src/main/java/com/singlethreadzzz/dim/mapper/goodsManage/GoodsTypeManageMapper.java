package com.singlethreadzzz.dim.mapper.goodsManage;

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


/**
 * <p>Class       : com.singlethreadzzz.dim.mapper.goodsManage.GoodsTypeManageMapper
 * <p>Descdription: 商品类型管理Mapper类
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
@Repository
public interface GoodsTypeManageMapper {
	
	/**
	 * <p>Method ：selectAllGoodsType
	 * <p>Description : 查询所有商品类型信息
	 *
	 * @return 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Results(id = "GoodsType", value = 
		{ @Result(property = "goodsTypeId", column = "GOODS_TYPE_ID"), 
		       @Result(property = "goodsTypeCode", column = "GOODS_TYPE_CODE"),
		       @Result(property = "goodsTypeName", column = "GOODS_TYPE_NAME")
		})
	@Select("select GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME from dim_goods_type")
	public List<GoodsType> selectAllGoodsType();

	/**
	 * <p>Method ：insertGoodsType
	 * <p>Description : 新增商品类型
	 *
	 * @param goodsType 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Insert("insert into dim_goods_type (GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME) values(#{goodsTypeId}, #{goodsTypeCode}, #{goodsTypeName})")
	public void insertGoodsType(GoodsType goodsType);

	/**
	 * <p>Method ：deleteGoodsTypeByGoodsTypeId
	 * <p>Description : 通过商品类型ID删除商品类型
	 *
	 * @param goodsTypeId 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Delete("delete from dim_goods_type where GOODS_TYPE_ID = #{goodsTypeId}")
	public void deleteGoodsTypeByGoodsTypeId(@Param("goodsTypeId") String goodsTypeId);

	/**
	 * <p>Method ：updateGoodsType
	 * <p>Description : 修改商品类型
	 *
	 * @param goodsType 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Update("update dim_goods_type set GOODS_TYPE_CODE = #{goodsTypeCode},GOODS_TYPE_NAME = #{goodsTypeName} where GOODS_TYPE_ID = #{goodsTypeId}")
	public void updateGoodsType(GoodsType goodsType);
    
    /**
     * <p>Method ：selectGoodsTypeByGoodsTypeId
     * <p>Description : 通过商品类型ID查询商品类型信息
     *
     * @param goodsTypeId
     * @return 
     * @author  単スレッド-singlethreadzzz@gmail.com
     *<p>
     *--------------------------------------------------------------<br>
     * 修改履历：<br>
     *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
     *--------------------------------------------------------------<br>
     *</p>
     */
    @ResultMap("GoodsType")
	@Select("select GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME from dim_goods_type where GOODS_TYPE_ID = #{goodsTypeId}")
	public GoodsType selectGoodsTypeByGoodsTypeId(@Param("goodsTypeId") String goodsTypeId);
    
    /**
     * <p>Method ：selectGoodsTypeByGoodsTypeCode
     * <p>Description : 通过商品类型编码查询商品类型信息
     *
     * @param goodsTypeCode
     * @return 
     * @author  単スレッド-singlethreadzzz@gmail.com
     *<p>
     *--------------------------------------------------------------<br>
     * 修改履历：<br>
     *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
     *--------------------------------------------------------------<br>
     *</p>
     */
    @ResultMap("GoodsType")
	@Select("select GOODS_TYPE_ID,GOODS_TYPE_CODE,GOODS_TYPE_NAME from dim_goods_type where GOODS_TYPE_CODE = #{goodsTypeCode}")
	public GoodsType selectGoodsTypeByGoodsTypeCode(@Param("goodsTypeCode") String goodsTypeCode);

}