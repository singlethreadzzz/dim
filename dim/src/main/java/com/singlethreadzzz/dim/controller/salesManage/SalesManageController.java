package com.singlethreadzzz.dim.controller.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.exception.BeforeJsonException;
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

}
