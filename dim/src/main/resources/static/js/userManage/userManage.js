$(function () {
	fnPageLoad();
});
function fnPageLoad(){
	//初始化用户信息table
	fnInitUserTable();
	//初始化用户信息table多选框点击事件
	fnInitUserTableCheckClick();
	//初始化新增用户点击事件
	fnInitAddUserClick();
	//初始化修改用户点击事件
	fnInitUpdateUserClick();
	//初始化删除用户点击事件
	fnInitDeleteUserClick();
	//初始化选择角色下拉框
	fnInitRoleNameOption();
	//初始化用户新增或修改弹出模态框点击事件
	fnInitSaveOrUpdateClick();
}
function fnInitUserTable(){
	
	var userTable = $("#userTable").DataTable({
		//显示搜索结果信息
		info:true,
		//
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
			url: path + "/userManage/getAllUsersInfo",
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
				toastr.error("获取全部用户信息异常");
			}
		},
		drawCallback: function(settings) {
			
		},
		columns: [
			{
				cname: "",
				sWidth: "10%",
				data: "userId",
				bSortable: false,
				render: function(data, type, full, meta) {
					return "<td><input type='checkbox'  class='checkchild'  value='" + data + "' /></td>";
				}
			},{
				cname: "用户帐号",
				sWidth: "30%",
				data: "userAccount",
				bSortable: true,
				render: function(data, type, full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "用户名",
				sWidth: "30%",
				data: "userName",
				bSortable: false,
				render: function(data, type, full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			},{
				cname: "用户角色",
				sWidth: "30%",
				data: "roleCnname",
				bSortable: false,
				render: function(data, type, full, meta) {
					return "<td title='" + data + "'>" + data + "</td>";
				}
			}
		]
	});

}

function fnInitUserTableCheckClick(){
	$(".checkall").click(function () {
	      var check = $(this).prop("checked");
	      $(".checkchild").prop("checked", check);
	});
}
function fnInitAddUserClick(){
	$("#addUser").click(function() {
		$("#userForm")[0].reset();
		$("#userAccount").attr('readonly', false);
		$("#userEdit").modal();
	});
}
function fnInitUpdateUserClick(){
	$("#updateUser").click(function() {
		 if ($(".checkchild:checked").length == 0) {
			toastr.warning("请选择要修改的用户");
			return;
		} else if ($(".checkchild:checked").length > 1) {
			toastr.warning("每次只能修改一个用户");
			return;
		}
		var userId = $(".checkchild:checked").attr("value");
		$("#userId").val(userId);
		$.ajax({
		      type: "GET",
		      url: path + "/userManage/getUserByUserId",
		      dataType: "json",
		      contentType: 'application/json',
		      data: {
		    	  userId: userId
		      },
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    				$("#userAccount").val(result.data.userAccount);
		    				$("#userAccount").attr('readonly', true);
		    				$("#userName").val(result.data.userName);
		    				$('#userPassword').val("");
		    				$("#roleName").val(result.data.roleId);
		    				$("#userEdit").modal();
		    		  }else{
		    			  toastr.error(result.message);
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("查询用户失败，请联系管理员");
			  }
		  });
	});
}
function fnInitDeleteUserClick(){
	$("#deleteUser").click(function () {
		 var userIdList = new Array();
		 if($(".checkchild:checked").length == 0){
			 toastr.warning("请选择要删除的用户");
			 return;
		 }
		 $(".checkchild:checked").each(function(){
			 userIdList.push($(this).attr("value"));
		  });
		$.ajax({
		      type: "DELETE",
		      url: path + "/userManage/deleteUser",
		      dataType: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(userIdList),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#userTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    			  $("#userTable").DataTable().ajax.reload();
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("删除用户失败，请联系管理员");
			  }
		  });
	});
}
function fnInitRoleNameOption() {
	$.ajax({
	      type: "GET",
	      url: path + "/userManage/getAllUserRoles",
	      dataType: "json",
	      success: function (result) {
	    	  if(result){
	    		  if(result.code == 1){
	    			  $.each(result.data, function (index, val) {
	    				  var option = "<option value=" + val.roleId + ">" + val.roleCnname + "</option>"
	    				  $("#roleName").append(option);
	    			  });
	    		  }else{
	    			  toastr.error(result.message);
	    		  }
	    	  }
		  },
	      error: function (e) {
	    	  toastr.error("获取角色信息失败，请联系管理员");
		  }
	  });
}
function fnInitSaveOrUpdateClick() {

	$("#saveOrUpdate").click(function() {
		var userAccount = $("#userAccount").val();
		if(!userAccount || $.trim(userAccount) == ""){
			$("#errorLabel").html("请填写用户帐号");
			$("#errorDiv").show();
			return;
		}
		var userName = $("#userName").val();
		if(!userName || $.trim(userName) == ""){
			$("#errorLabel").html("请填写用户昵称");
			$("#errorDiv").show();
			return;
		}
		var userPassword = $('#userPassword').val();
		if(!userPassword || $.trim(userPassword) == ""){
			$("#errorLabel").html("请填写密码");
			$("#errorDiv").show();
			return;
		}
		var roleId = $("#roleName").val();
		var userId = $("#userId").val();
		var user = {};
		user.userAccount = userAccount;
		user.userName = userName;
		user.userPassword = userPassword;
		user.roleId = roleId;
		if(userId && userId != ""){
			user.userId = userId;
			fnUpdateUser(user);
		}else{
			fnAddUser(user);
		}
	});
	function fnAddUser(user) {
		

		$.ajax({
		      type: "POST",
		      url: path + "/userManage/addUser",
		      dataType: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(user),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#userEdit").modal("hide");
		    			  $("#userTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("新增用户失败，请联系管理员");
			  }
		  });

	}
	function fnUpdateUser(user) {

		$.ajax({
		      type: "POST",
		      url: path + "/userManage/updateUser",
		      dataType: "json",
		      contentType: 'application/json',
		      data: JSON.stringify(user),
		      success: function (result) {
		    	  if(result){
		    		  if(result.code == 1){
		    			  toastr.success(result.message);
		    			  $("#userEdit").modal("hide");
		    			  $("#userTable").DataTable().ajax.reload();
		    		  }else{
		    			  toastr.error(result.message);
		    		  }
		    	  }
			  },
		      error: function (e) {
		    	  toastr.error("修改用户失败，请联系管理员");
			  }
		  });
	}
}
