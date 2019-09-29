$(function () {
	
	fnFooterChange();
	
	$(window).resize(function() {
		fnFooterChange();
	});
	
	function fnFooterChange() {
		$("#mainContainer").css("minHeight", $("body").outerHeight() - $(".header").outerHeight() - $(".footer").outerHeight() - 20);
	}
	//初始化toastr弹出框位置
	toastr.options.positionClass = 'toast-top-right';
});