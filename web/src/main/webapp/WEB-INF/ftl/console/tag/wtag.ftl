<#include "../common/header.ftl">

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
                    <li>
                        <a href="#">标签管理</a>
                    </li>
                </ul>
            </div>
            <div class="page-content clearfix">
                <div class="row ">
                    <div class="col-xs-12">
                        <div class="form-group clearfix">
                            <label class="col-sm-1 control-label">标签名称</label>
                            <input type="hidden" value="${(obj.item._id)!'0'}" id="_id">

                            <div class="col-sm-11">
                                <input type="input" class="form-control" name="_name" id="_name"
                                       class="required"
                                       value="${obj.item._name!''}" style="width: 200px;">
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="col-sm-1 control-label">分组名称</label>

                            <div class="col-sm-11">
                                <input name="_pname" id="_pname" class="form-control typeahead scrollable" type="text"
                                       value="${obj.item._pname!''}"
                                       style="font-size: 14px;padding: 6px 12px;width: 200px"/>
                            </div>
                        </div>


                        <div class="form-group clearfix">
                            <label class="col-sm-1 control-label">描述</label>

                            <div class="col-sm-11">
                                <textarea name="_intro" id="_intro" rows="5"
                                          class="autosize-transition form-control"
                                          style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 112px;">${obj.item._intro!''}</textarea>
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="col-sm-1 control-label">图标</label>

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
                                <div class="showimg" id="imgid" imgid="${obj.item._img!""}"
                                     style="margin: 2px auto">
                                    <a target="_blank"
                                       href="/images/${obj.item._img!""}/">${obj.item._img!""}</a>
                                </div>
                            </div>
                        </div>


                        <div class="form-actions form-group clearfix">
                            <div class="col-sm-11 col-sm-offset-1">
                                <button id="btn_submit" type="button" class="btn btn-default">提交</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
<#include "../common/footer.ftl">
</div>

<#include  "../common/endjs.ftl">

<script>

    function typeahead() {
        var substringMatcher = function (strs) {
            return function findMatches(q, cb) {
                var matches, substringRegex;
                matches = [];
                if (q === '') {
                    $.each(strs, function (i, str) {
                        matches.push({value: str});
                    });
                } else {
                    substrRegex = new RegExp(q, 'i');
                    $.each(strs, function (i, str) {
                        if (substrRegex.test(str)) {
                            matches.push({value: str});
                        }
                    });
                }
                cb(matches);
            }
        };
        $('#_pname').typeahead({
            hint: true,
            highlight: true,
            minLength: 0
        }, {
            name: 'states',
            displayKey: 'value',
            source: substringMatcher(${obj._pnames}),
            limit: 10
        });

    }
    $(function () {
        autosize($('textarea'));


        typeahead();

        fileupload("fileupload_group");
        //弹出选择图片 iframe
        $('#btn_selectimg').bind('click', function () {
                    layer.open({
                        title: "选择图片",
                        type: 2,
                        area: ['900px', '600px'],
                        offset: ['5%', ''],
                        content: ['/adm/upload/selectimg', 'no']
                    });

                }
        );


        $("#btn_submit").bind("click", function () {
            var _name = $("#_name").val();
            var _pname = $("#_pname").val();
            var _intro = $("#_intro").val();

            var _img = $("#imgid").attr("imgid");
            if (!_img) {
                alert("还没有上传图片");
                return;
            }

            if (!_name || !_pname || !_intro) {
                alert("内容不完整");
                return;
            }

            var data = {};
            data._id = $("#_id").val();
            data._name = _name;
            data._pname = _pname;
            data._intro = _intro;
            data._img = _img;

            $.post("/adm/tag_mgr/doaddup/?t=" + new Date().getTime(), data, function (rs) {
                console.log("返回结果" + rs);
                if (rs["status"] == "ok") {
                    window.location.href = "/adm/listtag";
                } else {
                    layer.msg(rs["status"], {icon: 0});
                }
            });
        });


    });

</script>
</body>
</html>