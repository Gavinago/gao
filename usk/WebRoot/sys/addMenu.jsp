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
    
    <title>新增菜单</title>
    
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
      
         <form:form action="saveMenu.do" method="post"  commandName="menu" id="mailForm" target="mailiframe">
            <table>
                <tr>
                    <td>名称:</td>
                  
                    <td>
                    <input type="hidden" name="state" value="1"/>
                    
                    <input style="width:200px;hight:100px;" class="easyui-validatebox" type="text" name="name" data-options="required:true">
                  
                    </td>
                </tr>
               <tr>
                    <td>父菜单:</td>
                  
                    <td>
                   <input id="menupid" name="menupid" class="easyui-combotree" data-options="url:'viewLeftMenuJson.do',method:'get'" style="width:200px;">
                  
                    </td>
                </tr>
                 <tr>
                    <td>类型:</td>
                  
                    <td>
                           <select class="easyui-combobox" name="type" style="width:130px;" data-options="required:true">
		<option value="1">导航菜单</option>
		<option value="0">功能权限</option>
		</select>
                  
                    </td>
                </tr>
                 <tr>
                    <td>url:</td>
                  
                    <td>
                    <input class="easyui-validatebox" style="width:200px;hight:100px;" type="text" name="pageurl" data-options="required:true">
                  
                    </td>
                </tr>
                  <tr>
                    <td>菜单排序:</td>
                  
                    <td>
                     <input class="easyui-validatebox" style="width:200px;hight:100px;" type="text" value="0" name="sortfiled" data-options="required:true">
                 (按照升序排列)
                    </td>
                </tr>
                <tr>
                    <td>描述:</td>
                    <td><textarea name="description" style="height:60px;"></textarea> </td>
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
	        url:'saveMenu.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
    
    
    $('#menupid').combotree({   
panelHeight:320

}); 
});

        function clearForm(){
        
            $('#mailForm').form('clear');
        }
    </script>
  </body>
</html>
