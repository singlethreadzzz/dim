package com.singlethreadzzz.dim.controller.goodsManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.Goods;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.GoodsInfo;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.goodsManage.GoodsManageService;

/**
 * <p>Class       : com.singlethreadzzz.dim.controller.goodsManage.GoodsManageController
 * <p>Descdription: 商品管理控制器
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
@Controller
@RequestMapping("/goodsManage")
public class GoodsManageController {
	
	@Autowired
	private GoodsManageService goodsManageService;
	
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
	@GetMapping("/getAllGoodsInfo")
	@ResponseBody
	public Result getAllGoodsInfo () throws Exception  {
		Result result = new Result();
		List<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		try {
			goodsList = this.goodsManageService.getAllGoodsInfo();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询全部商品信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部商品信息成功");
		result.setData(goodsList);
		return result;
	}

	/**
	 * <p>Method ：saveGoods
	 * <p>Description : 新增商品
	 *
	 * @param goods
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
	@PostMapping("/saveGoods")
	@ResponseBody
	public Result saveGoods(@RequestBody Goods goods) throws Exception {
		
		Result result = new Result();	
		try {
			this.goodsManageService.saveGoods(goods);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("新增商品失败");
		}
		result.setCode(1);
		result.setMessage("新增商品成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * <p>Method ：updateGoods
	 * <p>Description : 修改商品
	 *
	 * @param goods
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
	@PostMapping("/updateGoods")
	@ResponseBody
	public Result updateGoods(@RequestBody Goods goods) throws Exception {
		
		Result result = new Result();

		try {
			this.goodsManageService.updateGoods(goods);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("修改商品失败");
		}
		result.setCode(1);
		result.setMessage("修改商品成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * <p>Method ：deleteGoodsByGoodsId
	 * <p>Description : 通过商品ID删除商品
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
	@DeleteMapping("/deleteGoodsByGoodsId")
	@ResponseBody
	public Result deleteGoodsByGoodsId(@RequestBody List<String> goodsIdList) throws Exception {
		
		Result result = new Result();
		
		try {
			this.goodsManageService.deleteGoodsByGoodsId(goodsIdList);
		} catch (BeforeJsonException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("删除商品失败");
		}
		result.setCode(1);
		result.setMessage("删除商品成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * <p>Method ：getGoodsInfoByGoodsId
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
	@GetMapping("/getGoodsInfoByGoodsId")
	@ResponseBody
	public Result getGoodsByGoodsId (@RequestParam String goodsId) throws Exception  {
		Result result = new Result();
		Goods goods = new Goods();
		try {
			goods = this.goodsManageService.getGoodsByGoodsId(goodsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询商品信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询商品信息成功");
		result.setData(goods);
		return result;
	}
}
