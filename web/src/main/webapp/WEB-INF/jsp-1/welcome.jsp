<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 公共头  --%>
<%@ include file="include/header.jsp"%>
<link type="text/css" rel="stylesheet" href="${ctx}/content/css/index/index.css" />

<%-- 主体 --%>
<div class="statistics">
	<div style="display:none">
		${oscillationBo.statTime}
	</div>
	<ul>
       <li>会员数：${oscillationBo.memberCount}</li>
       <li>活跃会员数：${oscillationBo.activeMemberCount}</li>
	</ul>
</div>

<section class="container">
	<div class="content-wrap">
		<div class="aside">
		</div>
		<div class="content">
			<%-- 文章 --%>
			<article class="excerpt clearfix">
				aaa
			</article>
			<article class="excerpt clearfix">
				<div class="focus">
					<a href="#">
						<img style="display: inline;" 
							src="http://www.eyusky.net/wp-content/uploads/2015/08/p2238521094.jpg" 
							data-original="aaa" 
							onerror="this.onerror=null;this.src='${ctx}/static/images/errimg.jpg'" 
							rel="lazy" alt="在家图片描述">
					</a>
				</div>
				<header>
					<a class="label label-important" href="#">
						bbb
						<i class="label-arrow"></i>
					</a>
					<h2>
						<a href="#" title="ccc">ccc</a>
					</h2>
				</header>
				
				<p>
					<span class="muted"><i class="icon-user icon12"></i> <a rel="nofollow" href="http://www.eyusky.net/author/admin">意遇天空</a></span>
					<span class="muted"><i class="icon-time icon12"></i> 15小时前</span>
					<span class="muted"><i class="icon-eye-open icon12"></i> 216浏览</span>
					<span class="muted"><i class="icon-comment icon12"></i> </span>
				</p>
				
				<section>
					<p class="note">《终结者5：创世纪》是一部美国动作 / 科幻 / 惊悚电影，豆瓣评分7.1分，IMDB评分6.9分。
					终结者：创世纪的剧情简介
					天网拥有独立意识后，对创造它的人类展开血腥屠杀。此后的岁月，约翰·康纳（杰森·克拉科 Jason Clarke 饰）率领战...
					</p>
  				</section>
			</article>
			
		</div>
		
		<!-- 表示一个页面的一部分， 它的内容跟这个页面的其它内容的关联性不强，或者是没有关联，单独存在。
		<aside class="sidebar">
		    The movie earned $87 million during its initial release.
		</aside>
		 -->
	</div>
</section>

<%-- 公共尾  --%>
<jsp:include page="include/footer.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready( function() {
		$.sobox.tip({
			content: '欢迎光临~',
			stayTime : 1000,
			showMask :false
		});
	});
	
	function onViewExPlan(cronExp){
		jQuery.getJSON("${ctx}/task/onViewCronPlan/?cronExp="+cronExp, function(result){
			alert(result.data);
    	});
	}
	
</script>

