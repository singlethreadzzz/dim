$(function () {
	
	fnFooterChange();
	
	$(window).resize(function() {
		fnFooterChange();
	});
	
	function fnFooterChange() {
		$(".container").css("minHeight", $("body").outerHeight() - $(".header").outerHeight() - $(".footer").outerHeight());
	}
	//初始化toastr弹出框位置
	toastr.options.positionClass = 'toast-top-right';
});