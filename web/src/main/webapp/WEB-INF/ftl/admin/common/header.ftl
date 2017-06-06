<!DOCTYPE html>
<html lang="zh">
<head>
	<#-- 这3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <#--  Enable responsive viewport, 自适应，宽度等于当前设备的宽度（width=device-width）缩放比例是当前不缩放（initial-scale=1）。 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    
    <meta name="description" content="${title}">
    <meta name="author" content="云少">
    
    <title>${title}</title>
    <link rel="shortcut icon" href="http://static.yueny.website/favicon.ico">
    
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/adm/assets/font-awesome/v4.6.3/css/font-awesome.min.css" type="text/css">
		
    <link href="http://static.yueny.website/plugins/prettify/prettify.css" rel="stylesheet" type="text/css"/>
	
	<#-- MetisMenu css -->
	<link href="${ctx}/adm/assets/js/metisMenu/v2.5.2/dist/metisMenu.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/adm/assets/css/animate/animate.css" rel="stylesheet">
    
	<!-- text fonts  -->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/fonts.googleapis.com.css">
    
    <link href="${ctx}/assets/css/theme/gloal.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/assets/css/theme/gloal.y.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/assets/css/style/style.css" rel="stylesheet" type="text/css">
    
    <!-- ace styles
	 -->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">
	 
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->
    
    <!--
	-->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-skins.min.css">
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-rtl.min.css">
	
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-ie.min.css"/>
    <![endif]-->
    
    <!-- HTML5 Shim and Respond.js, for IE6-8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://static.yueny.website/plugins/libs/html5shiv.min.js"></script>
        <script src="http://static.yueny.website/plugins/libs/respond.min.js"></script>
    <![endif]-->
    
    <script src="http://static.yueny.website/plugins/jquery/v2.1.4/dist/jquery.js"></script>
    
    <!-- inline styles related to this page -->
    <!-- ace settings handler -->
    <script src="${ctx}/adm/assets/js/ace-extra.min.js"></script>
     
    <style>@keyframes nodeInserted {
               from {
                   outline-color: #fff
               }
               to {
                   outline-color: #000
               }
           }

    @-moz-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    @-webkit-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    @-ms-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    @-o-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    .ace-save-state {
        animation-duration: 10ms;
        -o-animation-duration: 10ms;
        -ms-animation-duration: 10ms;
        -moz-animation-duration: 10ms;
        -webkit-animation-duration: 10ms;
        animation-delay: 0s;
        -o-animation-delay: 0s;
        -ms-animation-delay: 0s;
        -moz-animation-delay: 0s;
        -webkit-animation-delay: 0s;
        animation-name: nodeInserted;
        -o-animation-name: nodeInserted;
        -ms-animation-name: nodeInserted;
        -moz-animation-name: nodeInserted;
        -webkit-animation-name: nodeInserted
    }</style>
    <style>@keyframes nodeInserted {
               from {
                   outline-color: #fff
               }
               to {
                   outline-color: #000
               }
           }

    @-moz-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    @-webkit-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    @-ms-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    @-o-keyframes nodeInserted {
        from {
            outline-color: #fff
        }
        to {
            outline-color: #000
        }
    }

    .ace-save-state {
        animation-duration: 10ms;
        -o-animation-duration: 10ms;
        -ms-animation-duration: 10ms;
        -moz-animation-duration: 10ms;
        -webkit-animation-duration: 10ms;
        animation-delay: 0s;
        -o-animation-delay: 0s;
        -ms-animation-delay: 0s;
        -moz-animation-delay: 0s;
        -webkit-animation-delay: 0s;
        animation-name: nodeInserted;
        -o-animation-name: nodeInserted;
        -ms-animation-name: nodeInserted;
        -moz-animation-name: nodeInserted;
        -webkit-animation-name: nodeInserted
    }</style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if IE]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        $(function () {
        <#if (obj.sidebar_openposition)?? >
            $("${obj.sidebar_openposition}").addClass("open");
        </#if>
        <#if  (obj.sidebar_activeposition)??>
            $("${obj.sidebar_activeposition}").addClass("active");
        </#if>
        });
    </script>
</head>
