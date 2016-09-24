<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/j/jheader-tag.jsp"%>
<%-- 我们的历史-日度报告 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="generator" content="PEC" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />

	<title>${title}</title>
	
	<%@ include file="../include/j/jheader-cj.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/global/style.default.css?ver=1.0.0" />
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/top.css?ver=1.0.0" />
	
	<link type="text/css" rel="stylesheet" href="${ctx}/static/css/timeline/timeline-style.css" />
</head>

<div>
<strong class="title_change_link" title="切换为月度报告">
	<a href="${ctx}/ourhistory/">月度报告</a>
</strong>
<br>
</div>
<div class="content radius">
	<div class="wrapper">
		<%-- 灯光 --%>
		<div class="light">
			<i></i>
		</div>
		
	    <hr class="line-left">
	    <hr class="line-right">
	    
	    <div class="main">
			<h1 class="title">${hisDevReports.title}</h1>
			<c:forEach items="${hisDevReports.hisYearDevReports}" var="hisYearDevReports">
				<div class="year">
					<h2><a href="#">${hisYearDevReports.year}年<i></i></a></h2>
			        <div class="list">
						<ul>
							<c:forEach items="${hisYearDevReports.hisDayDevReports}" var="hisDayDevReport">
								<%-- <li class="cls"> or  <li class="cls highlight">  --%> 
								<li class="cls highlight">
				           			<p class="date">${hisDayDevReport.reportTime}</p>
									<p class="intro">${hisDayDevReport.title}  ${hisDayDevReport.step}</p>
									<p class="version">&nbsp;</p>
									<div class="more">
										<p title="${hisDayDevReport.memo}">
											${hisDayDevReport.context}
										</p>
									</div>
								</li>
							</c:forEach>
						</ul>
			        </div>
				</div>
			</c:forEach>
		</div>
	</div>

</div>

<%-- 公共尾  --%>
<jsp:include page="../include/footer.jsp"></jsp:include>
<script>
	$(".main .year .list").each(function (e, target) {
	    var $target=  $(target),
	        $ul = $target.find("ul");
	    $target.height($ul.outerHeight()), $ul.css("position", "absolute");
	});
	
	$(".main .year>h2>a").click(function (e) {
	    e.preventDefault();
	    $(this).parents(".year").toggleClass("close");
	});
</script>

</body>
</html>
