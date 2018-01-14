$(document).ready(function() {
	// 表单提交
	$('#categoriesTagEditBtn').on('click', function (event) {
            event.preventDefault();
		
			$('.modal-content').block({ 
				// apply css props as desired
            		css: {
		            border: 'none',
		            padding: '15px',
		            backgroundColor: '#000',
		            '-webkit-border-radius': '10px',
		            '-moz-border-radius': '10px',
		            opacity: .5,
		            color: '#fff'
		        },
				message: 'Hold on!'
			 });
			 //setTimeout($('.modal-content').unblock(), 2000);
			 
			 
            var categoriesTagEditForm = $($(this).data('form'));
			var url = categoriesTagEditForm.attr("action");
			
            //构造参数发送给后台
            var postData = categoriesTagEditForm.serializeArray();
            
            $.post(url, postData, function (json) {
                var data = $.parseJSON(json);
                if (data.success) {
                    //保存成功  1.关闭弹出层，2.刷新表格数据
                    $('.modal-content').unblock();
                    
                    $.dialog.tips("保存成功");
                    window.location.reload(true);
                } else {
                		$('.modal-content').unblock();
                    
                		$.dialog.alert("异常", "保存失败:" + data.message);
                    window.location.reload(true);
                }
            }).error(function () {
            		$('.modal-content').unblock();
                 
            		$.dialog.alert("异常", "Exception!");
            });

     });
     
});
