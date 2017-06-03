<#include "../common/header.ftl">
<#--tools-->
<script src="/adm/assets/js/jquery.json.min.js"></script>
<script src="/adm/assets/js/autosize.min.js"></script>
<script src="/adm/assets/js/jquery.inputlimiter.min.js"></script>
<script src="/adm/assets/js/bootstrap-tag.min.js"></script>

<#--上传组件-->
<link href="/adm/assets/js/jquery-file-upload/jquery.fileupload.css" rel="stylesheet">
<script src="/adm/assets/js/jquery-file-upload/jquery.ui.widget.js"></script>
<script src="/adm/assets/js/jquery-file-upload/jquery.iframe-transport.js"></script>
<script src="/adm/assets/js/jquery-file-upload/jquery.fileupload.js"></script>
<script src="/adm/assets/js/jquery-file-upload/upload-base.js"></script>

<link rel="stylesheet" href="/adm/assets/js/editor.md/css/editormd.css"/>
<script src="/adm/assets/js/editor.md/editormd.min.js"></script>

<body class="no-skin">
<#include "../common/navbar.ftl">

<div class="main-container ace-save-state" id="main-container">
<#include "../common/sidebar.ftl">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/adm/index">Home</a>
                    </li>

                <#if (obj.b._id) gt 0 && (obj.s._id) gt 0>
                    <li>
                        <a href="#">读书笔记 《${obj.b._booktitle}》</a>
                    </li>
                    <li>
                        <a href="#">章节 ${obj.s._seristitle}</a>
                    </li>
                <#else >
                    <li>
                        <a href="#">系列教程 ${obj.s._seristitle}</a>
                    </li>
                </#if>
                    <li class="active"><#if obj.single._id &gt; 0 >【修改】<#else>
                        【新文章】</#if>${(obj.single._isdraft)?string("【草稿】","")}</li>

                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">

                <#if (obj.b._id) gt 0 && (obj.s._id) gt 0>
                    <input type="hidden" id="_bookid" value="${(obj.b._id)!'0'}"/>
                    <input type="hidden" id="_serisid" value="${obj.s._id}"/>
                    <input type="hidden" id="_index_inseris" value="${(obj.single._index_inseris)!'1'}"/>
                    <a href="/adm/listnote">返回图书列表</a>
                    <a href="/adm/books_mgr/showlist_in/?_id=${obj.b._id}">返回章节列表</a>
                    <a href="/adm/seris_mgr/showlist_in/?_id=${obj.s._id}&book_id=${(obj.b._id)!'0'}">返回系列教程  ${obj.s._seristitle}</a>
                <#else >
                    <input type="hidden" id="_bookid" value="${(obj.b._id)!'0'}"/>
                    <input type="hidden" id="_serisid" value="${obj.s._id}"/>
                    <input type="hidden" id="_index_inseris" value="${(obj.single._index_inseris)!'1'}"/>
                    <a href="/adm/seris_mgr/showlist_in/?_id=${obj.s._id}">返回系列教程  ${obj.s._seristitle}</a>
                </#if>


                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <form id="save_single_form" method="post" action="" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">标题</label>
                                <input type="hidden" value="${obj.single._id}" id="_id">
                                <input type="hidden" value="${obj.single._isdraft?string(1,0)}" id="_isdraft">
                                <div class="col-sm-11">
                                    <input type="input" class="form-control" name="_title" id="_title" class="required"
                                           value="${obj.single._title!''}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">简短名</label>

                                <div class="col-sm-11">
                                    <input type="input" class="form-control" name="_titleinlogo" id="_titleinlogo"
                                           class="required"
                                           value="${obj.single._titleinlogo!''}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">摘要</label>

                                <div class="col-sm-11">
                                <textarea class="form-control" rows="3" name="_showintro" id="_showintro"
                                          class="required">${obj.single._showintro!""}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-1 control-label">选择标签</label>

                                <div class="col-sm-11">
                                    <input type="text" name="_tags" id="_tags"
                                           value="${obj.single._tags!""}"
                                           placeholder="Enter tags ..."/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-1 control-label">置顶大图</label>

                                <div class="col-sm-11" id="fileupload_group">
                        <span class="btn btn-success btn-sm fileinput-button">
                            <i class="glyphicon glyphicon-plus"></i>
                            <span>上传</span>
                            <!-- The file input field used as target for the file upload widget -->
                            <input class="btn btn-primary" id="fileupload" type="file" name="f" multiple="">
                        </span>
                                    <button id="btn_selectimg" type="button" class="btn btn-sm  btn-info">选择图片</button>
                                    <br>
                                    <div id="progress" class="progress" style="display: none;margin: 2px auto">
                                        <div class="progress-bar progress-bar-success"></div>
                                    </div>
                                    <div class="showimg" id="imgid" imgid="${obj.single._toppic!""}"
                                         style="margin: 2px auto">
                                        <a target="_blank"
                                           href="/images/${obj.single._toppic!""}/">${obj.single._toppic!""}</a>
                                    </div>
                                </div>
                            </div>

                        <#--内容区域-->
                            <div class="form-group">
                                <div id="test-editormd" style="z-index: 1800">
                                    <textarea style="display:none;">${obj.single._content_markdown!""}</textarea>
                                </div>
                            </div>

                            <div class="form-actions form-group clearfix">
                                <div class="col-sm-11 col-sm-offset-1">
                                    <button id="btn_submit" type="button" class="btn btn-default">确认提交</button>
                                    <button id="btn_savedraft" type="button" class="btn btn-default">保存草稿</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <!-- /.page-content -->


        </div>
    </div>
<#include "../common/footer.ftl">
</div>

<#include  "../common/endjs.ftl">


<script>


    function tag_init() {
        var tag_input = $('#_tags');
        try {
            var tags = new Array();

        <#if (obj._tags) ??>
            <#list  obj._tags as one>
                tags.push("${one._name}");
            </#list>
        </#if>
            tag_input.tag(
                    {
                        placeholder: tag_input.attr('placeholder'),
                        source: tags
                    }
            )
        }
        catch (e) {
            tag_input.after('<textarea id="' + tag_input.attr('id') + '" name="' + tag_input.attr('name') + '" rows="3">' + tag_input.val() + '</textarea>').remove();
        }
    }

    //编辑器
    var testEditor = neweditor("test-editormd");

    function autosavecontent() {
        savecontent(1);
    }
    function savecontent(auto) {
        if (auto) {
            auto = true;
        } else {
            auto = false;
        }

        var _title = $("#_title").val();
        var _showintro = $("#_showintro").val();
        var _content_html = testEditor.getHTML();
        var _content_markdown = testEditor.getMarkdown();
        var _tags = $("#_tags").val();
        var _serisid = $("#_serisid").val();
        var _bookid = $("#_bookid").val();
        var _index_inseris = $("#_index_inseris").val();


        if (!_title || !_showintro || !_content_html) {
            if (!auto) {
                alert("内容不完整");
            }
            return;
        }

        if (_content_html.indexOf("<h2") < 0) {
            if (!auto) {
                alert("至少要有1个h2标签");
            }
            return;
        }
        var _toppic = $("#imgid").attr("imgid");
        if (!_toppic) {
            if (!auto) {
                alert("还没有上传图片");
            }
            return;
        }

        var data = {};
        data.auto = auto;
        data._title = _title;
        data._showintro = _showintro;
        data._content_html = _content_html;
        data._content_markdown = _content_markdown;
        data._toppic = _toppic;
        data._id = $("#_id").val();
        data._serisid = _serisid;
        data._bookid = _bookid;
        data._index_inseris = _index_inseris;
        data._tags = _tags;
        data._titleinlogo = $("#_titleinlogo").val();

        if (data._id == 0) {
            data._isdraft = 1;
        } else {
            data._isdraft = $("#_isdraft").val();
        }

        $.post("/adm/seris_mgr/doaddup_inlist", data, function (rs) {
            if (rs["status"] == "ok") {
                $("#_id").val(rs["item"]["_id"]);
                if (!auto) {
                    window.location.href = "/adm/seris_mgr/showlist_in/?_id=" + _serisid + "&book_id=" + _bookid;
                }
            }
            console.log("返回结果" + $.toJSON(rs));
        });
    }

    setInterval("autosavecontent()", 1000 * 10);

    $(function () {
        $("#btn_submit").bind("click", function () {
            $("#_isdraft").val("0");
            savecontent();
        });
        $("#btn_savedraft").bind("click", function () {
            $("#_isdraft").val("1");
            savecontent();
        });

        tag_init();

        //设置哪个 btn_upload 为上传控件
        fileupload("fileupload_group");


        //弹出选择图片 iframe
        $('#btn_selectimg').bind('click', function () {
                    layer.open({
                        title: "选择图片",
                        type: 2,
                        area: ['900px', '600px'],
                        offset: ['8%', ''],
                        content: ['/adm/upload/selectimg', 'no']
                    });
                }
        );


    });


</script>

</body>
</html>