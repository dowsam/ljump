<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.solex.com.cn/jsptag" prefix="jsptag"%>
<%@ page isELIgnored="false"%>
<c:set var="base"
	value="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
