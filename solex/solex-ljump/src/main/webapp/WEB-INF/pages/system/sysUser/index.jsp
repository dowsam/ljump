<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<jsptag:override name="title">用户管理</jsptag:override>
<jsptag:override name="content">
	<h1>用户管理</h1>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		id="data">
		<tr>
			<th width="60" scope="col"><a href="#">工号</a></th>
			<th width="60" scope="col"><a href="#">密码</a></th>
			<th width="60" scope="col"><a href="#">名称</a></th>
			<th width="60" scope="col"><a href="#">邮箱</a></th>
			<th width="60" scope="col"><a href="#">是否启用</a></th>
			<th width="80" scope="col"><a href="#">是否锁定</a></th>
			<th width="80" scope="col"><a href="#">权限</a></th>
			<th width="40" scope="col"><a href="#">操作</a></th>
		</tr>
		<c:forEach var="user" items="${users}" varStatus="status">
			<tr>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.loginName}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>></td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.userName}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.email}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>><input
					type="checkbox"
					<c:if test="${user.disabled}">checked="checked"</c:if> /></td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>><input
					type="checkbox"
					<c:if test="${user.accountLocked}">checked="checked"</c:if> /></td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.roleListName}</td>
				<td width="40" align="center"><a
					href="${base}/system/sysUser/${user.id}/edit"><img
					src="${base}/images/icon_edit.gif" alt="Edit" width="16"
					height="16" border="0" /></a>&nbsp;&nbsp;<a
					href="${base}/system/sysUser/${user.id}"
					onclick="doRestDelete(this,'确定删除吗?');return false;"><img
					src="${base}/images/icon_delete.gif" alt="Delete" width="16"
					height="16" border="0" /></a></td>
			</tr>
		</c:forEach>
	</table>
</jsptag:override>
<%@ include file="/WEB-INF/base/base.jsp"%>