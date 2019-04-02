package com.singlethreadzzz.dim.service.goodsManage;

import java.util.List;

import com.singlethreadzzz.dim.domain.Goods;
import com.singlethreadzzz.dim.pojo.GoodsInfo;

/**
 * <p>Class       : com.singlethreadzzz.dim.service.goodsManage.GoodsManageService
 * <p>Descdription: 商品管理逻辑层接口类
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
public interface GoodsManageService {
	
	/**
	 * <p>Method ：getAllGoodsInfo
	 * <p>Description : 查询所有商品信息
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
	public List<GoodsInfo> getAllGoodsInfo() throws Exception ;
		
	/**
	 * <p>Method ：saveGoods
	 * <p>Description : 新增商品
	 *
	 * @param goods
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public void saveGoods(Goods goods) throws Exception;
	
	/**
	 * <p>Method ：deleteGoodsByGoodsId
	 * <p>Description : 通过商品ID删除商品
	 *
	 * @param goodsId
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public void deleteGoodsByGoodsId(List<String> goodsIdList) throws Exception ;
	
	/**
	 * <p>Method ：updateGoods
	 * <p>Description : 修改商品
	 *
	 * @param goods
	 * @throws Exception 
	 * @author  単スレッド-singlethreadzzz@gmail.com
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2019年3月27日，singlethreadzzz@gmail.com，创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	public void updateGoods(Goods goods) throws Exception ;
	
	/**
	 * <p>Method ：getGoodsByGoodsId
	 * <p>Description : 通过商品ID查询商品信息
	 *
	 * @param goodsId
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
	public Goods getGoodsByGoodsId(String goodsId) throws Exception ;
	
	/**
	 * <p>Method ：getGoodsByGoodsCode
	 * <p>Description : 通过商品编码查询商品信息
	 *
	 * @param goodsCode
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
	public Goods getGoodsByGoodsCode(String goodsCode) throws Exception ;
	
	/**
	 * <p>Method ：getGoodsByGoodsId
	 * <p>Description : 通过商品ID查询商品信息
	 *
	 * @param goodsId
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
	public GoodsInfo getGoodsInfoByGoodsId(String goodsId) throws Exception ;

}
