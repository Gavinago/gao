<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+path+"/";
%>
<base href="<%=basePath %>">

</head>
<body>
<shiro:hasPermission name="sys:config">	
<div class='panel-body'>
	<div class='row'>
		<div class='col-xs-12 col-sm-3'>
			<jsp:include page="/leftNavigation/dynamenu.do"/>
		</div>
		<div id="ajaxcontent" class="col-xs-12 col-sm-9">
	     	<img src="<c:url value="/assets/img/wait5.gif"/>" />
		</div>
	</div>
</div>
</shiro:hasPermission>
<shiro:lacksPermission name="sys:config">
	<div class="alert alert-danger" role="alert">
		 <div class="row">
		 	<div class="col-xs-12" style="margin-bottom:20px">
			 	<h2>没有该功能的权限，请联系管理员</h2>
		 	</div>
		 </div>
		 <div class="row">
		 	<div class="col-xs-12">
		 		<a class="btn btn-primary" href="<c:url value="/index/login.do"/>">更换其他用户登录</a>
		 		<a class="btn btn-primary" href="<c:url value="/back/home.do"/>">返回首页</a>
		 	</div>
		 </div>
	</div>
</shiro:lacksPermission>
<input id="changepass" type="hidden" >
<script type="text/javascript">
$(function(){
	var flag = ${change==true};
	if(flag){
		$("#changepassword").click();
		$("#changepass").click();
	}
})
</script>
</body>
</html>