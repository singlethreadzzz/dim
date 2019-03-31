$(function () {
	fnPageLoad();
});
function fnPageLoad(){
	//初始化全部商品信息
	fnInitAllGoodsHtml();
	//初始化商品新增或修改弹出模态框点击事件
//	fnInitSaveOrUpdateClick();
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
	    				  if(JSON.stringify(goodsTypeCodeHtml) == "{}"){
			    			  var goodsTypeHtml = "<div id=" + data[i].goodsTypeCode +" class='row'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><h4 class='page-header'>" + data[i].goodsTypeName +"</h4></div></div><div class='row' id="+ data[i].goodsTypeCode + "Row" +" ></div>";
			    			  $(".stockwarning").append(goodsTypeHtml);
	    				  }
	    			      var goodsHtml = "<div name=" + data[i].goodsName + " class='col-xs-6 col-sm-6 col-md-3 col-lg-3'><div class='thumbnail'><img class='images' src='" + path + "/static/images/sample.png' alt='加载商品图片失败'><div class='caption'><h3>"+data[i].goodsName+"</h3><p>"+data[i].goodsDescribe +"</p><h4> 库存："+data[i].goodsStock +"</h4><p><a value="+ data[i].goodsId +" class='btn btn-primary purchase' role='button'>进货</a>&nbsp;&nbsp;<a value="+ data[i].goodsId +" class='btn btn-primary sell' role='button'>售出</a></p></div></div></div>";
    			          $("#"+data[i].goodsTypeCode + "Row").append(goodsHtml);
	    			  }
	    			  //初始化进货点击事件
	    			  fnInitPurchaseClick();
	    			  //初始化售出点击事件
	    			  fnInitSellClick();
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
	$(".purchase").each(function(){
	    $(this).click(function(){
			$("#purchaseForm")[0].reset();
			$("#purchaseModal").modal();
	     })
	});
}
function fnInitSellClick(){
	$(".sell").each(function(){
		var goodsId = $(this).attr("value");
	    $(this).click(function(){
			$("#sellForm")[0].reset();
			$("#sellModal").modal();
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
	     })
	});
}
function fnInitModalPurchaseClick() {

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
function fnInitModalSellClick() {

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