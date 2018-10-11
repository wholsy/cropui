<#-- body体的开始
<body>
 -->

	<#-- 1、 site-wrap Start -->
    <div class="site-wrap">
		<#if !hideHeader>
			<#-- menu Start
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-narrow">
						<a class="brand" href="${ctx}/">
							正值少年
							<small>
								So do it,and change it,no regret!
							</small>
						</a>

						<ul class="nav">
							<li><a href="${ctx}/">主页</a></li>
							<li><a href="${ctx}/archive.html">存档</a></li>
							<li><a href="${ctx}/owenerTags.html">我的分类标签</a></li>
							<li><a href="${ctx}/tags.html" onclick="return false">标签树</a></li>
							<li><a href="${ctx}/fks.html" onclick="return false">拓扑关系</a></li>
							<li><a href="${ctx}/about.html">关于我</a></li>
						</ul>
					</div>
				</div>
			</div>
 			-->

			<#-- menu Start -->
            <header class="site-header">
                <div class="mt2 wrap">
                    <div class="measure">
                        <a class="site-title" href="${ctx}/">
                            正值少年
                        </a>

                        <nav class="site-nav right">
                            <a href="${ctx}/">主页</a>
                            <a href="${ctx}/archive.html">存档</a>
                            <a href="${ctx}/owenerTags.html">我的分类标签</a>
                            <a href="${ctx}/tags.html" onclick="return false">标签树</a>
                            <a href="${ctx}/fks.html" onclick="return false">拓扑关系</a>
                            <a href="${ctx}/about.html">关于我</a>
                        </nav>
                        <div class="clearfix"></div>

                        <div class="social-icons">
                            <div class="left">
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <#--
								<a class="fa fa-linkedin" href="https://www.linkedin.com/in/nobodyiam"></a>
                                <a class="fa fa-rss" href="/feed.xml"></a>
                                -->

                                <small>
                                    :) So do it,and change it,no regret!
                                </small>

                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <#--
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                <a class="fa fa-github" target="_blank" href="https://github.com/yueny"></a>
                                -->
                            </div>
                            <div class="right">
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </header>
		</#if>