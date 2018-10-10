<#include "console/common/header_start.ftl">
	<#-- jqGrid组件基础样式包-必要 -->
	<link rel="stylesheet" href="http://static.codealy.com/plugins/jqgrid/4.6.0/css/ui.jqgrid.css" />
	<#-- jqGrid主题包-非必要 --> 
	<#-- 在jqgrid/css/css这个目录下还有其他的主题包，可以尝试更换看效果 -->
	<link rel="stylesheet" href="http://static.codealy.com/plugins/jqgrid/4.6.0/css/css/ui-lightness/jquery-ui-1.8.16.custom.css" />
	
<#include "console/common/header_end.ftl">
<#include "console/common/body_start.ftl">

		<!-- top tiles -->
		<div class="row tile_count">
	        <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
	          <span class="count_top"><i class="fa fa-user"></i> 博文总数</span>
	          <div class="count">${blogTotal}</div>
	          <#-- 
	          <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>4% </i> From last Week</span>
	           -->
	        </div>
	        <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
	          <span class="count_top"><i class="fa fa-clock-o"></i> 上月新增博文数</span>
	          <div class="count green">${incrForLastMonth}</div>
	          <#-- 
	          <span class="count_bottom">
	          	<i class="green">
	          		<i class="fa fa-sort-asc"></i>
	          		3%
	          	</i> 
	          	From last Month
	          </span>
	           -->
	        </div>
		</div>
		<!-- /top tiles -->
		
		<#-- row start -->
		<div class="row">
			<#-- 列表信息 -->
			<div class="col-md-24 col-sm-24 col-xs-24">
		        <div class="x_panel">
					<div class="x_title">
			            <h2>博文信息列表 <small>已发布博文</small></h2>
			            <#include "${ctx}/console/common/comp/panel_for_nav.ftl">
						<div class="clearfix"></div>
					</div>
		
					<div class="x_content">
						<p>
							发布的<code>博文</code> 信息列表
			          	</p>
			          	
			          	<table id="jsDataGrid"></table> 
						<div id="jsDataGrid_pager"></div>
		          </div>
				</div>
			</div>
			<#-- 列表信息结束 -->
		</div>
		<#-- row end -->
		
	<script src="${ctx}/console/javascript/pages/blog/blog_list.js"></script>
	
	<#-- jqGrid插件包-必要 -->
	<script type="text/javascript" src="http://static.codealy.com/plugins/jqgrid/4.6.0/js/jquery.jqGrid.src.js"></script>
	<#-- jqGrid插件的多语言包-非必要 -->
	<#-- 在jqgrid/js/i18n下还有其他的多语言包，可以尝试更换看效果 -->
	<script type="text/javascript" src="http://static.codealy.com/plugins/jqgrid/4.6.0/js/i18n/grid.locale-cn.js"></script>
	
    
<#include "console/common/body_end.ftl">

</html>