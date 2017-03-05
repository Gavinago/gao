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
    
    <title>邮件日志列表</title>
    
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
       收件地址:   
       
         <input class="easyui-validatebox" type="text" id="rmailaddress" name="rmailaddress" data-options="">
         
         唯一操作号:
          <input class="easyui-validatebox" type="text" id="uniquemailid" name="uniquemailid" data-options="" style="width:320px;">
      批次号:
          <input class="easyui-validatebox" type="text" id="batchnumber" name="batchnumber" data-options="" style="width:320px;"> 
           <br>  状态:
          <input class="easyui-validatebox" type="text" id="status" name="status" data-options="" style="width:120px;">         
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
            data-options="fit:true,collapsible:true,rownumbers:true,url:'viewAllMailLogJson.do',fitColumns:true,singleSelect:false,sortOrder:'desc',sortName:'domainName',pagination:true,toolbar:'#tb'"
            title="数据加载列表" iconCls="icon-save"
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
                <th field="id" >编号</th>
                <th field="rmailaddress" data-options="width:$(this).width()*0.1">收件地址</th>
                <th field=smailaddress data-options="width:$(this).width()*0.15">发送服务器</th>
                <th field="sendtime"  align="center" data-options="width:$(this).width()*0.15">发送时间</th>
                 <th field="subject"  align="center" data-options="width:$(this).width()*0.25" >邮件主题</th>
                <th field="status"  align="center" data-options="">发送状态</th>
                 <th field="batchnumber"   align="center" data-options="">批次号</th>
               <!--  <th field="remark"  align="center" data-options="width:$(this).width()*0.3">状态描述</th>
                <th field="batchnumber"   align="center" data-options="">批次号</th>
                <th field="uniquemailid"   align="center" data-options=""> 发送操作唯一号</th> -->
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
        addPanel("maillogview.do?id="+rowData['id'],'邮件日志'+rowIndex);
       			}
          });
        
       
        
        	 $("#submit_search").click(function () {
	        	 var f=$('#searchForm').form('validate');
	        	 //batchnumber
	        	 if(f){
	        	 $('#tt').datagrid('load', { "rmailaddress":  $('#rmailaddress').val(),
	        	 "uniquemailid":  $('#uniquemailid').val(),"batchnumber":  $('#batchnumber').val(),
	        	"status":$('#status').val() });
	        	 }else{
	        	 
	        	 return f;
	        	 }
         		
                
            });
        
        	
        });
    </script>
    
    
  </body>
</html>
