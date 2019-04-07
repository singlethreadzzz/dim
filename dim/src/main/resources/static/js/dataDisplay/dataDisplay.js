$(function () {
	fnPageLoad();
	
	function fnPageLoad() {
		
		fnEcharts();
		
	}
	
	function fnEcharts() {
		
		//初始化当月销售量top10Echarts
		fnSameMonthSalesVolumeTopEcharts();
		//初始化当月销售量类型top10Echarts
		fnSameMonthSalesVolumeTypeTopEcharts();
	}
	
	function fnSameMonthSalesVolumeTopEcharts() {
	  var sameMonthSalesVolumeTopEcharts = echarts.init(document.getElementById('sameMonthSalesVolumeTop'));
	  var defaultOption = {
			color: ['#98F5FF'],
            title: {
                text: '当月销售量top10'
            },
            tooltip: {},
            legend: {
                data:['销售量']
            },
            xAxis: {
                data: [],
                axisLabel: {  
                	   interval:0,  
                	   rotate:40  
                }
            },
            yAxis: {},
            series: [{
                name: '销售量',
                type: 'bar',
                data: []
            }],
            grid: {  
            	bottom:'20%'  
            }
        };
        sameMonthSalesVolumeTopEcharts.setOption(defaultOption);
		$.ajax({
			  type: "GET",
		      url: path + "/dataDisplay/getGoodsSalesVolumeTop10",
		      dataType: "json",
		      contentType: 'application/json',
		      data: {},
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  var data = result.data;
		    			  var xAxisData = [];
		    			  var seriesData = [];
		    			  for(var i=0;i<data.length;i++){
		    				  xAxisData.push(data[i].goodsName);
		    				  seriesData.push(data[i].salesVolume);
		    			  }
		    			  var ajaxOption = {
		    			            xAxis: {
		    			                data: xAxisData
		    			            },
		    			            series: [{
		    			                data: seriesData
		    			            }]
		    			        };
		    			  sameMonthSalesVolumeTopEcharts.setOption(ajaxOption);
		    		  }else{
		    			  toastr.error(result.message);
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("查询当月销售量top10异常，请联系管理员");
			  }
		  });
	}
	
	function fnSameMonthSalesVolumeTypeTopEcharts() {
		  var sameMonthSalesVolumeTypeTopEcharts = echarts.init(document.getElementById('sameMonthSalesVolumeTypeTop'));
		  var defaultOption = {
	            title: {
	                text: '当月销售量类型占比'
	            },
	            tooltip: {},
	            legend: {
	                data:[],
	            	left: 'center'
	            },
	            series: [{
	                type: 'pie',
	                radius : '65%',
	                center: ['50%', '50%'],
	                selectedMode: 'single',
	                data:[],
	                itemStyle: {
	                    emphasis: {
	                        shadowBlur: 10,
	                        shadowOffsetX: 0,
	                        shadowColor: 'rgba(0, 0, 0, 0.5)'
	                    }
	                }
	            }]
	        };
	        sameMonthSalesVolumeTypeTopEcharts.setOption(defaultOption);
			$.ajax({
				  type: "GET",
			      url: path + "/dataDisplay/getGoodsSalesVolumeTypeTop10",
			      dataType: "json",
			      contentType: 'application/json',
			      data: {},
			      success: function (result) {
			    	  if(result){
			    		  if(result.code == 1){
			    			  var data = result.data;
			    			  var pieDatas = [];
			    			  var legendDatas = [];
			    			  var colors = [];
			    			  for(var i=0;i<data.length;i++){
			    				  var pieData = {};
			    				  pieData.name = data[i].goodsTypeName;
			    				  pieData.value = data[i].salesTypeVolume;
			    				  pieDatas.push(pieData);
			    				  legendDatas.push(data[i].goodsTypeName);
			    				  colors.push(dimUtils.randomColor());
			    			  }
			    			  var ajaxOption = {
			    					    color: colors,
			    			            legend: {
			    			                data:legendDatas
			    			            },
			    			            series: [{
			    			                data: pieDatas
			    			            }]
			    			        };
			    			  sameMonthSalesVolumeTypeTopEcharts.setOption(ajaxOption);
			    		  }else{
			    			  toastr.error(result.message);
			    		  }
			    	  }
				  },
			      error: function (e) {
			    	  toastr.error("查询当月销售量类型top10异常，请联系管理员");
				  }
			  });
		}
});