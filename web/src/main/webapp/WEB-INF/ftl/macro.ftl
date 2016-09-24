<#-- 
title: 页面标题 
-->
<#macro page_header title='' hideHeader=false>
	<#include "include/page-header.ftl">
	
	<#-- 页面内容content Start -->
	<div class="content">
</#macro>

<#-- 
title: 页面页脚 
-->
<#macro page_footer date='2016'>
	<#-- 页面内容content End -->
	</div>
	
	<p>
        <a href="#">回到顶部</a>
    </p>
    
	<#-- 分割线 -->
    <hr>
    
	<footer>
		<p>
			Copyright © ${date} <span title="yueny09@163.com">正值少年</span>
			|
			<a href="${ctx}" target="_blank">浙ICP备16010432</a>.
			All rights reserved.
			With help from 
			<a href="http://twitter.github.com/bootstrap/" target="_blank">Twitter Bootstrap</a>
			<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/3.0/cn/"><img alt="知识共享许可协议" style="border-width:0" src="http://i.creativecommons.org/l/by-nc-sa/3.0/cn/80x15.png" /></a>
			
			<a href="http://yibo.iyiyun.com/">
			公益
			</a>
		</p>
	</footer>
	
	</div>
	<#-- container-narrow End -->
	
	<script src="http://static.yueny.site/plugins/bootstrap/2.3.2/js/bootstrap.js"></script>
	<script src="http://static.yueny.site/plugins/prettify/prettify.js" type="text/javascript"></script>
	<script src="${ctx}/assets/js/tools.js"></script>
	</body>
</html>
</#macro>

<#--
<#import "/lib/aaa.ftl" as m>
<@m.copyright date="1999-2002"/>
${m.mail}
-->
<#macro copyright date>
	<p>Copyright (C) ${date} 云少. All rights reserved.</p>
</#macro>

<#assign mail = "yueny09@163.com">
