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
    
    <title>修改公司</title>
    
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
      
         <form:form enctype="multipart/form-data"  action="updateComPany.do" method="post"  commandName="editCompanyinforsForm" id="mailForm" target="mailiframe">
          
           <table>
                <tr>
                    <td>公司名称:</td>
                  
                    <td>
                  
                     <form:hidden path="id"/>
                    <form:input style="width:300px;hight:100px;" class="easyui-validatebox" type="text" path="name" data-options="required:true"/>
                  
                    </td>
                </tr>
               <tr>
                    <td>法定代表人:</td>
                  
                    <td>
                   <form:input style="width:200px;hight:100px;" class="easyui-validatebox" type="text" path="legalrep" data-options="required:true"/>
                    </td>
                </tr>
                 <tr>
                    <td>企业性质:</td>
                  
                    <td>
                           <form:select class="easyui-combobox" path="nob" style="width:200px;" data-options="required:true">
		<form:option value="0">企业单位</form:option>
		<form:option value="1">事业单位或社会团体</form:option>
		<form:option value="2">个体经营</form:option>
		<form:option value="3">个人</form:option>
		</form:select>
                  
                    </td>
                </tr>
                 <tr>
                    <td>公司营业执照:</td>
                  
                    <td>
                        <img src="<%=request.getContextPath() %>/getCblProPhotoById.do?id=${editCompanyinforsForm.id}&width=300&height=300"/>  
                    <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="cbl" data-options="required:false"/>
                  
                    </td>
                </tr>
                 <tr>
                    <td>国税地税登记:</td>
                  
                    <td>
                     <img src="<%=request.getContextPath() %>/gettaxtaxregProPhotoById.do?id=${editCompanyinforsForm.id}&width=300&height=300"/> 
                    <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="taxtaxreg" data-options="required:false"/>
                  
                    </td>
                </tr>
                 <tr>
                    <td>组织结构代码证:</td>
                  
                    <td>
                     <img src="<%=request.getContextPath() %>/getorgstrucodeProPhotoById.do?id=${editCompanyinforsForm.id}&width=300&height=300"/> 
                    <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="orgstrucode" data-options="required:false"/>
                  
                    </td>
                </tr>
                 <tr>
                    <td>开户银行:</td>
                  
                    <td>
                   <form:input style="width:200px;hight:100px;" class="easyui-validatebox" type="text" path="bank" data-options="required:true"/>
                    </td>
                </tr>
                  <tr>
                    <td>银行帐号:</td>
                  
                    <td>
                   <form:input style="width:200px;hight:100px;" class="easyui-validatebox" type="text" path="bankaccount" data-options="required:true"/>
                    </td>
                </tr>
                 <tr>
                    <td>联系地址:</td>
                  
                    <td>
                   <form:input style="width:300px;hight:100px;" class="easyui-validatebox" type="text" path="address" data-options="required:true"/>
                    </td>
                </tr>
                 <tr>
                    <td>邮编:</td>
                  
                    <td>
                   <form:input style="width:200px;hight:100px;" class="easyui-validatebox" type="text" path="zipcode" data-options="required:true"/>
                    </td>
                </tr>
                 <tr>
                    <td>公司类型:</td>
                  
                    <td>
                  <form:select class="easyui-combobox" path="type" style="width:200px;">
		<form:option value="0">会员公司</form:option>
		<form:option value="1">系统公司</form:option>
		</form:select>
                    </td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><form:textarea path="description" style="height:60px;" cols="28"></form:textarea> </td>
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
	        url:'updateComPany.do',  
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
