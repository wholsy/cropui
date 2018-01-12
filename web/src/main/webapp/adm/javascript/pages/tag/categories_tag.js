$(document).ready(function() {
	
	/* 清除之前加载的数据，让对话框能够在每次打开时重新加载页面 */
	$(".bs-tag-edit-modal-lg").on("hidden", function() {  
	    $(this).removeData("modal");  
	});
	
});
