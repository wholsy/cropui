<#include "common/header.ftl">
<#--上传组件-->
<link href="/adm/assets/js/jquery-file-upload/jquery.fileupload.css" rel="stylesheet">
<script src="/adm/assets/js/jquery-file-upload/jquery.ui.widget.js"></script>
<script src="/adm/assets/js/jquery-file-upload/jquery.iframe-transport.js"></script>
<script src="/adm/assets/js/jquery-file-upload/jquery.fileupload.js"></script>
<script src="/adm/assets/js/jquery-file-upload/upload-base.js"></script>
<link rel="stylesheet" href="/adm/assets/css/bootstrap-datepicker3.min.css"/>

<#--markdown-->
<link rel="stylesheet" href="/adm/assets/js/editor.md/css/editormd.css"/>
<script src="/adm/assets/js/editor.md/editormd.min.js"></script>


<body class="no-skin">
<#include "common/navbar.ftl">

<div class="main-container ace-save-state" id="main-container">
<#include "common/sidebar.ftl">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/adm/index">Home</a>
                    </li>
                    <li>
                        <a href="#">站点设置</a>
                    </li>
                <#--<li class="active">博客列表</li>-->
                </ul>
            </div>
            <div class="page-content">
                <div class="page-header">
                    <h1>站点设置</h1>
                </div>
                <div class="row">
                    <div class="col-md-12 form-horizontal">


                    <#--FTP信息 ========================-->
                        <h4 class="header blue bolder smaller">FTP信息</h4>

                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">FTP IP</label>

                            <div class="col-sm-11">
                                <input type="text" value="${(obj.ftp_ip)!""}" id="ftp_ip">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">FTP 用户名</label>

                            <div class="col-sm-11">
                                <input type="text" value="${(obj.ftp_user)!""}" id="ftp_user">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">FTP 密码</label>

                            <div class="col-sm-11">
                                <input type="text" value="${(obj.ftp_pwd)!""}" id="ftp_pwd">
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-sm-offset-1">
                                <button class="btn btn-info" type="button" id="btn_save_ftp">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    保存
                                </button>
                            </div>
                        </div>


                    <#--站长信息 ========================-->
                        <h4 class="header blue bolder smaller">站长信息</h4>

                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">大名</label>

                            <div class="col-sm-11">
                                <input type="text" value="${(obj.admin)!''}" id="admin">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">上传头像</label>

                            <div class="col-sm-11">
                                <div id="fileupload_photo">
                        <span class="btn btn-success btn-sm fileinput-button">
                            <i class="glyphicon glyphicon-plus"></i>
                            <span>上传</span>
                            <!-- The file input field used as target for the file upload widget -->
                            <input class="btn btn-primary" id="fileupload" type="file" name="f" multiple="">
                        </span>
                                    <button id="btn_selectimg_photo" type="button" class="btn btn-sm  btn-info">选择图片
                                    </button>
                                    <br>

                                    <div id="progress" class="progress" style="display: none;margin: 2px auto">
                                        <div class="progress-bar progress-bar-success"></div>
                                    </div>
                                    <div class="showimg" id="imgid_photo" imgid="${(obj.admin_photo)!''}"
                                         style="margin: 2px auto">
                                        <a target="_blank"
                                           href="/images/${(obj.admin_photo)!''}/">${(obj.admin_photo)!''}</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">Email</label>

                            <div class="col-sm-11">
                                <input type="text" class="form-control" value="${(obj.admin_email)!''}"
                                       id="admin_email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">Github</label>

                            <div class="col-sm-11">
                                <input type="text" class="form-control" value="${(obj.admin_github)!''}"
                                       id="admin_github">
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-sm-offset-1">
                                <button class="btn btn-info" type="button" id="btn_save_admin">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    保存
                                </button>
                            </div>
                        </div>


                    <#--站点属性=====================================-->
                        <h4 class="header blue bolder smaller">站点属性</h4>
                    <#--site_name-->
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">博客名称</label>

                            <div class="col-sm-11">
                                <input type="text" value="${(obj.site_name)!''}" id="site_name">
                            </div>
                        </div>

                    <#--选择,上传logo   site_logo-->
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">上传Logo</label>

                            <div class="col-sm-11">
                                <div id="fileupload_logo">
                        <span class="btn btn-success btn-sm fileinput-button">
                            <i class="glyphicon glyphicon-plus"></i>
                            <span>上传</span>
                            <!-- The file input field used as target for the file upload widget -->
                            <input class="btn btn-primary" id="fileupload" type="file" name="f" multiple="">
                        </span>
                                    <button id="btn_selectimg_logo" type="button" class="btn btn-sm  btn-info">选择图片
                                    </button>
                                    <br>

                                    <div id="progress" class="progress" style="display: none;margin: 2px auto">
                                        <div class="progress-bar progress-bar-success"></div>
                                    </div>
                                    <div class="showimg" id="imgid_logo" imgid="${(obj.site_logo)!''}"
                                         style="margin: 2px auto">
                                        <a target="_blank"
                                           href="/images/${(obj.site_logo)!''}/">${(obj.site_logo)!''}</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    <#--site_fav-->
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">上传fav.ico</label>

                            <div class="col-sm-11">
                                <div id="fileupload_fav">
                        <span class="btn btn-success btn-sm fileinput-button">
                            <i class="glyphicon glyphicon-plus"></i>
                            <span>上传</span>
                            <!-- The file input field used as target for the file upload widget -->
                            <input class="btn btn-primary" id="fileupload" type="file" name="f" multiple="">
                        </span>
                                    <button id="btn_selectimg_fav" type="button" class="btn btn-sm  btn-info">选择图片
                                    </button>
                                    <span>jpg或png格式</span>
                                    <br>

                                    <div id="progress" class="progress" style="display: none;margin: 2px auto">
                                        <div class="progress-bar progress-bar-success"></div>
                                    </div>
                                    <div class="showimg" id="imgid_fav" imgid="${(obj.site_fav)!""}"
                                         style="margin: 2px auto">
                                        <a target="_blank"
                                           href="/images/${(obj.site_fav)!""}/">${(obj.site_fav)!""}</a>
                                    </div>
                                </div>

                            </div>
                        </div>

                    <#--site_createtime-->
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">建站时间</label>

                            <div class="col-sm-11">
                                <div class="input-group date " data-provide="datepicker" id="p_site_creaetime"
                                     data-date-format="yyyy-mm-dd">
                                    <input type="text" id="site_createtime" value="${(obj.site_createtime)!''}"
                                           class="form-control date-picker">

                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-th"></span>
                                    </div>
                                </div>
                            </div>
                        </div>

                    <#--site_tj-->
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">统计代码</label>

                            <div class="col-sm-11">
                                <textarea class="form-control" id="site_tj"
                                          placeholder="CNZZ,51la,百度统计">${(obj.site_tj)!''}</textarea>
                            </div>
                        </div>
                    <#--site_msgboard-->
                        <#--<div class="form-group">-->
                            <#--<label class="col-sm-1 control-label no-padding-right">留言板配置</label>-->

                            <#--<div class="col-sm-11">-->
                                <#--<textarea class="form-control" id="site_msgboard"-->
                                          <#--placeholder="多说,畅言,Disqus">${(obj.site_msgboard)!''}</textarea>-->
                            <#--</div>-->
                        <#--</div>-->


                    <#--site_aboutme-->
                        <div class="form-group">
                            <label class="col-sm-1 control-label no-padding-right">About Me</label>

                            <div class="col-sm-11">
                                <div id="site_aboutme">
                                    <textarea
                                            style="display:none;">${(obj.site_aboutme_md)!""}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="clearfix form-actions">
                            <div class="col-sm-offset-1">
                                <button class="btn btn-info" type="button" id="btn_save_site">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    保存
                                </button>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
<#include "common/footer.ftl">
</div>

<#include  "common/endjs.ftl">


<script>
    $(function () {

        //设置哪个 btn_upload 为上传控件
        fileupload("fileupload_photo");
        //弹出选择图片 iframe
        $('#btn_selectimg_photo').bind('click', function () {
                    layer.open({
                        title: "选择图片",
                        type: 2,
                        area: ['900px', '600px'],
                        offset: ['8%', ''],
                        content: ['/adm/upload/selectimg?callbackid=imgid_photo', 'no']
                    });
                }
        );

        //设置哪个 btn_upload 为上传控件
        fileupload("fileupload_logo");

        //弹出选择图片 iframe
        $('#btn_selectimg_logo').bind('click', function () {
                    layer.open({
                        title: "选择图片",
                        type: 2,
                        area: ['900px', '600px'],
                        offset: ['8%', ''],
                        content: ['/adm/upload/selectimg?callbackid=imgid_logo', 'no']
                    });
                }
        );

        //设置哪个 btn_upload 为上传控件
        fileupload("fileupload_fav");

        //弹出选择图片 iframe
        $('#btn_selectimg_fav').bind('click', function () {
                    layer.open({
                        title: "选择图片",
                        type: 2,
                        area: ['900px', '600px'],
                        offset: ['8%', ''],
                        content: ['/adm/upload/selectimg?callbackid=imgid_fav', 'no']
                    });
                }
        );

        $("#p_site_creaetime").datepicker({
            autoclose: true,
            todayHighlight: true
        });


        $("#btn_save_ftp").bind("click", function () {

            //1 对号
            //2 叉号
            //3 问号
            //4 锁
            //5 哭脸
            //6 笑脸
            //7 叹号

            var ftp_ip = $("#ftp_ip").val();
            var ftp_user = $("#ftp_user").val();
            var ftp_pwd = $("#ftp_pwd").val();
            if (!ftp_ip || !ftp_user || !ftp_pwd) {
                layer.msg("还没填完呢", {icon: 5});
                return;
            }

            //正在处理
            layer.load(2);

            $.post("/adm/save_ftp", {"ftp_ip": ftp_ip, "ftp_user": ftp_user, "ftp_pwd": ftp_pwd}, function (rs) {
                layer.closeAll('loading');
                if (rs["status"] == "ok") {
                    layer.msg("保存成功", {icon: 6});
                }
                else {
                    layer.msg(rs["status"], {icon: 5});
                }
            });
        });


        $("#btn_save_admin").bind("click", function () {
            var admin = $("#admin").val();
            var admin_github = $("#admin_github").val();
            var admin_email = $("#admin_email").val();
            if (!admin || !admin_email || !admin_github) {
                layer.msg("还没填完呢", {icon: 5});
                return;
            }
            var admin_photo = $("#imgid_photo").attr("imgid");
            if (!admin_photo) {
                layer.msg("请上传照片", {icon: 5});
                return;
            }

            //正在处理
            layer.load(2);

            $.post("/adm/save_admin", {
                "admin": admin,
                "admin_photo": admin_photo,
                "admin_email": admin_email,
                "admin_github": admin_github
            }, function (rs) {
                layer.closeAll('loading');
                if (rs["status"] == "ok") {
                    layer.msg("保存成功", {icon: 6});
                }
                else {
                    layer.msg(rs["status"], {icon: 5});
                }
            });
        });

        var testEditor = neweditor("site_aboutme");

        $("#btn_save_site").bind("click", function () {
            var site_name = $("#site_name").val();
            var site_createtime = $("#site_createtime").val();
            var site_aboutme = testEditor.getHTML();
            var site_aboutme_md = testEditor.getMarkdown();
            var site_tj = $("#site_tj").val();
            var site_msgboard = "";
            if (!site_name || !site_createtime || !site_aboutme || !site_tj ) {
                layer.msg("还没填完呢", {icon: 5});
                return;
            }

            var site_logo = $("#imgid_logo").attr("imgid");
            var site_fav = $("#imgid_fav").attr("imgid");
            if (!site_logo) {
                layer.msg("请上传Logo", {icon: 5});
                return;
            }
            if (!site_fav) {
                layer.msg("请上传fav.ico[格式PNG,JPG]", {icon: 5});
                return;
            }
            layer.load(2);

            $.post("/adm/save_site", {
                "site_name": site_name,
                "site_logo": site_logo,
                "site_fav": site_fav,
                "site_createtime": site_createtime,
                "site_aboutme": site_aboutme,
                "site_aboutme_md": site_aboutme_md,
                "site_tj": site_tj,
                "site_msgboard": site_msgboard
            }, function (rs) {
                layer.closeAll('loading');
                if (rs["status"] == "ok") {
                    layer.msg("保存成功", {icon: 6});
                }
                else {
                    layer.msg(rs["status"], {icon: 5});
                }
            });
        });


    });

</script>

</body>
</html>