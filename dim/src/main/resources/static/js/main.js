$(function () {

	if ($(window).height() == $(document).height()) {
		$(".footer").addClass("navbar-fixed-bottom");
		$(".footer").removeClass("navbar-static-bottom");
	}
	else {
		$(".footer").addClass("navbar-static-bottom");
		$(".footer").removeClass("navbar-fixed-bottom");
	}
	//初始化toastr弹出框位置
	toastr.options.positionClass = 'toast-top-right';
});