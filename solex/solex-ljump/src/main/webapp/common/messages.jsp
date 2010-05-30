<%@ include file="/common/taglibs.jsp"%>
<%-- Error Messages --%>
<c:if test="${not empty springMessages}">
	<p class="message"><c:forEach var="msg"
		items="${springMessages}">
		${msg}<br />
	</c:forEach></p>
</c:if>

<%-- Info Messages --%>
<c:if test="${not empty springErrors}">
	<p class="error"><c:forEach var="errorMsg"
		items="${springErrors}">
		${errorMsg}<br />
	</c:forEach></p>
</c:if>



