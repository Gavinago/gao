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
    
    <title>新增货物管理</title>
    
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
$(function() {
	//document.getElementById("user_editForm_id").style.display ="none";
	
	// combobox 在onLoadSuccess里需要制定联系人的的Url 但是那时候联系人控件还没初始化 所以在这里一开始就先初始化
	$('#gyslxrid').combobox({
		valueField:'id',
		textField:'text',
		editable:false,
		required:true,
		panelHeight:'auto'
	});
});
</script>
  </head>
  
  <body>

    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mysysform'" style="width:500px;height:380px;border:1px solid #ccc">
		 <div  style="padding:5px;width:500px" id="mysysform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:500px;height:380px;" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form enctype="multipart/form-data"  action="addGoods.do" method="post"  commandName="goodsForm" id="sysForm" target="mailiframe">
            <table>
             <tr>
                    <td> 仓单号:</td>
                  
                    <td>
                  
                    </td>
                </tr>
                <tr>
                    <td> 品种名称:</td>
                  
                    <td>
                   <input class="easyui-combobox" style="width:300px;" 
			name="vmid" id="vmid"
			data-options="
					url:'viewPzJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			">
                    </td>
                </tr>
                <tr>
                    <td>图片1:</td>
                  
                    <td>
                      <input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" name="image1" data-options="required:true">
                    </td>
                </tr>
                 <tr>
                    <td>图片2:</td>
                  
                    <td>
                      <input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" name="image2" data-options="required:true">
                    </td>
                </tr>
                 <tr>
                    <td>图片3:</td>
                  
                    <td>
                      <input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" name="image3" data-options="required:true">
                    </td>
                </tr>
                  <tr>
                    <td>图片4:</td>
                  
                    <td>
                      <input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" name="image4" data-options="required:true">
                    </td>
                </tr>
                 <tr>
                    <td>图片5:</td>
                  
                    <td>
                      <input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" name="image5" data-options="required:true">
                    </td>
                </tr>
                 <tr>
                    <td>重量:</td>
                    <td>
                           <input class="easyui-numberbox" name="weight" data-options="required:true,min:0,precision:3">
            (吨)
                 
                    </td>
                </tr>
                 
              <tr>
                    <td> 产地:</td>
                  
                    <td>
                   <input class="easyui-combobox" style="width:300px;" 
			name="omid" id="omid"
			data-options="
					url:'viewPoJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			">
                    </td>
                </tr>
              
                 <tr>
                    <td> 供应商:</td>
                  
                    <td>
                   <input class="easyui-combobox" style="width:300px;" 
			name="companyid" id="companyid"
			data-options="
					url:'viewSelectCompanyJson.do',
					  onSelect: function(rec){   
					  //alert(1); 
                                var url1 = 'viewContactsByComPanyIdJson.do?companyid='+rec.id;    
                                $('#gyslxrid').combobox('reload', url1);  
                                $('#gyslxrid').combobox('setValue','');    
                            },  
                        onLoadSuccess:function(){  
                         // alert(2); 
                            var districtId= $('#companyid').combobox('getValue');  
                            var url2='viewContactsByComPanyIdJson.do?companyid='+districtId;  
                            $('#gyslxrid').combobox('reload', url2);  
                        } , 
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			">
                    </td>
                </tr>
                    <tr>
                    <td> 供应商联系人:</td>
                  
                    <td>
                  <!--   <input class="easyui-combobox" style="width:300px;" 
			name="gyslxrid" id="gyslxrid"
			data-options="
					url:'viewSelectCompanyContactsJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			">-->
			<input id="gyslxrid" name="gyslxrid"/>  
                    </td>
                </tr> 
                 <tr>
                    <td>提货仓库:</td>
                  
                    <td>
                   <input class="easyui-combobox" style="width:300px;" 
			name="thckid" id="thckid"
			data-options="
					url:'viewDW.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			">
                    </td>
                </tr>
             
                <tr>
                    <td>备注:</td>
                    <td><textarea name="remark" style="height:60px;" cols="38"></textarea> </td>
                </tr>
                <tr>
                    <td> 验收人:</td>
                  
                    <td>
                 <shiro:principal property="account"/>
                    </td>
                </tr>
                <!--  <tr>
                    <td>在线联系qq:</td>
                    <td>
                           <input class="easyui-numberbox" name="qq" data-options="required:true">
            
                 
                    </td>
                </tr>--> 
                <tr>
                <td colspan="2">
                <input type="submit" class="easyui-linkbutton" value="提交">
                 <input type="reset" value="清除数据">
                </td>
                </tr>
            </table>
       </form:form>
       <form:errors/>
        </div>
        <div style="text-align:center;padding:5px">
      
        </div>
    </div>
	</div>
   

    <script>
$(function(){ 

       $('#sysForm').form({  
	        url:'addGoods.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
});

        function clearForm(){
        
            $('#sysForm').form('clear');
        }
    </script>
  </body>
</html>
