$(function () {
	fnPageLoad();
});
function fnPageLoad(){
	//初始化全部商品类型信息
//	fnInitAllGoodsTypeHtml();
	//初始化全部商品信息
	fnInitAllGoodsHtml();
	//初始化进货点击事件
//	fnInitPurchaseClick();
	//初始化售出点击事件
//	fnInitSellClick();
	//初始化商品新增或修改弹出模态框点击事件
//	fnInitSaveOrUpdateClick();
}
function fnInitAllGoodsTypeHtml(){
	$.ajax({
		  type: "GET",
	      url: path + "/salesManage/getAllUsedGoodsType",
	      data: "json",
	      contentType: 'application/json',
	      success: function (result) {
	    	  if(result){
	    		  if(result.code == 1){
	    			  var data = result.data;
	    			  for(i=0;i<data.length;i++){
		    			  var goodsHtml = "<div id=" + data[i].goodsTypeCode +" class='row'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><h4 class='page-header'>" + data[i].goodsTypeName +"</h4></div></div>";
		    			  $(".stockwarning").append(goodsHtml);
	    			  }
	    		  }else{
	    			  toastr.error(result.message);
	    		  }
	    	  }
		  },
	      error: function (e) {
	    	  toastr.error("查询已使用商品类型失败，请联系管理员");
		  }
	  });
}
function fnInitAllGoodsHtml(){
	
	$.ajax({
		  type: "GET",
	      url: path + "/goodsManage/getAllGoodsInfo",
	      data: "json",
	      contentType: 'application/json',
	      success: function (result) {
	    	  if(result){
	    		  if(result.code == 1){
	    			  var data = result.data;
	    			  for(i=0;i<data.length;i++){
	    				  var goodsTypeCodeHtml = $("#" + data[i].goodsTypeCode);
	    				  if(!$.isEmptyObject(goodsTypeCodeHtml)){
			    			  var goodsTypeHtml = "<div id=" + data[i].goodsTypeCode +" class='row'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><h4 class='page-header'>" + data[i].goodsTypeName +"</h4></div></div><div class='row' id="+ data[i].goodsTypeCode + "Row" +" ></div>";
			    			  $(".stockwarning").append(goodsTypeHtml);
	    				  }
	    			      var goodsHtml = "<div class='row'><div class='col-xs-6 col-sm-6 col-md-3 col-lg-3'><div class='thumbnail'><img class='images' src='../static/image/sample.png' alt='...'><div class='caption'><h3>"+data[i].goodsName+"</h3><p><a value="+ data[i].goodsId +" class='btn btn-primary purchase' role='button'>进货</a>&nbsp;&nbsp;<a value="+ data[i].goodsId +" class='btn btn-primary sell' role='button'>售出</a></p></div></div></div></div>";
    			          $("#"+data[i].goodsTypeCode + "Row").append(goodsHtml);
	    			  }
	    		  }else{
	    			  toastr.error(result.message);
	    		  }
	    	  }
		  },
	      error: function (e) {
	    	  toastr.error("查询商品失败，请联系管理员");
		  }
	  });
}
function fnInitPurchaseClick(){
	
}
function fnInitSellClick(){
	
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
				orderable: false,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品进货价格",
				sWidth: "10%",
				data: "goodsPurchasePrice",
				orderable: false,
				render: function(data, type , full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "商品售出价",
				sWidth: "10%",
				data: "goodsSellPrice",
				orderable: false,
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
