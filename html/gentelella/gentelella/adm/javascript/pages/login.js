$(function () {
        $("#btn_login").bind("click", function () {
            var username = $("#txt_username").val();
            var password = $("#txt_password").val();
            if (username && password) {
                $.post("${ctx}/console/login/dologin", {username: username, password: password}, function (rs) {
                    if (rs["data"] == false) {
                    	$.dialog.alert(rs["message"]);
                    } else {
                        window.location.href = "${ctx}/console/welcome.html";
                    }
                });
            }
        })

    });