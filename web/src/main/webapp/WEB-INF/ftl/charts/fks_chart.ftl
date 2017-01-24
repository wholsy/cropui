<#-- 报表 -->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="http://static.yueny.website/plugins/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/assets/css/chart.css" rel="stylesheet">
  </head>
    
	<body ng-app="frontEnd" ng-controller="frontEndCtrl">
	     <div id="tooltip" class="hidden">
	        <p><strong id="name"></strong></p>
	        <p><span id="desc"></span></p>
	     </div>
	     
	     <h3 class="title">
	        <a href="${ctx}/">回到首页</a>
	     </h3>
	     
	     <front-end-chart>
	     </front-end-chart>
	     
		<script src="http://static.yueny.website/plugins/jquery/v2.1.4/dist/jquery.js"></script>
		<script src="http://static.yueny.website/plugins/angular/angular.min.js"></script>
		<script src="http://static.yueny.website/plugins/marked/lib/marked.js"></script>
		<script src="http://static.yueny.website/plugins/angular-marked/dist/angular-marked.min.js"></script>
		<script src="http://static.yueny.website/plugins/d3/d3.v3.js"></script>
		
		<script src="${ctx}/assets/js/app.js"></script>
	</body>
  
</html>
