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
    jQuery("#jsGrid").jqGrid(
        {
            /**/
            //组件创建完成之后请求数据的url
            url: ctx + "/admin/service/do_show_list.json",
            //向后台请求数据的ajax的类型。可选 post,get
            mtype : "post",
			//请求数据返回的类型。可选json,xml,txt, local
            dataType: "json",
            
            //dataType: "local",
	
            colNames : [ 
            		'标识号', 
            		'文章标题articleBlogId', 
            		'创建日期'
            	],
            	//jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
            colModel : [ 
                 {
                 	name : 'articleBlogId', index : 'articleBlogId'
                 }, 
                 {
                 	name : 'articleBlogId',
                 	index : 'articleBlogId'
                 	
                 }, 
                 {
                 	name : 'createTime', 
                 	index : 'createTime'
                 }
            ],
            
            //一页显示多少条
            rowNum : 10,
            rowList : [ 10, 30, 50 ],
            pager : '#jsGrid_pager',
            
            viewrecords : true,
            multiselect: true, 
            shrinkToFit : true,
		    autoScroll : true,
		    cellEdit : false,
		    multiselect : true,
		    // 行编号
		    rownumbers : true, 
            autowidth : true,
    			height : 250,
    			altclass: 'altRowsColour',
           
            jsonReader : {
                root: "list",
                page: "currentpage",
                total: "total",
                records: "records",
                repeatitems: false
            },

            gridComplete : function() { 
            		var tm = jQuery("#jsGrid").jqGrid('getGridParam','totaltime'); 
            		$.dialog.tips("Render time: "+ tm+" ms "); 
            }


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
    jQuery("#jsGrid").jqGrid('navGrid', '#jsGrid_pager', 
    		{
    			edit : false, 
    			add : true, 
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
        jQuery("#jsGrid").jqGrid('addRowData',i+1, mydata[i]);  
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
			    //    jQuery("#jsGrid").jqGrid('addRowData',i+1, rs["list"][i]);  
			   //}
			   	var thegrid = jQuery("#jsGrid")[0];
				thegrid.addJSONData(eval(rs["list"]));
            }
        }
	});
	 */
	
}
