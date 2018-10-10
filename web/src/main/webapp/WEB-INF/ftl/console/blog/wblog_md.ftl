<#include "console/common/header_start.ftl">
	<#-- kindeditor -->
	<link href="http://static.codealy.com/plugins/markdown/editor.md/css/editormd.css" rel="stylesheet"/>

	<#--<link href="${ctx}/console/style/pages/blog/write/write.css" rel="stylesheet" type="text/css">-->

<#include "console/common/header_end.ftl">

<#include "console/common/body_start.ftl">
		<#-- row start -->
		<div class="row">
			<#-- 列表信息 -->
			<div class="col-md-12 col-sm-12 col-xs-12">
		        <div class="x_panel">
					<div class="x_title">
			            <#if item.articleBlogId &gt; 0 >
                    			【修改】
	                    	<#else>
	                        	【新文章】
	                     </#if>
	                     ${(isdraft)?string("【草稿】","")}
					</div>
					
					<#-- x_content start -->
					<div class="x_content">
			                    
					            <form method="post" action="" class="form-horizontal">
									<input type="hidden" id="articleBlogId" name="articleBlogId" value="${(item.articleBlogId)!''}"/>
		                            	<input type="hidden" name="isdraft" value="${isdraft?string('1','0')}">
									<div id="editType" style="display:none">0</div>
									
		                            <p class="subtit">
										1、文章标题
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
                                        2、摘要：
										<span class="gray">（默认使用文章标题，您也可以在这里自行编辑 ）</span>
										|
                                        <a id="btn_digest_view" value="0">
                                            预览
                                        </a>
                                        |
                                        <a id="btn_digest_hide" value="1">
                                            隐藏
                                        </a>
									</p>
									<div id="d_desc">
                                        <#--<div id="editormd-digest" name="articleDigest">aaa ${(item.articleDigest)!''}</div>-->
                                        <div id="editormd-digest">
                                            <textarea id="editormd-digest-markdown-doc" name="articleDigest">${(item.articleDigest)!''}</textarea>
                                        </div>
										<#--<textarea id="articleDigest" name="articleDigest" rows="6" maxlength="200"-->
											<#--placeholder="摘要：默认使用文章标题，您也可以在这里自行编辑"-->
											<#--style="width:99%;" >${(item.articleDigest)!''}-->
										<#--</textarea>-->
									</div>
									
									<p class="subtit">
                                        3、文章内容
										<span class="word_count" title="字数统计包含HTML代码/字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字"></span>
										<span id="autosave_note"></span>
                                        <a id="btn_context_view" value="0">
                                            预览
                                        </a>
									</p>

                                    <#--<div id="editormd-context" name="articleContext">${(item.articleContextForMd)!''}</div>-->
                                    <div id="editormd-context">
										<textarea id="editormd-context-markdown-doc" name="articleContext">${(item.articleContextForMd)!''}</textarea>
										<#--
										<textarea id="editormd-context" name="articleContext" class="xheditor" rows="30"
								        	spellcheck=bool
								        	style="width:99.4%;visibility:hidden;">
								        	${(item.articleContext)!''}
								        </textarea>
										-->
									</div>
									
									<#-- moreDiv start -->
									<div id="moreDiv">
									    <p class="subtit">
                                            4、文章标签（添加Tag）
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
									
										<p class="subtit" style="display:none;">文章别名（URL中使用，取代文章ID）</p>
										<div style="display:none;">
											<input type="text" id="articleAliasName" name="articleAlias" 
												style="width:60%; height:20px;" maxlength="30"
												value='${(item.articleAlias)!''}'/>
											（只能使用数字、字母、横线和下划线）
										</div>
										
										<p class="subtit">5、全站文章分类[<a href="${ctx}/admin/categories_tag.html" target="_blank">编辑全站分类</a>]</p>
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
										
										<p class="subtit">6、个人分类 [<a href="${ctx}/admin/owenerTags.html" target="_blank">编辑个人分类</a>]</p>
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
										<input id="btnPublish" type="button" class="btn btn-default btn-space btn-primary"
											value="发表文章" title="保存并跳转" />
										<input id="btnDraft" type="button" class="btn btn-default btn-space"
											value="立即保存" title="保存草稿" />
										<input id="btnCancel" type="button" class="btn btn-default btn-space" value="舍弃" />
										<#-- 显示错误 -->  
										<span id="sp_note" class="savenote" style="display:none;"></span>
									</div>
								</form>
						
		          	</div>
		          	<#-- x_content end -->
				</div>
			</div>
			<#-- 列表信息结束 -->
		</div>
		<#-- row end -->
	
		<script src="${ctx}/console/javascript/pages/blog/wblog/wblog_md.js"></script>
		<script src="http://static.codealy.com/plugins/markdown/editor.md/editormd.min.js"></script>
		<script type="text/javascript">
			jsonData={articleBlogId:'0',point:'False',max_point:'100',tohome_max_cnt:'5',isClientUser:0,_0:'0'};
			var utag2 = "Java".split(',');
		</script>

		<script src='http://static.codealy.com/plugins/jquery/jquery.autocomplete.min.js' type="text/javascript"></script>

<#include "console/common/body_end.ftl">

</html>