<#include "admin/common/header.ftl">
<body class="no-skin">
<#include "admin/common/navbar.ftl">

<div class="main-container ace-save-state" id="main-container">
<#include "admin/common/sidebar-menu.ftl">

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
               <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="${ctx}/admin/">Home</a>
                    </li>
                    <li>
                        <a href="#">博客管理</a>
                    </li>
                    <li class="active">标签列表</li>
                </ul>
            </div>
            
            <div class="page-content">
	            <div class="page-header">
		            <h1>
		           	 	博客列表
			            <small>
			            	<i class="ace-icon fa fa-angle-double-right"></i>
			            </small>
		            </h1>
	            </div>

                <div class="row">
                    <div class="col-md-12">
                        <form class="form-inline">
                            <div class="form-group">
                                <input type="text" class="form-control input-sm" id="txt_q" placeholder="标签">
                            </div>
                            <button id="btn_q" type="button" class="btn btn-info btn-xs">
                                	搜索
                            </button>
                        </form>
                        <form class="form-inline">
                            <div class="form-group">
                                <a href="/adm/tag_mgr/showaddup">添加标签</a>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>标签</th>
                                <th>时间</th>
                                <th>管理</th>
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
            <#-- /.page-content -->
        </div>
    </div>
<#include "admin/common/footer.ftl">
</div>

<#include "admin/common/endjs.ftl">

<script id="template_list" type="text/html">
    {{each datas as value i}}
    <tr>
        <td>{{value._id}}</td>
        <td>{{value._pname}} - {{value._name}}</td>
        <td>{{value.ut}}</td>
        <td>
            <a href="javascript:del({{value._id}});">删除</a>
            <a target="_self" href="/adm/tag_mgr/showaddup/?_id={{value._id}}">编辑</a>
        </td>
    </tr>
    {{/each}}
</script>


<script>
    function del(id) {
        if (confirm("确定删除吗?")) {
            $.get("/adm/tag_mgr/del/?_id=" + id + "", function (data) {
                window.location.reload();
            });
        }
    }

    $(function () {
        //page(1);
        
        $("#btn_q").bind("click", function () {
            page(1);
        });
    });

    function page(pageno) {
        var txt_q = $("#txt_q").val();
        $.post("/adm/tag_mgr/doshowlist_tag/?pageno=" + pageno + "&t=" + new Date().getTime() + "", {txt_q: txt_q}, function (data) {
            console.log(data);
            var html = template('template_list', data);
            $("#list_tbody").html(html);
            $("#pager").html(data["pages"]);

        })
    }


</script>
</body>
</html>