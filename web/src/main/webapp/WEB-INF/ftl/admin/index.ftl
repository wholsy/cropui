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
                        <a href="#">首页</a>
                    </li>
                </ul>
            </div>
            <div class="page-content">
                <!-- /.ace-settings-container -->
                <div class="page-header">
                    <h1>
                        快捷链接
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                        </small>
                    </h1>
                </div>
                <!-- /.page-header -->

                <div class="row">
                    <div class="col-xs-12" style="font-size: 16px">
                        <a target="_blank" href="/log">查看日志</a>
                        <br>
                        <a target="_self" href="/adm/lastblog">定位最后一次修改的文章</a>
                        <br>
                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
    </div>
<#include "admin/common/footer.ftl">
</div>

<#include "admin/common/endjs.ftl">
</body>
</html>