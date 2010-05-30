<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>错误</title>
<script language="javascript">
		function showDetail()
		{
			document.getElementById('detail_error_msg').style.display = "";
		}
	</script>
</head>
<body>
<div id="content">
<%
		//Exception from JSP didn't log yet ,should log it here.
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		LoggerFactory.getLogger(requestUri).error(exception.getMessage(),exception);
	%>

<h3>System Runtime Error: <br /><%=exception.getMessage()%></h3>
<br />

<button onclick="history.back();">Back</button>
<br />

<p><a href="#" onclick="showDetail();">Administrator click here
to get the detail.</a></p>

<div id="detail_error_msg" style="display: none"><pre>
<%exception.printStackTrace(new java.io.PrintWriter(out));%>
</pre></div>
</div>
</body>
</html>