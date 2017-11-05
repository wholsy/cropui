<%
/**
 *$file: header-toolbar.jsp,v $
 *$vision: 1.1 $
 *$Date: 2015/08/06 by yuanyang
 */
%>

<%  
//toolbar
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="j/jheader-tag.jsp"%>

<%-- header:标签如知识库，我的等  --%>
	<header class="header">
		<div class="d-m-nav radius">
			<div class="d-m-nav-top layout">
				<a class="d-m-nav-logo" href="${ctx}/">首页</a>
				<ul class="d-m-main-nav">
					<li>
						<a target="_blank" href="http://www.yueny.com/lily">lily</a>
					</li>
					<li>
						<a href="${ctx}/aboutus/">关于我们</a>
					</li>
				</ul>
				<div class="d-m-search">
					<form id="search" method="post" action="${ctx}/search">
						<%-- 搜索的类型 --%>
						<input id="type" name="type" value="project" type="hidden">
						<input id="q" name="q" value="" placeholder="请输入搜索内容" class="search-text" type="text">
	              		<a id="btn-search" href="javascript:search.submit()" class="search-btn">搜索</a>
	              		<b>
	              		</b>
	              		
	              		<%--
	              		<div style="display:none">
	              			<input name="csrfmiddlewaretoken" value="165775cec35a037ea61d36f78daa7ee4" type="hidden">
	              		</div>
	              		 --%>
	              		
	              		<c:choose>
						    <c:when test="${not empty isLogin && isLogin eq true}">
						        <a href="${ctx}/user/center/" onclick="return true;" title="用户中心">${displayName}</a>
						        <span class="muted">·</span>
						        <a href="${ctx}/loginOut/">退出</a>
						    </c:when>
						    <c:otherwise>
						    	<c:if test="${(empty LOGIN) || (not empty LOGIN) && LOGIN}">
									<a class="tologin_link"  href="${ctx}/login/">登录</a>
									<div style="display:none;" class="a-login-box-desc">
										欢迎您的登录，请点击
									</div>
								</c:if>
								<%@ include file="../common/register_content.jsp"%>
						    </c:otherwise>
						</c:choose>
				
						<c:if test="${(not empty HELP) && HELP}">
							<a href="${ctx}/helpme/">帮助</a>
						</c:if>
		           		<a href="${ctx}/ourhistory/" target="_blank">我们的历史</a>
					</form>
				</div>
			</div>
		</div>
	</header>

	<%--
	<div>
		<img src="images/04.gif" width="23" height="20"><a href="search.jsp">搜索</a>
	    |
	    <img src="images/logout.gif" width="17" height="17" border="0"><a href="login.jsp?logout=true" title="退出">退出</a>
	</div>
    --%>
	
	
<script type="text/javascript">
	$(function(){
		//tip
		$('.tologin_link').soOverTip({
			type:'target',
			target:'.a-login-box-desc',
			width:250,
			offset:[-20,30]
		});
	});
</script>