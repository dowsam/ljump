<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑用户</title>
</head>
<body>
<h1>编辑用户</h1>
<form:form method="put" action="${base}/system/sysUser/${user.id}"
	modelAttribute="user" cssClass="nice">
	<p class="left"><form:label path="loginName" for="loginName">工号:</form:label>
	<form:input path="loginName" cssClass="inputText" /><form:errors
		path="loginName" /> <form:label path="passWord" for="passWord">密码:</form:label><form:password
		path="passWord" showPassword="true" cssClass="inputText" /><form:errors
		path="passWord" /><form:label path="disabled">是否启用:</form:label><form:checkbox
		path="disabled" /><form:label path="email" for="email">邮箱:</form:label><form:input
		path="email" cssClass="inputText" /><form:errors path="email" /></p>
	<p class="right"><form:label path="userName" for="userName">用户名:</form:label><form:input
		path="userName" cssClass="inputText" /><form:errors path="userName" /><label>重复密码:</label><input
		type="password" name="oldPassWord" id="oldPassWord" class="inputText"
		value="${user.passWord}" /><form:label path="accountLocked">是否禁用:</form:label><form:checkbox
		path="accountLocked" /><label>Description:</label> <textarea cols=""
		rows="5" class="inputText_wide"></textarea> <br clear="all" />
	<button class="green" type="submit">Submit this form</button>

	</p>
	<div class="clear"></div>
</form:form>
</body>
</html>