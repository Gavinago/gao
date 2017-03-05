<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>back</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="<%=basePath%>/jq/jq2.0.3.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	$(function(){ 
jsonpajax_1();
});


function jsonpajax_1() {

    $.ajax({

        url: "<%=basePath%>backMail.do?pch=4d9bac5e-56b0-4e0c-a9ce-708592c4dcaf-1384307920925",

        type: "get",

        dataType: "jsonp",

        jsonp: "callback",

        success: function(data) {

            //var tt = '';

           // $.each(data, function(k, v) {

            //    tt += k + "：" + v + "<br/>";

           // });

           // $("#divmessage").html(tt);

        }

    });

}
</script>
  </head>
  
  <body>
   
  </body>
</html>
