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
    
    <title>等待上架货物列表</title>
    
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
    function imagemater(value,row,index)
{
return  "<a href='viewGoods.do?id="+row.id+"' target='_blank'><image src='<%=request.getContextPath() %>/getGoodsImagePhotoById.do?id="+row.id+"&imagenum=1&width=150&height=100' width='150' height='100' ></a>";
}
 
    </script>
  </head>
  
  <body style="visibility: visible;">
<div id="tb" style="padding:5px;height:auto"> 
<div style="margin-bottom:5px">  
     
          &nbsp; &nbsp;
          <shiro:hasPermission name="/addedGoods.do"> 
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-arrow_up" plain="true" id="upbtn">上架</a> 
        </shiro:hasPermission>
          &nbsp; &nbsp;
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-view" plain="true" id="viewbtn">详细查看</a> 
      
    
    </div>  
   <form:form action="" method="post" id="searchForm">
      品种:   
        <input class="easyui-combobox" style="width:300px;" 
			name="vmid" id="vmid"
			data-options="
					url:'viewPzJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto'
			">
			货号：<input class="easyui-validatebox" type="text" id="goodsnum" name="goodsnum" data-options="">
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
            data-options="fit:true,collapsible:true,rownumbers:true,url:'waitsj.do',fitColumns:false,singleSelect:false,sortOrder:'desc',sortName:'vmname',pagination:true,toolbar:'#tb'"
            title="数据加载列表" 
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
           		<th data-options="field:'id'">编号</th>
 <th  data-options="field:'image1',formatter:imagemater,width:$(this).width()*0.1">图片</th>
                <th  data-options="field:'vmname'">名称</th>
                <th  data-options="field:'goodsnum'">货号</th>
                <th  data-options="field:'weight'">重量(吨)</th>
                    <th  data-options="field:'omname'">产地</th>
                <th   align="center" data-options="field:'createtime'">入库时间</th>
                    <th  data-options="field:'companyname'">供应商</th>
                    <th  data-options="field:'account'">验收人</th>
                    <th  data-options="field:'addedprice'">价格</th>
                    <th  data-options="field:'isadded',formatter:function(value){
                    if(value=='1'){
                        return '已上架';
                    }else if(value=='0'){
                        return '未上架';
                    }
                }">是否上架</th>
            </tr>
        </thead>
    </table>
    <script type="text/javascript">
    
    function show(i)
{
var rows=$('#tt').datagrid('getRows');
console.logo(rows[i]);
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
        	 //alert($('#goodsnum').val());
        	 if(f){
        	 $('#tt').datagrid('load', { "vmid": $('#vmid').combobox('getValue'),"goodsnum":$('#goodsnum').val()});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addGoods.do','新增仓库');
        	 }); 
        	 $("#addmultbtn").click(function () {
        			addPanel('addGoodsByImageId.do','新增仓库(多图模式)');
        	 }); 
        	 
        	 
        	  $("#viewbtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要查看的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能查看一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       editPanel('viewGoods.do?id='+parkparams,'查看货物信息_'+parkparams);
			
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
					delDatas(parkparams,'#tt','deleteGoods.do');
			}
        	 });
        	
        	
        	 $("#upbtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要上架的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能上架一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                
       //editPanel('eidtGoods.do?id='+parkparams,'修改货物信息_'+parkparams);
			$.messager.prompt('上架操作', '价格（元/吨）', function(r){
				if (r){
					//alert('you type: '+r);
					
$.ajax({url: 'addedGoods.do', 
type: 'POST', 
data:{ids:parkparams,price:r}, 
dataType: 'json', 
timeout: 125000, 
error: function(){alert('Error ');}, 
success: function(result){
$.messager.alert('信息提示',result.resultMsg,'info');
$('#tt').datagrid('reload');
} 
});
					
				}
				

				
			});
			}
        	 });
        	
        	
        	 $("#downbtn").click(function () {
        			var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
			 var parks = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要上架的数据!","info");
                return;
            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能上架一条数据!","info");
                return;
            
            }else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].goodsnum);
                  }
                  
                   var ids = [];
                   for(var i=0;i<rows.length;i++){
                      ids.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                 
                  var idarams = ids.join(',');
                   //alert(idarams);
            $.messager.confirm('是否确认此货物下架？', '货号'+parkparams, function(r){
				if (r){
					$.ajax({url: 'downGoods.do', 
type: 'POST', 
data:{ids:idarams,price:r}, 
dataType: 'json', 
timeout: 15000, 
error: function(){alert('Error ');}, 
success: function(result){
$.messager.alert('信息提示',result.resultMsg,'info');
$('#tt').datagrid('reload');
} 
});
				}
			});
       //editPanel('eidtGoods.do?id='+parkparams,'修改货物信息_'+parkparams);
			
			}
        	 });
        	
        });
    </script>
    
    
  </body>
</html>
