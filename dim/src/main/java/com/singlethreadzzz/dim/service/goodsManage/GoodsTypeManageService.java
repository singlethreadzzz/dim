package com.singlethreadzzz.dim.service.goodsManage;

import java.util.List;

import com.singlethreadzzz.dim.domain.GoodsType;

/**
 * <p>Class       : com.singlethreadzzz.dim.service.goodsManage.GoodsTypeManageService
 * <p>Descdription: 商品类型管理逻辑层接口类
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
public interface GoodsTypeManageService {
	
	/**
	 * <p>Method ：getAllGoodsType
	 * <p>Description : 查询所有商品类型信息
	 *
	 * @return
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public List<GoodsType> getAllGoodsType() throws Exception ;
		
	/**
	 * <p>Method ：saveGoodsType
	 * <p>Description : 新增商品类型
	 *
	 * @param goodsType
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public void saveGoodsType(GoodsType goodsType) throws Exception;
	
	/**
	 * <p>Method ：deleteGoodsTypeByGoodsTypeId
	 * <p>Description : 通过商品类型ID删除商品类型
	 *
	 * @param goodsTypeId
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public void deleteGoodsTypeByGoodsTypeId(List<String> goodsTypeIdList) throws Exception ;
	
	/**
	 * <p>Method ：updateGoodsType
	 * <p>Description : 修改商品类型
	 *
	 * @param goodsType
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public void updateGoodsType(GoodsType goodsType) throws Exception ;
	
	/**
	 * <p>Method ：getGoodsTypeByGoodsTypeId
	 * <p>Description : 通过商品类型ID查询商品类型信息
	 *
	 * @param goodsTypeId
	 * @return
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public GoodsType getGoodsTypeByGoodsTypeId(String goodsTypeId) throws Exception ;
	
	/**
	 * <p>Method ：getGoodsTypeByGoodsTypeCode
	 * <p>Description : 通过商品类型编码查询商品类型信息
	 *
	 * @param goodsTypeCode
	 * @return
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public GoodsType getGoodsTypeByGoodsTypeCode(String goodsTypeCode) throws Exception ;

}
