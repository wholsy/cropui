$(document).ready(function() {
            $("#jsGrid").jsGrid({
                height: "auto",
                width: "100%",
                sorting: true,

                paging: true,     //本地分页
                //pageLoading: true, //服务端分页

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
