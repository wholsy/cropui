<#import "../../macro.ftl" as m>

<@m.page_header title='${title}'/>

<div class="row-fluid">
	<div class="span12">
		<#list us as me>
			<h2>${me.authorName}</h2>
			<#if (me.screenName)?? && me.screenName != 	null>
	    		<p>
					花名: ${me.screenName}
				</p>
			</#if>
			
			<#if (me.authorBirthdayYear)?? && me.authorBirthdayYear!=1900>
	    		<p>生于: ${me.authorBirthdayYear}.${me.authorBirthdayMonth}</p>
			</#if>
			
			<#if (me.resumes)?? && (me.resumes?size > 0)>
	    		 <p>工作履历</p>
	    		 <ol>
	    		 	<#list me.resumes as resume>
						<li>${resume}</li>
					</#list>
				</ol>
			</#if>
			
			<#if (me.contactWays)?? && (me.contactWays?size > 0)>
				<p>联系方式</p>
				<#list me.contactWays as contactWay>
					<p>${contactWay}</p>
				</#list>
				<#--
				<p>
					<a href="http://weibo.com/xxxxx" target="_blank">
					<img border="0" src="http://service.t.sina.com.cn/xxxxx.png"/>
					</a>
				</p>
				-->
			</#if>
	    </#list>
	</div>
	
	
			    
</div>

<@m.page_footer date="2015"/>
