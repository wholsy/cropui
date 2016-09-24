<#import "/macro.ftl" as m>

<#-- 用户类别管理  -->
<@m.page_header title='${title}'/>
	用户类别管理
	<#if (owenerTagBos)?? && (owenerTagBos?size > 0)>
    	<#list owenerTagBos as owenerTagBo>
    		${owenerTagBo.owenerTagName}
	    </#list>
    </#if>
    
    <link type="text/css" rel="Stylesheet" href="http://c.csdnimg.cn/pig/blog/write/css/main.css" />
    <link rel="stylesheet" href="http://c.csdnimg.cn/public/common/toolbar/css/index.css">

	<script src="${ctx}/assets/js/write/category.js" type="text/javascript"></script>
        
	<style type='text/css'>
		#lstBody input { padding:2px 4px; }
	</style>
        
	<table id="lstBox" cellspacing="0">
		<thead>
		<tr>
			<th class="tdleft">类别</th>
			<th style="width:120px;">文章</th>
			<th style="width:100px;">操作</th>
		</tr>
		</thead>
		<tbody id="lstBody">
		<tr>
			<td class='tdleft'><span>典藏【转载】</span></td>
			<td><a href='http://blog.csdn.net/yueny/article/category/6255878' class='red' target='_blank'>0</a></td>
			<td>
				<a href='#6255878' name='edit' onclick="javascript:doExec(this,6255878,'edit');return false;">编辑</a> | 
				<a href='?del=6255878' name='del' onclick="javascript:doExec(this,6255878,'del');return false;">删除</a><br />
				<span class='gray'>显示</span> | 
				<a href='?t=hide&id=6255878' class='hide' onclick="javascript:doExec(this,6255878,'hide');return false;">隐藏</a>
			</td>
			<td> <a href='javascript:void(0);' class='down' onclick="javascript:doExec(this,6255878,'down');return false;">下移</a></td>
		</tr>
		
		<tr class='altitem'>
			<td class='tdleft'><span>dubbo</span></td>
			<td><a href='http://blog.csdn.net/yueny/article/category/6255879' class='red' target='_blank'>0</a></td>
			<td>
				<a href='#6255879' name='edit' onclick="javascript:doExec(this,6255879,'edit');return false;">编辑</a> | 
				<a href='?del=6255879' name='del' onclick="javascript:doExec(this,6255879,'del');return false;">删除</a><br />
				<span class='gray'>显示</span> | 
				<a href='?t=hide&id=6255879' class='hide' onclick="javascript:doExec(this,6255879,'hide');return false;">隐藏</a>
			</td>
		</tr>
		</tbody>
	</table>

<div style="margin-top:20px;">
	<input id="txtCat" type="text" class="t_input" size="50" maxlength="30" />
	<input id="btnAdd" type="button" class="t_btn" value="添加分类" />
	<input id="btnCancel" type="button" class="t_btn" value="取消修改" style="display:none;" />
</div>

<p class="note">提示：文章分类有<b>1</b>分钟缓存，更改之后不会立即生效。</p>
   
<@m.page_footer date="2015"/>
