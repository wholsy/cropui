<div id="navbar" class="navbar navbar-default  ace-save-state navbar-fixed-top" style="">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header pull-left">
            <a href="${ctx}/admin/" class="navbar-brand">
                <small>
                    <i class="fa fa-laptop"></i> Codealy Blog
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" aria-expanded="false">
                        <img class="nav-user-photo" 
                        	src="
                        	<#if (distUser.userBaseInfoExp)?? && (distUser.userBaseInfoExp.headTinyImageIoId)??>
	                    		${distUser.userBaseInfoExp.headTinyImageIoUrl}
	                    	<#else>
	                        	${ctx}/adm/assets/images/avatars/user.jpg
	                        </#if>
                        	" 
                        	alt="${distUser.displayName}'s Photo" title="${distUser.displayName}'s Photo">
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>
                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="${ctx}/admin/settings">
                                <i class="ace-icon fa fa-cog"></i>
                                Settings
                            </a>
                        </li>
                        <li>
                            <a href="${ctx}/admin/settings">
                                <i class="ace-icon fa fa-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a id="a_logout" href="#">
                                <i class="ace-icon fa fa-power-off"></i>
                               ${distUser.displayName} Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <#-- /.navbar-container -->
</div>
