<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/contacts.js"></script>
</head>
<body style="font-family: Arial; font-size:smaller;">
    <center>
    <form action="searchContacts.do" method="post">        
            <table style="border-collapse: collapse;" border="0" bordercolor="#006699" width="500">
            <tr><td colspan="2" align="right"> 
            	Visiting User：${sessionScope.login.name }<br/>
				<a href="javascript:exit('exit.do');">Exit</a>
			</td></tr>
            <tr>
                <td>Enter Contact Name</td>
                <td><input type="text" name="name"/>
                    <input type="submit" value="Search"/>
            </td></tr>
        </table>
    </form>
    
    <table style="border-collapse: collapse;" border="1" bordercolor="#006699" width="500">
        <tr bgcolor="lightblue">
            <th>Id</th>
            <th>Name</th>            
            <th>Address</th>
            <th>Email</th>    
            <th>Mobile</th>
            <th>Operation</th>
        </tr>
        <c:if test="${empty SEARCH_CONTACTS_RESULTS_KEY}">
        <tr>
            <td colspan="4">No Results found</td>
        </tr>
        </c:if>
        <c:if test="${! empty SEARCH_CONTACTS_RESULTS_KEY}">
            <c:forEach var="contact" items="${SEARCH_CONTACTS_RESULTS_KEY}">        
            <tr>
                <td><c:out value="${contact.id}"></c:out></td>
                <td><c:out value="${contact.name}"></c:out></td>
                <td><c:out value="${contact.address}"></c:out> </td>
                <td><c:out value="${contact.email}"></c:out> </td>
                <td><c:out value="${contact.mobile}"></c:out></td>
                <td>
                     <a href="updateContact.do?id=${contact.id}">Edit</a>
                     <a href="javascript:deleteContact('deleteContact.do?id=${contact.id}');">Delete</a>
                </td>
            </tr>
            </c:forEach>
        </c:if>                
    </table>  
    <div align="center"><input type="button" value="Add Contact" onclick="javascript:go('saveContact2.do');"/> </div>  
    </center>
</body>
</html>
