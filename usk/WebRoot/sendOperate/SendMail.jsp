<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>邮件发送</title>
    

<%@include file="../common/common.jsp" %>
<script type="text/javascript" src="<%=basePaths %>Comet4J/comet4j.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="Plug/KindEditor/themes/default/default.css" />
	<link rel="stylesheet" href="Plug/KindEditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="Plug/KindEditor/kindeditor.js"></script>
	<script charset="utf-8" src="Plug/KindEditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="Plug/KindEditor/plugins/code/prettify.js"></script>
	<script>
	var editor ;
	
		KindEditor.ready(function(K) {
		 editor = K.create('textarea[name="content"]');
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : 'Plug/KindEditor/plugins/code/prettify.css',
				uploadJson : 'sendOperate/upload_json.jsp',
				fileManagerJson : 'sendOperate/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		
		
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
       
       
          function htmlinput(){
	     var vs=$('#mbname').val();
	     
	     var hzm = vs.substr(vs.indexOf("."));
					
					     if(hzm!=".html"){
						     $.messager.alert("系统提示","请选择html文件!","info");
						     return false;
	     				}
	     				
	     				//var f=$(this).form('validate');
	     				//if(!f){
	     				//return false;
	     				//}
	     				
	     				
	     				$('#StencilForm').form('submit',{
		        url:"importHtmlStencil.do",
		        onSubmit: function(){
	     				return $(this).form('validate');  
		        },
		        success:function(data){
		        		//alert(data);
		        		if(data=='模版名称已经存在请重命名'){
		        			$.messager.alert("操作提示", data); 
		        		}else{
		        		 editor.html(data);
		        		}
		              
		        }
			});	
	     
   // $.ajax({ //no need to judge the browser type  
                  //  type: "POST", //类型  
                  //  url: "importHtmlStencil.do", //请求访问的servlet  
                 //   dataType: "html",     
                  //  data: {'param1':'test'}, //传递的参数  
                  //  success: function(returnedData){   //成功返回的回调函数  
                    	//editor.html(returnedData);
                   // }  
               // }); 

	     	
																											    
	     
	   
     }
     
     function init(){
	var kbDom = document.getElementById('kb');
	//alert(kbDom);
        JS.Engine.on({
                hello : function(kb){//侦听一个channel
						//alert(kb);
                        kbDom.innerHTML = kb;
                }
        });
        JS.Engine.start('<%=basePaths %>conn');
}
	</script>
  </head>
  
  <body >
  <span id="kb" style="display:none;"></span>
      <!-- 剩余内存：<span id="kb">...</span>KB-->
  <span id="pbv"></span>
  
  <form:form enctype="multipart/form-data"  action="importHtmlStencil.do" method="post"  commandName="newHtmltemp" id="StencilForm" target="mailiframe">
   <table>
    <tr>
                    <td>导入模版:</td>
                  
                    <td>
                    <input type="file" name="mbname" id="mbname" class="easyui-validatebox"  data-options="required:true,missingMessage:'请选择html文件'">
                   <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="htmlinput()">执行模版导入</a>
                  是否保存模版:<input type="checkbox" name="status" id="status" value="1">
                  
               <span id="mbsp" style="display:none;">模版名称：<input type="text" name="tempname" id="tempname" class="easyui-validatebox" ></span>   
                    </td>
                </tr>
                <tr>
                <td>
                
                </td>
                 <td>
                <div style="margin:10px 0;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('open')">选择已有模版</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#dlg').dialog('close')">关闭窗口</a>
	</div>
                </td>
                </tr>
   </table>
  </form:form>
  	


     <form:form enctype="multipart/form-data"  action="sendMailSave.do" method="post"  commandName="newMailSend" id="mailForm" target="mailiframe">
            <table>
              <tr>
                    <td>  客户：</td>
                  
                    <td>
                  <input class="easyui-combobox" 
			name="cid" id="cid" style="width:300px;"
			data-options=" 
					url:'SelectMCJson.do',
					method:'get',
					valueField:'id',
					textField:'clintname',
					panelHeight:'auto',required:true
			">
                  
               &nbsp;  <a id="btn" href="javascript:addPanel('viewAllMailClient.do','客户管理');" class="easyui-linkbutton" data-options="iconCls:'icon-user'">客户管理</a>
      
                    </td>
                </tr>
                <tr>
                    <td>邮件主题:</td>
                  
                    <td>
                    <input style="width:300px;" class="easyui-validatebox" type="text" name="subject" data-options="required:true">
                  
                    </td>
                </tr>
             
                <tr>
                    <td>内容:</td>
                    <td><textarea name="content" cols="50" rows="8" style="width:700px;height:300px;visibility:hidden;"></textarea> </td>
                </tr>
                <tr>
                    <td>附件上传:</td>
                    <td><input type="file" name="fj" id="fj"> </td>
                </tr>
                <tr>
                <td>&nbsp;</td>
                <td >
                <input type="submit" name="button"  value="发送" onclick="start()">
                 <input type="reset" value="清除数据">
                 <div id="pb" class="easyui-progressbar" style="width:400px;"></div>
                 
               
                </td>
                </tr>
            </table>
       </form:form>
       <form:errors/>
       
       
     
	<div id="dlg" class="easyui-dialog" title="模版" data-options="" style="width:755px;height:400px;padding:10px">
		 <table id="tt" class="easyui-datagrid" style="width:720px;height:320px"
            data-options="collapsible:true,rownumbers:true,url:'viewAllTempJson.do',fitColumns:true,singleSelect:false,sortOrder:'desc',sortName:'createtime',pagination:true,toolbar:'#tb'"
            title="双击行选择" 
           >
        <thead title="双击">
            <tr title="双击">
           		<!-- <th data-options="field:'ck',checkbox:true"></th> -->
                <th field="id" data-options="width:200,title:'双击选择'">编号</th>
                <th field="tempname" data-options="width:200">模版名称</th>

                <th field="createtime"  align="center" data-options="width:300">创建时间</th>
            </tr>
        </thead>
    </table>
	</div>
	

       
          <script>
         
          function getFullPath(obj) 
				{ 
				if(obj) 
				{ 
				//ie 
				if (window.navigator.userAgent.indexOf("MSIE")>=1) 
				{ 
				obj.select(); 
				return document.selection.createRange().text; 
				} 
				//firefox 
				else if(window.navigator.userAgent.indexOf("Firefox")>=1) 
				{ 
				if(obj.files) 
				{ 
				return obj.files.item(0).getAsDataURL(); 
				} 
				return obj.value; 
				} 
				return obj.value; 
				} 
}



$(function(){ 



$('#dlg').dialog('close');
//$('#dlg_progressbar').dialog('close');

   $('#tt').datagrid({
            onDblClickRow: function (rowIndex, rowData) {
            
           // alert(rowIndex+"  "+rowData['id']);
            
            		     $.ajax({url: 'selectTemp.do', 

type: 'POST', 

data:{id:rowData['id']}, 

dataType: 'html', 

timeout: 15000, 

error: function(){alert('Error ');}, 

success: function(result){

 editor.html(result);


} 

});
          
          
          
       			}
          });

$("#status").click(function() {               //checkBox点击事件
        if($("#status").is(":checked")){//判断是否选中
        	$("#mbsp").toggle();
        	//$("#mbsp").html('<b>Hello world!</b>');
        	//$("#mbsp").html("模版名称：<input type=\"text\" name=\"tempname\" id=\"tempname\" class=\"easyui-validatebox\" data-options=\"required:true\">");
        	//模版名称：<input type="text" name="tempname" id="tempname" class="easyui-validatebox" data-options="required:true">
        }else{
        	$("#mbsp").toggle();
        	//$("#mbsp").html('');
        }              
           
        });


       $('#mailForm').form({  
	        url:'sendMailSave.do',  
	        onSubmit:function(){

	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	//$.messager.alert("操作提示", data); 
				if(data!=""){
				$.messager.alert("操作提示", data); 
				}
	        }  
    });
});



        function clearForm(){
        
            $('#mailForm').form('clear');
        }
        function start(){
        var kbDom = document.getElementById('kb');
	//alert(kbDom);
        JS.Engine.on({
                hello : function(kb){//侦听一个channel
						//alert(kb);
                        kbDom.innerHTML = kb;
                        var value = $('#pb').progressbar('getValue');
                       // if(kbDom.innerHTML=''){
                       	// kbDom.innerHTML=100;
                       // }
                       //alert(kbDom.innerHTML);
						$('#pb').progressbar('setValue', kbDom.innerHTML);
                }
        });
        JS.Engine.start('<%=basePaths %>conn');
        //alert(1);
        // var pbvDom = document.getElementById('pbv');
        	//JS.Engine.on({
              //  taskpersent : function(pbv){//侦听一个channel
					//	alert(pbv);
                     //   pbvDom.innerHTML = pbv;
              //  }
       // });
       // JS.Engine.start('<%=basePaths %>conn');
        
         //alert(kbDom.innerHTML);
			
			//if (value < 200){
				//value += Math.floor(Math.random() * 10);
				//$('#p').progressbar('setValue', value);
				//setTimeout(arguments.callee, 200);
			//}
		};
    </script>
  </body>
</html>
