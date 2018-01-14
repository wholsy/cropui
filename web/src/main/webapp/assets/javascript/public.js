$(function () {
		/* blockUI 全局设置 */
	   // override these in your code to change the default behavior and style
	   $.blockUI.defaults.css = {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        };
        
       $.blockUI.defaults.message = 'Hold on!';
});


//捐赠
function reciprocate(){
	layer.open({
	  type: 1,
	  skin: 'layui-layer-demo',
	  closeBtn:1,
	  anim: 2,
	  shadeClose: false,
	  title:'开心的蛋蛋',
	  content: '<div class="pl-20 pr-20">'
		  +'<table class="table table-bordered table-striped mt-10">'
		  	+'<tr>'
		  		+'<td><img src="'+ctx+'/assets/images/reciprocate/wechat_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>'
		  		+'<td><img src="'+ctx+'/assets/images/reciprocate/alipay_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>'
		  	+'</tr>'
		  	+'<tr class="cen">'
		  		+'<td class="text-primary">微信打赏</td>'
		  		+'<td class="text-primary">支付宝打赏</td>'
		  	+'</tr>'
		  +'</table>'
	  +'</div>'
	});
}

