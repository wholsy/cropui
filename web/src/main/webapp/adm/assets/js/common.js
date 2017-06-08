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

