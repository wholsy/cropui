<#-- 报表 -->
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <link href="https://static.codealy.com/plugins/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
	<link href="${ctx}/web/css/chart.css" rel="stylesheet">
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
	     
		<script src="https://static.codealy.com/plugins/jquery/v2.1.4/dist/jquery.js"></script>
		<script src="https://static.codealy.com/plugins/angular/angular.min.js"></script>
		<script src="https://static.codealy.com/plugins/marked/lib/marked.js"></script>
		<script src="https://static.codealy.com/plugins/angular-marked/dist/angular-marked.min.js"></script>
		<script src="https://static.codealy.com/plugins/d3/d3.v3.js"></script>
		
	</body>
  
</html>
