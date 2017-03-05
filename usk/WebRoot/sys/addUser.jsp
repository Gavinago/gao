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
    
    <title>新增用户</title>
    
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
      
         <form:form action="saveUser.do" method="post"  commandName="psu" id="mailForm" target="mailiframe">
            <table>
               <!--  <tr>
                    <td>用户名称:</td>
                  
                    <td>
                   
                    
                    <input class="easyui-validatebox" type="text" name="name" data-options="required:true">
                  
                    </td>
                </tr> -->
                 <tr>
                    <td>用户名:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="text" name="name" data-options="required:true">
                  
                    </td>
                </tr>
               <tr>
                    <td>登录帐号:</td>
                  
                    <td>
                     <input type="hidden" name="state" value="1"/>
                    <input class="easyui-validatebox" type="text" name="account" data-options="required:true">
                  
                    </td>
                </tr>
                 <tr>
                    <td>登录密码:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="password" name="pwd" id="pwd" data-options="required:true">
                  
                    </td>
                </tr>
                 <tr>
                    <td>确认登录密码:</td>
                  
                    <td>
                   <input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"  validType="equalTo['#pwd']" invalidMessage="两次输入密码不匹配"/>
                  
                    </td>
                </tr>
                <!--  <tr>
                    <td>性别:</td>
                  
                    <td>
                    <select class="easyui-combobox" name="sex" style="width:130px;" data-options="required:true">
		<option value="1">男</option>
		<option value="0">女</option>
		</select>
                  
                    </td>
                </tr>
                <tr>
                    <td>座机:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="text" name="phone" data-options="required:true">
                  
                    </td>
                </tr>
                 <tr>
                    <td>手机:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="text" name="mobile" data-options="required:true">
                  
                    </td>
                </tr>
                 <tr>
                    <td>邮箱:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="text" name="email" data-options="required:true,validType:'email'">
                  
                    </td>
                </tr>
                 <tr>
                    <td>地址:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="text" name="address" data-options="required:true">
                  
                    </td>
                </tr>
                <tr>
                    <td>年龄:</td>
                  
                    <td>
                    <input class="easyui-validatebox" type="text" name="age" data-options="required:true">
                  
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><textarea name="description" style="height:60px;"></textarea> </td>
                </tr> -->
                <tr>
                <td colspan="2">
                 <shiro:hasPermission name="/saveUser.do"> 
<input type="submit" class="easyui-linkbutton" value="提交"> 
</shiro:hasPermission>
                
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
   

    <script type="text/javascript">
    /**
 * @author 
 */
$.extend($.fn.validatebox.defaults.rules, { 
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
           
});

$(function(){ 

       $('#mailForm').form({  
	        url:'saveUser.do',  
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
