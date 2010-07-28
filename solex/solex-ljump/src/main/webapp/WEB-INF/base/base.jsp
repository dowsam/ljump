<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title><jsptag:block name="title">厦门松霖集团</jsptag:block>--厦门松霖集团</title>
<link href="${base}/css/reset.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${base}/css/text.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${base}/css/960.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="${base}/css/green.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet" type="text/css" href="${base}/css/style.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/sdmenu.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${base}/js/jqgrid/css/ui.jqgrid.css" />
<script src="${base}/js/jquery-1.3.2.min.js"></script>
<script src="${base}/js/jquery-ui-1.7.1.custom.js"></script>
<script src="${base}/js/components.js"></script>
<script src="${base}/js/effects.js"></script>
<script type="text/javascript" src="${base}/js/sdmenu.js"></script>
<script type="text/javascript" src="${base}/js/rest.js"></script>
<script src="${base}/js/jqgrid/js/grid.locale-en.js" type="text/javascript"></script>
<script src="${base}/js/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
<jsptag:block name="head">
</jsptag:block>
<!--[if IE 6]>
<style type="text/css" >
p.error span, p.info span, p.notice span, p.success span { 
	postion:static;
    margin-right:15px;
}
p.todoitem a.close {
	margin-right:10px;
}
button.green, button.brown {
	padding:0px !important;
}
</style>
<![endif]-->
</head>
<body>
<!-- start .grid_12 - the container -->
<div class="container_12">
<div class="grid_5"><img alt="image"
	src="${base}/images/loogo.jpg" width="380" height="100" border="0" /></div>
<!-- end .grid_5 -->
<div class="grid_7" id="login_data">
<p><strong><security:authorize
	ifNotGranted="ROLE_ANONYMOUS">Welcome <security:authentication
		property="principal.loginName" />!</security:authorize></strong><br />
<a href="#"><strong>样式</strong></a><strong> | <a href="#">更改设置</a>
| <a href="${base}/j_spring_security_logout">退出</a></strong></p>
</div>
<!-- end .grid_7 - HEADER -->
<div class="clear"></div>
<div class="grid_3">
<div class="widget" id="todo">
<h3 class="todo">导航菜单</h3>
<!--  <div id="treeBox"></div>-->

<div id="my_menu" class="sdmenu"></div>
</div>
</div>
<div class="grid_9 cnt" id="left920"><jsptag:block name="content">base_body_content</jsptag:block>
</div>
<div class="clear"></div>
<!--FOOTER START-->
<div class="grid_12 cnt" id="footer">This is a basic Footer -
Copyright and other information can go here. | <a href="#">link 1</a> |
<a href="#">link 2</a> | <a href="#">link 3</a> | <a href="#">link 4</a></div>
<!--FOOTER END-->
<div class="clear"></div>
</div>
<security:authorize ifNotGranted="ROLE_ANONYMOUS">
	<script type="text/javascript">
	//var tree = new dhtmlXTreeObject("treeBox", "100%", "100%", 0);
	//tree.setImagePath("${base}/js/dhtmlxTree/imgs/csh_vista/");
	//tree.setXMLAutoLoading("${base}/menu");
	//tree.loadXML("${base}/menu");
	//tree.attachEvent("onClick", function(id) {
	//var href=tree.getUserData(id, "href");
	//if(!(href=="" || href==null || href==undefined)){
	///window.location.replace("${base}" +href );
	//}
	//return true;
	//});
</script>
	<script type="text/javascript">
	var myMenu;
	$.get("${base}/index/menu",function(data){
		$("#my_menu").html(data);
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	});
</script>
</security:authorize>
</body>
</html>