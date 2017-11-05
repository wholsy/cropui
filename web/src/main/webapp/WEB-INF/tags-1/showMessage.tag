<%@ tag pageEncoding="UTF-8" description="显示操作成功/失败的消息，内容为:message/error" %>
<%@ attribute name="successMessage" type="java.lang.String" required="false" description="成功消息" %>
<%@ attribute name="errorMessage" type="java.lang.String" required="false" description="失败消息" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 
成功信息处理
接收参数 successMessage/message
  --%>
<c:if test="${not empty successMessage}">
    <c:set var="message" value="${successMessage}"/>
</c:if>

<c:if test="${not empty message}">
    <div class="alert alert-success">
        <%-- 
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <span class="icon-ok-sign icon-large"></span>&nbsp; 
         --%>
        ${message}
    </div>
</c:if>

<%-- 
错误信息处理
接收参数 errorMessage/error， errorlist
  --%>
<c:if test="${not empty errorMessage}">
    <c:set var="error" value="${errorMessage}"/>
</c:if>

<c:if test="${not empty error}">
    <div class="alert alert-error">
        <%-- 
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <span class="icon-remove-sign icon-large"></span>&nbsp;  
        --%>
        ${error}
    </div>
    <div style="display:none;" class="a-error-box-desc">
		<c:if test="${not empty solutions}">
			<ul class="errorlisttip">
				<c:forEach items="${solutions}" var="e">
					<li>${e}</li>
	    		</c:forEach>
			</ul>
			
			<script type="text/javascript">
				$(function(){
					//tip
					$('.alert-error').soOverTip({
						type:'target',
						target:'.a-error-box-desc',
						width:250,
						offset:[-20,30]
					});
				});
			</script>
   		</c:if>
	</div>
</c:if>
