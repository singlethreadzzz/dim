$(function () {
	fnPageLoad();
});
function fnPageLoad(){
	//初始化商品类型信息table
	fnInitGoodsTypeTable();
	//初始化商品类型信息table多选框点击事件
	fnInitGoodsTypeTableCheckClick();
	//初始化新增商品类型点击事件
	fnInitAddGoodsTypeClick();
	//初始化修改商品类型点击事件
	fnInitUpdateGoodsTypeClick();
	//初始化删除商品类型点击事件
	fnInitDeleteGoodsTypeClick();
	//初始化商品类型新增或修改弹出模态框点击事件
	fnInitSaveOrUpdateClick();
}
function fnInitGoodsTypeTable(){
	
	var goodsTypeTable = $("#goodsTypeTable").DataTable({
		
		info:true,
		bFilter: true,
		autoTidth: false,
		searching: true,
		paging: true,
		iDisplayLength: 10,
		bLengthChange: true,
		language: constant.dataTablesLanguage,
		ordering: true,
		order: [[ 1, 'asc' ]],
		bServerSide: false,
		ajax: {
			url: path + "/goodsManage/getAllGoodsType",
			type: "GET",
			dataSrc: function(result) {
				var code = result.code;
				if(code == 1){
					return result.data;
				}else{
					toastr.error(result.message);
				}
			},
			erroe: function(e) {
				toastr.error("获取全部商品类型信息异常");
			}
		},
		drawCallback: function(settings) {
			
		},
		columns: [
			{
				cname: "",
				sWidth: "20%",
				data: "goodsTypeId",
				orderable: false,
				render: function(data, type, full, meta) {
					return "<td><input type='checkbox'  class='checkchild'  value='" + data + "' /></td>";
				}
			},{
				cname: "商品类型编码",
				sWidth: "40%",
				data: "goodsTypeCode",
				orderable: true,
				render: function(data, type, full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品类型名称",
				sWidth: "40%",
				data: "goodsTypeName",
				orderable: false,
				render: function(data, type, full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			}
		]
	});

}

function fnInitGoodsTypeTableCheckClick(){
	$(".checkall").click(function () {
	      var check = $(this).prop("checked");
	      $(".checkchild").prop("checked", check);
	});
}
function fnInitAddGoodsTypeClick(){
	$("#addGoodsType").click(function() {
		$("#goodsTypeForm")[0].reset();
		$("#goodsTypeEdit").modal();
		$("#goodsTypeCode").attr('readonly', false);
	});
}
function fnInitUpdateGoodsTypeClick(){
	$("#updateGoodsType").click(function() {
		 if ($(".checkchild:checked").length == 0) {
			toastr.warning("请选择要修改的商品类型");
			return;
		} else if ($(".checkchild:checked").length > 1) {
			toastr.warning("每次只能修改一个商品类型");
			return;
		}
		var goodsTypeId = $(".checkchild:checked").attr("value");
		$("#goodsTypeId").val(goodsTypeId);
		$.ajax({
		      type: "GET",
		      url: path + "/goodsManage/getGoodsTypeByGoodsTypeId",
		      dataType: "json",
		      contentType: 'application/json',
		      data: {
		    	  goodsTypeId: goodsTypeId
		      },
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    				$("#goodsTypeCode").val(result.data.goodsTypeCode);
		    				$("#goodsTypeCode").attr('readonly', true);
		    				$("#goodsTypeName").val(result.data.goodsTypeName);
		    				$("#goodsTypeEdit").modal();
		    		  }else{
		    			  toastr.error(result.message);
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("查询商品类型失败，请联系管理员");
			  }
		  });
	});
}
function fnInitDeleteGoodsTypeClick(){
	$("#deleteGoodsType").click(function () {
		 var goodsTypeIdList = new Array();
		 if($(".checkchild:checked").length == 0){
			 toastr.warning("请选择要删除的商品类型");
			 return;
		 }
		 $(".checkchild:checked").each(function(){
			 goodsTypeIdList.push($(this).attr("value"));
		  });
		$.ajax({
		      type: "DELETE",
		      url: path + "/goodsManage/deleteGoodsTypeByGoodsTypeId",
		      dataType: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(goodsTypeIdList),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#goodsTypeTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#goodsTypeTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("删除商品类型失败，请联系管理员");
			  }
		  });
	});
}
function fnInitSaveOrUpdateClick() {

	$("#saveOrUpdate").click(function() {
		var goodsTypeCode = $("#goodsTypeCode").val();
		if(!goodsTypeCode || $.trim(goodsTypeCode) == ""){
			$("#errorLabel").html("请填写商品类型编码");
			$("#errorDiv").show();
			return;
		}
		var goodsTypeName = $("#goodsTypeName").val();
		if(!goodsTypeName || $.trim(goodsTypeName) == ""){
			$("#errorLabel").html("请填写商品类型名称");
			$("#errorDiv").show();
			return;
		}
		var goodsTypeId = $("#goodsTypeId").val();
		var goodsType = {};
		goodsType.goodsTypeCode = goodsTypeCode;
		goodsType.goodsTypeName = goodsTypeName;
		if(goodsTypeId && goodsTypeId != ""){
			goodsType.goodsTypeId = goodsTypeId;
			fnUpdateGoodsType(goodsType);
		}else{
			fnAddGoodsType(goodsType);
		}
	});
	function fnAddGoodsType(goodsType) {

		$.ajax({
		      type: "POST",
		      url: path + "/goodsManage/saveGoodsType",
		      dataType: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(goodsType),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#goodsTypeEdit").modal("hide");
		    			  $("#goodsTypeTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#goodsTypeTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("新增商品类型，请联系管理员");
			  }
		  });

	}
	function fnUpdateGoodsType(goodsType) {

		$.ajax({
		      type: "POST",
		      url: path + "/goodsManage/updateGoodsType",
		      dataType: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(goodsType),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#goodsTypeEdit").modal("hide");
		    			  $("#goodsTypeTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#goodsTypeTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("修改商品类型失败，请联系管理员");
			  }
		  });
	}
}
