/* 验证码发送时的倒计时显示 */
/*
 * tools - v1.1.0
 * A jQuery Tools
 *
 * Made by yunshao <yueny09@163.com>
 * Since 1.0.0 20160926
 * Modify 1.1.0 20161202
 */
var captical = captical || function () { };

var countdown=60;
captical.shundown = false;

/*q:参数名; url:可选*/
captical.show = function (obj, showMsg) {
    if(captical.shundown){
        captical.shundown = false;
    }

    settime(obj, showMsg);
};

/*q:参数名; url:可选*/
captical.clear = function (obj) {
    captical.shundown = true;

    obj.removeAttribute("disabled");
    obj.value="重新获取验证码";
    countdown = 60;
};

(function ($) {
    settime = function(obj, showMsg) {
        if(captical.shundown){
            return;
        }

        if (countdown == 0) {
            obj.removeAttribute("disabled");
            obj.value="重新获取验证码";
            countdown = 60;
            return;
        } else {
            obj.setAttribute("disabled", true);
            obj.value=showMsg + "(" + countdown + ")";
            countdown--;
        }

        setTimeout(function() {
            settime(obj, showMsg)},1000
        )
    };

}(jQuery));