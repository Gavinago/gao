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
	
	
	<%@include file="../../common/common.jsp" %>
 
<script src="<%=path %>/Plug/uploadify/jquery.uploadify.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/Plug/uploadify/uploadify.css">

	
	<script type="text/javascript">
		function rkgbfs(obj){
			var num=parseInt(obj.value);
			var rkgbf=${rkgbfem.price};
			$("#rkgbf").val(num.mul(rkgbf));
}


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
	
	
	$('#thckid').combobox({
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

    
    <div id="dd" class="easyui-draggable easyui-resizable" data-options="handle:'#mysysform'" style="width:900px;height:auto;border:1px solid #ccc">
		 <div  style="padding:5px;width:920px" id="mysysform">拖动</div>
		 <div class="easyui-panel" title=""  style="width:920px;height:auto;" >
        <div style="padding:10px 0 10px 60px">
      
         <form:form enctype="multipart/form-data"  action="addGoodsByImageId.do" method="post"  commandName="goodsForm" id="sysForm" target="mailiframe">
            <table border="1"  style=" border-spacing:0px;">
             <tr>
                    <td> 仓单号:</td>
                  
                    <td>
               <input id="goodsnum" name="goodsnum" style="width:300px;" class="easyui-validatebox"  data-options="required:true"/> 
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
                    <td>图片<font color="red">(不能大于1MB,一次最多上传五张图片)</font>:</td>
                  
                    <td>
                    <div id="queue"></div>
                   <script>
                   var uploadLimit = 5; 
                   function delimg() {
    $('#file_upload').uploadify('settings','queueSizeLimit',0);
}
                   
                   var timestamp = (new Date()).valueOf();
                    
                   $(function(){ 
                   
                   
                   $('#file_upload').uploadify({
				'formData'     : {
					'timestamp' : timestamp
				},
				'swf'      : '<%=path %>/Plug/uploadify/uploadify.swf',
				'uploader' : '<%=path %>/addGoodsMultipleFile.do',
				'buttonText' : '浏览图片',
				 'fileTypeExts': '*.gif; *.jpg; *.png',
				  'auto': false,
				  'fileSizeLimit' : 5120,	//5MB
				   'method'   : 'post',
				    'queueSizeLimit' : 5,
				    'uploadLimit' : 5,
				'width' : 50,
				'height' : 20,
				'onSelect' : function(e, queueId, fileObj) {
				/* alert("唯一标识:" + queueId + "\r\n" +
                  "文件名：" + fileObj.name + "\r\n" +
                  "文件大小：" + fileObj.size + "\r\n" +
                  "创建时间：" + fileObj.creationDate + "\r\n" +
                  "最后修改时间：" + fileObj.modificationDate + "\r\n" +
                  "文件类型：" + fileObj.type
            ); */
            //alert(file.name + ' 文件添加至上传队列.');
               if(arrayObj.length>=5){
	         $.messager.alert("操作提示", "已经上传图片，请勿重复上传"); 
	         
	         }
        },
				'onUploadSuccess' : function(file, data, response) {	//上传成功后执行的回调函数
				//console.log(file);
				 var path="<%=request.getContextPath() %>/getYlPhotoById.do?id="+data+"&imagenum=1&width=300&height=300";
					arrayObj.push(data);
					//$('#photo>img').attr("src",path); 
					$("#photo").append("<span id='image"+data+"' width='150' height='100'><img src='"+path+"' width='150' height='100' /></span>"); 
					$("#imagefileid").val(arrayObj);
					//alert("返回的图片主键数组："+$("#imagefileid").val());
					
					//
					
					
   var cancel=$("#"+file.id + " .cancel a");
	if (cancel) {
	cancel.on('click',function () {
	//在这此处处理...
	//通过uploadify的settings方式重置上传限制数量
	$('#file_upload').uploadify('settings','uploadLimit', ++uploadLimit);
	//防止手快多点几次x，把x隐藏掉
	$('#image'+data).hide();
	//arrayObj.splice(1,1);
	arrayObj.splice($.inArray(data,arrayObj),1);
	$("#imagefileid").val(arrayObj);
	
	});
	} 
	
	   
				},
				 'onUploadError' : function(file, errorCode, errorMsg, errorString) {
            			//alert(file.name + ' 上传失败。详细信息: ' + errorString);
        		},
        		 //检测FLASH失败调用
        'onFallback':function(){
            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
        },
        		//返回一个错误，选择文件的时候触发  
            'onSelectError':function(file, errorCode, errorMsg){  
                switch(errorCode) {  
                    case -100:  
                        alert("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");  
                        break;  
                    case -110:  
                        alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");  
                        break;  
                    case -120:  
                        alert("文件 ["+file.name+"] 大小异常！");  
                        break;  
                    case -130:  
                        alert("文件 ["+file.name+"] 类型不正确！");  
                        break;  
                }  
            },
        		onQueueComplete : function(stats) {
/*         		alert( '成功上传的文件数: ' + stats.successful_uploads
+ ' - 上传出错的文件数: ' + stats.upload_errors
+ ' - 取消上传的文件数: ' + stats.upload_cancelled
+ ' - 出错的文件数' + stats.queue_errors); */
$.messager.alert("操作提示","图片上传成功"); 
        		},
        		'removeCompleted' : false,
			});
			
             });
                   </script>
                      <input  type="file" name="file_upload" id="file_upload" multiple="true">
               <a href="javascript:$('#file_upload').uploadify('cancel', '*')">重新上传</a> | 
               <a href="javascript:$('#file_upload').uploadify('upload', '*')">开始上传</a>
              

                      
                       <input type="hidden" name="imagefileid" id="imagefileid" >
                        <div id="photo"></div> 
                        <!--
                       
                         <img src="images/default.jpg" width="130" height="150"/> -->
                    </td>
                </tr>
              
                 <tr>
                    <td>重量:</td>
                    <td>
                           <input class="easyui-numberbox" name="weight" id="weight" data-options="required:true,min:0,precision:3">
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
			">
                    </td>
                </tr>
                    <tr>
                    <td> 供应商联系人:</td>
                  
                    <td>
        
			<input id="gyslxrid" name="gyslxrid"/>  
                    </td>
                </tr> 
                 <tr>
                    <td>部门:</td>
                  
                    <td>
                   <input class="easyui-combobox" style="width:300px;" 
			name="deptid" id="deptid"
			data-options="
					url:'viewSelectDeptByUserid.do',
					  onSelect: function(rec){   
                                var url1 = 'viewDwhByDeptIdJson.do?deptid='+rec.id;    
                                $('#thckid').combobox('reload', url1);  
                                $('#thckid').combobox('setValue','');    
                            },  
                        onLoadSuccess:function(){  
                            var districtId= $('deptid').combobox('getValue');  
                            var url2='viewDwhByDeptIdJson.do?deptid='+districtId;  
                            $('#thckid').combobox('reload', url2);  
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
                    <td>提货仓库:</td>
                  
                    <td>
                  <input id="thckid" name="thckid"/>  
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
                      
                      


                     <input class="easyui-validatebox" type="text" name="xhf" id="xhf" data-options="required:true" readonly onfocus="getXhf()">元
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

                     <input class="easyui-validatebox" type="text" id="rkgbf" name="rkgbf" data-options="required:true" readonly value="${rkgbfem.price}">元
                    </td>
                </tr>
              <tr>
                    <td>到货日期:</td>
                    <td> <input class="easyui-datebox" id="dhrq" name="dhrq" data-options="formatter:myformatter,parser:myparser"></input></td>
                </tr>
                <tr>
                    <td>计量方式:</td>
                    <td>  <input value="过磅" class="easyui-validatebox" type="text" name="jlfs" id="jlfs" data-options="required:true" ></td>
                </tr>
                <tr>
                    <td>包装方式:</td>
                    <td>  <input  class="easyui-validatebox" type="text" name="bzfs" id="bzfs" data-options="required:true" ></td>
                </tr>
                   <tr>
                    <td>车号:</td>
                    <td>  <input  class="easyui-validatebox" type="text" name="ch" id="ch" data-options="required:true" ></td>
                </tr>
                   <tr>
                    <td>箱号:</td>
                    <td>  <input  class="easyui-validatebox" type="text" name="xh" id="xh" data-options="required:true" ></td>
                </tr> 
                 <tr>
                    <td>材质:</td>
                    <td>  <input  class="easyui-validatebox" type="text" name="cz" id="cz" data-options="required:true" ></td>
                </tr>
                 <tr>
                    <td>件数:</td>
                    <td>   <input class="easyui-numberbox" name="jianshu" id="jianshu" data-options="required:true"></td>
                </tr>
                  <tr>
                    <td>货物质量:</td>
                    <td>   
                       <select name="hwzl" class="easyui-combobox" style="width:100px;" panelHeight="80" >
   <option value="0">好</option>
    <option value="1">一般</option>
     <option value="2">差</option>
    </select>
                    </td>
                </tr>
                 <tr>
                    <td>存货货位:</td>
                    <td>  <input  class="easyui-validatebox" type="text" name="hw" id="hw" data-options="required:true" ></td>
                </tr>
                 <tr>
                    <td>质量初步判断:</td>
                    <td>  <input  class="easyui-validatebox" type="text" name="zlcbpd" id="zlcbpd" data-options="required:true" ></td>
                </tr>
                <tr>
                    <td>不合格产品处理方式:</td>
                    <td> 1.退回 <input type="checkbox" name="bhgcpclfs" value="0"> &nbsp;2.暂存<input type="checkbox" name="bhgcpclfs" value="1">&nbsp; 3.由中心代加工<input type="checkbox" name="bhgcpclfs" value="2"></td>
                </tr>
                <tr>
                    <td width="260" style="word-break:break-all">货物备注(有没用太空袋，共几车或几个柜，如是集装箱，请注明是几尺的柜子):</td>
                    <td><textarea name="hwbz" style="height:60px;" cols="38"></textarea> </td>
                </tr>
                
                <tr>
                    <td>货物详情:</td>
                    <td><textarea name="remark" style="height:60px;" cols="38"></textarea> </td>
                </tr>
                <tr>
                    <td> 验收人:</td>
                  
                    <td>
                 <shiro:principal property="account"/>
                    </td>
                </tr>
                 <!-- <tr>
                    <td>在线联系qq:</td>
                    <td>
                           <input class="easyui-numberbox" name="qq" data-options="required:true">
            
                 
                    </td>
                </tr> -->
                <tr>
                <td >
                 <!--  <input type="button" class="easyui-linkbutton" value="测试" onclick="test()">-->
                <input type="submit" class="easyui-linkbutton" value="提交">
                </td><td align="right">
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
   
<script type="text/javascript">
function test(){

alert($('#fylx').combobox('getValues'));
}


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


		
	</script>
    <script>
    var arrayObj = new Array(); //创建一个数组
			var v=$("#imagefileid").val();
	arrayObj= v.split("");   
$(function(){ 

var curr_time = new Date();
   var strDate = curr_time.getFullYear()+"-";
   strDate += curr_time.getMonth()+1+"-";
   strDate += curr_time.getDate();	//+"-"
   $("#dhrq").datebox("setValue", strDate); 

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







       $('#sysForm').form({  
	        url:'addGoodsByImageId.do',  
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
