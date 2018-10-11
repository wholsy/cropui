<#-- 查看文章详细 -->

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- 引入公共样式 -->
<#include "include/comp-head-style.ftl">

    <link href="${ctx}/web/css/tag.css" rel="stylesheet" type="text/css">
</head>

<body>
	<!-- 引入头样式 -->
	<#include "include/comp-body-nav.ftl">

	<#-- 页面内容content Start -->
    <div class="post p2 p-responsive wrap" role="main">

		<div class="measure">
            <h2>${title}</h2>

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
    </div>

	<#include "include/comp-body-footer.ftl">

</body>
</html>
