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
    
    <title>修改发送邮件</title>
    
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

    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mymailform'" style="width:500px;height:280px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mymailform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form action="updateSMail.do" method="post"  commandName="seditmail" id="mailForm" target="mailiframe">
            <table>
                <tr>
                    <td>邮件地址:</td>
                  
                    <td>
                     
                     <form:hidden path="dayuseLimit"/>
              <form:hidden path="id"/>
                  <form:input path="mailAddress" id="mailAddress" class="easyui-validatebox" data-options="required:true,validType:'email'"/>
                    </td>
                </tr>
                <tr>
                    <td>邮件密码:</td>
                    <td>
                    <form:input path="mailPwd" id="mailPwd" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>邮件端口:</td>
                    <td>
                    
                      <form:input path="mailPort" id="mailPort" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>邮件smtp地址:</td>
                    <td>
                    
                    <form:input path="maiSmtpAddress" id="maiSmtpAddress" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td>
                    
                    <form:textarea path="mailRemark" cols="20" rows="3"/>
                    </td>
                </tr>
                 <tr>
                    <td>日发送量限制:</td>
                    <td>
                     <form:input path="sendcount" id="sendcount" class="easyui-validatebox" data-options="required:true"/>
                    </td>
                </tr>
                <tr>
                    <td>任务标识:</td>
                    <td>	
                      <form:hidden path="id"/>
                   <form:select path="taskid" class="easyui-combobox" style="width:200px;">  
                   <form:option value="0">0</form:option>  
                   <form:option value="1">1</form:option>  
                    <form:option value="2">2</form:option>  
                    <form:option value="3">3</form:option>  
                     <form:option value="4">4</form:option>  
                      <form:option value="5">5</form:option>  
                       <form:option value="6">6</form:option>  
                        <form:option value="7">7</form:option>  
                         <form:option value="8">8</form:option>  
                         <form:option value="9">9</form:option>
                         <form:option value="10">10</form:option>
               </form:select> 
                   
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
       <!--  
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
            -->
        </div>
    </div>
	</div>
   

    <script>
$(function(){ 

       $('#mailForm').form({  
	        url:'updateSMail.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
});
        function submitForm(){
	       // var v=$("#mailForm").form('validate');
			//if(!v){
			 //  return false;
			//}
          // $('#mailForm').form('submit');
           //mailForm.submit();
          // $("#mailForm").submit();
          // location.href="${pageContext.request.contextPath}/viewAllSendMail.do";
	    



        }
        function clearForm(){
        
            $('#mailForm').form('clear');
        }
    </script>
  </body>
</html>
