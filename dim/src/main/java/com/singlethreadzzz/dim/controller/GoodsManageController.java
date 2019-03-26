package com.singlethreadzzz.dim.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.service.GoodsManageService;

@Controller
public class GoodsManageController {
	
	@Autowired
	private GoodsManageService goodsManageService;
	
	@GetMapping("/getAllGoodsTypeInfo")
	@ResponseBody
	public Result getAllGoodsTypeInfo () throws Exception  {
		Result result = new Result();
		List<GoodsType> goodsTypeList = new ArrayList<GoodsType>();
		try {
			goodsTypeList = this.goodsManageService.getAllGoodsTypeInfo();
		} catch (Exception e) {
			throw new BeforeJsonException("查询全部商品类型信息失败");
		}
		
		result.setCode(1);
		result.setMessage("查询全部商品类型信息成功");
		result.setData(goodsTypeList);
		return result;
	}

	@PostMapping("/saveGoodsType")
	@ResponseBody
	public Result saveGoodsType(@RequestBody GoodsType goodsType) throws Exception {
		
		Result result = new Result();	
		try {
			this.goodsManageService.saveGoodsType(goodsType);
		}catch (BeforeJsonException e) {
			throw e;
		} catch (Exception e) {
			throw new BeforeJsonException("新增商品类型失败");
		}
		result.setCode(1);
		result.setMessage("新增商品类型成功");
		result.setData(null);
		return result;
	}
	
	@PostMapping("/updateGoodsTypeByGoodsTypeId")
	@ResponseBody
	public Result updateGoodsTypeByGoodsTypeId(@RequestBody GoodsType goodsType) throws Exception {
		
		Result result = new Result();

		try {
			this.goodsManageService.updateGoodsTypeByGoodsTypeId(goodsType);
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
	
	@DeleteMapping("/deleteGoodsTypeByGoodsTypeId")
	@ResponseBody
	public Result deleteGoodsTypeByGoodsTypeId(@RequestParam String goodsTypeId) throws Exception {
		
		Result result = new Result();
		
		try {
			this.goodsManageService.deleteGoodsTypeByGoodsTypeId(goodsTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("删除商品类型失败");
		}
		result.setCode(1);
		result.setMessage("删除商品类型成功");
		result.setData(null);
		return result;
	}
	
	@GetMapping("/getGoodsTypeInfoByGoodsTypeId")
	@ResponseBody
	public Result getGoodsTypeInfoByGoodsTypeId (@RequestParam String goodsTypeId) throws Exception  {
		Result result = new Result();
		GoodsType goodsType = new GoodsType();
		try {
			goodsType = this.goodsManageService.getGoodsTypeInfoByGoodsTypeId(goodsTypeId);
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
