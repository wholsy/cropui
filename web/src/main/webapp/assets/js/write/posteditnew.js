$(pageInit);

var jsonData = { articleBlogId: '0' };
var editor = null;

function pageInit() {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="articleContext"]', {
			cssPath : ['http://static.yueny.site/plugins/prettify/prettify.css'],
			allowFileManager : true,
			fileManagerJson : ctxy + '/file_manager_json',
			allowImageUpload : true,
			uploadJson : ctxy + "/upload_json",
			autoHeightMode : true,
			langType : 'zh-CN',
			readonlyMode : false,
			//0 禁止粘贴; 1 纯文本粘贴;2 HTML粘贴
			pasteType : 2,
			//default 默认风格;simple 简单风格
			themeType : 'simple',
			items : tools.editorBtn('full'),
			afterCreate : function() {
				this.loadPlugin('autoheight');
			},
			afterBlur : function(){ 
				//Kindeditor下获取文本框信息
				this.sync(); 
			},
			afterChange : function() {
				//字数统计
				K('.word_count').html(+this.count('text') + "/" + this.count());
			}
		});
		
		K('input[name=getHtml]').click(function(e) {
			alert(editor.html());
		});
		K('input[name=isEmpty]').click(function(e) {
			alert(editor.isEmpty());
		});
		K('input[name=getText]').click(function(e) {
			alert(editor.text());
		});
		K('input[name=selectedHtml]').click(function(e) {
			alert(editor.selectedHtml());
		});
		K('input[name=insertHtml]').click(function(e) {
			editor.insertHtml('<strong>插入HTML</strong>');
		});
		K('input[name=clear]').click(function(e) {
			editor.html('');
		});
		
		prettyPrint();
	});

	tags_init();

    if (jsonData.isDeleted) {
        topNote("该文章已被删除，无法编辑。");
        editor.setSource('');
    } else {
        edit_init();
    }
    if (jsonData.isClientUser) {
    	$('#p_desc,#d_desc').show();
    }
    
    $("#btnPublish,#btnToPublishBole").click(
        function () {
            if( $(this).attr("id") == "btnToPublishBole")
            {
                publish(true);
            }else{
                publish(false);
            }
        }
        );
    $("#btnDraft").click(draft);
    $("#btnCancel").click(cancelSave);
    
    $("#selType").change(function () {
    	if (this.value == "1") {
    	    $("#panleCopyright").show();
    	}
    	else {
    	    $("#panleCopyright").hide();
    	}
    });

    window.onbeforeunload = function () {
        if (showConfirm) {
            if (tools.val2("editor").replace(/<.+?>/g, "").length > 0) {
                try { return "您的文章尚未保存！"; } catch (err) { }
            }
        }
    };

    $("#imagecode,#seeagain").click(function () {
        checkcodeRefesh();
    });

    $(".radioBox.channel input").click(function () {
        value=$(this).val()+"";
        if (value == "1" || value == "16" || value == "3" || value == "2") {
            $("#joinblogcontest").show();
            //$("#joinblogcontest").attr("checked", true);
            $("#joinblogcontesttext").show();
        }
        else {
            $("#joinblogcontest").hide();
            $("#joinblogcontest").attr("checked", false);
            $("#joinblogcontesttext").hide();
        }
    });
    var articlepanleid = 0;
    $("#submitMoreArticle").click(function () {
        var articleMore = $("#articleMore");
        if (articleMore.val() == "") {
            alert("请录入文章url。");
            return;
        }
        else {
        }
        
        var articlePanle = $("#articlePanle p");

        if (articlePanle.length > 5)
        {
            alert("最多关联6篇文章。");
            return;
        }
        var articlePanlevalues=$("#articlePanle p span")
        for (var i = 0; i < articlePanlevalues.length; i++)
        {
            if ($($("#articlePanle p span")[i]).html() == articleMore.val()) {
                alert("已关联此文章。");
                return;
            }            
        }
        
        var articleurlsplit = articleMore.val().split('/');
        var articleid = "";
        if (articleurlsplit.length == 7)
        {
            articleid = articleurlsplit[6];
        }
        var check = false;

        if (articleid > 0) {
            $.ajax({
                url: "/mdeditor/ismyarticle?articleBlogId=" + articleid,
                dataType: "text",
                async: false,
                type: "get",
                success: function (data) {
                    if (data + "" != "") {
                        alert(data);
                        return;
                    }
                    else {
                        check = true;
                    }
                }
            });                       
        }
        else {
            alert("文章不存在。");
            return;
        }
        if (check) {
            $("#articlePanle").append('<p id="articlePanle' + articlepanleid + '"><span>' + articleMore.val() + '</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);"  onclick="delarticlepanle(' + articlepanleid + ')">删除</a></p>');
            articleMore.val("");
            articlepanleid++;
        }
    });

}

function delarticlepanle(articlepanleid)
{
    $("#articlePanle" + articlepanleid).remove();
}

/* 验证码 */
function checkcodeRefesh()
{
    setTimeout(function () {
        var imagecode = $("#imagecode");
        if (imagecode.length > 0) {
            $("#imagecode").attr("src", "/image/index?r=" + Math.random());
        }       
    },500);    
}

function topNote(text) {
    var div = document.createElement("div");
    div.id = "top_note";
    div.className = "radius";
    div.innerHTML = text;
    document.body.appendChild(div);
    $("#txtTitle").blur();
    $(".btn_area_1 input").attr("disabled", true);
}

function getCodePlugin() {
    var codeArr1 = ["html", "objc", "javascript", "css", "php", "csharp", "cpp", "java", "python", "ruby", "vb", "delphi", "sql", "plain"];
    var codeArr2 = ["HTML/XML", "Objective-C", "JavaScript", "CSS", "PHP", "C#", "C++", "Java", "Python", "Ruby", "Visual Basic", "Delphi", "SQL", "其它"];
    var opts = '';
    for (var i = 0; i < codeArr1.length; i++) {
        opts += tools.format('<option value="{0}">{1}</option>', codeArr1[i], codeArr2[i]);
    }
    var htmlCode = '<div>编程语言: <select id="xheCodeType">' + opts + '</select></div>'
        + '<div><textarea id="xheCodeCon" rows=6 cols=40></textarea></div>'
        + '<div style="text-align:right;"><input type="button" id="xheSave" value="确定" /></div>';
    var htmlSCode = '<div><div><a href="javascript:;" class="quote_code">引用我的代码片</a><a href="http://blog.codealy.com/snippets_manage" target="_blank" class="trim_code">整理我的代码片</a></div><textarea id="xheSCodeCon" rows=6 cols=40></textarea></div>'
        + '<div style="text-align:right;"><a href="https://code.codealy.com/FAQ" style="float:left;" target=_blank>什么是CODE代码片？</a><input type="button" id="xheSSave" value="确定" /></div>';
    var codePlugins = {
    	Code: {
    		c: 'Code', t: '插入代码', h: 1, e: function () {
    			var _this = this;
    			var jTest = $(htmlCode);
    			var jSave = $('#xheSave', jTest);
    			jSave.click(function () {
    				var sel_code = $("#xheCodeType").val();
    			    //var str = tools.format('<pre name="code" class="{0}">{1}</pre><br />', sel_code, _this.domEncode($("#xheCodeCon").val()));
    				var xheCodeConContent = $("#xheCodeCon").val()+"";
    				xheCodeConContent = xheCodeConContent.replace(/&reg/g, "&amp;reg").replace(/&copy/g, "&amp;copy");

    				var str = '<pre name="code" class="' + sel_code + '">' + _this.domEncode(xheCodeConContent) + '</pre><br />';
    				_this.loadBookmark();
    				_this.pasteHTML(str);
    				_this.hidePanel();
    				document.cookie = "postedit_code=" + sel_code + "; expires=" + function () { var d = new Date(); d.setFullYear(d.getFullYear() + 1); return d.toGMTString(); }();
    				return false;
    			});
    			_this.saveBookmark();
    			_this.showDialog(jTest);
    			var _his_code = document.cookie.match(new RegExp("(^|\s)postedit_code=([^;]*)(;|$)"));
    			if (_his_code) $('#xheCodeType').val(_his_code);
    			else $("#xheCodeType option")[0].selected = true;
    		}
    	}
    };
    return codePlugins;
}

var showConfirm = true;


function publish(isPubToBole) {
    save(1, isPubToBole,false);
}
function draft() {
    save(0,false,false);
    checkcodeRefesh();
}
var artId = 0;
var saveInter = null;
var saving = false; /*标识文章正在保存*/
function save(isPub, isPubToBole,isPayC) {
    if (tools.doing) {/*有其它操作（如保存草稿），等其完成再保存*/
        setTimeout("save(" + isPub + "," + isPubToBole + ")", 500);
        return;
    }
    
    if (saving) {
        alert("文章正在保存，请耐心等待。");
        return;
    }
    
    if (!checkForm(isPubToBole)){
    	return;
    }
    
    if (isPub) {/*发布后停止自动保存*/
        if (saveInter) clearInterval(saveInter);
        saveInter = null;
    } else if (saveInter){/*如果是立即保存，重置定时器，避免同时保存2遍*/
        clearInterval(saveInter);
        saveInter = setInterval("autoSave()", 30 * 1000);
    }
    
    var data = getPostData();
    data += "&stat=" + (isPub ? "publish" : "draft");
    
    saving = true;
    showNote("正在保存。。。");
    //showErr("请输入验证码计算结果。");
    
	var _form_parents = $("#btnPublish").parents("form");
	if(_form_parents.length== 0){
		return false;
	}
	var _form = _form_parents[0];
//	_form.submit();
//	return;
	
    var link = _form.action;
    link += "?edit=1";
    if (isPub) {
        link += "&isPub=1";
    }
    if (isPayC)
    {
        link += "&isPayC=1";
    }
    if (isPubToBole)
    {
        link += "&isPubToBole=1";
    }
    link += "&r="+Math.random();
    
    $.ajax({
        type:  _form.method,
        url: link,
        data: data,
        success: function (ret) {
            saving = false;
            ret = tools.toJSON(ret);
            if (!tools.success(ret.code)) {
                //服务器明确表示失败
            	showErr(ret.message);
                
                // 错误时输入验证码防止恶意攻击
                if ($("#checkcodearea").html().length < 5) {
                	var r = Math.random();
                    var checkcodehtml = '<div style="line-height:24px;">   ' +
                        '<span style="padding:0px 0 0px 10px;float:left;display:block;line-height:22px;">请输入计算结果（结果为数字）&nbsp;&nbsp;</span>' +
                         '<input type="text" id="txtCheckCode" style="width:5%; height:20px;float:left;" maxlength="4">' +
                         '<img src="/Image/Index?r=' + r + '" id="imagecode" style="padding:0px 0 0px 10px;float:left;" alt="验证码">  <a href="javascript:void(0);" id="seeagain" style="display:block;float:left;line-height:22px;margin-left:5px;">看不清</a>' +
                         '&nbsp;&nbsp;&nbsp;&nbsp;提示：自动保存和点击立即保存时不需要输入计算结果' +
                         '</div>';
                    $("#checkcodearea").html(checkcodehtml);
                } else {
                    checkcodeRefesh();
                }
            } else {
            	//成功
                showConfirm = false;
                if (!isPub) {
                    artId = ret.data;
                    showNote(tools.format("文章已保存{0} {1}", (jsonData.articleBlogId == '0' ? "为草稿" : ""), (new Date()).format("hh:mm:ss")));
                    $("#autosave_note").html('');
                } else {
                    saving = true; //保存后避免再次保存
                    showNote("正在跳转..."); // + "<a href='" + ret.data + "' target=_blank>点击查看</a>");
                    if (jsonData.articleBlogId == '0') {/*如果不是编辑*/
                        $("#txtTitle").val('');
                        $("#editor").val('');
                    }
                    
                    //location
                    location.href = ctxy + "/article/" + ret.data + ".html";
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        	//请求服务器异常
            saving = false;
            showNote('保存失败，请稍后重试。');
        }
    });	//$.ajax({ END
}

var old_con = null;
function autoSave() {
    if (tools.doing || saving) return;
    var con = tools.val2("editor");
    if (con.replace(/<.+?>/g, "").length < 100) return;
    if (con == old_con) return;
    old_con = con;
    tools.doing = true;
    var data = getPostData() + "&stat=draft&isauto=1";
    $.ajax({
        type: 'POST',
        url: '?edit=1',
        data: data,
        success: function (ret) {
            tools.doing = false;
            //ret = tools.toJSON(ret);
            if (ret.code == 1) {
                artId = ret.data;
                showConfirm = false;
                $("#autosave_note").html("文章已自动保存为草稿 " + (new Date()).format("hh:mm:ss"));
                showNote('');
            } else {
                /*showErr("文章自动保存失败，请注意保存。");*/
            }
        },
        error: function () {
            tools.doing = false;
        }
    });
}

/* 获取表单数据 */
function getPostData() {    
    var selTypeCode = $("#selType").val();
    var articleBlogId = $("input[name=articleBlogId]").val();
    var articleTitle = tools.val2("txtTitle");
    var articleContext = encodeURIComponent(tools.val("editor").replace(/<a\s/gi, '<a target=_blank '));    
	//摘要
    var articleDigest = tools.val2("articleDigest");
    //个人分类
    var owenerTag = $("#owenerTag").data("ids");
    //文章别名
    var articleAlias = tools.val2("articleAliasName");
    //文章分类（到分类首页）
    var categoryTagCode = $("input[name=categoryTagCode]:checked").val() || 0;
    //是否允许评论
    var comm = $("input[name=allowcomment]:checked").val();
    //文章标签 xxxx
    var articleTag = encodeURIComponent(function () {
	    	var s = []; 
	    	$('#d_tag2 span').each(function () {
	    		s.push(this.innerHTML); 
	    	});
	    	return s.join(',');
    	}());
    
    var data = "selTypeCode=" + selTypeCode + "&articleBlogId=" + articleBlogId + "&articleTitle=" + articleTitle + "&articleContext=" + articleContext;
    data += "&articleTag=" + articleTag + "&owenerTag=" + owenerTag;
    data += "&articleDigest=" + articleDigest;
    data += "&articleAlias=" + articleAlias + "&categoryTagCode=" + categoryTagCode + "&comm=" + comm + "&level=" + 0;
    data += "&artid=" + artId;
    data += "&checkcode=" + $("#txtCheckCode").val();
    data += "&userinfo1=" + $("#userinfo2").val();   

    //更多文章（添加关联的文章url）
    var articlePanlevalues = $("#articlePanle p span");
    var articlePanleurls = "";
    for (var i = 0; i < articlePanlevalues.length; i++) {
        var articlePanlevalue = $($("#articlePanle p span")[i]).html().split('/');
        if (articlePanlevalue.length == 7 ) {
            articlePanlevalue = articlePanlevalue[3] +":"+ articlePanlevalue[6]

            articlePanleurls += articlePanlevalue;
            if (i < articlePanlevalues.length - 1) {
                articlePanleurls += ",";
            }
        }
    }
    if (articlePanleurls != "")
    {
        data +="&articleMore="+ articlePanleurls;
    }

    return data;
}

function cancelSave() {
    showConfirm = false;
    if (jsonData.articleBlogId != '0' && jsonData.isDraft == 'True')
        artId = jsonData.articleBlogId;
    if (artId) {
        $.get("?del=1&artid=" + artId, function (ret) {
            location = "/postlist";
        });
    } else {
        location = "/postlist";
    }
}

function checkForm(isPubToBole) {
    if ($("#selType").val()=='0') {
        showErr("请选择文章类型。");
        return false;
    }
    if (!tools.hasVal("txtTitle")) {
        showErr("请输入文章标题。");
        return false;
    }
  
    var con = editor.html(); //editor.text();
//    if (!$.trim(con)) {
    if(editor.isEmpty()){
    	showErr("请输入文章内容。");
        return false;
    }
   
    if (isPubToBole)
    {
        var type = $("#selType").val();
        var categoryTagCode = $("input[name=categoryTagCode]:checked").val() || 0;
        if (type!="1")
        {
            showErr("发布并@博乐,文章类型必需是原创。");
            return false;
        }
        else if (!categoryTagCode) {
            showErr("发布并@博乐,必需选择一个文章分类。");
            return false;
        }
        else
        {
            var context = "";
            context = encodeURIComponent(tools.val("editor").replace(/<a\s/gi, '<a target=_blank '));
            if (context.length < 500) {
                showErr("发布并@博乐,博文内容长度必需大于或等于500。");
                return false;
            }
        }
    }

    return true;
}
/*提示信息*/
function showErr(err) {
    showMsg(err, true);
}
function showNote(msg) {
    showMsg(msg, false);
}
function showMsg(msg, isErr) {
    var srcMsg = msg;
    if (isErr) msg = "<font color=red>* " + msg + "</span>";
    else msg = "<font color=green>" + msg + "</font>";

    $("#sp_note").html(msg);

    if (srcMsg) $("#sp_note").show();
    else $("#sp_note").hide();
}
/*end*/

function edit_init() {   
    if (jsonData.articleBlogId == '0' || jsonData.isDraft == 'True') {
        /*自动保存策略：
        1、修改文章不自动保存（修改草稿自动保存）；
        2、点击发布按钮后不自动保存；
        3、文章内容字数小于100不自动保存；
        4、自动保存后离开页面不弹出提示；
        */
        saveInter = setInterval("autoSave()", 30 * 1000);
        $("#txtTitle").focus();
    } else {$("#p_n").show(); }
    if (jsonData.articleBlogId != '0') {
        $("#txtTitle").focus().val(decodeURIComponent(jsonData.articleTitle));
        $("#selType").val(jsonData.selTypeCode);
        $("#owenerTag").val(decodeURIComponent(jsonData.owenerTag)).blur();
        $("#txtTag2").val(decodeURIComponent(jsonData.articleTag));formatTag2();
        $("#articleAliasName").val(jsonData.articleAlias);
        $("input[name=categoryTagCode][value=" + jsonData.categoryTagCode + "]").attr("checked", true);
        $("input[name=allowcomment][value=" + jsonData.allowcomment + "]").attr("checked", true);
    }
}

/*==tag start==*/
function tags_init() {
//    $("#owenerTag").autocomplete(tags);
    $("#owenerTag").blur(resetChksShowByOwenerTag);
    
    resetChksByTagbox();
}


function chkTag(e) {
    var val = "";
    var ids="";
    // $(e).val(): input value
    //$(e).next().text(): label value
    if (e.checked) {
        val = $("#owenerTag").val().trim(',').trim('，') + ',' + $(e).next().text();
        val = val.trim(',').trim('，');
        ids = ($("#owenerTag").data("ids").trim(',').trim('，') + ',' + $(e).val()).trim(',').trim('，');
    } else {
        val = $("#owenerTag").val().replace(new RegExp("(^|[,，])(" + $(e).next().text().replace(/([()])/g,'\\$1') + ")([,，]|$)", "ig"), function (all, s1, s2, s3) {
            return (s1 && s3) ? s1 : '';
        });
        ids = $("#owenerTag").data("ids").replace(new RegExp("(^|[,，])(" + $(e).val().replace(/([()])/g,'\\$1') + ")([,，]|$)", "ig"), function (all, s1, s2, s3) {
            return (s1 && s3) ? s1 : '';
        });
    }
    
    $("#owenerTag").val(val.trim(','));
    $("#owenerTag").data("ids", ids);
}

/*根据已经输入的个人分类tags重置tagbox div选择框状态*/
function resetChksShowByOwenerTag() {
    var arr = $("#owenerTag").val().toLowerCase().split(/[,，]+/g);
    $("#tagbox input").each(function () {
        var has = false;
        var val = $(this).next().text().toLowerCase();
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {has = true; break; }
        }
        this.checked = has;
    })
    
    //重置ids
    
}

/* 根据tagbox选择框状态重置个人分类输入框 */
function resetChksByTagbox() {
	//清空重置
	$("#owenerTag").val('');
	
    $("#tagbox input").each(function () {
        chkTag(this);
    });
}

var valArray = ['', '', ''];
function getCurrVal(obj) {
    valArray[1] = '';
    var leftVal = getLeftText(obj);
    if (!leftVal) return "";
    var lastChar = leftVal.substr(leftVal.length - 1, 1);
    if (isSplit(lastChar)) return "";
    var allVal = obj.value;
    var i;
    for (i = leftVal.length - 1; i >= -1; i--) {
        if (i > -1 && isSplit(leftVal.substr(i, 1))) break;
    }
    if (i < -1) i = -1;
    valArray[1] = leftVal.substr(i + 1).trimStart();
    valArray[0] = leftVal.substr(0, i + 1);
    for (i = leftVal.length; i <= allVal.length; i++) {
        if (i < allVal.length && isSplit(allVal.substr(i, 1))) break;
    }
    valArray[2] = allVal.substr(i, allVal.length - i);
    
    return (valArray[1]);
}
function isSplit(c) {
    return (c == ',' || c == '，');
}
function getLeftText(obj) {
    if (obj.selectionStart || obj.selectionStart==0) {
        var idx = obj.selectionStart;
        return obj.value.substr(0, idx);
    }
    var rngSel = document.selection.createRange();
    var flag = rngSel.getBookmark();
    var rngTxt = obj.createTextRange();
    rngTxt.collapse();
    rngTxt.moveToBookmark(flag);
    rngTxt.moveStart('character', -obj.value.length);
    return rngTxt.text;
}
/*
function setCurrVal(val) {
    if (!valArray[1] || !val) return;
    $("#owenerTag").val(valArray[0] + val + valArray[2]);
}
*/

function showAllTags(e) {
    if (e.innerHTML == "[全部]") {
        if ($("#tagbox").css("position") != "absolute") {
            var pos = $("#tagbox").position();
            $("#tagbox").css({ position: "absolute", left: pos.left + "px", top: pos.top+2 + "px" });
        }
        $("#tagtb tbody").show();
        e.innerHTML = "[收起]";
    } else {
        $("#tagtb tbody").hide();
        e.innerHTML = "[全部]";
    }
    e.blur();
}

function getAsc(str) {
    return str.charCodeAt(0);
}

function getcookie(name) {
    var cookie_start = document.cookie.indexOf(name);
    var cookie_end = document.cookie.indexOf(";", cookie_start);
    return cookie_start == -1 ? '' : unescape(document.cookie.substring(cookie_start + name.length + 1, (cookie_end > cookie_start ? cookie_end : document.cookie.length)));
}

/*==tag end==*/

/*==tag2 begin==*/

$(function () {

    $('#p_desc,#d_desc').show();

    //富文本域失去焦点时, 获取'推荐标签'数据
    $('.section').mouseout(getTag2);
    $('#txtTag2').focus(showTag2).blur(formatTag2).keydown(tagKeydown);
    $('#txtTag2,#tag2box').mouseover(function () {
    	window.overtag2 = true;
    }).mouseout(function () {
    	window.overtag2 = false;
    });
    $(document.body).click(function (ev) {
    	if (window.overtag2) return;
        $('#tag2box').hide();
    });
    utag2 = unique_sort(utag2).slice(0,10);
    //填充常用标签
    if (utag2[0] != '') {
        var s = '';
        for (var i = 0; i < utag2.length; i++) {
            s += '<a onclick="javascript:setTag2(this);return false;">' + utag2[i] + '</a>';
        }
        $('#td_tag21').html(s);
        activeTag2();
    }
    var selTypeValue = $("#selType").val();
    if ($("#selType").val() == "1") {
        $(".subtit span").first().show();
    } else {
        $(".subtit span").first().hide();
    }
    
    $("#selType").click(function() {
        if ($("#selType").val() == "1") {
            $(".subtit span").first().show();
        } else {
            $(".subtit span").first().hide();
        }
    });

    //引用我的代码片:
    var quote_myCode = (function(){
        var oHtml ='<div id="snippetWrap"><h4 class="snippet_t">引用我的代码片<span id="snippet_close">x</span></h4><div class="snippet_c"><iframe id="snippet_ifram" width=400 height=390 frameborder=0 scrolling=auto src="http://http://blog.codealy.com/content/angular-app/index.html"></iframe></div><div class="snippet_b"><button id="snippet_confirm" value="确定">确定</button> </div> </div>';

        $(document).delegate('.quote_code','click',function()
        {
            $("body").append('<div id="mask_snippet"></div>');
            $("body").append(oHtml);
            return false;
        });
        $(document).delegate('#snippet_close','click',function()
        {
            codeHide();
            return false;
        });
        $(document).delegate('#snippet_confirm','click',function()
        {
            var sinppet_url =$("#snippet_ifram").contents().find("input:hidden").val();
            codeHide();
            $("#xhePanel,#xheCntLine").show();
            return false;
        });
        function codeHide(){
            $("#snippetWrap").remove();
            $("#mask_snippet").remove();
        }
    })();


});
var old_con_len = 0;
/* 
 * 获取推荐标签数据 
 */
function getTag2() {
    var len = tools.val2("editor").replace(/<.+?>/g, '').length;
    if (Math.abs(len - old_con_len) < 10) {
        return;
    }
    old_con_len = len;

    var data = { title: tools.val("txtTitle"), body: tools.val("editor").replace(/<.+?>/g, '') };
    $.post('?gettag=1', data, function (resp) {
    	resp = tools.toJSON(resp);
    	if (!tools.success(resp.code)) {
            //服务器明确表示失败
        } else {
        	var s = '';
            for (var i = 0; i < resp.data.length; i++) {
                var name = resp.data[i];
            	if (!utag2.has(name) && name!='无') {
            		s += '<a onclick="javascript:setTag2(this);return false;">' + name + '</a>';
            	}
            }
            if (s) {
                $('#td_tag22').html(s);
            }
        }
    });
}
/* 
 * 对推荐标签数据进行文本赋值 
 */
function showTag2() {
    if ($('#tag2box a').length > 0) {
        $('#tag2box').show();
    }
}
function setTag2(e) {
    var t = e.innerHTML;
    if (e.className == 'act') {
        $('#d_tag2 span').each(function () {
            if (this.innerHTML == t) this.click();
        });
        e.className = '';
    } else {
        $('#txtTag2').val(t);
        formatTag2();
    }
}
function tagKeydown(ev) {
    var code = (ev ? ev.which : event.keyCode);
    if (code == 8) {
        //如果是退格键
        var ss = $('#d_tag2 span');
        if (ss.length > 0 && $("#txtTag2").val().length == 0) {
            ss.eq(ss.length - 1).remove();
            formatTag2();
        }
    }
}
function formatTag2() {
    if ($('#txtTag2').val()) {
        var tt = '';
        $('#d_tag2 span').each(function () {
            tt += this.innerHTML + ',';
        });
        tt += $('#txtTag2').val().trim(',').replace(/[^\u4e00-\u9fa5\w\s\-+.#,，]+/g, '');
        tt = tt.trim(',').split(/[,，]+/g);
        var s = [];
        for (var i = 0; i < tt.length; i++) {
            if (!s.has(tt[i])) {
                s.push(tt[i].trim().slice(0, 20));
            }
            if (s.length == 5) break;
        }
        var str = '';
        for (var i = 0; i < s.length; i++) {
            str += '<span>' + s[i] + '</span>';
        }
        $('#d_tag2').html(str);
        $('#d_tag2 span').click(function () {
            $(this).remove();
            formatTag2();
        }).attr('title', '单击删除该标签');
    }
    var w = $('#d_tag2').width();
    $('#txtTag2').val('').css({
        'padding-left': w + 2,
        'width': 576 - w
    });
    activeTag2();
}
/*高亮预选标签中被选中的标签*/
function activeTag2() {
    var tags = [];
    $('#d_tag2 span').each(function () {
        tags.push(this.innerHTML);
    });
    $('#td_tag21>a,#td_tag22>a').each(function () {
        if (tags.has(this.innerHTML)) {
            this.className = 'act';
        } else {
            this.className = '';
        }
    });
}

Array.prototype.has = function (a) {
    for (var i = 0; i < this.length; i++) {
        if (this[i].toLowerCase() == a.toLowerCase())
            return true;
    }
    return false;
};
/*对tag进行去重排序*/
function unique_sort(arr) {
    arr.sort();
    var res = [[arr[0], 1]];
    for (var i = 1; i < arr.length; i++) {
        if (arr[i] != res[res.length - 1][0]) {
            res.push([arr[i], 1]);
        } else {
            res[res.length - 1][1]++;
        }
    }
    res.sort(function (a, b) { return a[1] < b[1]; });
    var aa = new Array();
    for (var i = 0; i < res.length; i++) {
        aa.push(res[i][0]);
    }
    return aa;
}
/*==tag2 end==*/