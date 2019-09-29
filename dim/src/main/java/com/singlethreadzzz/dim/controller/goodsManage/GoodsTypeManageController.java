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

import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.goodsManage.GoodsTypeManageService;

/**
 * <p>Class       : com.singlethreadzzz.dim.controller.goodsManage.GoodsTypeManageController
 * <p>Descdription: 商品类型管理控制器
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
public class GoodsTypeManageController {
	
	@Autowired
	private GoodsTypeManageService goodsManageService;
	
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
	@GetMapping("/getAllGoodsType")
	@ResponseBody
	public Result getAllGoodsType () throws Exception  {
		Result result = new Result();
		List<GoodsType> goodsTypeList = new ArrayList<GoodsType>();
		try {
			goodsTypeList = this.goodsManageService.getAllGoodsType();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询全部商品类型信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部商品类型信息成功");
		result.setData(goodsTypeList);
		return result;
	}

	/**
	 * <p>Method ：saveGoodsType
	 * <p>Description : 新增商品类型
	 *
	 * @param goodsType
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
	@PostMapping("/saveGoodsType")
	@ResponseBody
	public Result saveGoodsType(@RequestBody GoodsType goodsType) throws Exception {
		
		Result result = new Result();	
		try {
			this.goodsManageService.saveGoodsType(goodsType);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("新增商品类型失败");
		}
		result.setCode(1);
		result.setMessage("新增商品类型成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * <p>Method ：updateGoodsType
	 * <p>Description : 修改商品类型
	 *
	 * @param goodsType
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
	@PostMapping("/updateGoodsType")
	@ResponseBody
	public Result updateGoodsType(@RequestBody GoodsType goodsType) throws Exception {
		
		Result result = new Result();

		try {
			this.goodsManageService.updateGoodsType(goodsType);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("修改商品类型失败");
		}
		result.setCode(1);
		result.setMessage("修改商品类型成功");
		result.setData(null);
		return result;
	}
	
	/**
	 * <p>Method ：deleteGoodsTypeByGoodsTypeId
	 * <p>Description : 通过商品类型ID删除商品类型
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
	@DeleteMapping("/deleteGoodsTypeByGoodsTypeId")
	@ResponseBody
	public Result deleteGoodsTypeByGoodsTypeId(@RequestBody List<String> goodsTypeIdList) throws Exception {
		
		Result result = new Result();
		
		try {
			this.goodsManageService.deleteGoodsTypeByGoodsTypeId(goodsTypeIdList);
		} catch (BeforeJsonException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("删除商品类型失败");
		}
		result.setCode(1);
		result.setMessage("删除商品类型成功");
		result.setData(null);
		return result;
	}
	
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
	@GetMapping("/getGoodsTypeByGoodsTypeId")
	@ResponseBody
	public Result getGoodsTypeByGoodsTypeId (@RequestParam String goodsTypeId) throws Exception  {
		Result result = new Result();
		GoodsType goodsType = new GoodsType();
		try {
			goodsType = this.goodsManageService.getGoodsTypeByGoodsTypeId(goodsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询商品类型信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询商品类型信息成功");
		result.setData(goodsType);
		return result;
	}
}
