$(function () {
	fnPageLoad();

	function fnPageLoad(){
		//初始化点击事件
		fnInitClick();
	}
	function fnInitClick(){
		$("#login").click(function(){
			var account = $("#account").val();
			if(!account || $.trim(account) == ""){
				$("#errorLabel").html("请填写用户名");
				$("#errorDiv").show();
				return;
			}
			var password = $("#password").val();
			if(!password || $.trim(password) == ""){
				$("#errorLabel").html("请填写密码");
				$("#errorDiv").show();
				return;
			}
			var remember = $("#remember").prop('checked');
			var loginInfo ={};
			loginInfo.name = account;
			loginInfo.password = password;
			loginInfo.isRememberMe = remember;
			
			$.ajax({
			      type: "POST",
			      url: path + "/login",
			      dataType: "json",
			      contentType: 'application/json',
			      data: JSON.stringify(loginInfo),
			      success: function (result) {
			    	  if(result){
			    		  if(result.code == 0){
			    			  $('#errorLabel').html(result.message);  
				    		  $('#errorDiv').show();
			    		  }else{
			    			  window.location.href = path +  "/index";
			    		  }
			    	  }
				  },
			      error: function (e) {
			    	  toastr.error("登录失败，请联系管理员");
				  }
			  });
		});
	}
});