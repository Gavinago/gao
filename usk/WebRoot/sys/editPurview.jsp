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
    
    <title>角色菜单权限分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../common/common.jsp" %>
	<!-- <script type="text/javascript" src="<%=basePaths %>js/jquery.scrollto.js"></script> -->
	<style type="text/css">

</style>
  </head>
  
  <body   >
  <div style=" overflow:scroll;height:380px;">  
  <div class="easyui-panel" title="角色名称:${editPermissionsForm.rolename }" style="" data-options="tools:'#tt'">
    
 <input type="checkbox"  onchange="$('#et').tree({cascadeCheck:$(this).is(':checked')})">级联勾选
    <form:form action="savePermission.do" method="post"  commandName="editPermissionsForm" id="mailForm" target="mailiframe">
          
            <table >
               
               <tr>
                    <td> <form:hidden path="roleid"/></td>
                  
                    <td>
                   <ul id="et"  class="easyui-tree" data-options="url:'viewPermissionsMenuJson.do?id=${editPermissionsForm.roleid}',method:'get',animate:true,checkbox:true,cascadeCheck:false"></ul>
	
                    </td>
                </tr>
                
                 <tr>
                <td colspan="2">
               
                <!-- x<input type="submit" class="easyui-linkbutton" value="提交"> 
                 <input type="reset" value="清除数据">-->
               </td>
                </tr>
            </table>
       </form:form>
       <form:errors/>
       </div>
       
       </div>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getChecked()">保存</a>
       <div id="tt">
		<a href="javascript:void(0)" class="icon-save" onclick="javascript:getChecked()"></a>

	</div>
	
	
	

       <script type="text/javascript">
              
       
       
       
        $(function () {
          /*   $("#et").tree({
   
                onCheck: function (node, checked) {
    
                
                
                    if (checked) {
                        var parentNode = $("#et").tree('getParent', node.target);
                        if (parentNode != null) {
                            $("#et").tree('check', parentNode.target);
                        }
                    } else {
                        var childNode = $("#et").tree('getChildren', node.target);
                        if (childNode.length > 0) {
                            for (var i = 0; i < childNode.length; i++) {
                                $("#et").tree('uncheck', childNode[i].target);
                            }
                        }
                    }
                }
            }); */
        });
       
       
    

       
		function getChecked(){
		//$("#positions").ScrollTo(800);
		progress();
		
			var nodes = $('#et').tree('getChecked');
			//alert(nodes);;
			var menuid = '';
			for(var i=0; i<nodes.length; i++){
				if (menuid != '') menuid += ',';
				
				menuid += nodes[i].id;
			}
			//alert(menuid);
			
	
 			$.ajax({
url: 'savePermission.do',
type: 'POST',
data:{roleid:$('#roleid').val(),menuid:menuid,state:1},
dataType: 'text',
timeout: 20000,
error: function(){
//$.messager.alert("操作提示", "保存出错");
$.messager.show({
				title:'操作提示',
				msg:"保存出错",
				showType:'slide',
				style:{
					right:'',
					bottom:''
				}
			});

},
success: function(result){

$.messager.progress('close');
/* $.messager.show({
				title:'操作提示',
				msg:result,
				showType:'fade',
				style:{
					right:'',
					bottom:''
				}
			}) */;
$.messager.alert("操作提示", result);
}


}); 



		
		
		}
		
		
		function progress(){
		
			 $.messager.progress({
				title:'正在处理请稍候',
				msg:'处理中...',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				},
				interval:300 
			}); 
/* 			setTimeout(function(){
				$.messager.progress('close');
			},5000) */
		}
	</script>
  </body>
</html>
