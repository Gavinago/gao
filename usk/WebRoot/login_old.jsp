<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title><spring:message code="App.Title"></spring:message> </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/contacts.js"></script>
    <title><spring:message code="App.Title"></spring:message></title>

  </head>
  
  <body style="font-family: Arial; font-size:smaller;" >
    <form:form action="login.do" method="post" commandName="login" modelAttribute="login">
    	 <table bgcolor="lightblue" align="center" style="border-collapse: collapse;" border="0" bordercolor="#006699" width="450">
    	<tr><td colspan="3" align="center">Login</td></tr>
    	<tr>
             <td width="100" align="right">Name</td>
             <td width="150">
             <form:input path="name" cssStyle="width:150; height:20"/></td>
             <td align="left">
             <form:errors path="name" cssStyle="color:red"></form:errors>
             </td>
         </tr>
         <tr>
             <td width="100" align="right">Password</td>
             <td width="150">
             <form:password path="password" cssStyle="width:150; height:20"/></td>
             <td align="left">
             <form:errors path="password" cssStyle="color:red"></form:errors>
             </td>
         </tr>
         <tr>
             <td colspan="2" align="right">
             <input type="submit" name="" value="Login">
             <input type="button"  value="Regist" onclick="javascript:go('saveContact.do');">
             </td>
             <td></td>
         </tr>
    	</table>
    </form:form>
  </body>
</html>
