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
    
    <title>新增角色</title>
    
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

    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mymailform'" style="width:500px;height:280px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mymailform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form action="saveRole.do" method="post"  commandName="pr" id="mailForm" target="mailiframe">
            <table>
                <tr>
                    <td>角色名称:</td>
                  
                    <td>
                    <input type="hidden" name="state" value="1"/>
                    
                    <input class="easyui-validatebox" type="text" name="name" data-options="required:true">
                  
                    </td>
                </tr>
               
                 
                <tr>
                    <td>备注:</td>
                    <td><textarea name="remark" style="height:60px;"></textarea> </td>
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
	        url:'saveRole.do',  
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
