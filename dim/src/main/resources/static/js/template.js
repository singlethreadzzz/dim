$(function () {
	fnPageLoad();
});
function fnPageLoad(){
	
	/**
	 * 自定义datatables过滤查询
	 */
	$.fn.dataTable.ext.search.push(function(settings, data, dataIndex) {
		
		var tagName = $('#tagName').val();
		var tagDescript = $('#tagDescript').val();
		var createStartTimeValue = $('#createStartTimeValue').val();
		var createEndTimeValue = $('#createEndTimeValue').val();
		var updateStartTimeValue = $('#updateStartTimeValue').val();
		var updateEndTimeValue = $('#updateEndTimeValue').val();
		
		if(tagModalName || $.trim(tagModalName) != ""){
			if(data[1].indexOf(tagName) < 0){
				return false;
			}
		}
		
		if(tagDescript || $.trim(tagDescript) != ""){
			if(data[2].indexOf(tagDescript) < 0){
				return false;
			}
		}
		
		if(createStartTimeValue || $.trim(createStartTimeValue) != ""){
			if(data[4] < createStartTimeValue){
				return false;
			}
		}
		
		if(createEndTimeValue || $.trim(createEndTimeValue) != ""){
			if(data[4] > createEndTimeValue){
				return false;
			}
		}
		
		if(updateStartTimeValue || $.trim(updateStartTimeValue) != ""){
			if(data[5] < updateStartTimeValue){
				return false;
			}
		}
		
		if(updateEndTimeValue || $.trim(updateEndTimeValue) != ""){
			if(data[5] > updateEndTimeValue){
				return false;
			}
		}
		
		return true;
	});

}