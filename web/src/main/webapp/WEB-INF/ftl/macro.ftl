<#-- 
title: 页面标题 。 作废
-->
<#macro page_header title='' hideHeader=false>
	<#include "include/page-header.ftl">
</#macro>

<#-- 
title: 页面页脚  。 作废
-->
<#macro page_footer date='2016'>
	<#include "include/comp-body-footer.ftl">

	</body>
</html>
</#macro>

<#--
<#import "/lib/aaa.ftl" as m>
<@m.copyright date="1999-2002"/>  。 作废
${m.mail}
-->
<#macro copyright date>
	<p>Copyright (C) ${date} 云少. All rights reserved.</p>
</#macro>

<#assign mail = "yueny09@163.com">
