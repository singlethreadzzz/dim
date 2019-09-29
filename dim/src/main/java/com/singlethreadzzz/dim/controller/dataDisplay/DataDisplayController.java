package com.singlethreadzzz.dim.controller.dataDisplay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singlethreadzzz.dim.exception.BeforeJsonException;
import com.singlethreadzzz.dim.pojo.Result;
import com.singlethreadzzz.dim.pojo.SalesVolumeInfo;
import com.singlethreadzzz.dim.pojo.SalesVolumeTypeInfo;
import com.singlethreadzzz.dim.service.dataDisplay.DataDisplayService;

@Controller
@RequestMapping("/dataDisplay")
public class DataDisplayController {
	
	@Autowired
	private DataDisplayService dataDisplayService;
 	
	@GetMapping("/getGoodsSalesVolumeTop10")
	@ResponseBody
	public Result getGoodsSalesVolumeTop10() throws Exception {
		Result result = new Result();
		List<SalesVolumeInfo> list = new ArrayList<SalesVolumeInfo>();
		try {
			list = this.dataDisplayService.getGoodsSalesVolumeTop10();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询商品销售top10失败");
		}
		result.setCode(1);
		result.setMessage("查询商品销售top10成功");
		result.setData(list);
		return result;
	}
	
	@GetMapping("/getGoodsSalesVolumeTypeTop10")
	@ResponseBody
	public Result getGoodsSalesVolumeTypeTop10() throws Exception {
		Result result = new Result();
		List<SalesVolumeTypeInfo> list = new ArrayList<SalesVolumeTypeInfo>();
		try {
			list = this.dataDisplayService.getGoodsSalesVolumeTypeTop10();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeforeJsonException("查询商品销售top10失败");
		}
		result.setCode(1);
		result.setMessage("查询商品销售top10成功");
		result.setData(list);
		return result;
	}

}
