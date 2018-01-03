<#include "admin/common/header_start.ftl">
    <!-- icheck -->
    <link href="${ctx}/plug-ins/icheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="${ctx}/plug-ins/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/plug-ins/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/plug-ins/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/plug-ins/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/plug-ins/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

<#include "admin/common/header_end.ftl">

<#include "admin/common/body_start.ftl">
	<!-- page content 页面内容  -->
    <div class="right_col" role="main">
		<!-- top tiles -->
		<div class="row tile_count">
	        <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
	          <span class="count_top"><i class="fa fa-user"></i> 博文总数</span>
	          <div class="count">2500</div>
	          <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>4% </i> From last Week</span>
	        </div>
	        <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
	          <span class="count_top"><i class="fa fa-clock-o"></i> 上月新增博文数</span>
	          <div class="count green">123.50</div>
	          <span class="count_bottom"><i class="green"><i class="fa fa-sort-asc"></i>3% </i> From last Week</span>
	        </div>
		</div>
		<!-- /top tiles -->
				
		<!-- 列表信息 -->
		<div class="col-md-12 col-sm-12 col-xs-12">
	        <div class="x_panel">
				<div class="x_title">
		            <h2>博文信息列表 <small>已发布博文</small></h2>
		            <!-- 页面按钮 -->
		            <ul class="nav navbar-right panel_toolbox">
		              <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
		              </li>
		              <li><a class="close-link"><i class="fa fa-close"></i></a>
		              </li>
		            </ul>
		            <div class="clearfix"></div>
				</div>
	
				<div class="x_content">
					<p>
						发布的<code>博文</code> 信息列表
						<span class="right">
							<a class="collapse-add">
								<i class="fa fa-plus-square"></i>
							</a>
						</span>
		          	</p>
		          	
		          	<#-- table-responsive start -->
					<div class="table-responsive">
			          	<form class="form-inline">
	                        <div class="form-group">
	                            <input type="text" class="form-control input-sm" id="txt_q" placeholder="标题">
	                        </div>
	                        <button id="btn_q" type="button" class="btn btn-info btn-xs">
	                            	搜索
	                        </button>
	                    </form>
						<table id="datatable-keytable-checkbox" class="table table-striped table-bordered">
			                <thead>
								<tr class="headings">
									<th>
									  <input type="checkbox" id="check-all" class="flat">
									</th>
									<th class="column-title">标识号 </th>
									<th class="column-title">标题</th>
									<th class="column-title">全站分类 </th>
									<th class="column-title">时间 </th>
									<th class="column-title">状态 </th>
									<th class="column-title no-link last"><span class="nobr">管理</span>
									</th>
									<th class="bulk-actions" colspan="7">
										<a class="antoo" style="color:#fff; font-weight:500;">多选 ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
									</th>
								</tr>
			                </thead>
							
							<tbody id="list_tbody">
                            </tbody>
                            <#--
			                <tbody >
			                  <tr class="even pointer">
			                  	<#list blogs as blog>
			                  		<td class="a-center ">
				                    	<input type="checkbox" class="flat" name="table_records">
				                    </td>
				                    <td>${blog.articleBlogId}</td>
				                    <td>${blog.articleTitle}</td>
				                    <td>
				                    	<#if (blog.categoryTagsForBlog)??>
								    		<#list blog.categoryTagsForBlog as categoryTagForBlog>
					                    		<a href="{{categoryTagForBlog.categoryTagCode}}">
													{{categoryTagForBlog.categoryTagName}}
												</a>
					                    	</#list>
					                    	<i class="success fa fa-long-arrow-up"></i>
										</#if>
					                </td>
				                    <td>${blog.today}</td>
				                    <td class="a-right a-right ">
				                    	<#if blog._isdraft==1>
								    		草稿 
										<#else>
											已发布
										</#if>
				                    </td>
				                    <td class=" last">
				                    	<a target="_blank" href="${ctx}/admin/wblog.html?articleBlogId=${blog.articleBlogId}">编辑</a>
							            <a href="javascript:del(${blog.articleBlogId});">删除</a>
							            <a target="_blank" href="${ctx}/article/${blog.articleBlogId}.html">预览</a>
				                    </td>
							    </#list>
			                  </tr>
			                  
			                  <tr class="even pointer">
			                    <td class="a-center ">
			                      <input type="checkbox" class="flat" name="table_records">
			                    </td>
			                    <td class=" ">121000040</td>
			                    <td class=" ">May 24, 2014 11:47:56 PM </td>
			                    <td class=" ">121000210</td>
			                    <td class=" ">John Blank L</td>
			                    <td class=" ">Paid</td>
			                    <td class="a-right a-right ">$7.45</td>
			                    <td class=" last"><a href="#">View</a>
			                    </td>
			                  </tr>
			                  <tr class="odd pointer">
			                    <td class="a-center ">
			                      <input type="checkbox" class="flat" name="table_records">
			                    </td>
			                    <td class=" ">121000039</td>
			                    <td class=" ">May 26, 2014 11:30:12 PM</td>
			                    <td class=" ">121000208 <i class="error fa fa-long-arrow-down"></i>
			                    </td>
			                    <td class=" ">John Blank L</td>
			                    <td class=" ">Paid</td>
			                    <td class="a-right a-right ">$741.20</td>
			                    <td class=" last"><a href="#">View</a>
			                    </td>
			                  </tr>
			                  <tr class="even pointer">
			                    <td class="a-center ">
			                      <input type="checkbox" class="flat" name="table_records">
			                    </td>
			                    <td class=" ">121000038</td>
			                    <td class=" ">May 26, 2014 10:55:33 PM</td>
			                    <td class=" ">121000203</td>
			                    <td class=" ">Mike Smith</td>
			                    <td class=" ">Paid</td>
			                    <td class="a-right a-right ">$432.26</td>
			                    <td class=" last"><a href="#">View</a>
			                    </td>
			                  </tr>
			                  <tr class="odd pointer">
			                    <td class="a-center ">
			                      <input type="checkbox" class="flat" name="table_records">
			                    </td>
			                    <td class=" ">121000037</td>
			                    <td class=" ">May 26, 2014 10:52:44 PM</td>
			                    <td class=" ">121000204</td>
			                    <td class=" ">Mike Smith</td>
			                    <td class=" ">Paid</td>
			                    <td class="a-right a-right ">$333.21</td>
			                    <td class=" last"><a href="#">View</a>
			                    </td>
			                  </tr>
			                </tbody>
			                -->
			                
			                <tfoot>
					            <tr class="headings">
									<th>
									  <input type="checkbox" id="check-all" class="flat">
									</th>
									<th class="column-title">标识号 </th>
									<th class="column-title">标题</th>
									<th class="column-title">全站分类 </th>
									<th class="column-title">时间 </th>
									<th class="column-title">状态 </th>
									<th class="column-title no-link last"><span class="nobr">管理</span>
									</th>
									<th class="bulk-actions" colspan="7">
										<a class="antoo" style="color:#fff; font-weight:500;">多选 ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
									</th>
								</tr>
					        </tfoot>
		              </table>
	            </div>
	            <#-- table-responsive end -->
	          </div>
	          
	          <!-- 分页 -->
	          
			</div>
		</div>
		<!-- 列表信息结束 -->
				
    </div>
    <!-- /page content 页面内容结束  -->
	
	<#include "admin/common/include/endjs.ftl">
	<script src="${ctx}/adm/js/custom_datatable.js"></script>
	
    <!-- icheck -->
    <script src="${ctx}/plug-ins/icheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="${ctx}/plug-ins/skycons/skycons.js"></script>
	<!-- Datatables -->
    <script src="${ctx}/plug-ins//datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-buttons/js/dataTables.buttons.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-buttons/js/buttons.flash.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-buttons/js/buttons.html5.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-buttons/js/buttons.print.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-responsive/js/dataTables.responsive.min.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
    <script src="${ctx}/plug-ins//datatables.net-scroller/js/dataTables.scroller.min.js"></script>
    
    
	<script id="template_list" type="text/html">
	    {{each list as value i}}
	    <tr>
	        <td>{{value.articleBlogId}}</td>
	        <td>{{value.articleTitle}}</td>
	        <td>
				{{each value.categoryTagsForBlog as categoryTagForBlog j}}
						<a href="{{categoryTagForBlog.categoryTagCode}}">
							{{categoryTagForBlog.categoryTagName}}
						</a>
				{{/each}}
	        </td>
	        <td>{{value.today}}</td>
	        <td>
	        	{{if value._isdraft ==1 }} 
	        		草稿 
	        	{{else}} 
	        		已发布 
	        	{{/if}}
	        </td>
	        <td>
	            <a target="_blank" href="${ctx}/admin/wblog.html?articleBlogId={{value.articleBlogId}}">编辑</a>
	            <a href="javascript:del({{value.articleBlogId}});">删除</a>
	            <a target="_blank" href="${ctx}/article/{{value.articleBlogId}}.html">预览</a>
	        </td>
	    </tr>
	    {{/each}}
	</script>
	
	
	<script>
	    function del(articleBlogId) {
	        if (confirm("确定删除吗?")) {
	        	$.ajax({
					url: "${ctx}/admin/del.json?articleBlogId=" + articleBlogId + "",
					type: 'DELETE',
					success: function(rs) {
						if (rs["data"] == true) {
							$.dialog.tips("删除成功！");
		                	window.location.reload();
		                }else {
		                	alert(rs["message"]);
		                }
					}
				});
	        }
	    }
	
	    $(function () {
	        page(1);
	        $("#btn_q").bind("click", function () {
	            page(1);
	        });
	    });
	
	    function page(pageno) {
	        var txt_q = $("#txt_q").val();
	        
	        $.post("${ctx}/admin/do_show_list.json?pageno=" + pageno + "&t=" + new Date().getTime() + "", 
	        	{"title_q": txt_q}, 
	        	function (rs) {
		            var html = template('template_list', rs);
		            $("#list_tbody").html(html);
		            $("#pager").html(rs["pages"]);
	        	}
	        );
	        
	    }
	</script>
	
	<#include "admin/common/body_end.ftl">

</html>
		
	<!--
	<div class="main-container ace-save-state" id="main-container">
	<#include "admin/common/sidebar-menu.ftl">
	
	    <div class="main-content">
	        <div class="main-content-inner">
	            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
	                <ul class="breadcrumb">
	                    <li>
	                        <i class="ace-icon fa fa-home home-icon"></i>
	                        <a href="${ctx}/admin/">Home</a>
	                    </li>
	                    <li>
	                        <a href="#">博客管理</a>
	                    </li>
	                    <li class="active">博客列表</li>
	                </ul>
	            </div>
	            <div class="page-content">
	
	                <div class="row">
	                    <div class="col-md-12">
	                        <form class="form-inline">
	                            <div class="form-group">
	                                <input type="text" class="form-control input-sm" id="txt_q" placeholder="标题">
	                            </div>
	                            <button id="btn_q" type="button" class="btn btn-info btn-xs">
	                                	搜索
	                            </button>
	                        </form>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="col-md-12">
	                        <table class="table  table-bordered table-hover">
	                            <thead>
	                            <tr>
	                                <th>标识号</th>
	                                <th>标题</th>
									<th>全站分类</th>
	                                <th>时间</th>
	                                <th>状态</th>
	                                <th>管理</th>
	                            </tr>
	                            </thead>
	                            <tbody id="list_tbody">
	                            </tbody>
	                        </table>
	                        <div class="row">
	                            <div class="col-md-12">
	                                <ul id="pager" class="pagination">
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	
	            </div>
	        </div>
	    </div>
	</div>
	<#include  "admin/common/endjs.ftl">
	-->

</html>