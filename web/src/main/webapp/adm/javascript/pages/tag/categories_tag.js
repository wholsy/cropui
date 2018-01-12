
$(document).ready(function() 
{
	/* 清除之前加载的数据，让对话框能够在每次打开时重新加载页面 */
	// 每次打开对话框之前移除数据
	$('.bs-tag-edit-modal-lg').on('hidden.bs.modal', function () {
            $(this).removeData("bs.modal");
            alert("窗口关闭了！");
     });
        
});
