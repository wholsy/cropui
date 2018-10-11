/*
 * tools - v1.1.0
 * A jQuery Tools
 *
 * Made by yunshao <yueny09@163.com>
 * Since 1.0.0 20160926
 * Modify 1.1.0 20161202
 */
var tools = tools || function () { };

/* Since 1.0.0 20160926 */
/* Modify 1.1.0 20161202 */
tools.doing = false;
tools.ck_un = "UserName";
tools.ck_user = "UserInfo";

/* 请求是否为成功 */
tools.success = function (val) {
    return val == "00000000";
};

/* 获取上传结果码 */
tools.upload_success = function (resp){
	return tools.success(resp.code);
};

/* 获取上传结果文本 */
tools.upload_msg_text = function (resp){
   return resp.message;
};

/* 获取上传结果的地址列表  */
tools.upload_urls = function (resp){
   return resp.data;
};

tools.isArray = function (val) {
    return !!(val &&
        typeof val == "object" &&
        typeof val.length == 'number' &&
        typeof val.splice == 'function' &&
        !(val.propertyIsEnumerable('length'))
        );
};

tools.val = function (e) {
    return $.trim(((typeof e == "string") ? $("#" + e) : e).val());
};
tools.val2 = function (e) {
    return encodeURIComponent(tools.val(e));
};
tools.hasVal = function (e) {
    var v = tools.val(e);
    return (v != "" && v != "请选择");
};

/*判断URL中是否包含字符串s*/
tools.urlHas = function (s) {
    return window.location.href.toLowerCase().indexOf(s.toLowerCase()) > 0;
};

/*获取一个指定长度随机数*/
tools.random = function (len) {
    if (!len) len = 5;
    var r = Math.random().toString();
    return r.substr(r.length - len);
};

tools.parent = (parent || top || window);
tools.toJSON = function (data) {
    if (typeof data == "string") data = eval("(" + data + ")");
    return data;
};

/*q:参数名; url:可选*/
tools.getQuery = function (q, url) {
    if (!url) url = window.location + '';
    else url += '';
    var reg = new RegExp("[?&](" + q + ")=([^&]+)", "i");
    var re = reg.exec(url);
    if (re) return decodeURIComponent(re[2].replace(/[+]/g,' '));
    else return "";
};

tools.format = function (s, pars) {
    if (!s) return "";
    if (pars == null) return s;

    var i = 0, j = 1;
    while (j < arguments.length) {
        var arg = arguments[j];
        if (!arg) arg = '';
        if (tools.isArray(arg)) {
            for (var k = 0; k < arg.length; k++) {
                s = s.replace(new RegExp("\\\{" + (i++) + "\\\}", "g"), arg[k]);
            }
        } else {
            s = s.replace(new RegExp("\\\{" + (i++) + "\\\}", "g"), arg.toString());
        }
        j++;
    }
    return s;
};

tools.htmlEncode = function (s) {
    if (!s) return "";

    s = s.replace(/[&]/g, "&amp;");
    s = s.replace(/[<]/g, "&lt;");
    s = s.replace(/[>]/g, "&gt;");
    return s;
};
tools.urls = window.location.href.toLowerCase().substr(7).split('/');

/*判断是否按下了enter键*/
tools.isEnter = function (ev) {
    ev = ev || window.event;
    var code = (ev.keyCode || ev.which);
    return (code == 10 || code == 13);
};

String.prototype.endWith = function (s) {
    return new RegExp("(" + s + ")$", "ig").test(this);
};
String.prototype.trim = function (c) {
    if (!c) c = "\\s";
    return this.replace(new RegExp("(^" + c + "+)|(" + c + "+$)", "ig"), "");
};
String.prototype.trimStart = function () {
    return this.replace(/(^\s+)/g, "");
};
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    }
    return fmt;
};

/*从cookie获取用户名*/
tools.getUN = function () {
    var m = document.cookie.match(new RegExp("(^| )UserName=([^;]*)(;|$)"));
    if (m) return m[2];
    else return '';
};

/* 编辑器按钮 */
tools.editorBtn = function (val) {
	var bugTools =
		[ 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic','underline', '|', 
		'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|',
		'emoticons', 'image', 'code', 'link', '|', 'removeformat','undo', 'redo', 'fullscreen', 'source'];

	var simpleTools = 
		[ 'source', '|', 'preview', 'code', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic','underline', '|', 
		  'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist', 'insertunorderedlist', '|',
		  'emoticons', 'image', 'code', 'link', '|', 'removeformat','undo', 'redo', 'fullscreen', 'source'];

	var fullTools = 
		[ 'source', '|', 'undo', 'redo', '|', 
		  'preview', 'template', 'table', 'code', 'cut', 'copy', 'paste', 'selectall', '|', 'plainpaste', 'wordpaste', '|', 
		'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', '|', 
		'insertorderedlist', 'insertunorderedlist', '|', 
		'indent', 'outdent', 'subscript', 'superscript', '|', 'fullscreen', '/',// '/' 为换行
		'formatblock', 'fontname', 'fontsize', 'lineheight' , '|', 
		'forecolor', 'hilitecolor', '|', 'bold', 'italic', 'underline', 'strikethrough', '|',
		'removeformat', 'clearhtml', 'quickformat', '|', 'image', 'multiimage',
		'flash', 'media', 'insertfile', '|', 'hr', 'emoticons', 'baidumap', 'anchor', '|', 
		'link', 'unlink'];
	
	if(val == "bug"){
		return bugTools;
	}
	if(val == "simple"){
		return simpleTools;
	}
	return fullTools;
};

/*通过文章URL获取ID，传入url或id*/
tools.getArticleId = function (val) {
  if (val.indexOf("http://") == 0) {
      var mats = /(\/(\d+)\.aspx)|(details\/(\d+))/i.exec(val);
      if (mats) return (mats[0].split('/')[1].split('.')[0]);
  } else if (!isNaN(val)) {
      return (val);
  }
  return 0;
};

/*
q:参数名; 
url:可选;
*/
tools.getLink = function (url, text, target) {
	var link = '<a ';
						
	if (target) {
		// 不为空
		link += 'target="' + target + '" ';
	}
	
	//if((/^true$/i).test(style)){
	//	link += 'style="text-decoration:underline;color:blue" ';
	//}
    
    if (url) {
		link += 'href="' + url + '" ';
	}
	
	link += '>' + text + '</a>';
    
    return link;
};
