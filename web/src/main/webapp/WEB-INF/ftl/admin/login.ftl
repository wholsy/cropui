<#include "admin/common/header_start.ftl">
<#include "admin/common/header_end.ftl">

<body class="login">
	<div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form class="form-horizontal form-label-left input_mask">
            	<header>
					<h1>
				        <i class="ace-icon fa  fa-laptop green"></i>
				        <span class="red">Codealy</span>
				        <span class="grey" id="id-text2">Blog</span>
				        ( ${profile} )
				    </h1>
				    <h4 class="blue" id="id-company-text">&copy; 
				    	<a href="${ctx}">blog.codealy.com</a>
				    </h4>
				</header>
							
				<#-- 用户名 -->
				<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
                	<input type="text" class="form-control has-feedback-left" title="用户名"
                			id="username" name="username" placeholder="username"
                			data-validate-length-range="18" data-validate-words="5" required="required">
                	<span class="fa fa-user form-control-feedback left" aria-hidden="true"></span>
              	</div>
              	
				<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
					<input type="password" class="form-control has-feedback-left" title="密码"
							id="password" name="password" placeholder="password" required="required" >
					<span class="fa fa-key fa-fw form-control-feedback left" aria-hidden="true"></span>
				</div>
              	
              	<div class="form-group input-group">
	                <input type="text" class="form-control" name="smsCode" placeholder="登录验证码"  style="width: 150px;" />
	                &nbsp;&nbsp;
	                <input type="button" class="btn btn-primary" 
	                	value="获取验证码" onclick="getValidateCode(this)"/>
	        	</div>

                <span id="showMesage" style="width: 100%;display: none"></span>

                <#--  登录 -->
                <div class="form-group">
                <button id="btn_login" type="button" class=" btn btn-sm btn-primary submit">
                    <i class="ace-icon fa fa-key"></i>
                    <span class="bigger-110">Login</span>
                </button>
                <#--
                <a class="reset_pass" href="#">Lost your password?</a>
                 -->
                </div>
			  
                <div class="clearfix"></div>

                <div class="separator">
                    <p class="change_link">New to site?
                        <#--
                        <a href="#signup" class="to_register"> Create Account </a>
                        -->
                    </p>

                    <div class="clearfix"></div>
                    <br/>
                </div>
            </form>
          </section>
          
          <#include "admin/common/include/footer_for_login.ftl">
        </div>
      </div>
    </div>
    
	<#include "admin/common/include/endjs.ftl">
	
	<script src="${ctx}/adm/javascript/pages/login.js"></script>
	
	<script type="text/javascript">
        function getValidateCode(obj) {
            sendSmsCode(obj);
        }

        function sendSmsCode(obj){
            captical.show(obj, "重新发送"); /* 发送中 */

            var username = $('#username').val();
            var password = $('#password').val();

            $.ajax({
                type : 'POST',
                url:"/validate/login/get",
                data : {"username": username,
                         "password" : password
                },
                success : function(data)
                {
                    if(data.code == '00000000') {
                        // none is unvisible
                        document.getElementById("showMesage").style.display="";
                        $("#showMesage").html("<font color='green'>" + data.message + "</font>")
                    } else {
                        captical.clear(obj);
                        document.getElementById("showMesage").style.display="";
                        $("#showMesage").html("<font color='red'>" + data.message + "</font>")
                    }
                },
                error : function()
                {
                    captical.clear(obj);
                    alert("出现未知错误!");
                }
            });
        }
    </script>
    
</body>
</html>
