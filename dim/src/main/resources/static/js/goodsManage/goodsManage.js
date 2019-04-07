$(function () {
	fnPageLoad();

	function fnPageLoad(){
		//初始化商品信息table
		fnInitGoodsTable();
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
		//初始化上传商品图片点击事件
		fnInitUploadGoodsImageClick();
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
			    dataType: "json",
			    contentType: 'application/json',
				dataSrc: function(result) {
					var code = result.code;
					if(code == 1){
						return result.data;
					}else{
						toastr.error(result.message);
					}
				},
				error: function(e) {
					toastr.error("获取全部商品信息异常");
				}
			},
			drawCallback: function(settings) {
				//初始化商品信息table多选框点击事件
				fnInitGoodsTableCheckClick();
			},
			columns: [
				{
					cname: "",
					sWidth: "5%",
					data: "goodsId",
					orderable: false,
					className: "center",
					render: function(data, type , full, meta) {
						return "<input type='checkbox'  class='checkchild center'  value='" + data + "' />";
					}
				},{
					cname: "商品名称",
					sWidth: "15%",
					data: "goodsName",
					orderable: true,
					render: function(data, type , full, meta) {
						return "<span title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "商品编码",
					sWidth: "10%",
					data: "goodsCode",
					orderable: false,
					render: function(data, type , full, meta) {
						return "<span title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "商品类型名称",
					sWidth: "10%",
					data: "goodsTypeName",
					orderable: false,
					className: "center",
					render: function(data, type , full, meta) {
						return "<span title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "商品库存",
					sWidth: "10%",
					data: "goodsStock",
					orderable: true,
					className: "center",
					render: function(data, type , full, meta) {
						return "<span class='center' title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "商品进货价格",
					sWidth: "10%",
					data: "goodsPurchasePrice",
					orderable: true,
					className: "center",
					render: function(data, type , full, meta) {
						return "<span class='center' title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "商品售出价",
					sWidth: "10%",
					data: "goodsSellPrice",
					orderable: true,
					className: "center",
					render: function(data, type , full, meta) {
						return "<span class='center' title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "商品描述",
					sWidth: "20%",
					data: "goodsDescribe",
					orderable: false,
					render: function(data, type , full, meta) {
						return "<span title='" + data + "'>" + data + "</span>";
					}
				},{
					cname: "是否上传图片",
					sWidth: "10%",
					data: "goodsPictureId",
					orderable: true,
					className: "center",
					render: function(data, type , full, meta) {
						if(data && data != null){
							return "<span>是</span>";
						}
						return "<span>否</span>";
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
		
		$(".checkchild").each(function(){
			var length = $(".checkchild").length;
		    $(this).on("change", function(e) {
				var selectedlength = $(".checkchild:checked").length;
				if(selectedlength == 0 || selectedlength!=length){
					$(".checkall").prop("checked",false);
				}else{
					$(".checkall").prop("checked",true); 
				}
			});
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
			      dataType: "json",
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
			      dataType: "json",
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
			      dataType: "json",
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
			      dataType: "json",
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
	
	function fnInitUploadGoodsImageClick () {
		$("#uploadGoodsImage").click(function() {
			 if ($(".checkchild:checked").length == 0) {
					toastr.warning("请选择要上传图片的商品");
					return;
				} else if ($(".checkchild:checked").length > 1) {
					toastr.warning("每次只能上传一个商品图片");
					return;
				}
				var goodsId = $(".checkchild:checked").attr("value");
				$("#goodsImage").fileinput('destroy')
				$("#goodsImage").off('fileuploaded');
				$("#goodsImage").off('filesuccessremove');
				fnInitImageInput(goodsId);
				fnInitGoodsImagePreview(goodsId);
				$("#imageInputModal").modal();
		});
	}
	
	function fnInitImageInput(goodsId) {
		
		$("#goodsImage").fileinput({
	        language: 'zh', //设置语言
	        uploadUrl: path + "/goodsManage/goodsImageInput", //上传的地址
	        allowedFileExtensions: ['jpg', 'png'],//接收的文件后缀
	        showPreview :true, //是否显示预览
	        showUpload: true, //是否显示上传按钮
	        showCaption: false,//是否显示标题
	        autoReplace: true,//自动替换
	        browseClass: "btn btn-primary", //按钮样式     
	        dropZoneEnabled: true,//是否显示拖拽区域
	        minImageWidth: 150, //图片的最小宽度
	        minImageHeight: 200,//图片的最小高度
	        maxImageWidth: 1500,//图片的最大宽度
	        maxImageHeight: 2000,//图片的最大高度
	        maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
	        minFileCount: 1, //表示允许同时上传的最小文件个数
	        maxFileCount: 1, //表示允许同时上传的最大文件个数
	        enctype: 'multipart/form-data',
	        validateInitialCount:true,
			uploadExtraData: function(){
	            return {
					"goodsId": goodsId
				};
			},
//			initialPreview: "<img src='" + path + "/static/images/"+ goodsId +".jpg' >",
	        msgFilesTooMany: "每件商品只能上传{n}张图片！"
	    });
		
	    $("#goodsImage").on("fileuploaded", function (event, result, previewId, index) {
	        var code = result.code;
	        if (code == 0) {
	            toastr.error("图片上传失败，请重试");
	            return;
	        }
	        fnInitGoodsImagePreview(goodsId);
	        $("#goodsTable").DataTable().ajax.reload();
	    });
	    
	    $("#goodsImage").on("filesuccessremove", function (event, id) {
	    	$.ajax({
				  type: "DELETE",
			      url: path + "/goodsManage/deleteGoodsImageByGoodsId/" + goodsId,
			      dataType: "json",
			      contentType: 'application/json',
			      success: function (result) {
			    	  if(result){
			    		  if(result.code == 1){
			    			  fnInitGoodsImagePreview(goodsId);
			    			  $("#goodsTable").DataTable().ajax.reload();
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
	
	function fnInitGoodsImagePreview(goodsId) {
		$.ajax({
			  type: "GET",
		      url: path + "/goodsManage/getGoodsInfoByGoodsId",
		      dataType: "json",
		      contentType: 'application/json',
		      data: {
		    	  goodsId: goodsId
		      },
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  var goodsPictureId = result.data.goodsPictureId;
		    			  if(goodsPictureId == null || goodsPictureId == ""){
		    				  $("#goodsImagePreview").attr("src",path + "/static/images/sample.png?"+Math.random());
		    			  }else{
		    				  $("#goodsImagePreview").attr("src",path + "/static/images/" + goodsPictureId + "?" + Math.random());
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
});