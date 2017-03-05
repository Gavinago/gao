<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String basePathFront = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/WsFrontSys"+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>财务操作列表</title>
    
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
   function rowformater(value,row,index)
{

return "<a href='<%=basePathFront%>"+row.pdfpath+"' target='_blank'>查看</a>";
}

   function confirmpayment(value,row,index)
{
//alert(row.status);
var value=row.status;
//if(row.status==3){
//return "<a href='#' >确认付款</a>";
//}else{
//return "";
//}
//alert();
 if(value=='1'){
                        return '已作废';
                    }else if(value=='0'){
                        return '交易成功';
                    }else if(value=='2'){
                        return '待付款';
                    }else if(value=='3'){
                    
                  return  "<a  href='javascript:void(0);' onclick=\"tradingdo('tradingsuc.do',"+row.id+");\">确认到款</a>";
                      // return "<a href='javascript:void(0);' onclick=\"tradingdo("+"tradingsuc.do"+","+row.id+")\">确认付款</a>";
                    }else{
                    	return '未付款';
                    }
}

function tradingdo(url,param){
//alert(1);
 $.messager.confirm('提示信息','确定到款？',function(b){
   if(b){
   		dk(url,param);	//到款操作
   		
   			
    }
  });
//$('#dlg').dialog('open');

    
}


function dk(url,param){

$.ajax({url: url, 
type: 'POST', 
data:{orderid:param}, 
dataType: 'json', 
timeout: 15000, 
error: function(){alert('Error ');}, 
success: function(result){

//到款短信通知
$.ajax({url: 'sysSmsPost.do', 
		type: 'POST', 
		data:{orderid:param}, 
		dataType: 'text', 
		timeout: 25000, 
		error: function(msg){
		  //alert('短信通知异常'+msg);
		}, 
		success: function(msgresult){
		//alert('短信通知成功');
		} 
	});
	
	
	
$.messager.alert('信息提示',result.resultMsg,'info');



$('#tt').datagrid('reload');
} 
});
}
    </script>
  </head>
  
  <body style="visibility: visible;">
 
<div id="tb" style="padding:5px;height:auto"> 
<div style="margin-bottom:5px">  


 <shiro:hasPermission name="/zfOrders.do"> 
<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-add" plain="true" id="zfbtn">作废订单</a> 
</shiro:hasPermission>
        
    </div>  
   <form:form action="" method="post" id="searchForm">
     <!--   品种:   
        <input class="easyui-combobox" style="width:300px;" 
			name="vmid" id="vmid"
			data-options="
					url:'viewPzJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto'
			">-->
			订单号：
			<input class="easyui-validatebox" type="text" id="ordernum" name="ordernum" data-options="">
        <a href="javascript:void(0)" class="easyui-linkbutton" id="submit_search" iconCls="icon-search" ">Search</a> 
        
       &nbsp;    分页位置调整
        <select onchange="changeP(this.value)">
            <option>bottom</option>
            <option>top</option>
            <option>both</option>
        </select>
        </form:form>
  </div> 

  
    <table id="tt" class="easyui-datagrid" style="auto;height:320px"
            data-options="fit:true,collapsible:true,rownumbers:true,url:'financeManager.do',fitColumns:false,singleSelect:false,sortOrder:'desc',sortName:'vmname',pagination:true,toolbar:'#tb'"
            title="数据加载列表" 
           >
        <thead>
            <tr>
           		<th data-options="field:'ck',checkbox:true"></th>
           		<th data-options="field:'id'">编号</th>
				<th  data-options="field:'ordernum'">订单号</th>
                <th  data-options="field:'vmname'">名称</th>
                <th  data-options="field:'goodsnum'">货号</th>
                <th  data-options="field:'addedweight'">重量(吨)</th>
                <th  data-options="field:'addedprice'">价格</th>
                    <th  data-options="field:'omname'">产地</th>
					<th  data-options="field:'ordertime',width:$(this).width()*0.2">下单时间</th>
					 <th  data-options="field:'buyaccout',width:$(this).width()*0.2">客户帐号</th>
                    <th  data-options="field:'pdfpath',width:$(this).width()*0.2,formatter:  rowformater">销售合同预览</th>
                    
                    
                    <th  data-options="field:'status',width:$(this).width()*0.2,formatter:  confirmpayment">确认付款操作</th>
            </tr>
        </thead>
    </table>
    
   <!--  <div id="dlg" class="easyui-dialog" title="到款确认" data-options="" style="width:400px;height:300px;padding:10px">
    <table>
    <tr>
    <td>名称：</td><td></td>
    </tr>
     <tr>
    <td>货号：</td><td></td>
    </tr>
     <tr>
    <td>重量：</td><td></td>
    </tr>
     <tr>
    <td>价格：</td><td></td>
    </tr>
     <tr>
    <td>金额：</td><td></td>
    </tr>
     <tr>
    <td>买受人：</td><td></td>
    </tr>
     <tr>
    <td>开户银行：</td><td></td>
    </tr>
     <tr>
    <td>帐号：</td><td></td>
    </tr>
     <tr>
    <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">确认到款</a></td><td></td>
    </tr>
    </table>
    
    </div> -->
    
    
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
 
 
 function zfDatas(delList,listId,address,zt){
  $.messager.confirm('提示信息','确定要操作选中行吗？',function(b){
   if(b){
    //$.post(address,{delId:0,ids:delList},function(result){
  //  var results=eval(result);
  //  $.messager.alert('信息提示',results.resultMsg,'warning');
    // $(listId).datagrid('reload');
     
   // });
   
     if(zt!=3){
                   $.messager.alert("系统提示","只能操作状态为确认到款的订单!","info");
                  }else{
                  
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
        
        	 $("#submit_search").click(function () {
        	 var f=$('#searchForm').form('validate');
        	 //alert($('#vmid').combobox('getValue'));
        	 // "vmid": $('#vmid').combobox('getValue')
        	 if(f){
        	 $('#tt').datagrid('load', { "ordernum":$('#ordernum').val()});
        	 }else{
        	 
        	 return f;
        	 }
         		
                
            });
        
        	//$('#tt').datagrid('reload',{pageindex:11});
        	 $("#addbtn").click(function () {
        			addPanel('addGoods.do','新增仓库');
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
                
       editPanel('eidtGoods.do?id='+parkparams,'修改货物信息_'+parkparams);
			
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
        	 
        	 
        	 
        	 	  $("#zfbtn").click(function () {
        						var rows = $("#tt").datagrid("getSelections");    // 获取所有选中的行
	   var parks = [];
	   
	   var parkszt = [];
            if(rows == 0){
                $.messager.alert("系统提示","请选择要作废的数据!","info");
                return;

            }else if(rows.length >1){
              $.messager.alert("系统提示","一次只能选择一条数据!","info");
                return;
            
            } else{
                  for(var i=0;i<rows.length;i++){
                      parks.push(rows[i].id);
                  }
                  var parkparams = parks.join(',');
                  
                  //状态
                  for(var i=0;i<rows.length;i++){
                      parkszt.push(rows[i].status);
                  }
                  var parkparamszt = parkszt.join(',');
                  
                  
                
					zfDatas(parkparams,'#tt','zfOrders.do',parkparamszt);
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
timeout: 15000, 
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
