<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page
	import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>
<%@ page
	import="org.springframework.security.core.AuthenticationException"%>
<jsptag:override name="title">登入</jsptag:override>
<jsptag:override name="content">
	<%@ include file="/common/messages.jsp"%>
	<form action="${base}/j_spring_security_check" method="post"
		name="form1" class="nice" id="form1">
	<h2>登入</h2>
	<p class="left"><label>用户名:</label> <input name="j_username"
		type="text" class="inputText" id="j_username"
		<c:if test="not empty param.error">
						value='<%=session
								.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</c:if> />
	<label> 密码: </label> <input name="j_password" type="password"
		class="inputText" id="j_password" /><label>验证码:</label> <input
		type="text" id='j_captcha' name='j_captcha' class="inputText" /><img
		alt="点击更换验证码" src="${base}/images/jcaptcha.jpg"
		onclick="javascript:this.src='${base}/images/jcaptcha.jpg?date='+new Date().getTime();"
		style="cursor: hand;" /><br />
	<input type="checkbox" name="_spring_security_remember_me" />两周内记住我 <br
		clear="all" />
	<button type="submit" class="green">Log in</button>
	<br />
	<a href="${base}/system/sysUser/forgot">Forgot your username or
	password?</a></p>
	<div class="clear"></div>
	</form>
</jsptag:override>
<%@ include file="/WEB-INF/base/base.jsp"%>
