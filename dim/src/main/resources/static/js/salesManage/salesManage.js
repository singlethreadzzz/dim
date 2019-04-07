$(function () {
	
	fnPageLoad();
	
	function fnPageLoad(){
		// 初始化全部商品信息
		fnInitAllGoodsHtml();
		// 初始化商品进货点击事件
		fnInitModalPurchaseClick();
		// 初始化商品售出点击事件
		fnInitModalSellClick();
		// 初始化商品查询点击事件
		fnInitSearchButtonClick();
		// 初始化回车键查询事件
		fnSearchGoodsName();
	}
	function fnInitAllGoodsHtml(){
		$.ajax({
			  type: "GET",
		      url: path + "/goodsManage/getAllGoodsInfo",
		      dataType: "json",
		      contentType: 'application/json',
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  fnInitGoodsHtml(result.data)
		    			  // 初始化进货点击事件
		    			  fnInitPurchaseClick();
		    			  // 初始化售出点击事件
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
			var goodsIdPurchase = $(this).attr("value");
		    $(this).click(function(){
				$("#purchaseForm")[0].reset();
				$("#errorDivPurchasel").hide();
				$("#goodsIdPurchase").val(goodsIdPurchase);
				$("#purchaseModal").modal();
				$.ajax({
					  type: "GET",
				      url: path + "/goodsManage/getGoodsInfoByGoodsId",
				      dataType: "json",
				      contentType: 'application/json',
				      data: {
				    	  goodsId: goodsIdPurchase
				      },
				      success: function (result) {
				    	  if(result){
				    		  if(result.code == 1){
				    				$("#goodsCodePurchase").html(result.data.goodsCode);
				    				$("#goodsNamePurchase").html(result.data.goodsName);
				    				$("#goodsTypeNamePurchase").html(result.data.goodsTypeName);
				    				$("#goodsDescribePurchase").html(result.data.goodsDescribe);
				    				$("#goodsStockPurchase").html(result.data.goodsStock);
				    				$("#goodsPurchasePrice").val(result.data.goodsPurchasePrice);
				    				$("#goodsPurchaseTime").val(getFormat());
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
	function fnInitSellClick(){
		$(".sell").each(function(){
		var goodsIdSell = $(this).attr("value");
	    $(this).click(function(){
			$("#sellForm")[0].reset();
			$("#errorDivSell").hide();
			$("#goodsIdSell").val(goodsIdSell);
			$("#sellModal").modal();
			$.ajax({
				  type: "GET",
			      url: path + "/goodsManage/getGoodsInfoByGoodsId",
			      dataType: "json",
			      contentType: 'application/json',
			      data: {
			    	  goodsId: goodsIdSell
			      },
			      success: function (result) {
			    	  if(result){
			    		  if(result.code == 1){
			    				$("#goodsCodeSell").html(result.data.goodsCode);
			    				$("#goodsNameSell").html(result.data.goodsName);
			    				$("#goodsTypeNameSell").html(result.data.goodsTypeName);
			    				$("#goodsDescribeSell").html(result.data.goodsDescribe);
			    				$("#goodsStockSell").html(result.data.goodsStock);
			    				$("#goodsSellPrice").val(result.data.goodsSellPrice);
			    				$("#goodsSellTime").val(getFormat());
			    				$("#goodsSellQuantity").attr("max", result.data.goodsStock);
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
	
		$("#goodsPurchase").click(function() {
			var goodsPurchasePrice = $("#goodsPurchasePrice").val();
			if(!goodsPurchasePrice || $.trim(goodsPurchasePrice) == ""){
				$("#errorLabelPurchase").html("请填写商品进货价格");
				$("#errorDivPurchase").show();
				return;
			}
			var goodsPurchaseTime = $("#goodsPurchaseTime").val();
			if(!goodsPurchaseTime || $.trim(goodsPurchaseTime) == ""){
				$("#errorLabelPurchase").html("请填写商品进货时间");
				$("#errorDivPurchase").show();
				return;
			}
			var goodsPurchaseQuantity = $("#goodsPurchaseQuantity").val();
			if(!goodsPurchaseQuantity || $.trim(goodsPurchaseQuantity) == "" || goodsPurchaseQuantity == 0){
				$("#errorLabelPurchase").html("请填写商品进货数");
				$("#errorDivPurchase").show();
				return;
			}
			var goodsIdPurchase = $("#goodsIdPurchase").val();
			var goodsPurchaseRemarks = $("#goodsPurchaseRemarks").val();
			var goodsPurchaseLog = {};
			goodsPurchaseLog.goodsId = goodsIdPurchase;
			goodsPurchaseLog.goodsPurchasePrice = goodsPurchasePrice;
			goodsPurchaseLog.goodsPurchaseTime = goodsPurchaseTime;
			goodsPurchaseLog.goodsPurchaseQuantity = goodsPurchaseQuantity;
			goodsPurchaseLog.goodsPurchaseRemarks = goodsPurchaseRemarks;
			$.ajax({
				type: "POST",
				url: path + "/salesManage/saveGoodsPurchaseLog",
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(goodsPurchaseLog),
				success: function (result) {
					if(result){
						if(result.code == 1){
							toastr.success(result.message);
							$("#purchaseModal").modal("hide");
							refreshGoodsStock(goodsIdPurchase);
						}else{
							toastr.error(result.message);
						}
					}
				},
				error: function (e) {
					toastr.error("商品进货失败，请联系管理员");
				}
			});
		});
	}
	function fnInitModalSellClick() {
		$("#goodsSell").click(function() {
			var goodsSellPrice = $("#goodsSellPrice").val();
			if(!goodsSellPrice || $.trim(goodsSellPrice) == ""){
				$("#errorLabelSell").html("请填写商品售出价格");
				$("#errorDivSell").show();
				return;
			}
			var goodsSellTime = $("#goodsSellTime").val();
			if(!goodsSellTime || $.trim(goodsSellTime) == ""){
				$("#errorLabelSell").html("请填写商品售出时间");
				$("#errorDivSell").show();
				return;
			}
			var goodsSellQuantity = $("#goodsSellQuantity").val();
			if(!goodsSellQuantity || $.trim(goodsSellQuantity) == "" || goodsSellQuantity == 0){
				$("#errorLabelSell").html("请填写商品售出数");
				$("#errorDivSell").show();
				return;
			}
			var goodsStockSell = $("#goodsStockSell").html();
			if(parseInt(goodsSellQuantity) > parseInt(goodsStockSell)){
				alert(1);
				$("#errorLabelSell").html("售出数不可大于库存数");
				$("#errorDivSell").show();
				return;
			}
			var goodsIdSell = $("#goodsIdSell").val();
			var goodsSellRemarks = $("#goodsSellRemarks").val();
			var goodsSellLog = {};
			goodsSellLog.goodsId = goodsIdSell;
			goodsSellLog.goodsSellPrice = goodsSellPrice;
			goodsSellLog.goodsSellTime = goodsSellTime;
			goodsSellLog.goodsSellQuantity = goodsSellQuantity;
			goodsSellLog.goodsSellRemarks = goodsSellRemarks;
			$.ajax({
				type: "POST",
				url: path + "/salesManage/saveGoodsSellLog",
				dataType: "json",
				contentType: 'application/json',
				data: JSON.stringify(goodsSellLog),
				success: function (result) {
					if(result){
						if(result.code == 1){
							toastr.success(result.message);
							$("#sellModal").modal("hide");
							refreshGoodsStock(goodsIdSell);
						}else{
							toastr.error(result.message);
						}
					}
				},
				error: function (e) {
					toastr.error("商品售出失败，请联系管理员");
				}
			});
		});
	}
	
	function fnInitSearchButtonClick() {
		$("#searchButton").click(function() {
			var searchGoodsName = $("#searchGoodsName").val();
			$("#salesManage").empty();
			if(!searchGoodsName || $.trim(searchGoodsName) == ""){
				fnInitAllGoodsHtml();
			}else{
				fnInitSearchGoodsHtml(searchGoodsName);
			}
		});
	}
	
	function fnInitSearchGoodsHtml (searchGoodsName){
		$.ajax({
			type: "GET",
			url: path + "/salesManage/getFuzzyGoodsInfoByGoodsName",
			dataType: "json",
			contentType: 'application/json',
			data: {
				goodsName : searchGoodsName
			},
			success: function (result) {
				if(result){
					if(result.code == 1){
		    			  fnInitGoodsHtml(result.data)
		    			  // 初始化进货点击事件
		    			  fnInitPurchaseClick();
		    			  // 初始化售出点击事件
		    			  fnInitSellClick();
					}else{
						toastr.error(result.message);
					}
				}
			},
			error: function (e) {
				toastr.error("商品查询异常，请联系管理员");
			}
		});
	}
	
	function getFormat(){
		var format = "";
		var nTime = new Date();
		format += nTime.getFullYear() + "-";
		format += (nTime.getMonth() + 1) < 10 ? "0" + (nTime.getMonth() + 1)
				: (nTime.getMonth() + 1);
		format += "-";
		format += nTime.getDate() < 10 ? "0" + (nTime.getDate())
				: (nTime.getDate());
		format += "T";
		format += nTime.getHours() < 10 ? "0" + (nTime.getHours()) : (nTime
				.getHours());
		format += ":";
		format += nTime.getMinutes() < 10 ? "0" + (nTime.getMinutes()) : (nTime
				.getMinutes());
		format += ":00";
		return format;
	}

	function refreshGoodsStock(goodsId){
		$.ajax({
			  type: "GET",
		      url: path + "/goodsManage/getGoodsInfoByGoodsId",
		      data: "json",
		      contentType: 'application/json',
		      data: {
		    	  goodsId : goodsId
		      },
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  var data = result.data;
		    			  $("[name=" + data.goodsName + "]").find("#goodsStockH4").html(" 库存：" + data.goodsStock);
		    		  }else{
		    			  toastr.error("刷新商品库存失败，请手动刷新页面");
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("刷新商品库存异常，请手动刷新页面");
			  }
		  });
	}
	
	function fnSearchGoodsName() {
		$("#searchGoodsName").keypress(function (e) {
			if (e.which == 13) {
				var searchGoodsName = $("#searchGoodsName").val();
				$("#salesManage").empty();
				if(!searchGoodsName || $.trim(searchGoodsName) == ""){
					fnInitAllGoodsHtml();
				}else{
					fnInitSearchGoodsHtml(searchGoodsName);
				}
			}
		});
	}
	
	function fnInitGoodsHtml(data) {
		  for(i=0;i<data.length;i++){
			  var goodsTypeCodeHtml = $("#" + data[i].goodsTypeCode);
			  if(JSON.stringify(goodsTypeCodeHtml) == "{}"){
    			  var goodsTypeHtml = "<div id=" + data[i].goodsTypeCode +" class='row'><div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'><h4 class='page-header'>" + data[i].goodsTypeName +"</h4></div></div><div class='row' id="+ data[i].goodsTypeCode + "Row" +" ></div>";
    			  $("#salesManage").append(goodsTypeHtml);
			  }
			  var imageName = data[i].goodsPictureId;
			  if(data[i].goodsPictureId == null ||data[i].goodsPictureId == ""){
				  imageName = "sample.png";
			  }
			  var goodsName = data[i].goodsName;
			  if(data[i].goodsName.length > 9){
				  goodsName = data[i].goodsName.substring(0, 8) + "...";
			  }
			  var goodsDescribe = data[i].goodsDescribe;
			  if(data[i].goodsDescribe == null ||data[i].goodsDescribe == ""){
				  goodsDescribe = "该商品暂无描述<br/><br/>";
			  }else if(data[i].goodsDescribe.length < 16){
				  goodsDescribe = data[i].goodsDescribe + "<br/><br/>";
			  }else if(data[i].goodsDescribe.length > 32){
				  goodsDescribe = data[i].goodsDescribe.substring(0, 31) + "...";
			  }
		      var goodsHtml = "<div name=" + data[i].goodsName + " class='col-xs-6 col-sm-6 col-md-3 col-lg-3'><div class='thumbnail'><img class='images' src='" + path + "/static/images/" + imageName + "' alt='加载商品图片失败'><div class='caption'><span title='" + data[i].goodsName + "'><h3>"+goodsName+"</h3></span title='"+data[i].goodsDescribe+"'><p>"+goodsDescribe +"</p><h4 id='goodsStockH4'> 库存："+data[i].goodsStock +"</h4><p><a value="+ data[i].goodsId +" class='btn btn-primary purchase' role='button'>进货</a>&nbsp;&nbsp;<a value="+ data[i].goodsId +" class='btn btn-primary sell' role='button'>售出</a></p></div></div></div>";
	          $("#"+data[i].goodsTypeCode + "Row").append(goodsHtml);
		  }
	}
});