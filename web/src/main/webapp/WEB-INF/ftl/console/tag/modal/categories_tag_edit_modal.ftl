	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		  	<span aria-hidden="true">x</span>
		</button>
		<h4 class="modal-title">
	  		<#if (categoriesTag)?? && (categoriesTag.owenerTags)??>
	  			全站文章分类 -- 修改
	  			<small><span>${categoriesTag.categoriesName}</span></small>
			<#else>
				全站文章分类 -- 新增
	    		</#if>
		</h4>
	</div>
	
	<form id="categoriesTagEditForm" class="form-horizontal form-label-left input_mask" role="form" enctype="multipart/form-data"
		 action="${ctx}/admin/categories_tag/update/"
		 data-toggle="validator" data-parsley-validate>
		<input type="hidden" name="categoriesTagCode" value="${categoriesTag.categoriesTagCode}"/>
		
		<#-- modal-body start -->
			<div class="modal-body">
					<div class="form-group">
							<div class="col-md-9 col-sm-9 col-xs-12 invalid-form-error-message"></div>
					</div>
					<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">全站文章分类名称<span class="required red">*</span></label>
							<div class="col-md-9 col-sm-9 col-xs-12">
							  <input type="text" class="form-control" name="categoriesName" 
							  		<#if (categoriesTag)?? && (categoriesTag.owenerTags)??>
							  			readonly="readonly"
							    		</#if>
							  		placeholder="全站文章分类名称" required="required"
							  		value="${categoriesTag.categoriesName}"/>
							</div>
					</div>
					<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">分类描述<span class="required red">*</span></label>
							<div class="col-md-9 col-sm-9 col-xs-12">
							  <input type="text" class="form-control" name="categoriesDesc" placeholder="分类描述" 
							  		required="required" data-parsley-trigger="blur"  
            							data-parsley-required-message="分类描述不可为空" data-parsley-group="blockInput"
							  		value="${categoriesTag.categoriesDesc}"/>
							</div>
					</div>
					<div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12">备注</label>
	                        <div class="col-md-9 col-sm-9 col-xs-12">
	                          <input type="text" class="form-control" name="memo" placeholder="备注"
							  		value="${categoriesTag.memo}"/>
	                        </div>
					</div>
					<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">上级分类</label>
							<div class="col-md-9 col-sm-9 col-xs-12">
								<select name="tagsForUpategoriesCode" class="select2_single form-control" tabindex="-1">
									<option value="">请选择</option>
									<#if (tagsForUp)?? && (tagsForUp?size > 0)>
                            					<#list tagsForUp as tp>
                            						<option value="${tp.categoriesTagCode}" 
	                            						<#if tp.categoriesTagCode == categoriesTag.categoriesParentCode>
	                            							selected="selected"
										    			</#if>
									    			 	title="${tp.memo}">${tp.categoriesName}
									    			 </option>
										    </#list>
								    </#if>
								</select>
							</div>
					</div>
					
					<#-- 
					<div class="form-group">
					    <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Grouped</label>
					    <div class="col-md-9 col-sm-9 col-xs-12">
							<select class="select2_group form-control">
							    <optgroup label="Alaskan/Hawaiian Time Zone">
							      <option value="AK">Alaska</option>
							      <option value="HI">Hawaii</option>
							    </optgroup>
							    <optgroup label="Eastern Time Zone">
							      <option value="GA">Georgia</option>
							      <option value="VA">Virginia</option>
							      <option value="WV">West Virginia</option>
							    </optgroup>
							</select>
					    </div>
					</div>
					
					<div class="form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Multiple</label>
                        <div class="col-md-9 col-sm-9 col-xs-12">
							<select class="select2_multiple form-control" multiple="multiple">
							    <option>Choose option</option>
							    <option>Option five</option>
							    <option>Option six</option>
							</select>
						</div>
					</div>
					 -->
					 
					<#-- tags -->
					<div class="control-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">个人分类列表</label>
						<div class="col-md-9 col-sm-9 col-xs-12">
							<input type="text" name="owenerTagNameArrays" class="tags form-control tagsInput" 
								value="
									<#if (categoriesTag.owenerTags)?? && (categoriesTag.owenerTags?size > 0)>
                            					<#list categoriesTag.owenerTags as ct>
                            						${ct.owenerTagName}, 
										    </#list>
								    </#if>"/>
							<div id="suggestions-container" style="position: relative; float: left; width: 250px; margin: 10px;"></div>
						</div>
					</div>
		</div>
		<#-- modal-body end -->
			
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button id="categoriesTagEditBtn" type="button" class="btn btn-primary" data-form="#categoriesTagEditForm">Save changes</button>
		</div>
	</form>

		
	<script src="https://static.codealy.com/plugins/jquery/v2.2.3/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="https://static.codealy.com/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
	
	<script src="${ctx}/console/javascript/pages/tag/categories_tag-form.js"></script>
	
	<!-- jQuery Tags Input -->
	<script src="${ctx}/console/javascript/custom_public.js"></script>
    <script src="https://static.codealy.com/plugins/jquery.tagsinput/src/jquery.tagsinput.js"></script>
    
    <#-- tips -->
	<!-- lhg dialog js -->
	<script src="https://static.codealy.com/plugins/lhgdialog/4.2.0/lhgdialog.js?skin=bootstrap2"></script>
	<#-- mask -->
	<script src="https://static.codealy.com/plugins/mask/blockui/jquery.blockUI.js"></script>
 	<#-- Parsley validator -->
    <script src="https://static.codealy.com/plugins/parsleyjs/dist/parsley.js"></script>