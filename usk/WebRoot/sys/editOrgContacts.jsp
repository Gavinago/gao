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
    
    <title>修改公司联系人</title>
    
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
    
    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mymailform'" style="width:500px;height:425px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mymailform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form action="updateOrgContacts.do" method="post"  commandName="editCompanycontactsForm" id="mailForm" target="mailiframe">
               <table>
                <tr>
                    <td>联系人名称:</td>
                  
                    <td>
                    <form:hidden path="id"/>
                    <form:input class="easyui-validatebox" type="text" path="name" data-options="required:true"/>
                  
                    </td>
                </tr>
               <tr>
                    <td>身份证号码:</td>
                  
                    <td>
                    
                    <form:input class="easyui-validatebox" type="text" path="idcard" data-options="required:true"/>
                  
                    </td>
                </tr>
                
                <tr>
                    <td>性别:</td>
                  
                    <td>
                    
                     <form:select class="easyui-combobox" path="sex" style="width:130px;" data-options="required:true">
		<form:option value="1">男</form:option>
		<form:option value="0">女</form:option>
		</form:select>
                  
                    </td>
                </tr>
                <tr>
                    <td>固定电话:</td>
                  
                    <td>
                    
                    <form:input class="easyui-validatebox" type="text" path="tel" data-options="required:true"/>
                  
                    </td>
                </tr>
                <tr>
                    <td>传真:</td>
                  
                    <td>
                    
                    <form:input class="easyui-validatebox" type="text" path="fax" data-options="required:true"/>
                  
                    </td>
                </tr>
                <tr>
                    <td>手机:</td>
                  
                    <td>
                    
                    <form:input class="easyui-validatebox" type="text" path="phone" data-options="required:true"/>
                  
                    </td>
                </tr>
                  <tr>
                    <td>邮箱:</td>
                  
                    <td>
                    
          <form:input class="easyui-validatebox" type="text" path="email" data-options="required:true,validType:'email'"/>
                  
                    </td>
                </tr>
                 <tr>
                    <td>联系人类型:</td>
                  
                    <td>
                  <form:select class="easyui-combobox" path="type" style="width:200px;">
		<form:option value="0">会员联系人</form:option>
		<form:option value="1">系统联系人</form:option>
		</form:select>
                    </td>
                </tr>
                <tr>
                    <td>在线联系qq:</td>
                    <td>
 <form:input class="easyui-numberbox" path="qq" data-options="required:true"/>
          
                 
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><form:textarea path="remark" style="height:60px;"></form:textarea> </td>
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
	        url:'updateOrgContacts.do',  
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
