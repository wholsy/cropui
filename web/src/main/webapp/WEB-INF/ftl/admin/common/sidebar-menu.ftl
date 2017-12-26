<#-- 左侧菜单， 前端代码中固定  -->
<div id="page-sidebar" class="navbar-default sidebar" role="navigation">
    <#-- sidebar-menu start -->
    <div id="sidebar-menu" class="sidebar-nav navbar-collapse tooltip-icons">
        <ul id="side-menu" class="nav">
            
            <li>
                <a href="#" name="sys_config" 
                	data-tooltip-toggle="tooltip" data-placement="right">
                	<i class="fa fa-cogs fa-spin fa-1x fa-fw"></i> 
                	<span class="sidebar-nav-item">博客管理</span>
                	<#-- 左箭头， 存在下一级子菜单 -->
                	<span class="fa arrow fa-fw"></span>
                </a>
                <ul class="nav nav-second-level animated zoomIn">
                    <li>
                        <a href="${ctx}/admin/list_blog.html" 
                			data-tooltip-toggle="tooltip" data-placement="right">
                        	<span class="sidebar-nav-item">博客列表</span>
                        </a>
                    </li>
                    <li>
	                    <a href="${ctx}/admin/wblog.html">
							<span class="sidebar-nav-item">写博客</span>
		                </a>
                    </li>
                </ul>
            </li>
            
            <li class="disabled">
                <a href="/admin/list_tag.html">
                <i class="fa fa-tachometer"></i> 
					<span class="sidebar-nav-item">标签管理</span>
                </a>
            </li>
            
        </ul>
    </div> <#-- sidebar-menu end -->
</div> <#-- page-sidebar 左侧菜单结束 -->