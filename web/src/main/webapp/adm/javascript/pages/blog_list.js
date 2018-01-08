$(function(){
	// Here we set the altRows option globally
	jQuery.extend(jQuery.jgrid.defaults, 
		{
			// 隔行换色
			altRows:true
		}
	);

    //页面加载完成之后执行
    pageInit();        
});

function pageInit(){
    $("#jsDataGrid").jqGrid(
        {
            /**/
            //组件创建完成之后请求数据的url
            url: ctx + "/admin/service/do_show_list.json",
            //向后台请求数据的ajax的类型。可选 post,get
            mtype : "POST",
			//请求数据返回的类型。可选json,xml,txt, local
            datatype: "json",
            //datatype: "local",
	
            colNames : [
            		'标识号', 
            		'文章标题', 
            		'全站分类',
            		'时间',
            		'状态',
            		'管理'
            	],
            	//jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
            colModel : [
                 {
                 	name : 'articleBlogId', index : 'articleBlogId',
                 	align:"center", hidden: true, editable:true
                 }, 
                 {
                 	name : 'articleTitle',
                 	index : 'articleTitle',
                 	width : 300,
                 	// cellvalue表示当前单元格的值, options 为当前配置, rowObject为当前行数据
                 	formatter : function (cellvalue, options, rowObject) {
						var alink = tools.getLink(ctx + '/article/' + rowObject.articleBlogId + '.html', cellvalue, '_blank', false);
						return alink;					
					}
                 }, 
                 {
                 	align:"center",width : 80,
                 	formatter : function (cellvalue, options, rowObject) {
                 		var tagLink = '';
                 		
                 		if(rowObject.categoryTagsForBlog != null && rowObject.categoryTagsForBlog.length > 0){
                 			for(var i=0; i < rowObject.categoryTagsForBlog.length; i++){
                 				var ctBlog = rowObject.categoryTagsForBlog[i]
                 				tagLink = '<a href="#">' + ctBlog.categoryTagName + '</a>';
									
								if(i != rowObject.categoryTagsForBlog.length-1){
									tagLink += ' | ';
								}
                 			}
                 		}
						
						return tagLink;
					}
                 },
                 {
                 	name : 'today', 
                 	index : 'today',
                 	width : 80, align:"center",
                 },
                 {
                 	width : 50, align:"center",
                 	// cellvalue表示当前单元格的值, options 为当前配置, rowObject为当前行数据
                 	formatter : function (cellvalue, options, rowObject) {
						if (rowObject._isdraft ==1) {
            					return '<span class="label label-warning">草稿</span>'
           				}
           				
           				return '<span class="label label-success">已发布</span>'
					}
                 },
                 {
                 	formatter : function (cellvalue, options, rowObject) {
                 		var tagLink = '';
                 		
                 		//<a target="_blank" href="${ctx}/admin/wblog.html?articleBlogId=s">编辑</a>
            				//<a href="javascript:del({{value.articleBlogId}});">删除</a>
            				//<a target="_blank" href="${ctx}/article/{{value.articleBlogId}}.html">预览</a>
            
            
                 		
                 		
                 		
						return tagLink;
					}
                 }
            ],
            
            //一页显示多少条
            rowNum : 10,
            rowList : [ 10, 30, 50 ],
            pager : '#jsDataGrid_pager',
            
            viewrecords : true,
            multiselect: true, 
            shrinkToFit : true,
		    autoScroll : true,
		    multiselect : true,
		    // 行编号
		    rownumbers : true, 
            autowidth : true,
    			height : 260,
           
            jsonReader : {
                root: "list",
                page: "currentpage",
                total: "total",
                records: "records",
                repeatitems: false
            },

            /*
            deleteConfirm: function(item) {
                return "The client <b>\"" + item.articleTitle + "\" </b>will be removed. Are you sure?";
            },
            controller: {
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
            */
        });

    /*创建jqGrid的操作按钮容器*/
    /*可以控制界面上增删改查的按钮是否显示*/
    $("#jsDataGrid").jqGrid('navGrid', '#jsDataGrid_pager', 
    		{
    			edit : false, 
    			add : false, 
    			del : true,
    			search : false,
    			refresh : true
    		}
    	);
    	
    	/* // local 本地获取数据方式
    	var mydata = [  
        {"articleBlogId":"100075785763", "articleTitle":"在CentOS 7上配置MySQL", "today":"2016/09/05"}  
    ];
    
    for(var i=0;i<=mydata.length;i++){  
        $("#jsDataGrid").jqGrid('addRowData',i+1, mydata[i]);  
    }
    */
    
    /*
    $.ajax({
        url: ctx + "/admin/service/do_show_list.json",
        dataType: "json",
        type: "POST",
        success: function(rs) {
            if (rs["success"] == true) {
            		console.log("get data success!");
            		
                //for(var i=0;i<=rs["list"].length;i++){  
			    //    jQuery("#jsDataGrid").jqGrid('addRowData',i+1, rs["list"][i]);  
			   //}
			   	var thegrid = jQuery("#jsDataGrid")[0];
				thegrid.addJSONData(eval(rs["list"]));
            }
        }
	});
	 */
	
}
