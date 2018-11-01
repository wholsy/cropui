<#include "console/common/header_start.ftl">

	<link rel="stylesheet" href="${ctx}/console/style/pages/tag/tag.css" />
	
	<link href="https://static.codealy.com/plugins/nprogress/nprogress.css" rel="stylesheet">
	<link href="https://static.codealy.com/plugins/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
	<link href="https://static.codealy.com/plugins/select2/dist/css/select2.min.css" rel="stylesheet">
	<link href="https://static.codealy.com/plugins/switchery/dist/switchery.min.css" rel="stylesheet">
	<link href="https://static.codealy.com/plugins/starrr/dist/starrr.css" rel="stylesheet">
	<link href="https://static.codealy.com/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
	
<#include "console/common/header_end.ftl">

<#include "console/common/body_start.ftl">

	<#-- title start -->
	<div class="page-title">
			<div class="title_left">
			    <h3>全站文章分类</h3>
			</div>
		
			<#-- 
			<div class="title_right">
				<div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
					<div class="input-group">
					        <input type="text" class="form-control" placeholder="Search for...">
					        <span class="input-group-btn">
					        		<button class="btn btn-default" type="button">Go!</button>
					        </span>      
					</div>
				</div>
			</div>
			 -->
	</div>
	
    <div class="clearfix"></div>
	<#-- title end -->
	
	<#-- row start -->
	<div class="row">
	
		<#-- col start -->
        <div>
			<div class="x_panel tile">
	            <div class="x_title">
	            		全站文章分类
	            		<#include "console/common/comp/panel_for_nav.ftl">
	            </div>
	            
	            <#-- x_content start -->
	            <div class="x_content">
					<#if (list)?? && (list?size > 0)>
				    		<#list list as categoriesTag>
				    			${categoriesTag.categoriesName}
				    			 |
					    </#list>
					    <a class="btn btn-round btn-default modal-link" data-toggle="modal" 
					    		data-url="${ctx}/admin/categories_tag/-1.html">
	                    		<i class="fa fa-plus-circle"></i> Insert
	                    </a>
	                    
					    <#-- row start -->
					    <div class="row">
	                			<#list list as categoriesTag>
	                				<#if (categoriesTag_index+1) % 4 != 0>
	                					<section class="tag-list__item col-md-3">
	                				</#if>
	                				
	            					<div class="tag-list__itemWraper">
	                            		<h3 class="h5 tag-list__itemheader">
	                            			<a class="modal-link" data-toggle="modal"
	                            				data-url="${ctx}/admin/categories_tag/${categoriesTag.categoriesTagCode}.html">
	                                        	${categoriesTag.categoriesName}
										</a>
	                            		</h3>
	                            		
	                            		<ul class="tag-list__itembody taglist--inline multi">
	                            			<li class="tagPopup">
	                            				<#if (categoriesTag.owenerTags)?? && (categoriesTag.owenerTags?size > 0)>
	                            					<#list categoriesTag.owenerTags as owenerTag>
										    			<a class="modal-link tag" data-toggle="popover" 
										    				data-id="1"
				                                        data-original-title="${owenerTag.owenerTagName}"
				                                        data-url="${ctx}/admin/owener_tag/${owenerTag.owenerTagCode}.html">
			                                        		<#--  
			                                        		<img src="https://sfault-avatar.b0.upaiyun.com/300/191/3001919899-5488009eb028c_small">
			                                        		-->
			                                             ${owenerTag.owenerTagName}
				                                		</a>
											    </#list>
	                            				<#else>
												<span>空空如也~</span>
										    </#if>
	                         			</li>
	                         		</ul>
	                         	</div>
		                			
						    		<#if (categoriesTag_index+1) % 4 !=0>
	                					</section>
	                				</#if>
						    </#list>
						</div>
						<#-- row end -->
						
					<#else>
						暂无全站文章分类标签
				    </#if>
				    
				    <#-- modal -->
				    <div class="modal in fade variant-modal" tabindex="2" role="dialog" 
				    		aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    		<div class="modal-dialog modal-lg" role="document">
		                      <div class="modal-content">
									<#-- form data -->									    
		                      		
		                      </div>
		                      <#--  modal-content end -->
		                  </div>
					</div>
						
					<#--  
                            <section class="tag-list__item col-md-3">
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">开发语言</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/java" class="tag" data-toggle="popover" data-id="1040000000089449"
                                               data-original-title="java">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/868/271/868271510-54cb382abb7a1_small">
                                                                                              java
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/c" class="tag" data-toggle="popover" data-id="1040000000089457"
                                               data-original-title="c">
                                                                                              c
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/c%2B%2B" class="tag" data-toggle="popover" data-id="1040000000089741"
                                               data-original-title="c++">
                                                                                              c++
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/php" class="tag" data-toggle="popover" data-id="1040000000089387"
                                               data-original-title="php">
                                                                                              php
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/perl" class="tag" data-toggle="popover" data-id="1040000000089698"
                                               data-original-title="perl">
                                                                                              perl
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/python" class="tag" data-toggle="popover" data-id="1040000000089534"
                                               data-original-title="python">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/252/177/2521771040-54cb53b372821_small">
                                                                                              python
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/javascript" class="tag" data-toggle="popover" data-id="1040000000089436"
                                               data-original-title="javascript">
                                                                                              javascript
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/c%23" class="tag" data-toggle="popover" data-id="1040000000089581"
                                               data-original-title="c#">
                                                                                              c#
                                            </a>
                                        </li>
                                        
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">PHP 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/php" class="tag" data-toggle="popover" data-id="1040000000089387"
                                               data-original-title="php">
                                                                                              php
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/mysql" class="tag" data-toggle="popover" data-id="1040000000089439"
                                               data-original-title="mysql">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/949/844/9498447-54cb56e325a72_small">
                                                                                              mysql
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/apache" class="tag" data-toggle="popover" data-id="1040000000089761"
                                               data-original-title="apache">
                                                                                              apache
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/nginx" class="tag" data-toggle="popover" data-id="1040000000090145"
                                               data-original-title="nginx">
                                                                                              nginx
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/mvc" class="tag" data-toggle="popover" data-id="1040000000089911"
                                               data-original-title="mvc">
                                                                                              mvc
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/symfony" class="tag" data-toggle="popover" data-id="1040000000091266"
                                               data-original-title="symfony">
                                                                                              symfony
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/zend-framework" class="tag" data-toggle="popover" data-id="1040000000090597"
                                               data-original-title="zend-framework">
                                                                                              zend-framework
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/composer" class="tag" data-toggle="popover" data-id="1040000000154201"
                                               data-original-title="composer">
                                                                                              composer
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/laravel" class="tag" data-toggle="popover" data-id="1040000000196640"
                                               data-original-title="laravel">
                                                                                              laravel
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">Ruby 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/ruby" class="tag" data-toggle="popover" data-id="1040000000089699"
                                               data-original-title="ruby">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/336/760/3367608758-54cb53ca5a132_small">
                                                                                              ruby
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ruby-on-rails" class="tag" data-toggle="popover" data-id="1040000000089646"
                                               data-original-title="ruby-on-rails">
                                                                                              ruby-on-rails
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/rubygems" class="tag" data-toggle="popover" data-id="1040000000090723"
                                               data-original-title="rubygems">
                                                                                              rubygems
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/rvm" class="tag" data-toggle="popover" data-id="1040000000198244"
                                               data-original-title="rvm">
                                                                                              rvm
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/macosx" class="tag" data-toggle="popover" data-id="1040000000090220"
                                               data-original-title="macosx">
                                                                                              macosx
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/bundle" class="tag" data-toggle="popover" data-id="1040000000090898"
                                               data-original-title="bundle">
                                                                                              bundle
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">搜索</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/%E6%90%9C%E7%B4%A2%E5%BC%95%E6%93%8E" class="tag" data-toggle="popover" data-id="1040000000090137"
                                               data-original-title="搜索引擎">
                                                                                              搜索引擎
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E4%B8%AD%E6%96%87%E5%88%86%E8%AF%8D" class="tag" data-toggle="popover" data-id="1040000000090419"
                                               data-original-title="中文分词">
                                                                                              中文分词
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E5%85%A8%E6%96%87%E6%A3%80%E7%B4%A2" class="tag" data-toggle="popover" data-id="1040000000090334"
                                               data-original-title="全文检索">
                                                                                              全文检索
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/lucene" class="tag" data-toggle="popover" data-id="1040000000090316"
                                               data-original-title="lucene">
                                                                                              lucene
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/solr" class="tag" data-toggle="popover" data-id="1040000000090126"
                                               data-original-title="solr">
                                                                                              solr
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/sphinx" class="tag" data-toggle="popover" data-id="1040000000090021"
                                               data-original-title="sphinx">
                                                                                              sphinx
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/analyzer" class="tag" data-toggle="popover" data-id="1040000000090318"
                                               data-original-title="analyzer">
                                                                                              analyzer
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/elasticsearch" class="tag" data-toggle="popover" data-id="1040000000250111"
                                               data-original-title="elasticsearch">
                                                                                              elasticsearch
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                    </section>
            -->
        
	            </div>
	            <#-- x_content start -->
			</div>
        </div>
		<#-- col start -->
		

	</div>
	<#-- row end -->
	
	<#include "console/common/body_end.ftl">
	
	<script src="${ctx}/console/javascript/pages/tag/categories_tag.js"></script>
	
	<script src="https://static.codealy.com/plugins/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<script src="https://static.codealy.com/plugins/icheck/icheck.min.js"></script>
	<#-- bootstrap-daterangepicker -->
	<script src="https://static.codealy.com/plugins/moment/2.13.0/min/moment.min.js"></script>
    <script src="https://static.codealy.com/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
    <#-- bootstrap-wysiwyg -->
	<script src="https://static.codealy.com/plugins/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
	<script src="https://static.codealy.com/plugins/jquery.hotkeys/jquery.hotkeys.js"></script>
	<script src="https://static.codealy.com/plugins/google-code-prettify/src/prettify.js"></script>
	
	<#-- Switchery -->
    <script src="https://static.codealy.com/plugins/switchery/dist/switchery.min.js"></script>
    <#-- Select2 -->
    <script src="https://static.codealy.com/plugins/select2/4.0.3/dist/js/select2.full.min.js"></script>
    <#-- Autosize -->
    <script src="https://static.codealy.com/plugins/autosize/dist/autosize.min.js"></script>
    <#-- jQuery autocomplete -->
    <script src="https://static.codealy.com/plugins/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
    <#-- starrr -->
    <script src="https://static.codealy.com/plugins/starrr/dist/starrr.js"></script>
	

</html>
