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
    
    <title>用户部门分配</title>
    
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
  <div class="easyui-panel" title="帐号:${pu.name }(${pu.account})" style="width:400px" data-options="tools:'#tt'">
    
 <input type="checkbox"  onchange="$('#et').tree({cascadeCheck:$(this).is(':checked')})">级联勾选
    <form:form action="savePermission.do" method="post"  commandName="pu" id="mailForm" target="mailiframe">
          
            <table >
               
               <tr>
                    <td> <form:hidden path="id"/></td>
                  
                    <td>
                   <ul id="et"  class="easyui-tree" data-options="url:'viewUserDeptTreeJson.do?id=${pu.id}',method:'get',animate:true,checkbox:true,cascadeCheck:false"></ul>
	
                    </td>
                </tr>
                
                 <tr>
                <td colspan="2">
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="getChecked()">保存</a>
                <!-- x<input type="submit" class="easyui-linkbutton" value="提交"> 
                 <input type="reset" value="清除数据">-->
               </td>
                </tr>
            </table>
       </form:form>
       <form:errors/>
       </div>
       
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
			var nodes = $('#et').tree('getChecked');
			//alert(nodes);;
			var deptid = '';
			for(var i=0; i<nodes.length; i++){
				if (deptid != '') deptid += ',';
				
				deptid += nodes[i].id;
			}
			//alert(menuid);
			
			
 			$.ajax({
url: 'saveUserDeptRelation.do',
type: 'POST',
data:{userid:$('#id').val(),deptid:deptid},
dataType: 'text',
timeout: 10000,
error: function(){$.messager.alert("操作提示", "保存出错");},
success: function(result){$.messager.alert("操作提示", result);}
}); 
		}
	</script>
  </body>
</html>
