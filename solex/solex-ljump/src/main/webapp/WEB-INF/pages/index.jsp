<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<jsptag:override name="title">首页</jsptag:override>
<jsptag:override name="content">
	<h1>Headers</h1>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		id="data">
		<tr>
			<th width="80" scope="col"><a href="#">Key</a></th>
			<th width="400" scope="col"><a href="#">Value</a></th>
		</tr>
		<c:forEach var="aParam" items="${pageContext.request.headerNames}"
			varStatus="index">
			<tr>
				<td <c:if test="${index.index % 2 == 0}">class="odd"</c:if>>${aParam}</td>
				<td <c:if test="${index.index % 2 == 0}">class="odd"</c:if>>${headerValues[aParam][0]}</td>
			</tr>
		</c:forEach>

	</table>
	<h1>Session infos</h1>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		id="data">
		<tr>
			<th width="80" scope="col"><a href="#">Key</a></th>
			<th width="400" scope="col"><a href="#">Value</a></th>
		</tr>
		<tr>
			<td>Id</td>
			<td>${pageContext.session.id}</td>
		</tr>
		<tr>
			<td class="odd">New</td>
			<td class="odd">${pageContext.session.new}</td>
		</tr>
		<tr>
			<td>Creation time</td>
			<td><%=new java.util.Date(session.getCreationTime())%></td>
		</tr>
		<tr>
			<td class="odd">Last accessed time</td>
			<td class="odd"><%=new java.util.Date(session.getLastAccessedTime())%></td>
		</tr>
		<tr>
			<td>Max Inactive interval</td>
			<td>${pageContext.session.maxInactiveInterval}</td>
		</tr>
		<tr>
			<td class="odd">Servlet context</td>
			<td class="odd">${pageContext.session.servletContext}</td>
		</tr>

	</table>
</jsptag:override>
<%@ include file="/WEB-INF/base/base.jsp"%>