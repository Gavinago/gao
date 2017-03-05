<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript" src="js/contacts.js"></script>
    <title><spring:message code="App.Title"></spring:message> </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
</head>
<body style="font-family: Arial; font-size:smaller;">

<table  bgcolor="lightblue" width="750" height="500" align="center" style="border-collapse: collapse;" border="1" bordercolor="#006699" >
    <tr>
        <td align="center"><h3>Edit Contact</h3></td>
    </tr>
  <tr valign="top" align="center">
    <td align="center">
         <form:form action="updateContact.do" method="post" commandName="editContact" id="mainForm">
                <table width="500" style="border-collapse: collapse;" border="0" bordercolor="#006699" cellspacing="2" cellpadding="2">                    
                    <tr>
                        <td width="100" align="right">Id</td>
                        <td width="150">
                        <form:input path="id" readonly="true"/></td>
                        <td align="left">
                        <form:errors path="id" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Name</td>
                        <td>
                        <form:input path="name" readonly="true"/></td>
                        <td align="left">
                        <form:errors path="name" cssStyle="color:red"></form:errors>
                        </td>
                    </tr>
                    
                    <tr>
                        <td width="100" align="right"></td>
                        <td>
                        <form:hidden path="password"/></td>
                        <td align="left">
                        <form:errors path="password" cssStyle="color:red"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">DOB</td>
                        <td><form:input path="dob"/></td>
                        <td align="left"><form:errors path="dob" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Gender</td>
                        <td>                        
                            <form:select path="gender">
                                <form:option value="M" label="Male"/>
                                <form:option value="F" label="Female"/>
                            </form:select>                        
                        </td>
                        <td>
                        </td>                        
                    </tr>
                    <tr>
                        <td width="100" align="right">Address</td>
                        <td><form:input path="address"/></td>
                        <td align="left">
                        <form:errors path="address" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Email</td>
                        <td><form:input path="email" id="email" onblur="checkEmail()"/></td>
                        <td align="left">
                         <span style="color:red" id="email1"></span>
                        <form:errors path="email" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Mobile</td>
                        <td><form:input path="mobile" id="mobile" onblur="checkMobile()"/></td>
                        <td align="left">
                         <span style="color:red" id="mobile1"></span>
                        <form:errors path="mobile" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr valign="bottom">
                        <td colspan="3" align="center">
                        <input type="button"  value="Delete" onclick="javascript:deleteContact('deleteContact.do?id=${editContact.id}');">
                          
                        <input type="submit" name="" value="Save">                        
                          
                        <input type="button"  value="Back" onclick="javascript:go('viewAllContacts.do');">
                        </td>
                    </tr>
                    
                </table>                
        </form:form>
    </td>    
  </tr>
</table>


</body>
</html>
