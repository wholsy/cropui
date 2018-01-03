// Table
$('table input').on('ifChecked', function () {
    checkState = '';
    $(this).parent().parent().parent().addClass('selected');
    countChecked();
});
$('table input').on('ifUnchecked', function () {
    checkState = '';
    $(this).parent().parent().parent().removeClass('selected');
    countChecked();
});

var checkState = '';

$('.bulk_action input').on('ifChecked', function () {
    checkState = '';
    $(this).parent().parent().parent().addClass('selected');
    countChecked();
});
$('.bulk_action input').on('ifUnchecked', function () {
    checkState = '';
    $(this).parent().parent().parent().removeClass('selected');
    countChecked();
});
$('.bulk_action input#check-all').on('ifChecked', function () {
    checkState = 'all';
    countChecked();
});
$('.bulk_action input#check-all').on('ifUnchecked', function () {
    checkState = 'none';
    countChecked();
});

function countChecked() {
    if (checkState === 'all') {
        $(".bulk_action input[name='table_records']").iCheck('check');
    }
    if (checkState === 'none') {
        $(".bulk_action input[name='table_records']").iCheck('uncheck');
    }

    var checkCount = $(".bulk_action input[name='table_records']:checked").length;

    if (checkCount) {
        $('.column-title').hide();
        $('.bulk-actions').show();
        $('.action-cnt').html(checkCount + ' Records Selected');
    } else {
        $('.column-title').show();
        $('.bulk-actions').hide();
    }
}

	    
	   
/* DATA TABLES */
function init_DataTables() {
	console.log('run_datatables');
	
	if( typeof ($.fn.DataTable) === 'undefined'){ return; }
	console.log('init_DataTables');
	
	var $datatable = $('#datatable-keytable-checkbox');
	$datatable.dataTable({
		"serverSide": true,               //设置为true才会进行服务器分页
		"pagingType": "full_numbers",       //分页按钮的显示形式
		"paging": true,     //如果为FALSE 所有数据显示在一个页面上
		//数据赋值
		"ajax" : {
            "url" : ctx + "/admin/do_show_list.json",
            "type" : "POST",
            "dataSrc" : function(json) {
            	alert("bb");
            	return json.data;
            }
		},
		
		scrollY: 380,
		scrollCollapse: true,
		scroller: true
	});
	
	$datatable.on('draw.dt', function() {
	  $('checkbox input').iCheck({
		checkboxClass: 'icheckbox_flat-green'
	  });
	});
	
};
	

$(document).ready(function() {
	init_DataTables();	
});