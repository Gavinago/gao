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
    
    <title>邮件发送统计列表</title>
    
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
<div id="tb" style="padding:5px;height:auto"> 

  
   &nbsp;
     <form:form action="" method="post" id="searchForm">
   
       批次号:
          <input class="easyui-validatebox" type="text" id="batchnumber" name="batchnumber" data-options="" style="width:320px;">
         
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">Search</a> 
     分页位置调整 &nbsp;
        <select onchange="changeP(this.value)">
            <option>bottom</option>
            <option>top</option>
            <option>both</option>
        </select>
   </form:form>
    </div>  
</div> 
   
    
    <table id="tt" class="easyui-datagrid" style="width:520px;height:320px"
            data-options="fit:true,collapsible:true,rownumbers:true,url:'viewStatisticsJson.do',fitColumns:true,singleSelect:false,sortOrder:'desc',sortName:'domainName',pagination:true,toolbar:'#tb'"
            title="数据加载列表" 
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
                <th field="id" >编号</th>
                <th field="batchnumber"  align="center" data-options="width:$(this).width()*0.2">批次号</th>

            </tr>
        </thead>
    </table>
    <script type="text/javascript">
    function addPanel(url,title){
	    var tab=parent.$('#tabs').tabs('exists',title);
			if(tab){
				//若存在，则直接打开
				parent.$('#tabs').tabs('select',title);
			}else{
				 parent.$('#tabs').tabs('add',{
	                title: title,
	                content: "<iframe width='100%' height='100%'  id='iframe' frameborder='0' scrolling='auto'  src='"+url+"'></iframe>",
	                closable: true
	            });
				
			}
           
       }
       
       //title 标题_编号
      function editPanel(url,title){
	     parent.$('#tabs').tabs('add',{
	                title: title,
	                content: "<iframe width='100%' height='100%'  id='iframe' frameborder='0' scrolling='auto'  src='"+url+"'></iframe>",
	                closable: true
	            });
           
       }
       
       
        //post提交批量删除信息并弹出处理结果
 function delDatas(delList,listId,address){
  $.messager.confirm('提示信息','确定要删除选中行吗？',function(b){
   if(b){
   
    $.ajax({url: address, 

type: 'POST', 

data:{ids:delList}, 

dataType: 'json', 

timeout: 15000, 

error: function(){alert('Error ');}, 

success: function(result){

$.messager.alert('信息提示',result.resultMsg,'info');
$(listId).datagrid('reload');


} 

});

   
   
   }
  });
 }
 
 

		
        function changeP(pos){
            $('#tt').datagrid('loadData',[]);
            $('#tt').datagrid({pagePosition:pos});
        }
        $(function (){
        
       $('#tt').datagrid({
            onDblClickRow: function (rowIndex, rowData) {
            
            //alert(rowIndex+"  "+rowData['batchnumber']);
            		//location.href ="tj.do?batchnumber="+rowData['batchnumber'];
        addPanel("tj.do?batchnumber="+rowData['batchnumber'],'邮件状态统计'+rowIndex);
       			}
          }); //datagrid----
          
          
          
        	 $("#submit_search").click(function () {
	        	 var f=$('#searchForm').form('validate');
	        	 if(f){
	        	 $('#tt').datagrid('load', { "batchnumber":  $('#batchnumber').val()});
	        	 }else{
	        	 
	        	 return f;
	        	 }
         		
                
            });
        
        	
        });
    </script>
    
    
  </body>
</html>
