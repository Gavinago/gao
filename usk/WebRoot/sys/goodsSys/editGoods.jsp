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
    
    <title>修改货物</title>
    
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
    
    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mysysform'" style="width:600px;height:425px;border:1px solid #ccc">
		 <div  style="padding:5px;width:600px" id="mysysform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:600px" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form enctype="multipart/form-data" action="eidtGoods.do" method="post"  commandName="editGoodsForm" id="sysForm" target="mailiframe">
          
            <table>
               <tr>
                    <td> 仓单号:</td>
                  
                    <td>
                  <form:hidden path="id"/>
                  <form:hidden path="state"/>
              ${editGoodsForm.goodsnum}
                  
                    </td>
                </tr>
                <tr>
                    <td> 品种名称:</td>
                  
                    <td>
                   <form:input class="easyui-combobox" style="width:300px;" 
			path="vmid"
			data-options="
					url:'viewPzJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			"/>
                    </td>
                </tr>
                <tr>
                    <td>图片1:</td>
                  
                    <td>
                    <img src="<%=request.getContextPath() %>/getGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=1&width=300&height=300"/> 
                      <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="image1" data-options=""/>
                    </td>
                </tr>
                 <tr>
                    <td>图片2:</td>
                  
                    <td>
                    <img src="<%=request.getContextPath() %>/getGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=2&width=300&height=300"/> 
                      <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="image2" data-options="" />
                    
                    </td>
                </tr>
                 <tr>
                    <td>图片3:</td>
                  
                    <td>
                    <img src="<%=request.getContextPath() %>/getGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=3&width=300&height=300"/> 
                      <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="image3" data-options="" />
                    </td>
                </tr>
                  <tr>
                    <td>图片4:</td>
                  
                    <td>
                    <img src="<%=request.getContextPath() %>/getGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=4&width=300&height=300"/> 
                      <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="image4" data-options=""/>
                    </td>
                </tr>
                 <tr>
                    <td>图片5:</td>
                  
                    <td>
                    <img src="<%=request.getContextPath() %>/getGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=5&width=300&height=300"/> 
                      <form:input class="easyui-validatebox" style="width:200px;hight:100px;" accept="image/*" type="file" path="image5" data-options=""/>
                    </td>
                </tr>
                 <tr>
                    <td>重量:</td>
                    <td>
                     <form:input class="easyui-numberbox" path="weight" data-options="required:true,min:0,precision:3"/>
            (吨)
                    </td>
                </tr>
                 
              <tr>
                    <td> 产地:</td>
                  
                    <td>
                   <form:input class="easyui-combobox" style="width:300px;" 
			path="omid" 
			data-options="
					url:'viewPoJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			"/>
                    </td>
                </tr>
              
                 <tr>
                    <td> 供应商:</td>
                  
                    <td>
                   <form:input class="easyui-combobox" style="width:300px;" 
			path="companyid" 
			data-options="
					url:'viewSelectCompanyJson.do',
					onSelect: function(rec){   
                                var url1 = 'viewContactsByComPanyIdJson.do?companyid='+rec.id;    
                                $('#gyslxrid').combobox('reload', url1);  
                                $('#gyslxrid').combobox('setValue','');    
                            },  
                        onLoadSuccess:function(){  
                            var districtId= $('#companyid').combobox('getValue');  
                            var url2='viewContactsByComPanyIdJson.do?companyid='+districtId;  
                            $('#gyslxrid').combobox('reload', url2);  
                        } , 
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			"/>
                    </td>
                </tr>
                   <tr>
                    <td> 供应商联系人:</td>
                  
                    <td>
              
			<form:input path="gyslxrid" /> 
                    </td>
                </tr> 
                 <tr>
                    <td> 提货仓库:</td>
                  
                    <td>
                   <form:input class="easyui-combobox" style="width:300px;" 
			path="thckid" 
			data-options="
					url:'viewDW.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			"/>
                    </td>
                </tr>
                
                  <tr>
                    <td>卸货费:</td>
                    <td>
                        费用类型 <select id="fylx" name="fylx" class="easyui-combotree" 
                        data-options="url:'json/treejson.json',method:'get'"
                         multiple style="width:200px;"></select>
                         
                         <div id="glx" style="">
                       20尺柜   :
                         
                    柜     <select class="easyui-combobox" style="width:100px;" id="twenty_jig">
                    <option>0柜</option>
                         <option>1柜</option>
                         <option>2柜 </option>
                         <option>3柜 </option>
                         <option>4柜 </option>
                         <option>5柜 </option>
                         <option>6柜 </option>
                         <option>7柜 </option>
                         <option>8柜 </option>
                         <option>9柜 </option>
                         <option>10柜 </option>
                         </select> 
                         <br>
                          40尺柜   :
                         
                       <select class="easyui-combobox" style="width:100px;" id="forty_jig">
                    <option>0柜</option>
                         <option>1柜</option>
                         <option>2柜 </option>
                         <option>3柜 </option>
                         <option>4柜 </option>
                         <option>5柜 </option>
                         <option>6柜 </option>
                         <option>7柜 </option>
                         <option>8柜 </option>
                         <option>9柜 </option>
                         <option>10柜 </option>
                         </select> 
                      </div> 

                     <form:input readonly="readonly" class="easyui-validatebox"  path="xhf" data-options="required:true" onfocus="getXhf()"/>元
                    (点击文本框获取卸货费用)
                    </td>
                </tr>
                
                 <tr>
                    <td>入库过磅费:</td>
                    <td>
                <select onchange="rkgbfs(this)">
   <option value="1">1</option>
    <option value="2">2</option>
     <option value="3">3</option>
      <option value="4">4</option>
       <option value="5">5</option>
        <option value="6">6</option>
         <option value="7">7</option>
          <option value="8">8</option>
           <option value="9">9</option>
            <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
    </select>车

                     <form:input readonly="readonly" class="easyui-validatebox" path="rkgbf"  data-options="required:true"  />元
                    </td>
                </tr>
                
                 <tr>
                    <td>到货日期:</td>
                    <td> <form:input class="easyui-datebox" path="dhrq" data-options="formatter:myformatter,parser:myparser"></form:input></td>
                </tr>
                <tr>
                    <td>计量方式:</td>
                    <td>  <form:input value="过磅" class="easyui-validatebox" type="text" path="jlfs" data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>包装方式:</td>
                    <td>  <form:input style="width:300px"  class="easyui-validatebox" type="text" path="bzfs"  data-options="required:true" /></td>
                </tr>
                   <tr>
                    <td>车号:</td>
                    <td>  <form:input  class="easyui-validatebox" type="text" path="ch"  data-options="required:true" /></td>
                </tr>
                 <tr>
                    <td>箱号:</td>
                    <td>  <form:input  class="easyui-validatebox" type="text" path="xh" data-options="required:true" /></td>
                </tr> 
                 <tr>
                    <td>材质:</td>
                    <td>  <form:input  class="easyui-validatebox" type="text" path="cz" data-options="required:true" /></td>
                </tr>
                 <tr>
                    <td>件数:</td>
                    <td>   <form:input class="easyui-numberbox" path="jianshu"  data-options="required:true"/></td>
                </tr>
                 <tr>
                    <td>货物质量:</td>
                    <td>   
                       <form:select path="hwzl" class="easyui-combobox" style="width:100px;" panelHeight="80" >
   <form:option value="0">好</form:option>
    <form:option value="1">一般</form:option>
     <form:option value="2">差</form:option>
    </form:select>
                    </td>
                </tr>
                  <tr>
                    <td>存货货位:</td>
                    <td>  <form:input  class="easyui-validatebox" type="text" path="hw"  data-options="required:true" /></td>
                </tr>
                 <tr>
                    <td>质量初步判断:</td>
                    <td>  <form:input style="width:300px"  class="easyui-validatebox" type="text" path="zlcbpd" data-options="required:true" /></td>
                </tr>
                <tr>
                    <td>不合格产品处理方式:</td>
                    <td> 1.退回 <input type="checkbox" name="bhgcpclfs" value="0"   <c:if test="${editGoodsForm.bhgcpclfs=='0'}"> checked </c:if> /> &nbsp;
                    2.暂存<input type="checkbox" name="bhgcpclfs" value="1" <c:if test="${editGoodsForm.bhgcpclfs=='1'}"> checked </c:if>/>&nbsp; 
                    3.由中心代加工<input type="checkbox" name="bhgcpclfs" value="2" <c:if test="${editGoodsForm.bhgcpclfs=='3'}"> checked </c:if>/></td>
                </tr>
                <!--  <tr>
                    <td> 联系人:</td>
                  
                    <td>
                   <form:input class="easyui-combobox" style="width:300px;" 
			path="contactsid" 
			data-options="
					url:'viewSelectCompanyContactsJson.do',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto',
					required:true
			"/>
                    </td>
                </tr>-->
                
                  <tr> 
                    <td>货物备注:</td>
                    <td><form:textarea path="hwbz" style="height:160px;"  cols="41"></form:textarea> </td>
                </tr>
                <tr> 
                    <td>备注:</td>
                    <td><form:textarea path="remark" style="height:160px;"  cols="41"></form:textarea> </td>
                </tr>
                <tr>
                    <td> 验收人:</td>
                  
                    <td>
                 <shiro:principal property="account"/>
                    </td>
                </tr>
                <!--   <tr>
                    <td>在线联系qq:</td>
                    <td>
 <form:input class="easyui-numberbox" path="qq" data-options="required:true"/>
          
                 
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
    function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){

			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
    function getXhf(){
var t = $('#fylx').combotree('tree');	// get the tree object
var n = t.tree('getSelected');		// get selected node

	var selectXhf=$('#fylx').combotree('getValues');
	if(selectXhf=='0'){	//柜
		var twenty_v=$('#twenty_jig').combobox('getValue');
		var forty_v=$('#forty_jig').combobox('getValue');
		var tv_price=${twentyem.price};
		var fv_price=${fortyem.price};
		var glx_v=parseInt(twenty_v).mul(tv_price)+parseInt(forty_v).mul(fv_price);	//柜 费用
		$('#xhf').val(glx_v);
	}else if(selectXhf=='1'){	//货车
	     var zl=$("#weight").val();
	     var fv_price=${hcem.price};
	     //alert(zl);
	     if(zl==''){
		    // $.messager.alert("操作提示", "请输入货物重量"); 
		     $("#weight").focus();
	    
	     }else{
	      $('#xhf').val(parseInt(zl).mul(fv_price));
	     }
	    
	}else if(selectXhf=='0,1'||selectXhf=='1,0'){	//柜货车
		var twenty_v=$('#twenty_jig').combobox('getValue');
		var forty_v=$('#forty_jig').combobox('getValue');
		var tv_price=${twentyem.price};
		var fv_price=${fortyem.price};
		var glx_v=parseInt(twenty_v).mul(tv_price)+parseInt(forty_v).mul(fv_price);	//柜 费用
		
		var zl=$("#weight").val();
	     var fv_price=${hcem.price};
	     var hc_price=parseInt(zl).mul(fv_price);
	     //alert(zl);
	     if(zl==''){
		    // $.messager.alert("操作提示", "请输入货物重量"); 
		     $("#weight").focus();
	    
	     }else{
	      	$('#xhf').val(glx_v.add(hc_price));
	     }
	}
}
$(function(){ 

  $('#sysForm').form({  
	        url:'eidtGoods.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });

$('#xhf').combotree('setValue', ${editGoodsForm.xhf});


$('#fylx').combotree({onChange:function(){

var newValue=$('#fylx').combotree('getValues');
     if(newValue==0){
		 		$("#glx").show();
		 		
		 	}else if(newValue==1){
		 	$("#glx").hide();
		 		
		 	}else if(newValue=='0,1'||newValue=='1,0'){
		 	$("#glx").show();
		 		
		 	}
		 	$('#xhf').val('');

}}
);

     
});

        function clearForm(){
        
            $('#sysForm').form('clear');
        }
    </script>
  </body>
</html>
