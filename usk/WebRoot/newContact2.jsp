<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript" src="js/contacts.js"></script>
    <title><spring:message code="App.Title"></spring:message> </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="js/register.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<body style="font-family: Arial; font-size:smaller;">

<table  bgcolor="lightblue" width="350" height="500" align="center" style="border-collapse: collapse;" border="1" bordercolor="#006699" >
    <tr>
        <td align="center"><h3>Add Contact</h3></td>
    </tr>
    <tr valign="top" align="center">
    <td align="center">
         <form:form action="saveContact2.do" method="post" commandName="newContact2" id="mainForm">
                <table width="500" style="border-collapse: collapse;" border="0" bordercolor="#006699" cellspacing="2" cellpadding="2">    
                     <tr>
                        <td width="100" align="right">Name</td>
                        <td width="150">
                        <form:input path="name" id="name" onblur="checkUser()" cssStyle="width:150; height:20"/></td>
                        <td align="left">
                        <span style="color:red" id="name1"></span>
                        <form:errors path="name" cssStyle="color:red"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Password</td>
                        <td width="150">
                        <form:password path="password" id="password" onblur="checkPassword()" cssStyle="width:150; height:20"/></td>
                        <td align="left">
                        <span style="color:red" id="password1"></span>
                        <form:errors path="password" cssStyle="color:red"></form:errors>
                        </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">DOB</td>
                        <td><form:input path="dob" cssStyle="width:150; height:20"/></td>
                        <td align="left"><form:errors path="dob" cssStyle="color:red"></form:errors></td>
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
                        <td><form:input path="address" cssStyle="width:150; height:20"/></td>
                        <td align="left">
                        <form:errors path="address" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Email</td>
                        <td><form:input path="email" id="email" onblur="checkEmail()" cssStyle="width:150; height:20"/></td>
                        <td align="left">
                         <span style="color:red" id="email1"></span>
                        <form:errors path="email" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td width="100" align="right">Mobile</td>
                        <td><form:input path="mobile" id="mobile" onblur="checkMobile()" cssStyle="width:150; height:20"/></td>
                        <td align="left">
                        <span style="color:red" id="mobile1"></span>
                        <form:errors path="mobile" cssStyle="color:red"></form:errors>  </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
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
