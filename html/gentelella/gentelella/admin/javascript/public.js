
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
		  		+'<td><img src="../images/wechat_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>'
		  		+'<td><img src="../images/alipay_qrcode.jpg" style="width:auto;max-width:100%;height:120px;"/></td>'
		  	+'</tr>'
		  	+'<tr class="cen">'
		  		+'<td class="text-primary">微信打赏</td>'
		  		+'<td class="text-primary">支付宝打赏</td>'
		  	+'</tr>'
		  +'</table>'
	  +'</div>'
	});
}
