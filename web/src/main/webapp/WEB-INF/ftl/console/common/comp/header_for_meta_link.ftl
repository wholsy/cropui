	<#--
      	作者：yuany
      	时间：2018-01-01
      	描述：include header meta and common link
    -->
      
	<#-- 这3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <#--  Enable responsive viewport, 自适应，宽度等于当前设备的宽度（width=device-width）缩放比例是当前不缩放（initial-scale=1）。 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    
    <meta name="description" content="${title}">
	<meta name="keywords"  content="${title}" />
    <meta name="author" content="云少">
    
    <meta name="renderer" content="webkit">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name='apple-touch-fullscreen' content='yes'>
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<meta name="format-detection" content="address=no">
		
    <title>${title}</title>
    <link rel="shortcut icon" href="https://static.codealy.com/favicon.ico" type="image/x-icon">
    
    <!-- Bootstrap -->
    <link href="https://static.codealy.com/plugins/bootstrap/3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="https://static.codealy.com/plugins/Font-Awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="https://static.codealy.com/plugins/nprogress/nprogress.css" rel="stylesheet">
    
    <#-- Parsley validator -->
    <link href="https://static.codealy.com/plugins/parsleyjs/src/parsley.css" rel="stylesheet">
    
    <!-- Animate.css -->
    <link href="https://static.codealy.com/plugins/animate.css/animate.min.css" rel="stylesheet">

    <#--<link href="https://static.codealy.com/plugins/prettify/prettify.css" rel="stylesheet" type="text/css"/>-->
    <#--or-->
    <#--<link href="https://static.codealy.com/plugins/markdown/editor.md/css/editormd.preview.css" rel="stylesheet">-->

    <!-- Custom Theme Style -->
    <link href="${ctx}/console/style/custom.css" rel="stylesheet">
    <link href="${ctx}/console/style/style.css" rel="stylesheet" type="text/css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if IE]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    