function hideprocess(id) {

    //上传完毕，5秒钟后消失。并且使得进度条
    $("#" + id).find('.progress').hide();

    //进度值减为0
    $("#" + id).find('.progress-bar').css(
        'width', '0%'
    );

}


//使用方法
//fileupload("fileupload");
//
//模板

//<div id="fileupload">
//<span class="btn btn-success fileinput-button">
//<span>上传</span>
//<input class="btn btn-primary" type="file" name="files[]" multiple="">
//</span>
//<br>
//<div class="progress" style="display: none;margin-bottom: 0px;">
//<div class="progress-bar progress-bar-success">0</div>
//</div>
//<br>
//<div class="showimg">
//</div>
//</div>


var manyfile = false;

// fileupload_1
// progress_1


//jquery  file upload
function fileupload(id) {
    $("#" + id).find("input[type='file']").fileupload({
        url: "/adm/upload",
//                dataType: 'json', //如果不返回json数据，需要注释掉。
        add: function (e, data) {

            $("#" + id).find(".showimg").attr("imgid", ""); //存储img图片信息

            var uploadErrors = [];
            var acceptFileTypes = /^image\/(jpe?g)$/i;
            if ((data.originalFiles[0]['type'].length == 0) || (data.originalFiles[0]['type'].length && (data.originalFiles[0]['type'].indexOf("png") <= 0 && data.originalFiles[0]['type'].indexOf("jpeg") <= 0)  )) {
                uploadErrors.push('只能上传JPG或PNG图片');
            }

            if (!manyfile) {
                if (data.originalFiles.length > 1) {
                    uploadErrors.push('只能上传1个图片');
                    manyfile = true;
                }
            }
            if (data.originalFiles[0]['size'] && data.originalFiles[0]['size'] > 1024 * 1024) {
                uploadErrors.push('上传文件不能大于1MB');
            }
            if (uploadErrors.length > 0) {
                alert(uploadErrors.join("\n"));
            }
            else {
                $("#" + id).find('.progress').show();
                $("#" + id).attr("disabled", "disabled");
                data.submit();
            }
        },
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $("#" + id).find('.progress-bar').css(
                'width',
                progress + '%'
            ).text(progress + '%');
        },
        done: function (e, data) {
            data = data.result;
            var imgid = data["url"];
            $("#" + id).find(".showimg").html("<a  target='_blank' href=\"" + imgid + "\">查看</a>");
            $("#" + id).find(".showimg").attr("imgid", imgid);
            $("#" + id).removeAttr("disabled");
            setTimeout("hideprocess(\"" + id + "\")", 3000);
        }
    });
}

