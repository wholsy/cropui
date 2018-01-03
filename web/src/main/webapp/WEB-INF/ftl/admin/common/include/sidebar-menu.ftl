<#--
	作者：yuany
	时间：2018-01-01
	描述：左侧菜单， 前端代码中固定
-->
<div class="col-md-3 left_col">
  <div class="left_col scroll-view">
    <div class="navbar nav_title" style="border: 0;">
      <a href="${ctx}/admin/" class="site_title">
      	<i class="fa fa-bitcoin"></i>
      	<span>Codealy Blog !</span>
      </a>
    </div>

    <div class="clearfix"></div>

    <!-- menu profile quick info -->
    <div class="profile clearfix">
      <div class="profile_pic">
        <img class="img-circle profile_img"
        	src="<#if (distUser.userBaseInfoExp)?? && (distUser.userBaseInfoExp.headTinyImageIoId)??>
	        		${distUser.userBaseInfoExp.headTinyImageIoUrl}
	        	<#else>
	            	${ctx}/adm/images/img.jpg
	            </#if>
	        	" 
	        alt="${distUser.displayName}'s Photo" 
	        title="${distUser.displayName}'s Photo">
      </div>
      <div class="profile_info">
        <span>Welcome,</span>
        <h2>${distUser.displayName}</h2>
      </div>
    </div>
    <!-- /menu profile quick info -->

    <br/>

    <!-- sidebar menu -->
    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
		<#-- 第一个大菜单 -->
		<div class="nav menu_section">
        	<h3 title="常规菜单">General</h3>
			<ul class="nav side-menu">
				<li>
					<a title="首页">
					  	<i class="fa fa-home"></i> 
					  	Home <span class="fa fa-chevron-down"></span>
					</a>
					<ul class="nav child_menu" title="首页面板">
						<li><a href="${ctx}/admin/">Dashboard</a></li>
					</ul>
				</li>
				  
				<li>
					<a title="博客">
					  	<i class="fa fa-edit"></i> 
					  	博客管理 
					  	<span class="fa fa-chevron-down"></span>
					</a>
					<ul class="nav child_menu animated zoomIn">
						<li>
							<a href="${ctx}/admin/list_blog.html" 
								data-tooltip-toggle="tooltip" 
								data-placement="right">
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
	          
				<li>
					<a>
						<#--
					  	  fa-bar-chart-o、fa-clone
					  	  -->
						<i class="fa fa-desktop"></i> 
						标签管理
						<span class="fa fa-chevron-down"></span>
					</a>
					<ul class="nav child_menu">
						<li class="disabled">
							<a href="general_elements.html">
								<span class="sidebar-nav-item">个性管理</span>
							</a>
						</li>
					</ul>
				</li>
				
				<#--
				<li>
					<a>
						<i class="fa fa-laptop"></i> 
				          Tables <span class="fa fa-chevron-down"></span>
					</a>
					<ul class="nav child_menu">
						<li><a href="tables.html">Tables</a></li>
						<li><a href="tables_dynamic.html">Table Dynamic</a></li>
					</ul>
				</li>
				-->
			</ul>
		</div>
		
		<#-- 第二个大菜单 -->
		<#--
		<div class="menu_section">
	        <h3>Live On</h3>
	        <ul class="nav side-menu">
	          <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
	            <ul class="nav child_menu">
	              <li><a href="e_commerce.html">E-commerce</a></li>
	              <li><a href="projects.html">Projects</a></li>
	              <li><a href="project_detail.html">Project Detail</a></li>
	              <li><a href="contacts.html">Contacts</a></li>
	              <li><a href="profile.html">Profile</a></li>
	            </ul>
	          </li>
	          <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
	            <ul class="nav child_menu">
	              <li><a href="page_403.html">403 Error</a></li>
	              <li><a href="page_404.html">404 Error</a></li>
	              <li><a href="page_500.html">500 Error</a></li>
	              <li><a href="plain_page.html">Plain Page</a></li>
	              <li><a href="login.html">Login Page</a></li>
	              <li><a href="pricing_tables.html">Pricing Tables</a></li>
	            </ul>
	          </li>
	          <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
	            <ul class="nav child_menu">
	                <li><a href="#level1_1">Level One</a>
	                <li><a>Level One<span class="fa fa-chevron-down"></span></a>
	                  <ul class="nav child_menu">
	                    <li class="sub_menu"><a href="level2.html">Level Two</a>
	                    </li>
	                    <li><a href="#level2_1">Level Two</a>
	                    </li>
	                    <li><a href="#level2_2">Level Two</a>
	                    </li>
	                  </ul>
	                </li>
	                <li><a href="#level1_2">Level One</a>
	                </li>
	            </ul>
	          </li>                  
	          <li>
		          <a href="javascript:void(0)">
			          <i class="fa fa-laptop"></i> 
			          Landing Page 
			          <span class="label label-success pull-right">Coming Soon</span>
		          </a>
	          </li>
	        </ul>
		</div>
		-->
    </div>
    <!-- /sidebar menu -->

    <!-- /menu footer buttons -->
    <div class="sidebar-footer hidden-small">
      <a data-toggle="tooltip" data-placement="top" title="Settings">
        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
      </a>
      <a data-toggle="tooltip" data-placement="top" title="FullScreen">
        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
      </a>
      <a data-toggle="tooltip" data-placement="top" title="Lock">
        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
      </a>
      <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
      </a>
    </div>
    <!-- /menu footer buttons -->
    
  </div>
</div>
<!--
	作者： yuany
	时间：2018-01-01
	描述：左侧菜单元素(END)
-->