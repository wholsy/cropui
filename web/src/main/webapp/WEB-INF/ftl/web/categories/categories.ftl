<#import "/macro.ftl" as m>

<#-- 全站分类 -->
<@m.page_header title='${title}'/>
	 <#if (lists)?? && (lists?size > 0)>
    		<#list lists as categoriesTag>
    			${categoriesTag.categoriesName}
	    </#list>
    </#if>
<@m.page_footer date="2015"/>
