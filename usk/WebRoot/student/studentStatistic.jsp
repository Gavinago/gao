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
    
    <title>信息统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
table.gridtable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
<%@include file="../common/common.jsp" %>
  </head>
  
  <body>
  <form action="stuStatistics.do" method="post" name="tjForm">
  按生源分类统计:    	<select class="easyui-combobox" name="syfl" id="syfl" style="width:200px;">
			<option value="">未选择</option>
			<option value="0">全年级</option>
			<option value="1">本区学生信息查询(同安区）</option>
			<option value="2">本市内非本区学生信息查询</option>
			<option value="3">省内非本市学生信息查询</option>
			<option value="4">省外学生信息查询</option>
		</select>  
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">开始统计</a> 
    </form>
    <div style="margin:10px 0;"></div>
	<div class="easyui-panel" title="分类统计信息-${title }" style="width:400px">
		<div style="padding:10px 0 10px 60px">
		
	<table  class="gridtable">
	<tr><th>年龄</th><th>性别</th><th>总数</th></tr>
    <c:forEach var="stu" items="${listStudentBean}">
     <tr>
      <td>
   ${stu.age}
      </td>
      <td>
      
		
		 <c:choose>
       <c:when test="${stu.sex==0}">
		女
       </c:when>
       <c:when test="${stu.sex==1}">
                        男
       </c:when>
       <c:otherwise>
                       合计
       </c:otherwise>
</c:choose>
      </td>
      <td>
       ${stu.sums}
      </td>
     </tr>
    </c:forEach>
   </table>
		
		</div>
		</div>
		
  </body>
   <script type="text/javascript">
    $(function (){
        
       	
        
        	 $("#submit_search").click(function () {
	        	 var f=$('#syfl').combobox('getValue');
	        	 if(f==''){
		        	 $.messager.alert("系统提示","请选择要统计的生源!","info");
	                 return;
	        	 }
	        	 tjForm.submit();
	        	
	        	 
            });
            
            });
    </script>
</html>
