<!DOCTYPE html>
	<html lang="en">
	
	<head>
		<#-- 这3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <#--  Enable responsive viewport, 自适应，宽度等于当前设备的宽度（width=device-width）缩放比例是当前不缩放（initial-scale=1）。 -->
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
	    <meta name="description" content="${title}">
	    <meta name="author" content="云少">
	    
	    <title>${title}</title>
	    <link rel="shortcut icon" href="http://static.yueny.website/favicon.ico">
	    
	    <link href="http://static.yueny.website/plugins/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
	    <link href="http://static.yueny.website/plugins/prettify/prettify.css" rel="stylesheet" type="text/css"/>
		
	    <link href="${ctx}/assets/css/theme/gloal.css" rel="stylesheet" type="text/css">
		<link href="${ctx}/assets/css/theme/gloal.y.css" rel="stylesheet" type="text/css">
	    <link href="${ctx}/assets/css/style/style.css" rel="stylesheet" type="text/css">
	    
	    <!-- HTML5 Shim and Respond.js, for IE6-8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="http://static.yueny.website/plugins/libs/html5shiv.min.js"></script>
	        <script src="http://static.yueny.website/plugins/libs/respond.min.js"></script>
	    <![endif]-->
	</head>
	
	<body>
		<script src="http://static.yueny.website/plugins/jquery/v2.1.4/dist/jquery.js"></script>
	    
	    <#if !hideHeader>
			<#-- menu Start -->
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container-narrow">
						<a class="brand" href="${ctx}/">
							正值少年
							<small>
								 So do it,and change it,no regret!
							</small>
						</a>
						
						<ul class="nav">
							<li><a href="${ctx}/">主页</a></li>
					      	<li><a href="${ctx}/archive.html">存档</a></li>
					      	<li><a href="${ctx}/owenerTags.html">我的分类</a></li>
					      	<li><a href="${ctx}/tags.html">标签树</a></li>
					      	<#--
					      	<li><a href="${ctx}/fks.html">拓扑关系</a></li>
					      	-->
					      	<li><a href="${ctx}/about.html">关于我</a></li>
						</ul>
					</div>
				</div>
			</div>
		</#if>
		
    	
    	
    	<#-- container-narrow Start -->
	    <div class="container-narrow">
	    	<#if !hideHeader>
		    	<#-- menu Start
				<div class="page-header">
					<h1>正值少年 <small> Thinking all about tech & life </small></h1>
					<div class="home-nav">
					    <a href="${ctx}/" >主页</a>
					    <a href="${ctx}/archive.html" >存档</a>
					    <a href="${ctx}/owenerTags.html">我的分类</a>
					    <a href="${ctx}/tags.html">标签树</a>
					    <a href="${ctx}/about.html">关于我</a>
					</div>
				</div>
				 -->
			</#if>
			