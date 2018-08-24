$(function () {
        $("#btn_login").bind("click", function () {
            //alert("dwdwd");

            var username = $("#username").val();
            var password = $("#password").val();
            
            if (username && password) {
                $.post(ctx + "/admin/login/dologin", {username: username, password: password}, function (rs) {
                    if (rs["data"] == false) {
                    	$.dialog.alert(rs["message"]);
                    } else {
                        window.location.href = ctx + "/admin/welcome.html";
                    }
                });
            }
        })

});
