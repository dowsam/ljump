<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<jsptag:override name="title">短信队列管理</jsptag:override>
<jsptag:override name="content">
	<h1>短信队列管理,队列大小:${fn:length(data)}</h1>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		id="data">
		<tr>
			<th width="60" scope="col"><a href="#">序列号</a></th>
			<th width="60" scope="col"><a href="#">主叫号码</a></th>
			<th width="60" scope="col"><a href="#">接收号码</a></th>
			<th width="60" scope="col"><a href="#">定时发送时间</a></th>
			<th width="60" scope="col"><a href="#">是否返回状态报告</a></th>
			<th width="80" scope="col"><a href="#">短信内容</a></th>
		</tr>
		<c:forEach var="user" items="${data}" varStatus="status">
			<tr>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.seq}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.srcNum}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.dstNums}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.scheduleTime}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.needRpt}</td>
				<td <c:if test="${status.index % 2 == 0}">class="odd"</c:if>>${user.msgStr}</td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
	window.setInterval(function() {
		window.location.reload(true);
	}, 10000);
	//-->
</script>
</jsptag:override>
<%@ include file="/WEB-INF/base/base.jsp"%>