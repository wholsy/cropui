<#import "/macro.ftl" as m>

<#-- 我的分类  -->
<@m.page_header title='${title}'/>

    <h2>${title}</h2>

	<div class="row-fluid">
		<div class="span12">
			<ul class="tag_box inline">
				<#list owenerTags as owenerTag>
					<li>
						<a href="#${owenerTag.owenerTagName}-ref">
			    			${owenerTag.owenerTagName}
			    			<span class="new-label">${owenerTag.correlaArticleSum}</span>
		    			</a>
			    	</li>
			    </#list>
			</ul>
			
			<#list tagSimpleBlogs as tagSimpleBlog>
				<h2 id="${tagSimpleBlog.owenerTagName}-ref">
					${tagSimpleBlog.owenerTagName}
				</h2>
				<ul>
					<#list tagSimpleBlog.list as simpleBlog>
						<li>
							<a href="${ctx}/article/${simpleBlog.articleBlogId}.html">
								${simpleBlog.articleTitle}
							</a>
						</li>
				    </#list>
				</ul>
		    </#list>
	  	</div>
	</div>
	
<@m.page_footer date="2015"/>
