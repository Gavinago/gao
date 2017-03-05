<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@
	taglib uri="http://shiro.apache.org/tags" prefix="shiro" %><%@ 
	taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %><%@
	taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %><!DOCTYPE html>
<html>
<head>
<meta property="qc:admins" content="3510175104463740463757374047" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%
	//设定页面的相对基路径
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">

<!-- 公共css部分 -->	
<%@include file="inc_headercss.jsp" %>
<!-- 头部css嵌入 -->
<decorator:getProperty property="page.headercss"/>

<!-- Title 嵌入 -->
<title><decorator:title default="${site_name}" /></title>
<!-- 头部脚本嵌入 -->
<decorator:getProperty property="page.headerscript"/>
</head>
<body>

<!-- 主菜单 -->	
<%@include file="inc_menu_back.jsp" %>
<!-- 用户注销对话框 -->
<%@include file="inc_logout.jsp" %>

<div class="container-fluid">
	<decorator:body />
</div>

<%@include file="inc_copyright.jsp" %>

<!-- bootstrap、jquery等优化性能后置脚本部分 -->	
<%@include file="inc_footer.jsp" %>
<!-- 尾部脚本嵌入 -->
<decorator:getProperty property="page.footerscript"/>

</body>
</html>