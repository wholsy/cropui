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
                        <a href="#">读书笔记</a>
                    </li>
                    <li>
                        <a href="#">《${obj.book._booktitle}》</a>
                    </li>
                    <li class="active">添加/修改章节</li>
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <a href="/adm/listnote">返回读书笔记列表</a>
                    <a href="/adm/books_mgr/showaddup_inlist/?book_id=${obj.book._id}">在该读书笔记下新增章节</a>
                </div>


                <div class="row">
                    <div class="col-md-12">
                        <table class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>标题</th>
                                <th>时间</th>
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
        <td>{{value._seristitle}}</td>
        <td>{{value.ut}}</td>
        <td>
            <a href="javascript:del({{value._id}});">删除</a>
            <a target="_self"
               href="/adm/books_mgr/showaddup_inlist/?book_id=${obj.book._id}&seris_id={{value._id}}">编辑</a>
            <a target="_self" href="/adm/seris_mgr/showlist_in/?_id={{value._id}}&book_id=${obj.book._id}">管理章节中的文章</a>
            <a target="_blank" href="/html/seris/{{value._id}}">[生成html]</a>
        </td>
        <td>
            {{value._index_inbook}}
            <a target="_self" href="javascript:upone({{value._id}});">上移</a>
            <a target="_self" href="javascript:downone({{value._id}});">下移</a>
        </td>
    </tr>
    {{/each}}
</script>


<script>
    function upone(id) {
        $.get("/adm/books_mgr/upone/?id=" + id + "", function (data) {
            window.location.reload();
        });
    }

    function downone(id) {
        $.get("/adm/books_mgr/downone/?id=" + id + "", function (data) {
            window.location.reload();
        });
    }


    function del(id) {
        if (confirm("确定删除吗?")) {
            $.get("/adm/seris_mgr/del/?_id=" + id + "", function (rs) {
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
        $.post("/adm/books_mgr/doshowlist_in/?pageno=" + pageno + "&t=" + new Date().getTime() + "",
                {"_bookid": ${obj.book._id}}, function (data) {
                    console.log(data);
                    var html = template('template_list', data);
                    $("#list_tbody").html(html);
                    $("#pager").html(data["pages"]);
                })
    }


</script>
</body>
</html>