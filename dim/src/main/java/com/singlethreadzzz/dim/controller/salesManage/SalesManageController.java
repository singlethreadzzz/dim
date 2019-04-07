package com.singlethreadzzz.dim.controller.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.GoodsPurchaseLog;
import com.singlethreadzzz.dim.domain.GoodsSellLog;
import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.GoodsInfo;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.salesManage.SalesManageService;

@Controller
@RequestMapping("/salesManage")
public class SalesManageController {
	
	@Autowired
	private SalesManageService salesManageService;
	
	@GetMapping("/getAllUsedGoodsType")
	@ResponseBody
	public Result getAllUsedGoodsType() throws Exception {
		Result result = new Result();
		List<GoodsType> goodsTypeList = new ArrayList<GoodsType>();
		try {
			goodsTypeList = this.salesManageService.getAllUsedGoodsType();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询全部使用商品类型失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部使用商品类型成功");
		result.setData(goodsTypeList);
		return result;
	}
	
	@PostMapping("/saveGoodsPurchaseLog")
	@ResponseBody
	public Result saveGoodsPurchaseLog(@RequestBody GoodsPurchaseLog goodsPurchaseLog) throws Exception {
		Result result = new Result();
		try {
			this.salesManageService.saveGoodsPurchaseLog(goodsPurchaseLog);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("保存进货信息失败");
		}
		
		result.setCode(1);
		result.setMessage("保存进货信息成功");
		result.setData(null);
		return result;
		
	}
	
	@PostMapping("/saveGoodsSellLog")
	@ResponseBody
	public Result saveGoodsSellLog(@RequestBody GoodsSellLog goodsSellLog) throws Exception {
		Result result = new Result();
		try {
			this.salesManageService.saveGoodsSellLog(goodsSellLog);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("保存售出信息失败");
		}
		
		result.setCode(1);
		result.setMessage("保存售出信息成功");
		result.setData(null);
		return result;
	}
	
	@GetMapping("/getFuzzyGoodsInfoByGoodsName")
	@ResponseBody
	public Result getFuzzyGoodsInfoByGoodsName(@RequestParam String goodsName) throws Exception {
		Result result = new Result();
		List<GoodsInfo> goodsInfoList = new ArrayList<GoodsInfo>();
		try {
			goodsInfoList = this.salesManageService.getFuzzyGoodsInfoByGoodsName(goodsName);
		}catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询商品信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询商品信息成功");
		result.setData(goodsInfoList);
		return result;
		
	}

}
