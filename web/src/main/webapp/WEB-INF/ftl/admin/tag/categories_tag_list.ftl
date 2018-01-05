<#include "admin/common/header_start.ftl">
<#include "admin/common/header_end.ftl">

<#include "admin/common/body_start.ftl">

	<!-- top tiles -->
	<div class="row tile_count">
		<div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
			<span class="count_top"><i class="fa fa-user"></i> Total Users</span>
			<div class="count">2500</div>
			<span class="count_bottom"><i class="green">4% </i> From last Week</span>
        </div>
        <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
			<span class="count_top"><i class="fa fa-user"></i> Total Males</span>
			<div class="count green">2,500</div>
			<span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>34% </i> From last Week</span>
        </div>
	</div>
	<!-- top tiles -->

	<#-- row start -->
	<div class="row">
	
		<#-- col start -->
        <div class="col-md-4 col-sm-4 col-xs-12">
			<div class="x_panel tile fixed_height_320">
	            <div class="x_title">
	            		个第三方
	            		<#include "admin/common/comp/panel_for_nav.ftl">
	            </div>
	            
	            <#-- x_content start -->
	            <div class="x_content">
	               edsfdsf
	            </div>
	            <#-- x_content start -->
			</div>
        </div>
		<#-- col start -->
		
		<#-- col start -->
		<div class="col-md-4 col-sm-4 col-xs-12">
			<#-- x_panel start -->
			<div class="x_panel tile fixed_height_320 overflow_hidden">
				<#-- x_title start -->
				<div class="x_title">
					<h2>Device Usage</h2>
					<#include "admin/common/comp/panel_for_nav.ftl">
					<div class="clearfix"></div>
				</div>
				<#-- x_title end -->
				
				<#-- x_content start -->
	            <div class="x_content">
	               分润若若若
	            </div>
	            <#-- x_content start -->
			</div>
			<#-- x_panel start -->
		</div>
		<#-- col end -->

	</div>
	<#-- row end -->

	

<#include "admin/common/body_end.ftl">

</html>

<#-- 
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
        </div>
    </div>
</div>
 -->
