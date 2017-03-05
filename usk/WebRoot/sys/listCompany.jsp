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
    
    <title>公司信息列表</title>
    
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
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a>  
    </div>  
   <form:form action="" method="post" id="searchForm">
      公司名称:   
        <input class="easyui-validatebox" type="text" id="name" name="name" data-options="">
    <!--     公司类型： <select class="easyui-combobox" name="type" id="type" style="width:200px;">
        <option value="">全部</option>
		<option value="0">会员公司</option>
		<option value="1">系统公司</option>
		</select>-->
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
            data-options="fit:true,collapsible:true,rownumbers:true,url:'viewAllCompanyJson.do',fitColumns:false,singleSelect:false,sortOrder:'desc',sortName:'clintname',pagination:true,toolbar:'#tb'"
            title="数据加载列表" 
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
           		<th  data-options="field:'id'">编号</th>
                <th  data-options="field:'name'">公司名称</th>
   <th  data-options="field:'legalrep'">法定代表人</th>
      <th data-options="field:'nob',formatter:function(value){
                    if(value=='0'){
                        return '企业单位';
                    }else if(value=='1'){
                        return '事业单位';
                    }else if(value=='2'){
                        return '个体经营';
                    }else if(value=='3'){
                        return '个人';
                    }
                    
                }">公司类型</th>
                 <th data-options="field:'type',formatter:function(value){
                    if(value=='0'){
                        return '会员公司';
                    }else if(value=='1'){
                        return '系统公司';
                    }
                    
                }">企业性质</th>
      <th  data-options="field:'bank'">开户银行</th>
       <th  data-options="field:'zipcode'">邮编</th>
                <th align="center" data-options="field:'createtime'">创建时间
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
        
       	
        
        	 $("#submit_search").click(function () {
        	 var f=$('#searchForm').form('validate');
        	 //, "type":  $('#type').combobox('getValue')
        	 if(f){
        	 $('#tt').datagrid('load', { "name":  $('#name').val()});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addCompany.do','新增公司');
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
                
       editPanel('eidtComPany.do?id='+parkparams,'修改公司_'+parkparams);
			
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
					delDatas(parkparams,'#tt','deleteComPany.do');
			}
        	 });
        	
        });
    </script>
    
    
  </body>
</html>
