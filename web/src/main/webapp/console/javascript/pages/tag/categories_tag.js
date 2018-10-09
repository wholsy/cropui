$(document).ready(function() {
	$('.modal-link').on('click', function (event) {
			event.preventDefault();
			
			/*  */
			$('.x_content').block({ 
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
				message: '数据加载中...'
			 });
			
			var remoteUrlAddres = $(this).data('url');
            var modal = $('.variant-modal');
            modal.modal({
            		backdrop: 'static',
            		keyboard: true
            	});
            	modal.find(".modal-content").empty();
			
			$('.x_content').unblock();

            $.ajax({
            		url: remoteUrlAddres, 
            		type : "GET",
            		dataType: 'text',
            		
            		success: function (rs) {
                    		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  						// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
						modal.find(".modal-content").append(rs);
                    		modal.modal('show');
				}
            });
     });
     
     /* 清除之前加载的数据，让对话框能够在每次打开时重新加载页面 */
	$('.variant-modal').on('hidden.bs.modal', function (e) {
            $(this).find(".modal-content").empty();
            //$(e.target).removeData("bs.modal").find(".modal-content").empty();
            //window.location.reload(true);
     });
    $('.variant-modal').on('hide.bs.modal', function () {
			$(this).find(".modal-content").empty();
	});
     
});
