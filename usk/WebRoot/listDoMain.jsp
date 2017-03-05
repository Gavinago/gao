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
    
    <title>域列表</title>
    
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
<div id="tb" style="padding:5px;height:auto"> 

   <div style="margin-bottom:5px">  
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a> 
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="allresetbtn">重置域已发送量 </a> 
    
   &nbsp;
     <form:form action="" method="post" id="searchForm">
       服务编号(只能输入数字):   
        <input class="easyui-numberbox" type="text" id="sid" name="sid" data-options="">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">Search</a> 
     分页位置调整 &nbsp;
        <select onchange="changeP(this.value)">
            <option>bottom</option>
            <option>top</option>
            <option>both</option>
        </select>
        
        点击 <span style="color:#FF6633">域已发送量</span> 即可排序
   </form:form>
    </div>  
</div> 
   
    
    <table id="tt" class="easyui-datagrid" style="width:520px;height:320px"
            data-options="remoteSort:true,sortName:'domainDayuse',sortOrder:'desc',fit:true,collapsible:true,rownumbers:true,url:'viewAllDoMainJson.do',fitColumns:true,singleSelect:false,pagination:true,toolbar:'#tb'"
            title="数据加载列表" iconCls="icon-save"
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
                <th field="id" >编号</th>
                <th field="domainName" data-options="width:$(this).width()*0.2">域名称</th>
                <th field="domainDaylimit"  align="center">域日限量</th>
                <th field="domainMinutelimit"  align="center">域每分钟限量</th>
                <th field="domainDayuse"  align="center" data-options="sortable:true" >域已发送量</th>
                <th field="remark"  align="center" data-options="width:$(this).width()*0.2">备注</th>
                <th field="sendmailaddress"  align="center">所属服务地址</th>
                <th field="sid"  align="center">所属服务编号</th>
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
 
 
    var toolbar = [{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){

}
		},{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
		
			}
		},'-',{
			text:'删除',
			iconCls:'icon-remove',
			handler:function(){
			
			
			
			
			}
		}];
		
        function changeP(pos){
            $('#tt').datagrid('loadData',[]);
            $('#tt').datagrid({pagePosition:pos});
        }
        $(function (){
        
      
        
       
        
        	 $("#submit_search").click(function () {
	        	 var f=$('#searchForm').form('validate');
	        	 if(f){
	        	 $('#tt').datagrid('load', { "sid":  $('#sid').val()});
	        	 }else{
	        	 
	        	 return f;
	        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        		addPanel('addD.do','新增域');
        	 }); 
        	 
        	  $("#editbtn").click(function () {
        				var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要修改的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能修改一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       editPanel('eidtD.do?id='+parkparams,'修改域_'+parkparams);
			
			}
        	 });
        	 
        	  $("#delbtn").click(function () {
        					var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要删除的数据!","info");
                return;
            //}else if(rows.length>1){
           //  $.messager.alert("系统提示","为了慎重起见一次只能删除一条数据!","info");
             //   return;
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                  		//$.messager.confirm('提示', '你确定要这么做吗？', function(r){
				//if (r){
					delDatas(parkparams,'#tt','deleteDoMain.do');
				//}
			//});
			}
        	 });
        	 
        	  $("#allresetbtn").click(function () {
        	   $.messager.confirm('提示信息','确定要重置吗？',function(b){
        	    if(b){
        	    	 $.ajax({url: 'updateDoMainDayUseAllReset.do', 
							type: 'POST', 
							data:{}, 
							dataType: 'text', 
							timeout: 15000, 
							error: function(){alert('Error ');}, 
							success: function(result){
								$.messager.alert('信息提示',result,'info');
								$('#tt').datagrid('reload');
							} 
							});
        	    }
        	  });
        	  
        	  		   
							
							
        	   });
        	 
        });
    </script>
    
    
  </body>
</html>
