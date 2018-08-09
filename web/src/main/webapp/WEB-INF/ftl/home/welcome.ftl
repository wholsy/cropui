<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入公共样式 -->
		<#include "include/comp-head-style.ftl">

        <link href="${ctx}/assets/css/style/index.css" rel="stylesheet" type="text/css">
	</head>

    <body>
		<!-- 引入头样式 -->
		<#include "include/comp-body-nav.ftl">

		<#-- 增加目录页面 -->

		<#-- 2、 页面内容 post Start -->
        <div class="post p2 p-responsive wrap" role="main">
            <div class="measure">
                <div class="home"><#-- home Start -->
					<#-- 文章列表体  -->
                    <div class="posts">

						<#list articleBlogs as articleBlog>
							<div class="post">
								<a href="${ctx}/article/${articleBlog.articleBlogId}.html" class="post-link">
                                    <p class="post-meta">${articleBlog.createTime?date}</p>
									<h3 class="h2 post-title">${articleBlog.articleTitle}</h3>
									<p class="post-summary">
									${articleBlog.articleDigest}
									</p>
								</a>
							</div>
						</#list>
                    </div>
                </div><#-- home End -->
            </div>
        </div><#-- 2、 页面内容content End -->

		<#-- 2、 页面内容 content Start
        <div class="content">
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
        </div> --><#-- 2、 页面内容content End -->

        <p>
            <a href="#">回到顶部</a>
        </p>

		<#-- 分割线
		<hr>
 		-->

		<#include "include/comp-body-footer.ftl">

    </body>
</html>
