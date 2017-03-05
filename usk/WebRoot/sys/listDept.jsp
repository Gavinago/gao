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
    
    <title>部门列表</title>
    
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
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增顶级部门</a> 
     
         <shiro:hasPermission name="/deptbuttonedit"> 
     <a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()">编辑</a>
     </shiro:hasPermission>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()">取消</a>
		
        &nbsp; &nbsp;(可以右键菜单操作部门新增)
        <!--  
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a>  
        -->
    </div>  
   <form:form action="" method="post" id="searchForm">
       部门名称:   
        <input class="easyui-validatebox" type="text" id="name" name="name" data-options="">
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">Search</a> 
        
       &nbsp;  
        </form:form>
  </div> 

  
   <table id="tg" class="easyui-treegrid" title="数据列表" style="width:480px;height:350px"
			data-options="
			url: 'viewDeptJson.do',
			fit:true,
				method: 'get',
				rownumbers: true,
				idField: 'id',
				treeField: 'name',
				animate: true,
				collapsible: true,
				fitColumns: true,
				toolbar:'#tb',
				onContextMenu: onContextMenu
			">
		<thead>
			<tr>
				<th data-options="field:'pid',checkbox:true"></th>
           		<th data-options="field:'id',editor:'text'" >编号</th>
                <th data-options="field:'name',width:$(this).width()*0.2,editor:'text'">部门名称</th>
                <th  data-options="field:'pidname',width:$(this).width()*0.2">所属部门</th>
                <th align="center" data-options="field:'description',width:$(this).width()*0.2,editor:'text'">描述</th>
                <th  align="center" data-options="field:'createtime',width:$(this).width()*0.2">创建时间
                </th>
                <th  align="center" data-options="field:'dwname',width:$(this).width()*0.2">提货仓库
                </th>
			</tr>
		</thead>
	</table>
	<div id="mm" class="easyui-menu" style="width:120px;">
		<div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
		<div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
		<div class="menu-sep"></div>
		<div onclick="collapse()">收缩</div>
		<div onclick="expand()">展开</div>
	</div>
	
	
	<div id="dlg" class="easyui-dialog" title="部门新增" data-options="" style="width:355px;height:200px;padding:10px">
		 <form:form action="saveContextDept.do" method="post"  commandName="pd" id="deptForm" target="mailiframe">
            <table>
                <tr>
                    <td>名称:</td>
                  
                    <td>
                 
                  <input type="hidden" name="pid" id="pid" value="0"/>
                    <input style="width:200px;hight:100px;" class="easyui-validatebox" type="text" id="deptname" name="deptname" data-options="required:true">
                  
                    </td>
                </tr>
              
                
                
                <tr>
                    <td>描述:</td>
                    <td><textarea name="description" id="description" style="height:60px;" cols="28"></textarea> </td>
                </tr>
                <tr>
                <td colspan="2">
                <input type="button" class="easyui-linkbutton" value="提交" onclick="savedept()">
                 <input type="reset" value="清除数据">
                </td>
                </tr>
            </table>
       </form:form>
	</div>
    <script type="text/javascript">
 var editingId;
		function edit(){
		
			if (editingId != undefined){
				$('#tg').treegrid('select', editingId);
				return;
			}
			var row = $('#tg').treegrid('getSelected');
			//alert(row);
			if (row){
				editingId = row.id;
				$('#tg').treegrid('beginEdit', editingId);
			}
		}
		function save(){
			if (editingId != undefined){
				var t = $('#tg');
				var row = $('#tg').treegrid('getSelected');
				
				
				var nameEditor=$('#tg').treegrid('getEditor', {index:row.id,field:"name"});
				var descriEditor=$('#tg').treegrid('getEditor', {index:row.id,field:"description"});
				var namestr=nameEditor.target.val();
				//alert(namestr);
				var descriptionstr=descriEditor.target.val();
				//alert(descriptionstr);
				var node = $('#tg').treegrid('getSelected');
				var pid=node.pid;
				var id=node.id;
				
							$.ajax({
url: 'updateDept.do',
type: 'POST',
data:{name:namestr,description:descriptionstr,pid:pid,id:id},
dataType: 'text',
timeout: 10000,
error: function(){$.messager.alert("操作提示", "保存出错");},
success: function(result){

//$.messager.alert("操作提示", result);
$('#tg').treegrid('reload');
}
}); 


			
				
				t.treegrid('endEdit', editingId);
editingId = undefined;
				
				//$('#tg').treegrid('reload');
				
				
				//end
				/* var persons = 0;
				var rows = t.treegrid('getChildren');
				for(var i=0; i<rows.length; i++){
					var p = parseInt(rows[i].persons);
					//alert(p);
					if (!isNaN(p)){
						persons += p;
					}
				}
				var frow = t.treegrid('getFooterRows')[0];
				frow.persons = persons;
				t.treegrid('reloadFooter'); */
			}
		}
		function cancel(){
			if (editingId != undefined){
				$('#tg').treegrid('cancelEdit', editingId);
				editingId = undefined;
			}
		}
				
				
				
				
		function onContextMenu(e,row){
			e.preventDefault();
			$(this).treegrid('select', row.id);
			$('#mm').menu('show',{
				left: e.pageX,
				top: e.pageY
			});
		}
		var idIndex = 100;
		
		function savedept(){
		var r=$("#deptForm").form('validate');	//easyui 表单验证
if(!r){
   return false;
}
//alert($('#deptname').val());

							$.ajax({
url: 'saveContextDept.do',
type: 'POST',
data:{pid:$('#pid').val(),name:$('#deptname').val(),description:$('#description').val()},
dataType: 'text',
timeout: 10000,
error: function(){$.messager.alert("操作提示", "保存出错");},
success: function(result){$.messager.alert("操作提示", result);
$('#pid').val('');$('#deptname').val('');$('#description').val('');
$('#dlg').dialog('close');
$('#tg').treegrid('reload');
}
}); 
		
		}
		function append(){
		$('#dlg').dialog('open')
		
		var node = $('#tg').treegrid('getSelected');
		var pid=node.id;
		$('#pid').val(pid);
		//alert($('#pid').val());
			//idIndex++;
			/* var d1 = new Date();
			var d2 = new Date();
			d2.setMonth(d2.getMonth()+1);
			var node = $('#tg').treegrid('getSelected');
			$('#tg').treegrid('append',{
				parent: node.id,
				data: [{
					id: idIndex,
					name: 'New Task'+idIndex,
					persons: parseInt(Math.random()*10),
					begin: $.fn.datebox.defaults.formatter(d1),
					end: $.fn.datebox.defaults.formatter(d2),
					progress: parseInt(Math.random()*100)
				}]
			}) */
		}
		function removeIt(){
			var node = $('#tg').treegrid('getSelected');
			
			
			$.messager.confirm('提示信息','确定要删除选中部门吗？',function(b){
   if(b){
   
    $.ajax({url: 'deleteDept.do', 

type: 'POST', 

data:{ids:node.id}, 

dataType: 'json', 

timeout: 15000, 

error: function(){alert('Error ');}, 

success: function(result){

$.messager.alert('信息提示',result.resultMsg,'info');
$('#tg').treegrid('reload');


} 

});

   
   
   }
  });
			//if (node){
				//$('#tg').treegrid('remove', node.id);
			//}
		}
		function collapse(){
			var node = $('#tg').treegrid('getSelected');
			if (node){
				$('#tg').treegrid('collapse', node.id);
			}
		}
		function expand(){
			var node = $('#tg').treegrid('getSelected');
			if (node){
				$('#tg').treegrid('expand', node.id);
			}
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

timeout: 15000, 

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
        $('#dlg').dialog('close');
       	
       	
       	
       	 $('#deptForm').form({  
	        url:'saveDept.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
        
        	 $("#submit_search").click(function () {
        	 var f=$('#searchForm').form('validate');
        	 if(f){
        	 $('#tg').treegrid('load', { "name":  $('#name').val()});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addDept.do','新增部门');
        	 }); 
        	 
        	 
        	 
        	 	  $("#addDwhRebtn").click(function () {
        			var row = $('#tg').treegrid('getSelected');
					var editingId = row.id;
       				editPanel('dwhrelist.do?deptid='+editingId,'分配提货仓库_'+editingId);
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
        	
        });
    </script>
    
    
  </body>
</html>
