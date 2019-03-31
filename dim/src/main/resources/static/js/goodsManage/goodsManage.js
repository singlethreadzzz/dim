$(function () {
	fnPageLoad();
});
function fnPageLoad(){
	//初始化商品信息table
	fnInitGoodsTable();
	//初始化商品信息table多选框点击事件
	fnInitGoodsTableCheckClick();
	//初始化新增商品点击事件
	fnInitAddGoodsClick();
	//初始化修改商品点击事件
	fnInitUpdateGoodsClick();
	//初始化删除商品点击事件
	fnInitDeleteGoodsClick();
	//初始化新增商品类型下拉框
	fnInitGoodsTypeSelect();
	//初始化商品新增或修改弹出模态框点击事件
	fnInitSaveOrUpdateClick();
}
function fnInitGoodsTable(){
	
	var goodsTable = $("#goodsTable").DataTable({
		
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
			url: path + "/goodsManage/getAllGoodsInfo",
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
				toastr.error("获取全部商品信息异常");
			}
		},
		drawCallback: function(settings) {
			
		},
		columns: [
			{
				cname: "",
				sWidth: "5%",
				data: "goodsId",
				orderable: false,
				render: function(data, type , full, meta) {
					return "<td><input type='checkbox'  class='checkchild'  value='" + data + "' /></td>";
				}
			},{
				cname: "商品名称",
				sWidth: "15%",
				data: "goodsName",
				orderable: true,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品编码",
				sWidth: "10%",
				data: "goodsCode",
				orderable: false,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品类型名称",
				sWidth: "10%",
				data: "goodsTypeName",
				orderable: false,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品库存",
				sWidth: "10%",
				data: "goodsStock",
				orderable: true,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品进货价格",
				sWidth: "10%",
				data: "goodsPurchasePrice",
				orderable: true,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品售出价",
				sWidth: "10%",
				data: "goodsSellPrice",
				orderable: true,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品描述",
				sWidth: "30%",
				data: "goodsDescribe",
				orderable: false,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			}
		]
	});

}

function fnInitGoodsTableCheckClick(){
	$(".checkall").click(function () {
	      var check = $(this).prop("checked");
	      $(".checkchild").prop("checked", check);
	});
}
function fnInitAddGoodsClick(){
	$("#addGoods").click(function() {
		$("#goodsForm")[0].reset();
		$("#goodsEdit").modal();
		$("#goodsCode").attr('readonly', false);
	});
}
function fnInitUpdateGoodsClick(){
	$("#updateGoods").click(function() {
		 if ($(".checkchild:checked").length == 0) {
			toastr.warning("请选择要修改的商品");
			return;
		} else if ($(".checkchild:checked").length > 1) {
			toastr.warning("每次只能修改一个商品");
			return;
		}
		var goodsId = $(".checkchild:checked").attr("value");
		$("#goodsId").val(goodsId);
		$.ajax({
			  type: "GET",
		      url: path + "/goodsManage/getGoodsInfoByGoodsId",
		      data: "json",
		      contentType: 'application/json',
		      data: {
		    	  goodsId: goodsId
		      },
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    				$("#goodsCode").val(result.data.goodsCode);
		    				$("#goodsCode").attr('readonly', true);
		    				$("#goodsName").val(result.data.goodsName);
		    				$("#goodsTypeCode").val(result.data.goodsTypeCode);
		    				$("#goodsPurchasePrice").val(result.data.goodsPurchasePrice);
		    				$("#goodsSellPrice").val(result.data.goodsSellPrice);
		    				$("#goodsDescribe").val(result.data.goodsDescribe);
		    				$("#goodsEdit").modal();
		    		  }else{
		    			  toastr.error(result.message);
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("查询商品失败，请联系管理员");
			  }
		  });
	});
}
function fnInitDeleteGoodsClick(){
	$("#deleteGoods").click(function () {
		 var goodsIdList = new Array();
		 if($(".checkchild:checked").length == 0){
			 toastr.warning("请选择要删除的商品");
			 return;
		 }
		 $(".checkchild:checked").each(function(){
			 goodsIdList.push($(this).attr("value"));
		  });
		$.ajax({
			  type: "DELETE",
		      url: path + "/goodsManage/deleteGoodsByGoodsId",
		      data: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(goodsIdList),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#goodsTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#goodsTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("删除商品失败，请联系管理员");
			  }
		  });
	});
}
function fnInitGoodsTypeSelect() {
	$.ajax({
	      type: "GET",
	      url: path + "/goodsManage/getAllGoodsType",
	      dataType: "json",
	      success: function (result) {
	    	  if(result){
	    		  if(result.code == 1){
	    			  $.each(result.data, function (index, val) {
	    				  var option = "<option value=" + val.goodsTypeCode + ">" + val.goodsTypeName + "</option>"
	    				  $("#goodsTypeCode").append(option);
	    			  });
	    		  }else{
	    			  toastr.error(result.message);
	    		  }
	    	  }
		  },
	      error: function (e) {
	    	  toastr.error("获取商品类型信息失败，请联系管理员");
		  }
	  });
}
function fnInitSaveOrUpdateClick() {

	$("#saveOrUpdate").click(function() {
		var goodsName = $("#goodsName").val();
		if(!goodsName || $.trim(goodsName) == ""){
			$("#errorLabel").html("请填写商品名称");
			$("#errorDiv").show();
			return;
		}
		var goodsCode = $("#goodsCode").val();
		if(!goodsCode || $.trim(goodsCode) == ""){
			$("#errorLabel").html("请填写商品编码");
			$("#errorDiv").show();
			return;
		}
		var goodsPurchasePrice = $("#goodsPurchasePrice").val();
		if(!goodsPurchasePrice || $.trim(goodsPurchasePrice) == ""){
			$("#errorLabel").html("请填写商品进货价格");
			$("#errorDiv").show();
			return;
		}
		var goodsSellPrice = $("#goodsSellPrice").val();
		if(!goodsSellPrice || $.trim(goodsSellPrice) == ""){
			$("#errorLabel").html("请填写商品售出价格");
			$("#errorDiv").show();
			return;
		}
		var goodsId = $("#goodsId").val();
		var goodsDescribe = $("#goodsDescribe").val();
		var goodsTypeCode = $("#goodsTypeCode").val(); 
		var goods = {};
		goods.goodsName = goodsName;
		goods.goodsCode = goodsCode;
		goods.goodsTypeCode = goodsTypeCode;
		goods.goodsPurchasePrice = goodsPurchasePrice;
		goods.goodsSellPrice = goodsSellPrice;
		goods.goodsDescribe = goodsDescribe;
		if(goodsId && goodsId != ""){
			goods.goodsId = goodsId;
			fnUpdateGoods(goods);
		}else{
			fnAddGoods(goods);
		}
	});
	function fnAddGoods(goods) {
		$.ajax({
			  type: "POST",
		      url: path + "/goodsManage/saveGoods",
		      data: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(goods),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#goodsEdit").modal("hide");
		    			  $("#goodsTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#goodsTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("新增商品失败，请联系管理员");
			  }
		  });

	}
	function fnUpdateGoods(goods) {

		$.ajax({
			  type: "POST",
		      url: path + "/goodsManage/updateGoods",
		      data: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(goods),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#goodsEdit").modal("hide");
		    			  $("#goodsTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#goodsTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("修改商品失败，请联系管理员");
			  }
		  });
	}
}
