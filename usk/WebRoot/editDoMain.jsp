<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>
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
		<%@include file="common/common.jsp" %>
 </head>
  
  <body>

    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mymailform'" style="width:500px;height:260px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mymailform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form action="updateSMail.do" method="post"  commandName="editDoMainAttribute" id="mailForm" target="mailiframe">
            <table>
                 <tr>
                    <td>域名称:</td>
                  
                    <td>
                    
                     <form:hidden path="id"/>
                   <form:select path="domainName" class="easyui-combobox" style="width:200px;">  
                   <form:option value="qq">qq</form:option>  
                   <form:option value="163">163</form:option>  
                    <form:option value="yahoo">雅虎邮箱</form:option>  
                    <form:option value="sina">新浪邮箱</form:option> 
                    <form:option value="gmail">gmail</form:option> 
                    <form:option value="hotmail">hotmail</form:option>  
                    <form:option value="126">126</form:option> 
                     <form:option value="sohu">sohu</form:option> 
                     
               </form:select> 
                
                    </td>
                </tr>
               <tr>
                    <td>日限量:</td>
                  
                    <td>
                     <form:input path="domainDaylimit"  class="easyui-validatebox" data-options="required:true"/>
                
                  
                    </td>
                </tr>
                 <tr>
                    <td>域每日已发送量:</td>
                  
                    <td>
                     <form:input path="domainDayuse"  class="easyui-validatebox" data-options="required:true"/>
                
                  
                    </td>
                </tr>
                 <tr>
                    <td>每分钟限量:</td>
                  
                    <td>
                   
                  <form:input path="domainMinutelimit"  class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                
                <tr>
                    <td>所属邮件服务地址:</td>
                  
                    <td>
                   
		 <form:select path="sid" items="${selectSendMail}" itemLabel="mailAddress" itemValue="id"/>  
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td>
                    
                     <form:textarea path="remark" cols="20" rows="3"/>
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
	        url:'updateDoMain.do',  
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
