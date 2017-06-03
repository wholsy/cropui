<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta charset="utf-8">
    <title>Blog 后台管理</title>
    
    <meta name="description" content="blog">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link rel="icon" href="http://static.yueny.website/favicon.ico" type="image/x-icon">
    
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/adm/assets/font-awesome/v4.6.3/css/font-awesome.min.css" type="text/css">
		
    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/fonts.googleapis.com.css">

    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style">

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-skins.min.css">
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-rtl.min.css">

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="${ctx}/adm/assets/css/ace-ie.min.css"/>
    <![endif]-->

	<#-- MetisMenu css -->
	<link href="${ctx}/adm/assets/js/metisMenu/v2.5.2/dist/metisMenu.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/adm/assets/css/animate/animate.css" rel="stylesheet">
    
    <script src="${ctx}/adm/assets/js/jquery-2.1.4.min.js"></script>
    
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