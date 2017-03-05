<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<%@include file="../../common/common.jsp" %>
 </head>
  
  <body>
    
    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mymailform'" style="width:500px;height:425px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mymailform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form action="eidtFrontUser.do" method="post"  commandName="editFrontUserForm" id="mailForm" target="mailiframe">
           <h1><form:label path="account" >帐号:${editFrontUserForm.account} </form:label></h1>
            <table>
                <tr>
                    <td>用户名称:</td>
                  
                    <td>
              <form:hidden path="id"/>
              
                  <form:input path="name" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>性别:</td>
                  
                    <td>
                 <form:select path="sex" class="easyui-combobox" style="width:200px;">  
                   <form:option value="1">男</form:option>  
                   <form:option value="0">女</form:option>  
               </form:select> 
                    </td>
                </tr>
                  <tr>
                    <td>座机:</td>
                  
                    <td>
                  <form:input path="phone" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                 <tr>
                    <td>手机:</td>
                  
                    <td>
                  <form:input path="mobile" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                  
                    <td>
                  <form:input path="email" class="easyui-validatebox" data-options="required:true,validType:'email'"/>
                    </td>
                </tr>
                <tr>
                    <td>地址:</td>
                  
                    <td>
                  <form:input path="address" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                  
                    <td>
                  <form:input path="age" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>状态:</td>
                  
                    <td>
                     <form:select path="state" class="easyui-combobox" style="width:200px;">  
                   <form:option value="1">启用</form:option>  
                   <form:option value="0">禁用</form:option>  
               </form:select> 
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
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
	        url:'eidtFrontUser.do',  
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
