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
  <form action="studentStaticsAll.do" method="post" name="tjForm">
  
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">开始统计</a> 
    </form>
   
   <div align="center">
<div style="width:600px;">
   
   
<div style="float:left;width:100px;border:1px solid #ff0000;">
<h2>分类统计信息-全年级</h2>		
	<table  class="gridtable">
	<tr><th>年龄</th><th>性别</th><th>总数</th></tr>
    <c:forEach var="stu" items="${listStudentBeanAll}">
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
		
		<div style="float:left;width:100px;border:1px solid #ff0000;">
		<h2>分类统计信息-本区学生信息查询(同安区）</h2>
		
	<table  class="gridtable">
	<tr><th>年龄</th><th>性别</th><th>总数</th></tr>
    <c:forEach var="stu" items="${listStudenttaq}">
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
		
		<div style="float:left;width:100px;border:1px solid #ff0000;">
	<h2>分类统计信息-本市内非本区学生信息查询</h2>
		
	<table  class="gridtable">
	<tr><th>年龄</th><th>性别</th><th>总数</th></tr>
    <c:forEach var="stu" items="${listStudentBeanbsfbq}">
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


<div style="float:left;width:100px;border:1px solid #ff0000;">
 <h2>分类统计信息-省内非本市学生信息查询</h2>
		
	<table  class="gridtable">
	<tr><th>年龄</th><th>性别</th><th>总数</th></tr>
    <c:forEach var="stu" items="${listStudentBeansnfbs}">
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

<div style="float:left;width:100px;border:1px solid #ff0000;">
<h2>分类统计信息-省外学生信息查询</h2>
		
	<table  class="gridtable">
	<tr><th>年龄</th><th>性别</th><th>总数</th></tr>
    <c:forEach var="stu" items="${listStudentBeansw}">
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
</div>
		
		
  </body>
   <script type="text/javascript">
    $(function (){
        
       	
        
        	 $("#submit_search").click(function () {
	        	 
	        	 tjForm.submit();
	        	
	        	 
            });
            
            });
    </script>
</html>
