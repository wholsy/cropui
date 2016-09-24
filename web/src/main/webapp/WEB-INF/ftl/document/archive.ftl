<#import "/macro.ftl" as m>

<#-- 存档  -->
<@m.page_header title='${title}'/>

	<h2>${title}</h2>
	<div class="row-fluid">
		<#list archiveList as archive>
			<#-- year -->
			<div class="span12">
	 			<h2>${archive.year}</h2>
				
				<#-- month
				<h3>June</h3>
				 -->
				<ul>
					<#list archive.list as simpleBlog>
						<li>
							<span>${simpleBlog.today}</span>&raquo; 
							
							<a href="${ctx}/article/${simpleBlog.articleBlogId}.html">
								${simpleBlog.articleTitle}
							</a>
						</li>
				    </#list>
				</ul>
			</div>
	    </#list>
	</div>
      
	
<@m.page_footer date="2015"/>
