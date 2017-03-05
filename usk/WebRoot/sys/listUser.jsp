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
    
    <title>用户列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../common/common.jsp" %>
<script type="text/javascript">
 
    </script>
  </head>
  
  <body style="visibility: visible;">
<div id="tb" style="padding:5px;height:auto"> 
<div style="margin-bottom:5px">  
 <shiro:hasPermission name="/addUser.do"> 
  <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增</a>  
</shiro:hasPermission>
       
        &nbsp; &nbsp;
           <shiro:hasPermission name="/eidtUserPwd.do"> 
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改用户密码</a>
     </shiro:hasPermission>  

        
        &nbsp; &nbsp;
        <shiro:hasPermission name="/deleteUser.do"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a> 
        </shiro:hasPermission>
         &nbsp; &nbsp;
         <shiro:hasPermission name="/aps.do"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="addrolebtn">分配角色</a>
        </shiro:hasPermission>
         <shiro:hasPermission name="/udAllocation.do"> 
         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="adddeptbtn">分配部门</a>
         </shiro:hasPermission>
         &nbsp; &nbsp;
       
          
 
         <shiro:hasPermission name="/resetUserPwd.do"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="resetpwdbtn">重置密码(重置后密码123456)</a>
        </shiro:hasPermission> 
        
        
        </div>  
   <form:form action="" method="post" id="searchForm">
       用户账号:   
        <input class="easyui-validatebox" type="text" id="account" name="account" data-options="">
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">Search</a> 
        
       &nbsp;    分页位置调整
        <select onchange="changeP(this.value)">
            <option>bottom</option>
            <option>top</option>
            <option>both</option>
        </select>
        </form:form>
  </div> 

  
    <table id="tt" class="easyui-datagrid" style="width:auto;height:320px"
            data-options="fit:true,collapsible:true,rownumbers:true,url:'viewAllUserJson.do',fitColumns:false,singleSelect:false,sortOrder:'desc',sortName:'clintname',pagination:true,toolbar:'#tb'"
            title="数据加载列表" iconCls="icon-save"
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
           		<th  data-options="field:'id'">编号</th>
                <th  data-options="field:'name'">姓名</th>
                <th  data-options="field:'account'">账号</th>
                
                    <th  data-options="field:'rolename'">用户角色名称</th>
                    
                <th   align="center" data-options="field:'createtime'">创建时间
                </th>
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
 var mes="确定要删除选中行吗";
 if(address=='resetUserPwd.do'){
 mes="确定要重置密码么？";
 }
 
  if(address=='setAcceptUser.do'||address=='setAcceptDkUser.do'||address=='setAcceptCkUser'){
 mes="确定要设置？";
 }
 
   if(address=='CancelAcceptUser.do'||address=='CancelAcceptUserDk.do'||address=='CancelAcceptUserCk.do'){
 mes="确定要取消该用户的短信通知？";
 }
  $.messager.confirm('提示信息',mes,function(b){
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
        
       	
        
        	 $("#submit_search").click(function () {
        	 var f=$('#searchForm').form('validate');
        	 if(f){
        	 $('#tt').datagrid('load', { "account":  $('#account').val()});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addUser.do','新增用户');
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
                
       editPanel('eidtUserPwd.do?id='+parkparams,'修改用户密码_'+parkparams);
			
			}
        	 });
        	 
        	   $("#addrolebtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要分配的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能分配一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       editPanel('aps.do?id='+parkparams,'分配用户角色_'+parkparams);
			
			}
        	 });
        	 
        	 
        	  $("#adddeptbtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要分配的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能分配一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       editPanel('udAllocation.do?id='+parkparams,'分配用户部门_'+parkparams);
			
			}
        	 });
        	 
        	  $("#delbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要删除的数据!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','deleteUser.do');
			}
        	 });
        	
        	
        	 $("#resetpwdbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要重置的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','resetUserPwd.do');
			}
        	 });
        	
        	
        		 $("#smsbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要接受的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','setAcceptUser.do');
			}
        	 });
        	 
        	 
        	  $("#smsDkbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要接受的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','setAcceptDkUser.do');
			}
        	 });
        	 
        	  $("#smsCancelDkbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要取消的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','CancelAcceptUserDk.do');
			}
        	 });
        	 
        	 
        	   $("#smsCkbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要接受的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','setAcceptCkUser.do');
			}
        	 });
        	 
        	  $("#smsCancelCkbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要取消的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','CancelAcceptUserCk.do');
			}
        	 });
        	 
        	 	 $("#cancelbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要接受的用户!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','CancelAcceptUser.do');
			}
        	 });
        	 
        	 	  $("#addcompanybtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要分配的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能分配一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       editPanel('distributionCompany.do?id='+parkparams,'分配用户公司_'+parkparams);
			
			}
        	 });
        	 
        	 
        	   $("#addContactsbtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要分配的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能分配一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       editPanel('saveUserContactsRelation.do?id='+parkparams,'分配用户联系人_'+parkparams);
			
			}
        	 });
        	 
        	 
        	 
        });
    </script>
    
    
  </body>
</html>
