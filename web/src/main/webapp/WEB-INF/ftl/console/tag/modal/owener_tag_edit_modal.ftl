	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		  	<span aria-hidden="true">x</span>
		</button>
		<h4 class="modal-title">个人分类类目 -- 修改</h4>
	</div>
	
	<form id="owenerTagEditForm" class="form-horizontal form-label-left input_mask" role="form" enctype="multipart/form-data"
		 action="${ctx}/admin/owener_tag/update/" 
		 data-toggle="validator" data-parsley-validate>
		<input type="hidden" name="owenerTagId" value="${owenerTag.owenerTagId}"/>
		<input type="hidden" name="owenerTagCode" value="${owenerTag.owenerTagCode}"/>
		<#-- 全站文章分类编号 -->
		<input type="hidden" name="categoriesTagCode" value="${owenerTag.categoriesTagCode}"/>
	
			<#-- modal-body start -->
			<div class="modal-body">
				<div class="form-group">
						<div class="col-md-6 col-sm-6 col-xs-6 invalid-form-error-message"></div>
				</div>
				<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-6">个人分类名称<span class="required red">*</span></label>
						<div class="col-md-3 col-sm-3 col-xs-6">
						  <input type="text" class="form-control" name="owenerTagName" 
						  		placeholder="个人分类名称" required="required"
						  		value="${owenerTag.owenerTagName}"/>
						</div>
						
						<label class="control-label col-md-3 col-sm-3 col-xs-6">权重<span class="required red">*</span></label>
						<div class="col-md-3 col-sm-3 col-xs-6">
						  <input type="text" class="form-control" name="weight" placeholder="权重" 
						  		required="required" data-parsley-trigger="blur"  
        							data-parsley-required-message="分类描述不可为空" data-parsley-group="blockInput"
						  		value="${owenerTag.weight}"/>
						</div>
				</div>
				<div class="form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-6">关联文章数目</label>
						<div class="col-md-3 col-sm-3 col-xs-6">
						  <input type="text" class="form-control" name="correlaArticleSum" 
						  		placeholder="关联文章数目" readonly="readonly"
						  		value="${owenerTag.correlaArticleSum}"/>
						</div>
						
						<label class="control-label col-md-3 col-sm-3 col-xs-6" title="1显示,0隐藏">是否显示</label>
						<div class="col-md-3 col-sm-3 col-xs-6">
	                          <div class="">
		                            <label>
		                              <input type="checkbox" name="isShow" class="js-switch" 
		                              	<#if (owenerTag.isShow)??>
								  			checked
								    		</#if>/>
		                            </label>
	                          </div>
						</div>
				</div>
		</div>
		<#-- modal-body end -->
			
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button id="categoriesTagEditBtn" type="button" class="btn btn-primary" data-form="#owenerTagEditForm">Save changes</button>
		</div>
	</form>

		
	<script src="http://static.codealy.com/plugins/jquery/v2.2.3/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="http://static.codealy.com/plugins/bootstrap/3.3.7/dist/js/bootstrap.min.js"></script>
	
	<script src="${ctx}/adm/javascript/pages/tag/categories_tag-form.js"></script>
	
	<#-- Switchery -->
	<script src="${ctx}/assets/javascript/public.js"></script>
    <script src="http://static.codealy.com/plugins/switchery/dist/switchery.min.js"></script>
    <script>
    		$(document).ready(function() {
    			var clickCheckBox = document.querySelector('.js-switch');
    			clickCheckBox.addEventListener('click', function(){
    					alert(clickCheckBox.checked);
    			});
    		});
	</script>

    <#-- tips -->
	<!-- lhg dialog js -->
	<script src="http://static.codealy.com/plugins/lhgdialog/4.2.0/lhgdialog.js?skin=bootstrap2"></script>
	<#-- mask -->
	<script src="http://static.codealy.com/plugins/mask/blockui/jquery.blockUI.js"></script>
 	<#-- Parsley validator -->
    <script src="http://static.codealy.com/plugins/parsleyjs/dist/parsley.js"></script>