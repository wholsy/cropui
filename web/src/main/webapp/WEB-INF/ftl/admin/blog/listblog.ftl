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
                    <li class="active">博客列表</li>
                </ul>
            </div>
            <div class="page-content">
                <!-- /.ace-settings-container -->
            <#--<div class="page-header">-->
            <#--<h1>-->
            <#--博客列表-->
            <#--<small>-->
            <#--<i class="ace-icon fa fa-angle-double-right"></i>-->
            <#--</small>-->
            <#--</h1>-->
            <#--</div>-->
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-md-12">
                        <form class="form-inline">
                            <div class="form-group">
                                <input type="text" class="form-control input-sm" id="txt_q" placeholder="标题">
                            </div>
                            <button id="btn_q" type="button" class="btn btn-info btn-xs">
                                	搜索
                            </button>
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <table class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>标识号</th>
                                <th>标题</th>
                                <th>时间</th>
                                <th>状态</th>
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
            <!-- /.page-content -->
        </div>
    </div>
<#include "admin/common/footer.ftl">
</div>

<#include  "admin/common/endjs.ftl">

<script id="template_list" type="text/html">
    {{each list as value i}}
    <tr>
        <td>{{value.articleBlogId}}</td>
        <td>{{value.articleTitle}}</td>
        <td>{{value.today}}</td>
        <td>
        	{{if value._isdraft ==1 }} 
        		草稿 
        	{{else}} 
        		已发布 
        	{{/if}}
        </td>
        <td>
            <a href="javascript:del({{value.articleBlogId}});">删除</a>
            <#-- _blank or _self -->
            <a target="_blank" href="${ctx}/admin/wblog.html?articleBlogId={{value.articleBlogId}}">编辑</a>
            <a target="_blank" href="${ctx}/article/{{value.articleBlogId}}.html">预览</a>
        </td>
    </tr>
    {{/each}}
</script>


<script>
    function del(articleBlogId) {
        if (confirm("确定删除吗?")) {
        	$.ajax({
				url: "${ctx}/admin/del.json?articleBlogId=" + articleBlogId + "",
				type: 'DELETE',
				success: function(rs) {
					if (rs["data"] == true) {
						alert("删除成功");
	                	window.location.reload();
	                }else {
	                	alert(rs["message"]);
	                }
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
        
        $.post("${ctx}/admin/do_show_list.json?pageno=" + pageno + "&t=" + new Date().getTime() + "", 
        	{"title_q": txt_q}, 
        	function (rs) {
	            var html = template('template_list', rs);
	            $("#list_tbody").html(html);
	            $("#pager").html(rs["pages"]);
        	}
        );
        
    }


</script>
</body>
</html>