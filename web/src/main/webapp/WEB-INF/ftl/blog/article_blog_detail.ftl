<#-- 查看文章详细 -->

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- 引入公共样式 -->
		<#include "include/comp-head-style.ftl">

		<link href="${ctx}/assets/css/tag.css" rel="stylesheet" type="text/css">
	</head>

<body>
	<!-- 引入头样式 -->
	<#include "include/comp-body-nav.ftl">

		<#-- 增加目录页面 -->

		<#-- 2、 页面内容 Start -->
		<div class="post p2 p-responsive wrap" role="main">
			<div class="measure">
				<#-- 3、 页面头 Start -->
				<div class="post-header mb2">
					<h1>${articleBlog.articleTitle}</h1>
					<span class="post-meta">
						<span>${articleBlog.createTime}</span>
					</span>
				</div><#-- 3、 页面头 End -->

				<article class="post-content">

					<div class="row-fluid post-full">
						<#-- tag -->
                        <div class="span12">
							<#-- article_meta start -->
                            <div class="article_meta">
                                <div style="margin-bottom: 5px;">
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

								<#if (owenerTags)?? && (owenerTags?size > 0)>
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
								</#if>
                            </div>
						<#-- article_meta end -->
                        </div>

						<#-- 摘要
						${articleBlog.articleDigest}
 						-->
						<#-- 正文 -->
                        <div class="content">
							${articleBlog.articleContext}
                        </div>
                        <br/>

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

				</article>
			</div>
		</div><#-- 2、 页面内容 End -->

    <p>
        <a href="#">回到顶部</a>
    </p>

	<#-- 分割线
    <hr>
     -->

	<#include "include/comp-body-footer.ftl">

</body>
</html>
