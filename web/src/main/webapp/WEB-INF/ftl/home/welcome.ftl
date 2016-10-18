<#import "/macro.ftl" as m>

<@m.page_header title='${title}'/>

<p>
:)
</p>

<ul class="posts">
	<#list articleBlogs as articleBlog>
		<li>
    		<a href="${ctx}/article/${articleBlog.articleBlogId}.html">${articleBlog.articleTitle}</a>
			<span>${articleBlog.createTime}</span>
    	</li>
	</#list>
</ul>

<script>
	$().ready(function() {
				
	});
</script>
		
<@m.page_footer/>

