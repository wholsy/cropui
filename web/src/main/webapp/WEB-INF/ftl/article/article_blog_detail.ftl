<#import "/macro.ftl" as m>

<#-- 查看文章详细 -->
<@m.page_header title='${title}'/>
	<link href="${ctx}/assets/css/tag.css" rel="stylesheet" type="text/css">
	
	<div class="post-header">
		<h2>${articleBlog.articleTitle}</h2>
	</div>

	<div class="row-fluid post-full">
		<#-- tag -->
		<div class="span12">
			<#-- article_meta start -->
			<div class="article_meta">
	            <div style="margin-bottom: 5px;">
		            <span class="timestamp">
		            	时间
			      		<span>${articleBlog.createTime}</span>
		            </span>
					 | 
		            <span class="from" title="全站分类">
		                <i class="icon-globe"></i>
		                <#if (categoriesTagList)?? && (categoriesTagList?size > 0)>
		                	<#list categoriesTagList as categoriesTag>
		                		<#-- <a href="${ctx}/categories.html"> -->
				    			<a href="#">
					    			${categoriesTag.categoriesName}
				    			</a>
						    </#list>
		                </#if>
		            </span>
					 | 
		            <span class="link_view" title="阅读次数">
		            	${articleBlog.readTimes}次阅读
		            </span>
	            </div>
	            
	            <#-- 原文信息 -->
	            <div class="source">
	            	<#-- 原文/转载 -->
	            	<i style="float:left;">${articleBlog.selTypeCode.desc}</i>&nbsp;
	                
	                <#if (articleBlog)??>
						<#if articleBlog.selTypeCode.value == '1'>
		                	<a class="cut cut70" style="display:inline-block;"
		                		href="${ctx}/article/${articleBlog.articleBlogId}.html">
			                	原文链接
			                </a>
		                <#else>
							<#--- 转载 and 翻译
							<a class="cut cut70" style="display:inline-block;"
								href="http://www.xxx.com">
			                	原文链接
							</a>
							 -->
							<a class="cut cut70" style="display:inline-block;"
								href="#">
			                	<#-- 原文链接 -->
			                	功能完善中... 
							</a>
						</#if>
					</#if>
	            </div>
	            
	            <div>
		            <div class="tag_box inline">
		            	标签:
		            	<#list owenerTags as owenerTag>
			    			<a href="${ctx}/owenerTags.html#${owenerTag.owenerTagName}-ref">
				    			${owenerTag.owenerTagName}
				    			<span class="new-label">${owenerTag.correlaArticleSum}</span>
			    			</a>
					    </#list>
			    	</div>
	            </div>
	        </div>
			<#-- article_meta end -->
		</div>
		 
		<#-- 正文 -->
	    <div class="content">
			${articleBlog.articleContext}
	    </div>
	    
	    <#-- 上/下一篇博文 -->
	    <div class="article_meta"></div>
	    <div style="width: 100%;">
		    <#if (previousSimpleBlog)??>
				<div style="float: left;">
					上一篇: 
					<a href="${ctx}/article/${previousSimpleBlog.articleBlogId}.html"
						title="${previousSimpleBlog.articleTitle}">
		    			${previousSimpleBlog.articleSchemaTitle}
					</a>
				</div>
			</#if>
		    <#if (nextSimpleBlog)??>
		    	<div style="float: right;">
					下一篇: 
					<a href="${ctx}/article/${nextSimpleBlog.articleBlogId}.html"
						title="${nextSimpleBlog.articleTitle}">
		    			${nextSimpleBlog.articleSchemaTitle}
					</a>
				</div>
			</#if>
		</div>
	</div>
	
<@m.page_footer date="2015"/>
