package com.singlethreadzzz.dim.service.dataDisplay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.singlethreadzzz.dim.mapper.dataDisplay.DataDisplayMapper;
import com.singlethreadzzz.dim.pojo.SalesVolumeInfo;
import com.singlethreadzzz.dim.pojo.SalesVolumeTypeInfo;
import com.singlethreadzzz.dim.service.dataDisplay.DataDisplayService;

@Service
public class DataDisplayServiceImpl implements DataDisplayService {
	
	@Autowired
	private DataDisplayMapper dataDisplayMapper;

	@Override
	public List<SalesVolumeInfo> getGoodsSalesVolumeTop10() throws Exception {
		return this.dataDisplayMapper.selectGoodsSalesVolumeTop10();
	}

	@Override
	public List<SalesVolumeTypeInfo> getGoodsSalesVolumeTypeTop10() throws Exception {
		return this.dataDisplayMapper.selectGoodsSalesVolumeTypeTop10();
	}

}
