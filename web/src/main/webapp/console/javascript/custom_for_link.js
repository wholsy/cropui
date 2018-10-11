/* 快捷折叠和关闭按钮 */

/* 退出登陆按钮 */
$(function () {
    $("#a_logout").bind("click", function () {
        $.post(ctx+"/admin/login/dologout", function (ret) {
        	ret = tools.toJSON(ret);
        	if (ret.data) {
        		window.location.href = ctx+"/admin/login/login.html";
        	}else {
        		 $.dialog.tips("当前退出失败！");
        	}
        })
    });
    
});

// Panel toolbox
$(document).ready(function() {
	// 收缩折叠
    $('.collapse-link').on('click', function() {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');
        
        // fix for some div with hardcoded fix class
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function(){
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200); 
            $BOX_PANEL.css('height', 'auto');  
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });
	
 	//关闭
    $('.close-link').click(function () {
        var $BOX_PANEL = $(this).closest('.x_panel');

        $BOX_PANEL.remove();
    });
});
// /Panel toolbox
