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

import com.singlethreadzzz.dim.domain.Goods;
import com.singlethreadzzz.dim.pojo.GoodsInfo;


/**
 * <p>Class       : com.singlethreadzzz.dim.mapper.goodsManage.GoodsManageMapper
 * <p>Descdription: 商品管理Mapper类
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
public interface GoodsManageMapper {
	
	/**
	 * <p>Method ：selectAllGoods
	 * <p>Description : 查询所有商品
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
	@Results(id = "Goods", value = 
		{ @Result(property = "goodsId", column = "GOODS_ID"), 
			   @Result(property = "goodsName", column = "GOODS_NAME"),
		       @Result(property = "goodsCode", column = "GOODS_CODE"),
		       @Result(property = "goodsTypeCode", column = "GOODS_TYPE_CODE"),
		       @Result(property = "goodsStock", column = "GOODS_STOCK"),
		       @Result(property = "goodsPurchasePrice", column = "GOODS_PURCHASE_PRICE"),
		       @Result(property = "goodsSellPrice", column = "GOODS_SELL_PRICE"),
		       @Result(property = "goodsDescribe", column = "GOODS_DESCRIBE"),
		       @Result(property = "goodsPictureId", column = "GOODS_PICTURE_ID"),
		       @Result(property = "goodsCreateUser", column = "GOODS_CREATE_USER"),
		       @Result(property = "goodsCreateTime", column = "GOODS_CREATE_TIME"),
		       @Result(property = "goodsUpdateUser", column = "GOODS_UPDATE_USER"),
		       @Result(property = "goodsUpdateTime", column = "GOODS_UPDATE_TIME")
		})
	@Select("select GOODS_ID,GOODS_NAME,GOODS_CODE,GOODS_TYPE_CODE,GOODS_STOCK,GOODS_PURCHASE_PRICE,GOODS_SELL_PRICE,GOODS_DESCRIBE,GOODS_PICTURE_ID,GOODS_CREATE_USER,GOODS_CREATE_TIME,GOODS_UPDATE_USER,GOODS_UPDATE_TIME from dim_goods")
	public List<Goods> selectAllGoods();

	/**
	 * <p>Method ：insertGoods
	 * <p>Description : 新增商品
	 *
	 * @param Goods 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Insert("insert into dim_goods (GOODS_ID,GOODS_NAME,GOODS_CODE,GOODS_TYPE_CODE,GOODS_STOCK,GOODS_PURCHASE_PRICE,GOODS_SELL_PRICE,GOODS_DESCRIBE,GOODS_PICTURE_ID,GOODS_CREATE_USER,GOODS_CREATE_TIME,GOODS_UPDATE_USER,GOODS_UPDATE_TIME) values(#{goodsId}, #{goodsName}, #{goodsCode}, #{goodsTypeCode}, #{goodsStock}, #{goodsPurchasePrice}, #{goodsSellPrice}, #{goodsDescribe}, #{goodsPictureId}, #{goodsCreateUser}, #{goodsCreateTime}, #{goodsUpdateUser}, #{goodsUpdateTime})")
	public void insertGoods(Goods Goods);

	/**
	 * <p>Method ：deleteGoodsByGoodsId
	 * <p>Description : 通过商品ID删除商品
	 *
	 * @param GoodsId 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Delete("delete from dim_goods where GOODS_ID = #{goodsId}")
	public void deleteGoodsByGoodsId(@Param("goodsId") String goodsId);

	/**
	 * <p>Method ：updateGoods
	 * <p>Description : 修改商品
	 *
	 * @param Goods 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@Update("update dim_goods set GOODS_NAME = #{goodsName},GOODS_TYPE_CODE = #{goodsTypeCode},GOODS_STOCK = #{goodsStock},GOODS_PURCHASE_PRICE = #{goodsPurchasePrice},GOODS_SELL_PRICE = #{goodsSellPrice},GOODS_DESCRIBE = #{goodsDescribe},GOODS_PICTURE_ID = #{goodsPictureId},GOODS_UPDATE_USER = #{goodsUpdateUser},GOODS_UPDATE_TIME = #{goodsUpdateTime} where GOODS_ID = #{goodsId}")
	public void updateGoods(Goods Goods);
    
    /**
     * <p>Method ：selectGoodsByGoodsId
     * <p>Description : 通过商品ID查询商品信息
     *
     * @param GoodsId
     * @return 
     * @author  単スレッド-singlethreadzzz@gmail.com
     *<p>
     *--------------------------------------------------------------<br>
     * 修改履历：<br>
     *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
     *--------------------------------------------------------------<br>
     *</p>
     */
    @ResultMap("Goods")
	@Select("select GOODS_ID,GOODS_NAME,GOODS_CODE,GOODS_TYPE_CODE,GOODS_STOCK,GOODS_PURCHASE_PRICE,GOODS_SELL_PRICE,GOODS_DESCRIBE,GOODS_PICTURE_ID,GOODS_CREATE_USER,GOODS_CREATE_TIME,GOODS_UPDATE_USER,GOODS_UPDATE_TIME from dim_goods where GOODS_ID = #{goodsId}")
	public Goods selectGoodsByGoodsId(@Param("goodsId") String goodsId);
    
    /**
     * <p>Method ：selectGoodsByGoodsCode
     * <p>Description : 通过商品编码查询商品信息
     *
     * @param GoodsCode
     * @return 
     * @author  単スレッド-singlethreadzzz@gmail.com
     *<p>
     *--------------------------------------------------------------<br>
     * 修改履历：<br>
     *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
     *--------------------------------------------------------------<br>
     *</p>
     */
    @ResultMap("Goods")
	@Select("select GOODS_ID,GOODS_NAME,GOODS_CODE,GOODS_TYPE_CODE,GOODS_STOCK,GOODS_PURCHASE_PRICE,GOODS_SELL_PRICE,GOODS_DESCRIBE,GOODS_PICTURE_ID,GOODS_CREATE_USER,GOODS_CREATE_TIME,GOODS_UPDATE_USER,GOODS_UPDATE_TIME from dim_goods where GOODS_CODE = #{goodsCode}")
	public Goods selectGoodsByGoodsCode(@Param("goodsCode") String goodsCode);
    
	/**
	 * <p>Method ：selectAllGoodsInfo
	 * <p>Description : 查询所有商品信息(商品类型编码替换为商品类型名称)
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
	@Results(id = "GoodsInfo", value = 
		{ @Result(property = "goodsId", column = "GOODS_ID"), 
			   @Result(property = "goodsName", column = "GOODS_NAME"),
		       @Result(property = "goodsCode", column = "GOODS_CODE"),
		       @Result(property = "goodsStock", column = "GOODS_STOCK"),
		       @Result(property = "goodsPurchasePrice", column = "GOODS_PURCHASE_PRICE"),
		       @Result(property = "goodsSellPrice", column = "GOODS_SELL_PRICE"),
		       @Result(property = "goodsDescribe", column = "GOODS_DESCRIBE"),
		       @Result(property = "goodsPictureId", column = "GOODS_PICTURE_ID"),
		       @Result(property = "goodsCreateUser", column = "GOODS_CREATE_USER"),
		       @Result(property = "goodsCreateTime", column = "GOODS_CREATE_TIME"),
		       @Result(property = "goodsUpdateUser", column = "GOODS_UPDATE_USER"),
		       @Result(property = "goodsUpdateTime", column = "GOODS_UPDATE_TIME"),
		       @Result(property = "goodsTypeId", column = "GOODS_TYPE_ID"),
		       @Result(property = "goodsTypeCode", column = "GOODS_TYPE_CODE"),
		       @Result(property = "goodsTypeName", column = "GOODS_TYPE_NAME"),
		})
	@Select("select a.GOODS_ID,a.GOODS_NAME,a.GOODS_CODE,a.GOODS_STOCK,a.GOODS_PURCHASE_PRICE,a.GOODS_SELL_PRICE,a.GOODS_DESCRIBE,a.GOODS_PICTURE_ID,a.GOODS_CREATE_USER,a.GOODS_CREATE_TIME,a.GOODS_UPDATE_USER,a.GOODS_UPDATE_TIME,b.GOODS_TYPE_ID,b.GOODS_TYPE_CODE,b.GOODS_TYPE_NAME from dim_goods a,dim_goods_type b where a.GOODS_TYPE_CODE = b.GOODS_TYPE_CODE")
	public List<GoodsInfo> selectAllGoodsInfo();

}