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
			 
            var tagEditForm = $($(this).data('form'));
            
			//调用parsley表单验证插件(提交时才会验证 是否必填和输入格式)
			var submitok  = tagEditForm.parsley().validate();
			if (!submitok){
			    $('.invalid-form-error-message')
			      .html(submitok ? '' : 'You must correctly fill *at least one of these two blocks!')
			      .toggleClass('filled', !submitok);
			    
				$('.modal-content').unblock();
				return false;
			}
			
			var url = tagEditForm.attr("action");
            //构造参数发送给后台
            var postData = tagEditForm.serializeArray();
            
            $.ajax({
			   url: url,
			   type: 'PUT',
			   data: postData,
			   success: function(data) {
			   		//alert(data);
			   		$('.modal-content').unblock();
	                if (data.success) {
	                    $.dialog.tips("保存成功");
	                    
	                    window.location.reload(true);
	                } else {
	                		$.dialog.alert(data.message);
	                }
			   },
			   error : function(rs){
	            		$('.modal-content').unblock();
	            		$.dialog.alert("出错了:" + rs.message);
	            }
			});
			// $.ajax end 
     });
     // 表单提交 end 
});
