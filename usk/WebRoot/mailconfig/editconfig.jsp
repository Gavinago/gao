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
    
    <title>修改域</title>
    
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

    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mymailform'" style="width:500px;height:260px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mymailform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form action="updateIc.do" method="post"  commandName="editIc" id="mailForm" target="mailiframe">
            <table>
                 <tr>
                    <td>配置值:</td>
                    <td>
                    <form:hidden path="id"/>
                    <form:hidden path="configkey"/>
                   <form:input path="configvalue"  class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
               <tr>
                    <td>配置描述:</td>
                    <td>
                     <form:textarea path="description" cols="20" rows="3"/>
                    </td>
                </tr>
                
                <tr>
                <td colspan="2">
                <input type="submit" class="easyui-linkbutton" value="提交">
                 <input type="reset" value="清除数据">
                </td>
                </tr>
            </table>
       </form:form>
       <form:errors/>
        </div>
        <div style="text-align:center;padding:5px">
    
        </div>
    </div>
	</div>
   

    <script>
$(function(){ 

       $('#mailForm').form({  
	        url:'updateIc.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
});

        function clearForm(){
            $('#mailForm').form('clear');
        }
    </script>
  </body>
</html>
