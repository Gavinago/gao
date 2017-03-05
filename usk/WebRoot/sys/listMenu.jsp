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
    
    <title>菜单列表</title>
    
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
        
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="zdbtn">折叠全部</a>  
        
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="zkbtn">展开全部</a>    
    </div>  
   <form:form action="" method="post" id="searchForm">
       菜单名称:   
        <input class="easyui-validatebox" type="text" id="name" name="name" data-options="">
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">Search</a> 
        
       &nbsp;  
        </form:form>
  </div> 

  
   <table id="tt" class="easyui-treegrid" title="数据列表" style="width:auto;height:350px"
			data-options="
			url: 'viewAllMenuJson.do',
			fit:true,
				method: 'get',
				rownumbers: true,
				idField: 'id',
				treeField: 'name',
				animate: true,
				collapsible: true,
				fitColumns: false,
				
				toolbar:'#tb'
			">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
           		<th data-options="field:'id'" >编号</th>
                <th data-options="field:'name'">名称</th>
                <th  data-options="field:'menupid'">父菜单编号</th>
                <th align="center" data-options="field:'description'">描述</th>
                <th  align="center" data-options="field:'pageurl'">地址</th>
                <th  align="center" data-options="field:'type',formatter:function(value){
                    if(value=='0'){
                        return '功能按钮';
                    }else if(value=='1'){
                        return '导航菜单';
                    }
                }"">类型</th>
                <th  align="center" data-options="field:'createtime'">创建时间
                </th>
			</tr>
		</thead>
	</table>
    <script type="text/javascript">
    
    
 
		 function formatDollar(value){
if (value){
return '$'+value;
} else {
return '';
}
}
		function pagerFilter(data){
            if ($.isArray(data)){    // is array  
                data = {  
                    total: data.length,  
                    rows: data  
                }  
            }
            var dg = $(this);  
			var state = dg.data('treegrid');
            var opts = dg.treegrid('options');  
            var pager = dg.treegrid('getPager');  
            pager.pagination({  
                onSelectPage:function(pageNum, pageSize){  
                    opts.pageNumber = pageNum;  
                    opts.pageSize = pageSize;  
                    pager.pagination('refresh',{  
                        pageNumber:pageNum,  
                        pageSize:pageSize  
                    });  
                    dg.treegrid('loadData',data);  
                }  
            });  
            if (!data.topRows){  
            	data.topRows = [];
            	data.childRows = [];
            	for(var i=0; i<data.rows.length; i++){
            		var row = data.rows[i];
            		row._parentId ? data.childRows.push(row) : data.topRows.push(row);
            	}
				data.total = (data.topRows.length);
            }  
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);  
            var end = start + parseInt(opts.pageSize);  
			data.rows = $.extend(true,[],data.topRows.slice(start, end).concat(data.childRows));
			return data;
		}
    
    
    
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

timeout: 25000, 

error: function(){alert('Error ');}, 

success: function(result){

$.messager.alert('信息提示',result.resultMsg,'info');
$(listId).treegrid('reload');


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
        
       	  
       $('#tt').treegrid('collapseAll');
        
        	 $("#submit_search").click(function () {
        	 var f=$('#searchForm').form('validate');
        	 if(f){
        	 $('#tt').treegrid('load', { "name":  $('#name').val()});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addMenu.do','新增菜单');
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
                
       editPanel('eidtMenu.do?id='+parkparams,'修改菜单_'+parkparams);
			
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
					delDatas(parkparams,'#tt','deleteMenu.do');
			}
        	 });
        	 
        	  $("#zdbtn").click(function () {
        	   	$('#tt').treegrid('collapseAll');
        	   });
        	 $("#zkbtn").click(function () {
        	   $('#tt').treegrid('expandAll');
                
        	   });
        	   
        	 
        });
    </script>
    
    
  </body>
</html>
