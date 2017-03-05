<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>重量加成</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@include file="../common/common.jsp" %>
  </head>
  
  <body>
  <form action="updateMySysPwd.do" method="post" id="ff" onsubmit="return submitForm();">
  
 新密码
   <input class="easyui-validatebox" type="text" name="pwd" data-options="required:true"></input>

    
     <input type="submit"  value="提交">
    </form>
    
  <font color="red">${message }</font>  
  
  
  	<script>
  	$(function(){ 

 
    
      });
  	function submitForm(){
	  	var f=$('#ff').form('validate');
	  	if(f){
	  		return true;
	  	}else{
	  		return false;
	  	}
			//$('#ff').form('submit');
		}
	</script>
  </body>
</html>
