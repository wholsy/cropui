//载入
$(document).ready(function() 
{
	// Here we set the altRows option globally
	jQuery.extend(jQuery.jgrid.defaults, 
		{
			// 隔行换色
			altRows:true,
			
			//一页显示多少条
            rowNum : 10,
            rowList : [ 10, 30, 50 ],
            viewrecords : true,
            multiselect: true, 
		    // 行编号
		    rownumbers : true, 
            autowidth : true,
            height: 250, 
			caption: "数据列表显示",
    		
			// 解析返回的数据格式
            jsonReader : {
                root: "list",
                page: "currentpage",
                total: "total",
                records: "records",
                repeatitems: false
            },
            
            // 选择行但不选中复选框
			beforeSelectRow: function (rowid, e) {
			    var $myGrid = $(this),
		        i = $.jgrid.getCellIndex($(e.target).closest('td')[0]),
		        cm = $myGrid.jqGrid('getGridParam', 'colModel');
		        
			    return (cm[i].name === 'cb');
			}
		}
	);
	
 	historys.init();
});

var historys = function() 
{
	// cellvalue表示当前单元格的值, options 为当前配置, rowObject为当前行数据
    function format_for_articleTitle(cellvalue, options, rowObject){
        var alink = tools.getLink(ctx + '/article/' + rowObject.articleBlogId + '.html', cellvalue, '_blank', 'true');
		return alink;
    }
	
    function format_for_categoryTagsForBlog(cellvalue, options, rowObject){
        var tagLink = '';
                 		
 		if(rowObject.categoryTagsForBlog != null && rowObject.categoryTagsForBlog.length > 0){
 			for(var i=0; i < rowObject.categoryTagsForBlog.length; i++){
 				var ctBlog = rowObject.categoryTagsForBlog[i]
 				tagLink = tools.getLink('#', ctBlog.categoryTagName, '');
				
				if(i != rowObject.categoryTagsForBlog.length-1){
					tagLink += ' | ';
				}
 			}
 		}
		
		return tagLink;
    }
	
	function format_for_isdraft(cellvalue, options, rowObject){
        if (rowObject._isdraft ==1) {
			return '<span class="label label-warning">草稿</span>'
		}
		
		return '<span class="label label-success">已发布</span>'
    }
    
    function format_for_date(cellvalue, options, rowObject){
		return cellvalue;
    }
    
    // 数据删除
    this.del = function(articleBlogId) {
        if (confirm("确定删除该文章吗?")) {
        		$.ajax({
				url: ctx + "/console/del.json?articleBlogId=" + articleBlogId + "",
				type: 'DELETE',
				success: function(rs) {
					if (rs["data"] == true) {
						$.dialog.tips("删除成功！");
	                		//window.location.reload();
	                		jQuery("#jsDataGrid").trigger("reloadGrid");
	                }else {
	                		alert(rs["message"]);
	                }
				}
			});
        }
    }
    
   /**加载数据列表*/
   this.load_processHistories_list = function()
   {
   		$("#jsDataGrid").jqGrid(
        {
            /**/
            //组件创建完成之后请求数据的url
            url: ctx + "/console/service/do_show_list.json",
            //向后台请求数据的ajax的类型。可选 post,get
            mtype : "POST",
			//请求数据返回的类型。可选json,xml,txt, local
            datatype: "json",
            //datatype: "local",
	
            colNames : [
            		'标识号', 
            		'文章标题', 
            		'全站分类',
            		'个人分类标签',
            		'时间',
            		'状态',
            		'修改',
            		'删除'
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
                 	formatter : format_for_articleTitle
                 }, 
                 {
                 	align:"center",width : 80,
                 	formatter : format_for_categoryTagsForBlog
                 },
                 {
                 	name : 'owenerTag', 
                 	index : 'owenerTag',
                 	align:"center",
                 },
                 {
                 	name : 'today', 
                 	index : 'today',
                 	width : 80, align:"center",
                 	formatter : format_for_date
                 },
                 {
                 	name : 'isdraft', width : 50, align:"center",
                 	formatter : format_for_isdraft
                 },
                 {
                 	name : 'modify', 
                 	width : 50,
                 	align:"center"
                 },
                 {
                 	name : 'delete', 
                 	width : 50,
                 	align:"center"
                 }
            ],
            
            pager : '#jsDataGrid_pager',
			
			// 双击预览
			ondblClickRow: function(rowid, iRow, iCol, e) {
		        var ret = $(this).jqGrid('getRowData', rowid);
		        
		        // url
			    var hrefData = ctx + '/article/' + ret.articleBlogId + '.html';
		        
		        window.open(hrefData, '_blank');
			},
			// //当表格所有数据都加载完成，处理统计行数据
			gridComplete: function() {
				var ids = jQuery("#jsDataGrid").jqGrid('getDataIDs');
				for(var i=0; i < ids.length;i++){
					// 行数
					var rowid = ids[i];
					var ret = $("#jsDataGrid").jqGrid('getRowData', rowid);
					
					ee = "<a target='_blank' class='fa fa-pencil' href='" + ctx + "/console/wblog.html?articleBlogId=" + ret.articleBlogId + "'>编辑</a>";
					de = "<a href='#' class='fa fa-trash' onclick='del(" + ret.articleBlogId +")'>删除</a>";
       				//re = '<a target="_blank" href="' + ctx + '/article/' + ret.articleBlogId + '.html">预览</a>';
            			
					jQuery("#jsDataGrid").jqGrid('setRowData',ids[i],{ modify: ee, delete : de});
				}	
			}
			
        });
       
       	/*创建jqGrid的操作按钮容器*/
	    /*可以控制界面上增删改查的按钮是否显示*/
	    $("#jsDataGrid").jqGrid('navGrid', '#jsDataGrid_pager', 
	    		{
	    			edit : false, 
	    			add : false, 
	    			del : false,
	    			search : false,
	    			refresh : true
	    		}
	    	);
	    	
	    	jQuery("#jsDataGrid").jqGrid('setGroupHeaders', {
			  useColSpanStyle: true, 
			  groupHeaders:[
				{startColumnName: 'act', numberOfColumns: 2, titleText: '<em>操作</em>'},
			  ]	
		});
   }


    //载入
    return {
      init: function() 
      {
        //加载数据列表
        load_processHistories_list();
        
        //$('#link_search_ok').click(search_openwindow);
        //$('#link_search_cancel').click(search_closewindow);
        
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
		        url: ctx + "/console/service/do_show_list.json",
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
      
	      
    }
  
}();

 