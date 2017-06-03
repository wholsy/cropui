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

                <#if (obj.b._id) gt 0>
                    <li>
                        <a target="_self"
                           href="/adm/books_mgr/showlist_in/?_id=${obj.b._id}">读书笔记  ${obj.b._booktitle}</a>
                    </li>
                    <li>
                        <a href="#">章节  ${obj.s._seristitle}</a>
                    </li>
                <#else >
                    <li>
                        <a href="#">系列教程列表</a>
                    <li>
                        <a href="#">${obj.s._seristitle}</a>
                    </li>
                    </li>
                </#if>
                    <li class="active">添加/修改博客</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">

                <#if (obj.b._id) gt 0>
                    <a href="/adm/listnote">返回读书笔记列表</a>
                    <a href="/adm/books_mgr/showlist_in/?_id=${obj.b._id}">返回《${obj.b._booktitle}》章节列表</a>
                    <a href="/adm/seris_mgr/showaddup_inlist/?seris_id=${obj.s._id}&book_id=${obj.b._id}">在该章节下新增文章</a>
                <#else >
                    <a href="/adm/listseris">返回教程列表</a>
                    <#if (obj.isdraft==0) >
                        <a href="/adm/seris_mgr/showaddup_inlist?seris_id=${obj.s._id}">在该系列教程下新增文章</a>
                    </#if>
                </#if>

                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>标题</th>
                                <th>时间</th>
                                <th>状态</th>
                                <th>管理</th>
                                <th>排序</th>
                            </tr>
                            </thead>
                            <tbody id="list_tbody">
                            </tbody>
                        </table>
                        <div class="row">
                            <div class="col-md-12">
                                <ul id="pager" class="pagination">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.page-content -->
        </div>
    </div>
<#include "../common/footer.ftl">
</div>

<#include  "../common/endjs.ftl">

<script id="template_list" type="text/html">
    {{each datas as value i}}
    <tr>
        <td>{{value._id}}</td>
        <td>{{value._title}}</td>
        <td>{{value.ut}}</td>
        <td>{{if value._isdraft == 0}}已发布{{else}}草稿{{/if}}</td>
        <td>
            <a href="javascript:del({{value._id}});">删除</a>
            <a target="_self"
               href="/adm/seris_mgr/showaddup_inlist?book_id=${(obj.b._id)!'0'}&seris_id=${obj.s._id}&single_id={{value._id}}">编辑</a>
            <a target="_blank" href="/pages/{{value._id}}/{{value._titleen}}.html">预览</a>
            <a target="_blank" href="/html/single/{{value._id}}">[生成html]</a>
        </td>
        <td>
            {{value._index_inseris}}
            <a target="_self" href="javascript:upone({{value._id}});">上移</a>
            <a target="_self" href="javascript:downone({{value._id}});">下移</a>
        </td>
    </tr>
    {{/each}}
</script>


<script>
    function upone(id) {
        $.get("/adm/seris_mgr/upone/?id=" + id + "", function (data) {
            window.location.reload();
        });
    }

    function downone(id) {
        $.get("/adm/seris_mgr/downone/?id=" + id + "", function (data) {
            window.location.reload();
        });
    }

    function del(id) {
        if (confirm("确定删除吗?")) {
            $.get("/adm/single_mgr/del/?id=" + id + "", function (rs) {
                if (rs == "ok") {
                    window.location.reload();
                } else {
                    alert(rs);
                }
            });
        }
    }

    $(function () {
        page(1);
        $("#btn_q").bind("click", function () {
            page(1);
        });
    });

    function page(pageno) {
        var txt_q = $("#txt_q").val();
        $.post("/adm/seris_mgr/doshowlist_in/?pageno=" + pageno + "&t=" + new Date().getTime() + "",
                {"txt_q": txt_q, "_serisid": ${obj.s._id}}, function (data) {
                    console.log(data);
                    var html = template('template_list', data);
                    $("#list_tbody").html(html);
                    $("#pager").html(data["pages"]);
                })
    }


</script>
</body>
</html>