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
    
    <title>导入</title>
    
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
 function downXlsTemplate(){

			importForm.action="downloadexceltemplate.do";
			importForm.submit();
		}
 
 
 function ckFile(){
		var file =fileForm.myfile.value;
			if(!file){
				alert('请选择文件!');
				return false;
			}else{
		 		var patrn=/.xls$/;
		 		var patrn2=/.xlsx$/;
		 		if(!patrn.test(file)&&!patrn2.test(file)){
					alert('请选择正确的文件格式!');
					return false; 		
		 		}
		 	}
		 	return true;
		}


			function imp(){
			
				if(!ckFile()){return;}
				if(!window.confirm('确定导入么？'))return;
				
				
				
				//progress();
		
		fileForm.action="importExcel.do";
				fileForm.submit();

//
//$.messager.progress('close');
			}
 
 	function progress(){
		
			 $.messager.progress({
				title:'正在处理请稍候',
				msg:'处理中...',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				},
				interval:300 
			}); 
/* 			setTimeout(function(){
				$.messager.progress('close');
			},5000) */
		}
    </script>
  </head>
  
  <body>
    <div id="tb" style="padding:5px;height:auto"> 
<div style="margin-bottom:5px">  
        <!-- <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="addbtn">新增</a>  
        &nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="editbtn">修改</a>  
         -->&nbsp; &nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delbtn">删除</a>
         &nbsp; &nbsp;
         <form name="importForm" method="post">
          <a href="javascript:downXlsTemplate()"><font color="green">模板下载(xls后缀格式,EXCEL97-2003)</font></a>
       
         </form>
      
      <form name="fileForm" enctype="multipart/form-data" method="post">
         <input type="file" name="myfiles" id="myfile"  >
 <input type="button"  value="导入" onclick="imp()">
      </form>
    </div>  
   <form:form action="" method="post" id="searchForm">
     学生名称:   
        <input class="easyui-validatebox" type="text" id="name" name="name" data-options="">
 年龄:    <input class="easyui-validatebox" type="text" id="age" name="age" data-options="">
性别:    	<select class="easyui-combobox" name="sex" id="sex" style="width:200px;">
			<option value="">未选择</option>
			<option value="1">男</option>
			<option value="0">女</option>
		</select>
		
籍贯:    <input class="easyui-validatebox" type="text" id="origin" name="origin" data-options="">
民族:    	<select class="easyui-combobox" name="nation" id="nation" style="width:200px;">
			<option value="">未选择</option>
			<option value="0">汉族</option>
			<option value="1">非汉族</option>
		</select>
 <br>按生源分类查询:    	<select class="easyui-combobox" name="syfl" id="syfl" style="width:200px;">
			<option value="">未选择</option>
			<option value="1">本区学生信息查询(同安区）</option>
			<option value="2">本市内非本区学生信息查询</option>
			<option value="3">省内非本市学生信息查询</option>
			<option value="4">省外学生信息查询</option>
		</select>  
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
            data-options="fit:true,collapsible:true,rownumbers:true,url:'studentinfos.do',fitColumns:true,singleSelect:false,sortOrder:'desc',sortName:'createdate',pagination:true,toolbar:'#tb'"
            title="数据加载列表" 
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
           		<th field="id" >编号</th>
                <th field="name" data-options="">名称</th>
   <th field="age" data-options="">年龄</th>
    <th field="sex" data-options="formatter:function(value){
                    if(value=='0'){
                        return '女';
                    }else if(value=='1'){
                        return '男 ';
                    }
                }">性别</th>
     <th field="origin" data-options="">籍贯</th>
      <th field="nation" data-options="">民族</th>
        <th field="idno" data-options="">身份证件号</th>
         <th field="schoolnumber" data-options="">学校标识码</th>
         <th field="hkproperties" data-options="formatter:function(value){
                    if(value=='0'){
                        return '非农业户口';
                    }else if(value=='1'){
                        return '农业户口 ';
                    }
                }">户口性质</th>
        
        
        
                <th field="createdate"  align="center" data-options="">创建时间
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
        	 if(f){
        	 $('#tt').datagrid('load', { "name":  $('#name').val(),"age":  $('#age').val(),
        	 "sex":$('#sex').combobox('getValue'),"origin":  $('#origin').val(),"nation":  $('#nation').combobox('getValue')
        	 ,"syfl": $('#syfl').combobox('getValue')});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addRole.do','新增角色');
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
                
       editPanel('eidtRole.do?id='+parkparams,'修改角色_'+parkparams);
			
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
					delDatas(parkparams,'#tt','deleteStudentsInfos.do');
			}
        	 });
        	 
        	 
        	 	  $("#fpqxbtn").click(function () {
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
                
       editPanel('assignPermissions.do?id='+parkparams,'修改权限_'+parkparams);
			
			}
        	 });
        	
        });
    </script>
  </body>
</html>
