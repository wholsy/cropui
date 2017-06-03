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
                        <a href="#">写系列教程</a>
                    </li>
                </ul>
            </div>
            <div class="page-content clearfix">
                <div class="row ">
                    <div class="col-xs-12">
                        <div class="form-group clearfix">
                            <label class="col-sm-1 control-label">标题</label>
                            <input type="hidden" value="${obj.item._id}" id="_id">

                            <div class="col-sm-11">
                                <input type="input" class="form-control" name="_seristitle" id="_seristitle"
                                       class="required"
                                       value="${obj.item._seristitle!''}">
                            </div>
                        </div>

                        <div class="form-group clearfix">
                            <label class="col-sm-1 control-label">内容</label>
                            <div class="col-sm-11">
                                <textarea name="_serisintro" id="_serisintro" rows="5"
                                          class="autosize-transition form-control"
                                          style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 112px;">${obj.item._serisintro!''}</textarea>
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

    $(function () {
        autosize($('textarea'));


        $("#btn_submit").bind("click", function () {
            var _seristitle = $("#_seristitle").val();
            var _serisintro = $("#_serisintro").val();

            if (!_serisintro || !_seristitle) {
                alert("内容不完整");
                return;
            }
            var data = {};
            data._id = $("#_id").val();
            data._seristitle = _seristitle;
            data._serisintro = _serisintro;

//            var jsondata = $.toJSON(data);

            $.post("/adm/seris_mgr/doaddup/?t=" + new Date().getTime(), data, function (rs) {
                console.log("返回结果" + rs);
                window.location.href = "/adm/listseris";
            });


        });


    });

</script>
</body>
</html>