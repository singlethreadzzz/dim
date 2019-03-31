$(function () {
	
	fnFooterChange();
	
	$(window).resize(function() {
		fnFooterChange();
	});
	
	function fnFooterChange() {
//		alert($("body").outerHeight());
//		alert($(".header").outerHeight());
//		alert($(".footer").outerHeight());
		$(".container").css("minHeight", $("body").outerHeight() - $(".header").outerHeight() - $(".footer").outerHeight() - 20);
	}
	//初始化toastr弹出框位置
	toastr.options.positionClass = 'toast-top-right';
});