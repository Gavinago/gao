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
    
    <title>用户分配角色</title>
    
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
      
         <form:form action="updateUserRole.do" method="post"  commandName="editPusReUserRoleForm" id="mailForm" target="mailiframe">
           <h1>帐号:${account} </h1>
            <table>
                <tr>
                    <td>角色名称:</td>
                  
                    <td>
              <form:hidden path="userid"/>
              	<input id="roleid"  value="${roleid }" class="easyui-combotree"
              	 data-options="url:'viewRolesJson.do?id=${editPusReUserRoleForm.userid }',method:'get',required:true" style="width:200px;">
                <!--  <select id="roleid" class="easyui-combotree" data-options="url:'viewRolesJson.do?id=${editPusReUserRoleForm.userid }',method:'get'"
                 multiple style="width:200px;"></select>-->
                    </td>
                </tr>
               
                <tr>
                <td colspan="2">
                <input type=button class="easyui-linkbutton" value="提交" onclick="gettrees()">
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
	        url:'updateUserRole.do',  
	        onSubmit:function(){  

	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
});


function gettrees(){
	if($('#roleid').combotree('getText')==''){
	$.messager.alert("操作提示", "您还没有选择角色"); 
	return false;
	}
	
	//alert($('#roleid').combotree('getValues'));
	var roleidarray=$('#roleid').combotree('getValues');
	var roleidstrs=roleidarray+"";
	//alert(roleids);
$.messager.confirm('提示信息','确定要分配这几个角色么: '+$('#roleid').combotree('getText'),function(b){
   if(b){
   	  			$.ajax({
url: 'updateUserRole.do',
type: 'POST',
data:{roleids:roleidstrs,userid:$('#userid').val()},
dataType: 'text',
timeout: 10000,
error: function(){$.messager.alert("操作提示", "保存出错");},
success: function(result){$.messager.alert("操作提示", result);}
}); 
   }
   
   });
}
        function clearForm(){
        
            $('#mailForm').form('clear');
        }
    </script>
  </body>
</html>
