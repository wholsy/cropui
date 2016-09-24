<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:if test="${(not empty REGISTER) && REGISTER}">
	<a class="toregister" href="/cropui/register/" title="欢迎您的注册，请点击">注册</a>
</c:if>