<#include "admin/common/header_start.ftl">
    <!-- icheck -->
    <link href="${ctx}/plug-ins/icheck/skins/flat/green.css" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.css" />
	<link type="text/css" rel="stylesheet" href="http://js-grid.com/css/jsgrid-theme.min.css" />
	
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
					<div id="jsGrid"></div>
	          </div>
			</div>
		</div>
		<!-- 列表信息结束 -->
		
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
	          </div>
			</div>
		</div>
		<!-- 列表信息结束 -->
		
    </div>
    <!-- /page content 页面内容结束  -->
	 
	<#include "admin/common/include/endjs.ftl">
	
	<script src="${ctx}/adm/js/custom_datatable.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jsgrid/1.5.3/jsgrid.min.js"></script>
	<script type="text/javascript" src="http://js-grid.com/js/i18n/jsgrid-fr.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
	
    <!-- icheck -->
    <script src="${ctx}/plug-ins/icheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="${ctx}/plug-ins/skycons/skycons.js"></script>
	
	<script>
		$(document).ready(function() {
			$("#jsGrid").jsGrid({
		        height: "auto",
		        width: "100%",
		        sorting: true,
		        paging: true,     //本地分页
      			//pageLoading: true, //服务端分页
		        //pageIndex: 1,
      			//pageSize: 20,
		        autoload: true,
		    		deleteConfirm: function(item) {
		            return "The client <b>\"" + item.articleTitle + "\" </b>will be removed. Are you sure?";
		        },
		        controller: {
		            loadData: function() {
		            		var d = $.Deferred();
		
		                $.ajax({
		                    url: ctx + "/admin/service/do_show_list.json",
		                    dataType: "json",
		                    type: "POST"
		                }).done(function(response) {
		                    d.resolve(response.list);
		                });
		
		                return d.promise();
		            },
		            deleteItem: function(item) {
		            		return $.ajax({
							url: ctx + "/admin/del.json?articleBlogId=" + item.articleBlogId + "",
							type: 'DELETE',
							success: function(rs) {
								if (rs["data"] == true) {
									$.dialog.tips("删除成功！");
				                }else {
				                		alert("删除失败：" + rs["message"]);
				                }
							}
						});
			        }
		        },
		
		        fields: [
		            { name: "articleBlogId", title: "标识号", type: "text",
		            		itemTemplate: function(value, item) {
                    			return $("<a target='_blank' href='" + ctx + "/article/" + item.articleBlogId + ".html'>").append(value).append("</a>");
                			}
                		},
		            { name: "articleTitle", title: "标题", type: "textarea", width: 150,
		            		itemTemplate: function(value, item) {
                    			return $("<div title='" + item.articleAlias + "'>").append(value).append("</div>");
                			}
                		},
		            { name: "today", title: "创建日期", type: "date",
		            		itemTemplate: function(value, item) {
                    			return item.today;
                			}
                		},
		            {
		            		type: "control",
		                	modeSwitchButton: false,
		                	editButton: true,
		                	headerTemplate: function() {
		                    return $("<a>").attr("title", "新增博文").attr("target", "_blank").attr("href", "#").text("新增博文");
		                }
		            }
		            
		        ]
		    });
		    
		});
	</script>
<#include "admin/common/body_end.ftl">

</html>