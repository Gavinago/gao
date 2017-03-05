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
    
    <title>数据统计</title>
    
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
<table class="easyui-datagrid" title="该批次客户打开邮件记录" style="width:700px;height:250px"
			data-options="rownumbers:true,toolbar:'#tjdiv',fit:true,fitColumns: true,singleSelect:true,collapsible:true,url:'viewtjJson.do?batchnumber=${batchnumber}',method:'get'">
		<thead>
			<tr>
				<th field="id" >编号</th>
                 <th field="rmailId"  align="center">收件地址</th>
                
                <th field="opentime" data-options="width:$(this).width()*0.2">打开时间</th>
                <th field="ip" data-options="width:$(this).width()*0.05">浏览ip</th>
                <th field="smailId"  align="center">发送操作唯一号</th>
                <th field="batchnumber"  align="center">批次号</th>
			</tr>
		</thead>
	</table>
  
  <div id="tjdiv" style="padding:5px;height:auto">
  <h1>批次号:${batchnumber}</h1>
  首次打开数：${fc}    &nbsp; 首次打开率 :${firstRate }  &nbsp; 总打开数：${sumopen }   &nbsp;总打开率：${sumRate }
  </div>
  </body>
</html>
