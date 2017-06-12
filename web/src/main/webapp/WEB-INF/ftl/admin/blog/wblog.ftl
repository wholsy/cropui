<#include "admin/common/header.ftl">

<#-- kindeditor -->
<link href="http://static.yueny.website/plugins/kindeditor/themes/default/default.css" rel="stylesheet"/>

<link href="${ctx}/assets/css/write/write.css" rel="stylesheet" type="text/css">
<link href="${ctx}/assets/css/write/main.css" rel="stylesheet" type="text/css">
	
<body class="no-skin">
<#include "admin/common/navbar.ftl">

<div class="main-container ace-save-state" id="main-container">
<#include "admin/common/sidebar-menu.ftl">
    <#-- 页面内容content Start -->
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="${ctx}/admin/">Home</a>
                    </li>
                    <li>
                        <a href="#">博客管理</a>
                    </li>
                    <li class="active">
                    	<#if item.articleBlogId &gt; 0 >
                    		【修改】
                    	<#else>
                        	【新文章】
                        </#if>
                        	${(isdraft)?string("【草稿】","")}
                        </li>
                </ul>
            </div>
            
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                    
			            <form method="post" action="" class="form-horizontal">
							<input type="hidden" id="articleBlogId" name="articleBlogId" value="${(item.articleBlogId)!''}"/>
                            <input type="hidden" name="isdraft" value="${isdraft?string('1','0')}">
							<div id="editType" style="display:none">0</div>
							
                            <p class="subtit">
								文章标题
								<script>
									document.write('<span style="color: green; margin-left: 18px; display: none">尊重原创，请保证您的文章为原创作品</span>');
								</script>
							</p>
							
							<div>
								<#-- 文章标题类型:原文链接 -->
								<select id="selType" name="selTypeCode">
									<#list articleSelTypes as articleSelType>
										<option value="${articleSelType.value}"
											<#if item?? && item.selTypeCode==articleSelType>
									    		selected="selected"
											</#if>
								    	 >${articleSelType.desc}</option>
								    </#list>
								</select>
						
								<#-- 文章标题 -->
								<input id="articleTitle" name="articleTitle" type="text" 
									style="width:560px; height:20px; float:left;" maxlength="100"
									class="required" value="${(item.articleTitle)!''}"/>
							</div>
							
							<p id="p_desc" class="subtit" style="display:none;">
								摘要：
								<span class="gray">（默认自动提取您文章的前200字显示在博客首页作为文章摘要，您也可以在这里自行编辑 ）</span>
							</p>
							<div id="d_desc">
								<textarea id="articleDigest" name="articleDigest" rows="6" maxlength="200"
									placeholder="摘要： （默认自动提取您文章的前200字显示在博客首页作为文章摘要，您也可以在这里自行编辑 ）"
									style="width:99%;" >${(item.articleDigest)!''}
								</textarea>
							</div>
							
							<p class="subtit">
								文章内容
								<span class="word_count" title="字数统计包含HTML代码/字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字"></span>
								<span id="autosave_note"></span>
								<a id="wblog_md" 
									href="${ctx}/admin/wblog_md.html?articleBlogId={{value.articleBlogId}}" 
									target="_blank"
									>切换到MarkDown编辑器</a>
							</p>
							<div class="section">
						        <textarea id="articleContext" name="articleContext" class="xheditor" rows="30" 
						        	spellcheck=bool
						        	style="width:99.4%;visibility:hidden;">
						        	${(item.articleContext)!''}
						        </textarea>
							</div>
							
							<#-- moreDiv start -->
							<div id="moreDiv">
							    <p class="subtit">
							    	文章标签（添加Tag）
							    </p>
								<div style="position:relative;">
									<div id="d_tag2"></div>
									<input id="txtTag2" name="articleTag" type="text" style="width:60%; height:20px;" maxlength="100" />
									（最多添加5个标签，多个标签之间用“,”分隔）
									<div id="tag2box" style="display:none;">
										<table>
											<tr><th>常用标签</th><td id="td_tag21" class="tracking-ad" data-mod="popu_133"></td></tr>
											<tr><th>推荐标签</th><td id="td_tag22" class="tracking-ad" data-mod="popu_73" ></td></tr>
										</table>
									</div>
								</div>
							
								<p class="subtit">个人分类 [<a href="${ctx}/admin/owenerTags.html" target="_blank">编辑个人分类</a>]</p>
								<div>
									<input id="owenerTag" name="owenerTag" data-ids="" type="text" 
										style="width:60%; height:20px;" maxlength="100" placeholder="请选择个人分类"/>
									（多个分类之间用“,”分隔）
									<div>
										<div id="tagbox">
											<table id="tagtb" cellspacing="0">
												<tr>
													<#list owenerTags as owenerTag>
												    	<td>
												    		<input id="chk_tag_${owenerTag.owenerTagId}" type="checkbox" 
													    		onclick="chkTag(this)" value="${owenerTag.owenerTagId}" 
													    		<#list item.owenerTagIds as owenerTagId>
															    	<#if owenerTagId==owenerTag.owenerTagId>
													    				checked="checked"
													    			</#if>
															    </#list>
													    	/>
												    		<label for="chk_tag_${owenerTag.owenerTagId}">${owenerTag.owenerTagName}</label>
												    	</td>
												    </#list>
												</tr>
											</table>
										
										</div>
									</div>
								</div>
						
								<p class="subtit" style="display:none;">文章别名（URL中使用，取代文章ID）</p>
								<div style="display:none;">
									<input type="text" id="articleAliasName" name="articleAlias" 
										style="width:60%; height:20px;" maxlength="30"
										value='${(item.articleAlias)!''}'/>
									（只能使用数字、字母、横线和下划线）
								</div>

								<p class="subtit">全站文章分类（到分类首页）</p>
								<div class="radioBox channel">
									<#list categoryTags as categoryTag>
								    	<input type='radio' name='categoryTagCode' id='categoryChl-${categoryTag.categoriesId}' 
								    		value='${categoryTag.categoriesTagCode}' 
								    		<#if (item.categoryTagCodes)?? && ((item.categoryTagCodes)?size > 0) && (item.categoryTagCodes)?seq_contains(categoryTag.categoriesTagCode)>
							    				checked
							    			<#else>
							    			</#if>
							    		/>
										<label for='categoryChl-${categoryTag.categoriesId}'>
											${categoryTag.categoriesName}
										</label>
									</#list>
								</div>
						    	<#-- 横线 -->
								<p class="subtit"></p>
						    	
								<p style="text-indent:2em;">提示：请不要发布任何推广、广告（包括招聘）、政治、低俗等方面的内容，否则可能会影响到您的使用。</p>
								<div class="clear"></div>
								<p id="p_n" style="text-indent:2em;">提示：文章内容有<b>1</b>分钟的缓存，更改之后不会立即生效。</p>
								<#-- 错误时输入验证码防止恶意攻击
								<div id="checkcodearea" ></div>
								 -->
							</div>
							<#-- moreDiv end -->
	
							<div class="btn_area_1">   
								<input id="btnPublish" type="button" class="input_btn_1" 
									value="发表文章" title="保存并跳转" />
								<input id="btnDraft" type="button" class="input_btn_1" 
									value="立即保存" title="保存草稿" />
								<input id="btnCancel" type="button" class="input_btn_1" value="舍弃" />
								<#-- 显示错误 -->  
								<span id="sp_note" class="savenote" style="display:none;"></span>
							</div>
						</form>
			
					</div>
                </div>
            </div>
            <!-- /.page-content -->
            
        </div>
    </div>
<#include "admin/common/footer.ftl">
</div>
<#include "admin/common/endjs.ftl">

<script type="text/javascript">
	jsonData={articleBlogId:'0',point:'False',max_point:'100',tohome_max_cnt:'5',isClientUser:0,_0:'0'};
	var utag2 = "Java".split(',');
</script>
<script>
	//$(function () {
	//    $("#wblog_md").bind("click", function () {
	 //   	// $.dialog.tips($('#articleContext').val());
	 //   	html_2_md($('#articleContext').val());
	//    });
	//});

	function html_2_md(html) {
		$('#output').val(toMarkdown(html));
	}
	function md_2_html(md) {
		$('#output').val(markdown.toHTML(md));
	}
</script>
			
<#-- kindeditor -->
<script src="${ctx}/assets/plugins/kindeditor/kindeditor-all.js"></script>
<script src="http://static.yueny.website/plugins/kindeditor/lang/zh-CN.js"></script>
<script src="${ctx}/adm/assets/js/wblog/wblog.js"></script>

<script src="http://static.yueny.website/plugins/markdown/to-markdown.js"></script>
<script src="http://static.yueny.website/plugins/markdown/markdown.min.js"></script>

<script src='http://static.yueny.website/plugins/jquery/jquery.autocomplete.min.js' type="text/javascript"></script>

</body>
</html>