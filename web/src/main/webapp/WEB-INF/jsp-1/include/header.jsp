<%
/**
 *$file: header.jsp,v $
 *$vision: 1.1 $
 *$Date: 2015/08/06 by yuanyang
 */
%>
<%  
//公共头文件
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="j/jheader-tag.jsp"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="generator" content="PEC" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    
	<!--[if lte IE 8]>
	       为了你的浏览体验，请升级您的IE或者使用其他高级浏览器。
	<![endif]-->
     
	<title>
		<c:if test="${empty title}">
			cropui 欢迎您！
		</c:if>
		<c:if test="${not empty title}">
			${title}
		</c:if>
	</title>
	
	<%@ include file="j/jheader-cj.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx}/content/css/global/style.default.css?ver=1.0.0" />
	<link type="text/css" rel="stylesheet" href="${ctx}/content/css/toolbar.css?ver=1.0.0" />
	
	<link type="text/css" rel="stylesheet" href="${ctx}/content/css/common.css?ver=1.0.0" />
	<link type="text/css" rel="stylesheet" href="${ctx}/content/css/top.css?ver=1.0.0" />
</head>

<body>
	<%-- toolbar，后期打算把 首页，当前项目logo 以及 登录和注册加进去 --%>
	<%@ include file="header-toolbar.jsp"%>
	
	<%@ include file="header-context.jsp"%>
    
	<%@ include file="power/ad.jsp"%>
	
