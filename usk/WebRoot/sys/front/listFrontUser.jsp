<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>会员列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../../common/common.jsp" %>
<script type="text/javascript">
 
    </script>
  </head>
  
  <body style="visibility: visible;">
<div id="tb" style="padding:5px;height:auto"> 
<div style="margin-bottom:5px">  
 <shiro:hasPermission name="/addFrontUser.do"> 
  <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增</a>  
</shiro:hasPermission>
       
        &nbsp; &nbsp;
        <!--   <shiro:hasPermission name="/eidtFrontUser.do"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改</a>
        </shiro:hasPermission>  -->
         <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="frontuserContactsbtn">分配会员联系人</a>
        &nbsp; &nbsp;
        <shiro:hasPermission name="/deleteFrontUser.do"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a> 
        </shiro:hasPermission>
         &nbsp; &nbsp;

       <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="addcompanybtn">分配公司</a>
        
        
         
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="resetpwdbtn">重置密码(重置后密码123456)</a>
        
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

  
    <table id="tt" class="easyui-datagrid" style="width:480px;height:320px"
            data-options="fit:true,collapsible:true,rownumbers:true,url:'viewAllFrontUserJson.do',fitColumns:true,singleSelect:false,sortOrder:'desc',sortName:'clintname',pagination:true,toolbar:'#tb'"
            title="数据加载列表" iconCls="icon-save"
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
           		<th  data-options="field:'id'">编号</th>
                
                <th  data-options="field:'account',width:$(this).width()*0.1">账号</th>
               <!-- <th  data-options="field:'name',width:$(this).width()*0.2">用户名称</th>
                <th   align="center" data-options="field:'sex',width:$(this).width()*0.2">性别</th>
                <th  align="center" data-options="field:'mobile',width:$(this).width()*0.2">手机</th>
                <th   align="center" data-options="field:'description',width:$(this).width()*0.2">描述</th>
                 -->
                 <th  data-options="field:'cname',width:$(this).width()*0.3">公司名字</th>
                  <th  data-options="field:'ccname',width:$(this).width()*0.1">联系人</th>
                   <th  data-options="field:'tel',width:$(this).width()*0.2">联系人电话</th>
                    <th  data-options="field:'phone',width:$(this).width()*0.2">联系人手机</th>
                <th   align="center" data-options="field:'createtime',width:$(this).width()*0.2">创建时间
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
 if(address=='resetFrontUserPwd.do'){
 mes="确定要重置密码么？";
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
        			addPanel('addFrontUser.do','新增会员');
        	 }); 
        	 
        	  $("#frontuserContactsbtn").click(function () {
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
                
       editPanel('saveFrontUserContacts.do?id='+parkparams,'修改会员联系人关系_'+parkparams);
			
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
					delDatas(parkparams,'#tt','deleteFrontUser.do');
			}
        	 });
        	
        	
        	 $("#resetpwdbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要重置的会员!","info");
                return;

            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
					delDatas(parkparams,'#tt','resetFrontUserPwd.do');
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
                
       editPanel('distributionFrontCompany.do?id='+parkparams,'分配会员公司_'+parkparams);
			
			}
        	 });
        	 
        	 
        });
    </script>
    
    
  </body>
</html>
