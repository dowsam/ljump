<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<jsptag:override name="title">计划任务</jsptag:override>
<jsptag:override name="content">
	<h1>计划任务管理</h1>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		id="data">
		<tr>
			<th width="60" scope="col"><a href="#">Trigger 名称</a></th>
			<th width="60" scope="col"><a href="#">Trigger 分组</a></th>
			<th width="60" scope="col"><a href="#">任务描述</a></th>
			<th width="60" scope="col"><a href="#">任务处理类</a></th>
			<th width="60" scope="col"><a href="#">下次执行时间</a></th>
			<th width="80" scope="col"><a href="#">上次执行时间</a></th>
			<th width="80" scope="col"><a href="#">优先级</a></th>
			<th width="40" scope="col"><a href="#">Trigger 状态</a></th>
			<th width="40" scope="col"><a href="#">Trigger 类型</a></th>
			<th width="40" scope="col"><a href="#">开始时间</a></th>
			<th width="40" scope="col"><a href="#">结束时间</a></th>
			<th width="40" scope="col"><a href="#">操作</a></th>
		</tr>
		<c:forEach var="data" items="${data}" varStatus="status">
			<tr>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.trigger_name}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.trigger_group}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.job_details_description}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.job_details_job_class_name}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.next_fire_time}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.prev_fire_time}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.priority}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.trigger_state}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.trigger_type}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.start_time}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${data.END_TIME}</td>
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