package com.singlethreadzzz.dim.service.dataDisplay;

import java.util.List;

import com.singlethreadzzz.dim.pojo.SalesVolumeInfo;
import com.singlethreadzzz.dim.pojo.SalesVolumeTypeInfo;


public interface DataDisplayService {
	

	public List<SalesVolumeInfo> getGoodsSalesVolumeTop10() throws Exception ;
	
	public List<SalesVolumeTypeInfo> getGoodsSalesVolumeTypeTop10() throws Exception ;

}
