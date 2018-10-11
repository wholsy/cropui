<#--
  	作者：yuany
  	时间：2018-01-01
  	描述：top navigation 顶部菜单元素
-->
<div class="top_nav">
  <div class="nav_menu">
    
    <nav>
      <div class="nav toggle">
        <a id="menu_toggle" data-toggle="tooltip" data-placement="right" title="菜单控制">
        		<i class="fa fa-bars"></i>
        	</a>
      </div>
	  
      <ul class="nav navbar-nav navbar-right">
        <li class="">
          <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
	          <#-- 头像设置 -->
              <img src="<#if (distUser.userBaseInfoExp)?? && (distUser.userBaseInfoExp.headTinyImageIoId)??>
		        			${distUser.userBaseInfoExp.headTinyImageIoUrl}
			        	<#else>
			            	${ctx}/console/images/img.jpg
			            </#if>
			        	" 
			        alt="${distUser.displayName}'s Photo" 
			        title="${distUser.displayName}'s Photo">
                </img>

	            ${distUser.displayName}
	            <span class="fa fa-angle-down"></span>
          </a>
          
          <ul class="dropdown-menu dropdown-usermenu pull-right">
            <li>
            	<a href="${ctx}/admin/settings">
                    <i class="fa fa-user pull-right"></i>
                    Profile
                </a>
            </li>
            <li>
              <a href="javascript:;">
                <span class="badge bg-red pull-right">50%</span>
                <span>Settings</span>
              </a>
            </li>
            <li>
            		<a href="javascript:;" data-toggle="tooltip" data-placement="left" title="帮助">Help</a>
            </li>
            
            <li class="divider"></li>
            <li>
	            <a id="a_logout" href="#" data-toggle="tooltip" data-placement="left" title="退出">
		            <i class="fa fa-sign-out pull-right"></i>
		            ${distUser.displayName} Logout
	            </a>
            </li>
          </ul>
        </li>
		
		<#-- 右侧邮件通知
        <li role="presentation" class="dropdown">
          <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
            <i class="fa fa-envelope-o"></i>
            <span class="badge bg-green">6</span>
          </a>
          <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
            <li>
              <a>
                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                <span>
                  <span>John Smith</span>
                  <span class="time">3 mins ago</span>
                </span>
                <span class="message">
                  Film festivals used to be do-or-die moments for movie makers. They were where...
                </span>
              </a>
            </li>
            <li>
              <a>
                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                <span>
                  <span>John Smith</span>
                  <span class="time">3 mins ago</span>
                </span>
                <span class="message">
                  Film festivals used to be do-or-die moments for movie makers. They were where...
                </span>
              </a>
            </li>
            <li>
              <a>
                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                <span>
                  <span>John Smith</span>
                  <span class="time">3 mins ago</span>
                </span>
                <span class="message">
                  Film festivals used to be do-or-die moments for movie makers. They were where...
                </span>
              </a>
            </li>
            <li>
              <a>
                <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                <span>
                  <span>John Smith</span>
                  <span class="time">3 mins ago</span>
                </span>
                <span class="message">
                  Film festivals used to be do-or-die moments for movie makers. They were where...
                </span>
              </a>
            </li>
            <li>
              <div class="text-center">
                <a>
                  <strong>See All Alerts</strong>
                  <i class="fa fa-angle-right"></i>
                </a>
              </div>
            </li>
          </ul>
        </li>
        -->
        
      </ul>
    </nav>
    
  </div>
</div>
<!--
	作者：yuany
	时间：2018-01-01
	描述：top navigation 顶部菜单元素(END)
-->
