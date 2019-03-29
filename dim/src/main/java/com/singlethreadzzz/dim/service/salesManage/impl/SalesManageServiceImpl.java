package com.singlethreadzzz.dim.service.salesManage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.domain.GoodsType;
import com.singlethreadzzz.dim.mapper.salesManage.SalesManageMapper;
import com.singlethreadzzz.dim.service.salesManage.SalesManageService;

@Service
public class SalesManageServiceImpl implements SalesManageService{
	
	@Autowired
	private SalesManageMapper salesManageMapper;

	@Override
	public List<GoodsType> getAllUsedGoodsType() throws Exception {
		return this.salesManageMapper.selectAllUsedGoodsType();
	}

}
