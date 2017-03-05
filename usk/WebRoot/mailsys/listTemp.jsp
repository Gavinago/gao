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
    
    <title>模版列表</title>
    
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
   <div style="margin-bottom:5px">  
   <!--  
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改</a> 
        &nbsp; &nbsp; 
        
        -->
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a>  
          &nbsp; &nbsp; 
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-view" plain="true" id="viewbtn">查看</a> 
   &nbsp;
     分页位置调整 &nbsp;
        <select onchange="changeP(this.value)">
            <option>bottom</option>
            <option>top</option>
            <option>both</option>
        </select>
   
    </div>  
  
      
   
</div> 
 
    
    <table id="tt" class="easyui-datagrid" style="width:720px;height:320px"
            data-options="fit:true,collapsible:true,rownumbers:true,url:'viewAllTempJson.do',fitColumns:true,singleSelect:false,sortOrder:'desc',sortName:'createtime',pagination:true,toolbar:'#tb'"
            title="数据加载列表" iconCls="icon-save"
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
                <th field="id" >编号</th>
                <th field="tempname" data-options="width:$(this).width()*0.2">模版名称</th>

                <th field="createtime"  align="center" data-options="width:$(this).width()*0.2">创建时间</th>
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
    //$.post(address,{delId:0,ids:delList},function(result){
  //  var results=eval(result);
  //  $.messager.alert('信息提示',results.resultMsg,'warning');
    // $(listId).datagrid('reload');
     
   // });
   
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
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        	 addPanel('addS.do','新增发送邮件服务');
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
		                
		       editPanel('eidtS.do?id='+parkparams,'修改发送邮件服务_'+parkparams);
					
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
		            
							delDatas(parkparams,'#tt','deleteTemp.do');

					}
        	   });
        	   
        	   
        	    $("#viewbtn").click(function () {
        	    var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			   var parks = [];
		            if(rows == 0){
		                $.messager.alert("系统提示","请选择要查看的数据!","info");
		                return;
		            }else if(rows.length>1){
		             $.messager.alert("系统提示","一次只能查看一条数据!","info");
		                return;
		            }else{
		                   for(var i=0;i<rows.length;i++){
		                      parks.push(rows[i].id);
		                  }
		                  var parkparams = parks.join(',');
		                
		       editPanel('seeTemp.do?id='+parkparams,'查看模版_'+parkparams);

					}
        	    });
        });
    </script>
    
    
  </body>
</html>
