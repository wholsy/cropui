<#include "admin/common/header_start.ftl">

	<link rel="stylesheet" href="${ctx}/adm/style/pages/tag/tag.css" />

<#include "admin/common/header_end.ftl">

<#include "admin/common/body_start.ftl">

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
	            		<#include "admin/common/comp/panel_for_nav.ftl">
	            </div>
	            
	            <#-- x_content start -->
	            <div class="x_content">
					<#if (list)?? && (list?size > 0)>
				    		<#list list as categoriesTag>
				    			${categoriesTag.categoriesTagBo.categoriesName}
				    			 |
					    </#list>
					    <a href="javascript:;" class="btn btn-round btn-default" data-toggle="modal"
					    		data-target=".bs-tag-edit-modal-lg">
	                    		<i class="fa fa-plus-circle"></i> Insert
	                    </a>
	                    
						<div class="modal fade bs-tag-edit-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
	                    		<div class="modal-dialog modal-lg">
			                      <div class="modal-content">
				                        <div class="modal-header">
				                          <button type="button" class="close" data-dismiss="modal">
				                          	<span aria-hidden="true">×</span>
				                          </button>
				                          <h4 class="modal-title" id="myModalLabel">
				                          	全站文章分类-新增
				                          </h4>
				                        </div>
				                        
				                        <div class="modal-body">
				                          <h4>Text in a modal</h4>
				                          <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
				                          <p>Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.</p>
				                        </div>
				                        <div class="modal-footer">
				                          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				                          <button type="button" class="btn btn-primary">Save changes</button>
				                        </div>
			                      </div>
			                  </div>
						</div>
                  
                  
                  
	                    
					    <#-- row start -->
					    <div class="row">
	                			<#list list as categoriesTag>
	                				<#if (categoriesTag_index+1) % 4 != 0>
	                					<section class="tag-list__item col-md-3">
	                				</#if>
	                				
	            					<div class="tag-list__itemWraper">
	                            		<h3 class="h5 tag-list__itemheader">
	                            			<a href="/t/${categoriesTag.categoriesTagBo.categoriesTagCode}" data-toggle="popover" 
	                                           data-original-title="${categoriesTag.categoriesTagBo.categoriesName}">
	                                        	${categoriesTag.categoriesTagBo.categoriesName}
										</a>
	                            		</h3>
	                            		<ul class="tag-list__itembody taglist--inline multi">
	                            			<li class="tagPopup">
	                                        <#if (categoriesTag.owenerTags)?? && (categoriesTag.owenerTags?size > 0)>
									    			<#list categoriesTag.owenerTags as owenerTag>
										    			<a href="/t/${owenerTag.owenerTagId}" class="tag" data-toggle="popover" data-id="1040000000089442"
				                                           data-original-title="${owenerTag.owenerTagName}">
			                                        		<#--  
			                                        		<img src="https://sfault-avatar.b0.upaiyun.com/300/191/3001919899-5488009eb028c_small">
			                                        		-->
			                                             ${owenerTag.owenerTagName}
				                                		</a>
											    </#list>
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
				    
				    
					
						<#--
						<section class="tag-list__item col-md-3">
                         	<div class="tag-list__itemWraper">
                            		<h3 class="h5 tag-list__itemheader">iOS 开发</h3>
                            		<ul class="tag-list__itembody taglist--inline multi">
                            			<li class="tagPopup">
                                            <a href="/t/ios" class="tag" data-toggle="popover" data-id="1040000000089442"
                                               data-original-title="ios">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/300/191/3001919899-5488009eb028c_small">
                                                                                              ios
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/objective-c" class="tag" data-toggle="popover" data-id="1040000000090209"
                                               data-original-title="objective-c">
                                                                                              objective-c
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/sqlite" class="tag" data-toggle="popover" data-id="1040000000090354"
                                               data-original-title="sqlite">
                                                                                              sqlite
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/safari" class="tag" data-toggle="popover" data-id="1040000000089499"
                                               data-original-title="safari">
                                                                                              safari
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/xcode" class="tag" data-toggle="popover" data-id="1040000000089579"
                                               data-original-title="xcode">
                                                                                              xcode
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/phonegap" class="tag" data-toggle="popover" data-id="1040000000115193"
                                               data-original-title="phonegap">
                                                                                              phonegap
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/cocoa" class="tag" data-toggle="popover" data-id="1040000000090722"
                                               data-original-title="cocoa">
                                                                                              cocoa
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/javascript" class="tag" data-toggle="popover" data-id="1040000000089436"
                                               data-original-title="javascript">
                                                                                              javascript
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/macos" class="tag" data-toggle="popover" data-id="1040000000260425"
                                               data-original-title="macos">
                                                                                              macos
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/iphone" class="tag" data-toggle="popover" data-id="1040000000089498"
                                               data-original-title="iphone">
                                                                                              iphone
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ipad" class="tag" data-toggle="popover" data-id="1040000000090638"
                                               data-original-title="ipad">
                                                                                              ipad
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/swift" class="tag" data-toggle="popover" data-id="1040000000531223"
                                               data-original-title="swift">
                                                                                              swift
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">Android 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/android" class="tag" data-toggle="popover" data-id="1040000000089658"
                                               data-original-title="android">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/287/281/287281599-5a263edae47e9_small">
                                                                                              android
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/java" class="tag" data-toggle="popover" data-id="1040000000089449"
                                               data-original-title="java">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/868/271/868271510-54cb382abb7a1_small">
                                                                                              java
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/eclipse" class="tag" data-toggle="popover" data-id="1040000000089509"
                                               data-original-title="eclipse">
                                                                                              eclipse
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/xml" class="tag" data-toggle="popover" data-id="1040000000089583"
                                               data-original-title="xml">
                                                                                              xml
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/phonegap" class="tag" data-toggle="popover" data-id="1040000000115193"
                                               data-original-title="phonegap">
                                                                                              phonegap
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/json" class="tag" data-toggle="popover" data-id="1040000000090043"
                                               data-original-title="json">
                                                                                              json
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/webview" class="tag" data-toggle="popover" data-id="1040000000090505"
                                               data-original-title="webview">
                                                                                              webview
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/android-studio" class="tag" data-toggle="popover" data-id="1040000000410239"
                                               data-original-title="android-studio">
                                                                                              android-studio
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">Python 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/python" class="tag" data-toggle="popover" data-id="1040000000089534"
                                               data-original-title="python">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/252/177/2521771040-54cb53b372821_small">
                                                                                              python
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/list" class="tag" data-toggle="popover" data-id="1040000000394828"
                                               data-original-title="list">
                                                                                              list
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/django" class="tag" data-toggle="popover" data-id="1040000000089564"
                                               data-original-title="django">
                                                                                              django
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/flask" class="tag" data-toggle="popover" data-id="1040000000125263"
                                               data-original-title="flask">
                                                                                              flask
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/tornado" class="tag" data-toggle="popover" data-id="1040000000116331"
                                               data-original-title="tornado">
                                                                                              tornado
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/web.py" class="tag" data-toggle="popover" data-id="1040000000090740"
                                               data-original-title="web.py">
                                                                                              web.py
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/sqlalchemy" class="tag" data-toggle="popover" data-id="1040000000125013"
                                               data-original-title="sqlalchemy">
                                                                                              sqlalchemy
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/virtualenv" class="tag" data-toggle="popover" data-id="1040000000123810"
                                               data-original-title="virtualenv">
                                                                                              virtualenv
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">Java 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/java" class="tag" data-toggle="popover" data-id="1040000000089449"
                                               data-original-title="java">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/868/271/868271510-54cb382abb7a1_small">
                                                                                              java
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/java-ee" class="tag" data-toggle="popover" data-id="1040000000090793"
                                               data-original-title="java-ee">
                                                                                              java-ee
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/jar" class="tag" data-toggle="popover" data-id="1040000000157938"
                                               data-original-title="jar">
                                                                                              jar
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/spring" class="tag" data-toggle="popover" data-id="1040000000090103"
                                               data-original-title="spring">
                                                                                              spring
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/hibernate" class="tag" data-toggle="popover" data-id="1040000000090592"
                                               data-original-title="hibernate">
                                                                                              hibernate
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/struts" class="tag" data-toggle="popover" data-id="1040000000089638"
                                               data-original-title="struts">
                                                                                              struts
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/tomcat" class="tag" data-toggle="popover" data-id="1040000000090117"
                                               data-original-title="tomcat">
                                                                                              tomcat
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/maven" class="tag" data-toggle="popover" data-id="1040000000090186"
                                               data-original-title="maven">
                                                                                              maven
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/eclipse" class="tag" data-toggle="popover" data-id="1040000000089509"
                                               data-original-title="eclipse">
                                                                                              eclipse
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/intellij-idea" class="tag" data-toggle="popover" data-id="1040000000090560"
                                               data-original-title="intellij-idea">
                                                                                              intellij-idea
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                    </section>
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
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ruby" class="tag" data-toggle="popover" data-id="1040000000089699"
                                               data-original-title="ruby">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/336/760/3367608758-54cb53ca5a132_small">
                                                                                              ruby
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/objective-c" class="tag" data-toggle="popover" data-id="1040000000090209"
                                               data-original-title="objective-c">
                                                                                              objective-c
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/go" class="tag" data-toggle="popover" data-id="1040000000090511"
                                               data-original-title="go">
                                                                                              go
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/lua" class="tag" data-toggle="popover" data-id="1040000000090208"
                                               data-original-title="lua">
                                                                                              lua
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/node.js" class="tag" data-toggle="popover" data-id="1040000000089918"
                                               data-original-title="node.js">
                                                                                              node.js
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/erlang" class="tag" data-toggle="popover" data-id="1040000000090201"
                                               data-original-title="erlang">
                                                                                              erlang
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/scala" class="tag" data-toggle="popover" data-id="1040000000090228"
                                               data-original-title="scala">
                                                                                              scala
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/bash" class="tag" data-toggle="popover" data-id="1040000000089686"
                                               data-original-title="bash">
                                                                                              bash
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/actionscript" class="tag" data-toggle="popover" data-id="1040000000090194"
                                               data-original-title="actionscript">
                                                                                              actionscript
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
                            <section class="tag-list__item col-md-3">
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">前端开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/html" class="tag" data-toggle="popover" data-id="1040000000089571"
                                               data-original-title="html">
                                                                                              html
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/html5" class="tag" data-toggle="popover" data-id="1040000000089409"
                                               data-original-title="html5">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/102/500/1025005915-54cb538538eea_small">
                                                                                              html5
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/css" class="tag" data-toggle="popover" data-id="1040000000089434"
                                               data-original-title="css">
                                                                                              css
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/css3" class="tag" data-toggle="popover" data-id="1040000000090141"
                                               data-original-title="css3">
                                                                                              css3
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/javascript" class="tag" data-toggle="popover" data-id="1040000000089436"
                                               data-original-title="javascript">
                                                                                              javascript
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/jquery" class="tag" data-toggle="popover" data-id="1040000000089733"
                                               data-original-title="jquery">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/745/445/745445564-54cb540ec31aa_small">
                                                                                              jquery
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/json" class="tag" data-toggle="popover" data-id="1040000000090043"
                                               data-original-title="json">
                                                                                              json
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ajax" class="tag" data-toggle="popover" data-id="1040000000090169"
                                               data-original-title="ajax">
                                                                                              ajax
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F" class="tag" data-toggle="popover" data-id="1040000000089653"
                                               data-original-title="正则表达式">
                                                                                              正则表达式
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/bootstrap" class="tag" data-toggle="popover" data-id="1040000000091210"
                                               data-original-title="bootstrap">
                                                                                              bootstrap
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">数据库</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/%E6%95%B0%E6%8D%AE%E5%BA%93" class="tag" data-toggle="popover" data-id="1040000000089846"
                                               data-original-title="数据库">
                                                                                              数据库
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
                                            <a href="/t/sqlite" class="tag" data-toggle="popover" data-id="1040000000090354"
                                               data-original-title="sqlite">
                                                                                              sqlite
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/oracle" class="tag" data-toggle="popover" data-id="1040000000090701"
                                               data-original-title="oracle">
                                                                                              oracle
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/sql" class="tag" data-toggle="popover" data-id="1040000000090049"
                                               data-original-title="sql">
                                                                                              sql
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/nosql" class="tag" data-toggle="popover" data-id="1040000000089432"
                                               data-original-title="nosql">
                                                                                              nosql
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/redis" class="tag" data-toggle="popover" data-id="1040000000089431"
                                               data-original-title="redis">
                                                                                              redis
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/mongodb" class="tag" data-toggle="popover" data-id="1040000000089488"
                                               data-original-title="mongodb">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/183/816/1838168166-54cb5552ef5de_small">
                                                                                              mongodb
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/memcached" class="tag" data-toggle="popover" data-id="1040000000089873"
                                               data-original-title="memcached">
                                                                                              memcached
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/postgresql" class="tag" data-toggle="popover" data-id="1040000000090861"
                                               data-original-title="postgresql">
                                                                                              postgresql
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">开发工具</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/vim" class="tag" data-toggle="popover" data-id="1040000000089467"
                                               data-original-title="vim">
                                                                                              vim
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/emacs" class="tag" data-toggle="popover" data-id="1040000000090216"
                                               data-original-title="emacs">
                                                                                              emacs
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ide" class="tag" data-toggle="popover" data-id="1040000000090473"
                                               data-original-title="ide">
                                                                                              ide
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/eclipse" class="tag" data-toggle="popover" data-id="1040000000089509"
                                               data-original-title="eclipse">
                                                                                              eclipse
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/xcode" class="tag" data-toggle="popover" data-id="1040000000089579"
                                               data-original-title="xcode">
                                                                                              xcode
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/intellij-idea" class="tag" data-toggle="popover" data-id="1040000000090560"
                                               data-original-title="intellij-idea">
                                                                                              intellij-idea
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/textmate" class="tag" data-toggle="popover" data-id="1040000000089663"
                                               data-original-title="textmate">
                                                                                              textmate
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/sublime-text" class="tag" data-toggle="popover" data-id="1040000000126097"
                                               data-original-title="sublime-text">
                                                                                              sublime-text
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/visual-studio" class="tag" data-toggle="popover" data-id="1040000000090231"
                                               data-original-title="visual-studio">
                                                                                              visual-studio
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/git" class="tag" data-toggle="popover" data-id="1040000000089459"
                                               data-original-title="git">
                                                                                              git
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/github" class="tag" data-toggle="popover" data-id="1040000000091226"
                                               data-original-title="github">
                                                                                              github
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/svn" class="tag" data-toggle="popover" data-id="1040000000089447"
                                               data-original-title="svn">
                                                                                              svn
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/hg" class="tag" data-toggle="popover" data-id="1040000000183694"
                                               data-original-title="hg">
                                                                                              hg
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/docker" class="tag" data-toggle="popover" data-id="1040000000366352"
                                               data-original-title="docker">
                                                                                              docker
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ci" class="tag" data-toggle="popover" data-id="1040000000372952"
                                               data-original-title="ci">
                                                                                              ci
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">开放平台</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/%E6%96%B0%E6%B5%AA%E5%BE%AE%E5%8D%9A" class="tag" data-toggle="popover" data-id="1040000000125894"
                                               data-original-title="新浪微博">
                                                                                              新浪微博
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E4%BA%BA%E4%BA%BA%E7%BD%91" class="tag" data-toggle="popover" data-id="1040000000089426"
                                               data-original-title="人人网">
                                                                                              人人网
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E5%BE%AE%E4%BF%A1" class="tag" data-toggle="popover" data-id="1040000000090818"
                                               data-original-title="微信">
                                                                                              微信
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E8%85%BE%E8%AE%AF%E5%BE%AE%E5%8D%9A" class="tag" data-toggle="popover" data-id="1040000000152166"
                                               data-original-title="腾讯微博">
                                                                                              腾讯微博
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E7%99%BE%E5%BA%A6" class="tag" data-toggle="popover" data-id="1040000000090113"
                                               data-original-title="百度">
                                                                                              百度
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/facebook" class="tag" data-toggle="popover" data-id="1040000000089479"
                                               data-original-title="facebook">
                                                                                              facebook
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/twitter" class="tag" data-toggle="popover" data-id="1040000000122015"
                                               data-original-title="twitter">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/489/442/489442300-54cb55806c256_small">
                                                                                              twitter
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/paddle" class="tag" data-toggle="popover" data-id="1040000010066962"
                                               data-original-title="paddle">
                                                                                              paddle
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                    </section>
                            <section class="tag-list__item col-md-3">
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">JavaScript 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/javascript" class="tag" data-toggle="popover" data-id="1040000000089436"
                                               data-original-title="javascript">
                                                                                              javascript
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/jquery" class="tag" data-toggle="popover" data-id="1040000000089733"
                                               data-original-title="jquery">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/745/445/745445564-54cb540ec31aa_small">
                                                                                              jquery
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/node.js" class="tag" data-toggle="popover" data-id="1040000000089918"
                                               data-original-title="node.js">
                                                                                              node.js
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/chrome" class="tag" data-toggle="popover" data-id="1040000000089407"
                                               data-original-title="chrome">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/281/209/2812096638-54cb55083b2cc_small">
                                                                                              chrome
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/firefox" class="tag" data-toggle="popover" data-id="1040000000090217"
                                               data-original-title="firefox">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/334/705/334705769-54cb552688a94_small">
                                                                                              firefox
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/internet-explorer" class="tag" data-toggle="popover" data-id="1040000000125504"
                                               data-original-title="internet-explorer">
                                                                                              internet-explorer
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/angular.js" class="tag" data-toggle="popover" data-id="1040000000210853"
                                               data-original-title="angular.js">
                                                                                              angular.js
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/typescript" class="tag" data-toggle="popover" data-id="1040000000365394"
                                               data-original-title="typescript">
                                                                                              typescript
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ecmascript" class="tag" data-toggle="popover" data-id="1040000000432618"
                                               data-original-title="ecmascript">
                                                                                              ecmascript
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/vue.js" class="tag" data-toggle="popover" data-id="1040000004003243"
                                               data-original-title="vue.js">
                                                                                              vue.js
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/react.js" class="tag" data-toggle="popover" data-id="1040000002893277"
                                               data-original-title="react.js">
                                                                                              react.js
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">.NET 开发</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/.net" class="tag" data-toggle="popover" data-id="1040000000089849"
                                               data-original-title=".net">
                                                                                              .net
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/c%23" class="tag" data-toggle="popover" data-id="1040000000089581"
                                               data-original-title="c#">
                                                                                              c#
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/asp.net" class="tag" data-toggle="popover" data-id="1040000000090433"
                                               data-original-title="asp.net">
                                                                                              asp.net
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/visual-studio" class="tag" data-toggle="popover" data-id="1040000000090231"
                                               data-original-title="visual-studio">
                                                                                              visual-studio
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/mvc" class="tag" data-toggle="popover" data-id="1040000000089911"
                                               data-original-title="mvc">
                                                                                              mvc
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/microsoft" class="tag" data-toggle="popover" data-id="1040000000090284"
                                               data-original-title="microsoft">
                                                                                              microsoft
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">云计算</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/%E4%BA%91%E8%AE%A1%E7%AE%97" class="tag" data-toggle="popover" data-id="1040000000090078"
                                               data-original-title="云计算">
                                                                                              云计算
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E5%8F%88%E6%8B%8D%E4%BA%91%E5%AD%98%E5%82%A8" class="tag" data-toggle="popover" data-id="1040000000126696"
                                               data-original-title="又拍云存储">
                                                                                              又拍云存储
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E4%B8%83%E7%89%9B%E4%BA%91%E5%AD%98%E5%82%A8" class="tag" data-toggle="popover" data-id="1040000000137810"
                                               data-original-title="七牛云存储">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/119/227/1192272433-i-1040000000137810_small">
                                                                                              七牛云存储
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/google-app-engine" class="tag" data-toggle="popover" data-id="1040000000126683"
                                               data-original-title="google-app-engine">
                                                                                              google-app-engine
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/sina-app-engine" class="tag" data-toggle="popover" data-id="1040000000126678"
                                               data-original-title="sina-app-engine">
                                                                                              sina-app-engine
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/amazon-web-services" class="tag" data-toggle="popover" data-id="1040000000126680"
                                               data-original-title="amazon-web-services">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/185/450/1854509098-54cb56ae231b7_small">
                                                                                              amazon-web-services
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E7%99%BE%E5%BA%A6%E4%BA%91" class="tag" data-toggle="popover" data-id="1040000000134134"
                                               data-original-title="百度云">
                                                                                              百度云
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E9%87%91%E5%B1%B1%E4%BA%91" class="tag" data-toggle="popover" data-id="1040000000403764"
                                               data-original-title="金山云">
                                                                                              金山云
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E7%BE%8E%E5%9B%A2%E4%BA%91" class="tag" data-toggle="popover" data-id="1040000000383227"
                                               data-original-title="美团云">
                                                                                              美团云
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/%E8%85%BE%E8%AE%AF%E4%BA%91" class="tag" data-toggle="popover" data-id="1040000000309629"
                                               data-original-title="腾讯云">
                                                                                              腾讯云
                                            </a>
                                        </li>
                                                                                                </ul>
                        </div>
                                            <div class="tag-list__itemWraper">
                            <h3 class="h5 tag-list__itemheader">服务器</h3>
                            <ul class="tag-list__itembody taglist--inline multi">
                                                                                                            <li class="tagPopup">
                                            <a href="/t/linux" class="tag" data-toggle="popover" data-id="1040000000089392"
                                               data-original-title="linux">
                                                                                              linux
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/unix" class="tag" data-toggle="popover" data-id="1040000000090646"
                                               data-original-title="unix">
                                                                                              unix
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/ubuntu" class="tag" data-toggle="popover" data-id="1040000000090245"
                                               data-original-title="ubuntu">
                                                                                                  <img src="https://sfault-avatar.b0.upaiyun.com/335/109/3351090335-569f0f26a2a86_small">
                                                                                              ubuntu
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/windows-server" class="tag" data-toggle="popover" data-id="1040000000090609"
                                               data-original-title="windows-server">
                                                                                              windows-server
                                            </a>
                                        </li>
                                                                                                                                                <li class="tagPopup">
                                            <a href="/t/centos" class="tag" data-toggle="popover" data-id="1040000000090249"
                                               data-original-title="centos">
                                                                                              centos
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

	<script src="${ctx}/adm/javascript/pages/tag/categories_tag.js"></script>

<#include "admin/common/body_end.ftl">

</html>
