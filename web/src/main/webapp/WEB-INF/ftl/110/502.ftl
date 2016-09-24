<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>oh~ 我勒个去！服务呢 T.T</title>

	<link rel="shortcut icon" href="http://static.yueny.site/favicon.ico"/>
    <!-- Bootstrap-->
    <link rel="stylesheet" href="http://static.yueny.site/js/bootstrap/3.3.5/css/bootstrap.min.css"/>
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://static.yueny.site/plugins/libs/html5shiv.min.js"></script>
        <script src="http://static.yueny.site/plugins/libs/respond.min.js"></script>
    <![endif]-->

    <style>
        body {
            font-family: "微软雅黑", Helvetica, Verdana, Arial, sans-serif;
        }

        .row {
            padding-top: 5%;
        }
    </style>
</head>

<body>
	<div class="container">
	    <div class="row">
	        <div class="col-md-10 col-md-offset-1">
	            <div class="well text-danger">
	                <h1>
	                	我勒个去！服务没了 T.T
	                    <small>An service error occurred.</small>
	                </h1>
	                
	                <!-- <div class="text-center"> -->
	                <div>
	                    Sorry, the page you are looking for is currently unavailable.
						Please try again later.
						
						<#if (url)?? && url!="">
		                	${url}<br/>
		                </#if>
	                    <#if (message)?? && message!="">
		                	${message}
		                </#if>
	                </div>
	
	                <div class="text-center">
	                    <br/>
	                    <a class="btn btn-info" role="button"
	                       href="javascript:history.back();"><i
	                            class="glyphicon glyphicon-chevron-left"></i> Back</a>
	                </div>
	            </div>
	        </div>
	    </div>
	
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="http://static.yueny.site/js/jquery/jquery-1.11.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="http://static.yueny.site/js/bootstrap/3.3.5/bootstrap.min.js"></script>
	

</body>
</html>