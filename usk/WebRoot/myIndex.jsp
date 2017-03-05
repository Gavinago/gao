<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="common/common.jsp" %>

	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	
	-->
	<script type="text/javascript" src="<%=basePath%>js/contacts.js"></script>
	
<script type="text/javascript" src="<%=basePath%>ui/tabs.js"></script>
<style>
.topmenu{
	border-top:1px solid #ccc;
	background:url(images/btn.jpg) repeat-x;
	width:100%;
}


</style>
	<script type="text/javascript" >

function updatetabs(tabPanel,title,urls){

    $("#"+tabPanel).tabs('select',title,urls);  
            var tab = $('#'+tabPanel).tabs('getSelected');       
            $("#"+tabPanel).tabs('update',{  
            tab:tab,  
            options:{  
               title:title,  
               style:{padding:'1px'},  
           //href:URL, // 使用href会导致页面加载两次，所以使用content代替  
               content:"<iframe width='100%' height='100%'  id='iframe' frameborder='0' scrolling='auto'  src='"+urls+"'></iframe>",  
           closable:false,  
           fit:true,  
           selected:true  
            }  
             });  
             
           // window.open(URL,urls);  
}
	/*
	*添加选项卡方法
	*/
	function addTab(title,url){
		//先判断是否存在标题为title的选项卡
		var tab=$('#tabs').tabs('exists',title);
		if(tab){
			
			//$('#tabs').tabs('select',title);	//若存在，则直接打开
			updatetabs('tabs',title,url);	//若存在，刷新
		}else{
			//否则创建
			$('#tabs').tabs('add',{
				title:title,
				content:"<iframe width='100%' height='100%'  id='iframe' frameborder='0' scrolling='auto'  src='"+url+"'></iframe>",
				closable:true
			});
		}
		
	}
	


	 
	</script>
	<style type="text/css">
.ww{font-family:"微软雅黑", "幼圆", "宋体";font-size:14px; color:#0066cc;}
.ww li{ list-style-image:url(images/bullet_blue.png);}
.ww li a{ text-decoration:none; padding:5px;inheritcolor:#0066CC;}
.ww li a:visited{font-family:"微软雅黑", "幼圆", "宋体";font-size:14px; color:#0066cc;}
.ww li a:hover{border:1px dashed #069; width:auto; height:20px; background:#6CF; color:#fff;}
</style>
  </head>
  
  <body class="easyui-layout">
    <div data-options="region:'north',collapsed:false" style="height:60px;background:#B3DFDA;padding:10px">
    
    欢迎您登录系统：<shiro:principal property="name"/>
				<a href="javascript:exit('userlogout.do');">退出</a>
				
			
				
	 <div style="padding:5px;border:1px solid #ddd">
		<a href="javascript:addTab('主页','monitoring?period=jour');" class="easyui-linkbutton" data-options="plain:true">主页</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-edit'">换肤</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-help'">Help</a>
		<a href="#" class="easyui-menubutton" data-options="menu:'#mm3'">About</a>
	</div>
	
	<div id="mm1" style="width:150px;">
		 <div href="javascript:changeThemeFun('default');">default</div> 
	         <div href="javascript:changeThemeFun('gray');">gray</div> 
	  
	       <!--  <div href="javascript:changeThemeFun('cupertino');">cupertino</div>
	      <div href="javascript:changeThemeFun('dark-hive');">dark-hive</div>
	        <div href="javascript:changeThemeFun('pepper-grinder');">pepper-grinder</div> 
	       <div href="javascript:changeThemeFun('sunny');">sunny</div>-->
	        
	           <div href="javascript:changeThemeFun('bootstrap');">bootstrap</div>
	                <div href="javascript:changeThemeFun('metro');">metro</div>
	           <div href="javascript:changeThemeFun('metro-blue');">metro-blue</div>
	           <div href="javascript:changeThemeFun('metro-gray');">metro-gray</div>
	           <div href="javascript:changeThemeFun('metro-green');">metro-green</div>
	           <div href="javascript:changeThemeFun('metro-orange');">metro-orange</div>
	           <div href="javascript:changeThemeFun('metro-red');">metro-red</div>
	     
	</div>
	<div id="mm2" style="width:100px;">
		<div>Help</div>
		<div>Update</div>
		<div>about</div>
	</div>
	<div id="mm3" class="menu-content" style="width:100px;background:#f0f0f0;padding:10px;text-align:left">
		
		<p style="font-size:14px;color:#444;">V1.0</p>
	</div>
	
	 
	 
    </div>
	<div data-options="region:'west',split:true,title:'菜单栏'" style="width:235px;">
		<div class="easyui-accordion" data-options="fit:true,border:false" style="overflow:auto;">
			  	
			  	
			  	<!--  <div title="邮件群发系统" style="padding:10px;" >
					
					<ul class="ww">
						<li onclick="javascript:addTab('查看发送邮件服务','viewAllSendMail.do');"><span>查看发送邮件服务</span></li>
						<li onclick="javascript:addTab('新增发送邮件服务','addS.do');"><span>新增发送邮件服务</span></li>
						<li onclick="javascript:addTab('发送服务域管理','viewAllDoMain.do');"><span>发送服务域管理</span></li>
						<li onclick="javascript:addTab('客户管理','viewAllMailClient.do');">客户管理</li>
					<li onclick="javascript:addTab('查看接受邮件服务','viewAllRMail.do');">查看接受邮件服务</li>
						<li onclick="javascript:addTab('新增接受邮件服务','addR.do');">新增接收邮件服务</li>
						<li onclick="javascript:addTab('发送邮件','sendMail.do');">发送邮件</li>
						<li onclick="javascript:addTab('模版管理','viewTemp.do');">模版管理</li>
						<li onclick="javascript:addTab('邮件发送日志','maillog.do');">邮件发送日志</li>
					<li onclick="javascript:addTab('邮件用户浏览反馈','mailcallbacklist.do');">邮件用户浏览反馈</li>
					
						<li onclick="javascript:addTab('个性化设置','viewAllIc.do');">个性化设置</li>
						<li onclick="javascript:addTab('邮件发送统计','viewStatistics.do');">邮件发送统计</li>
					</ul>
				    
				</div>
			-->
			<!-- 	<div title="汇金交易平台" style="padding:10px">
					<ul class="ww">
					<li onclick="javascript:addTab('发送邮件','sendMail.do');">xx</li>
						<li onclick="javascript:addTab('模版管理','viewTemp.do');">xx</li>
						</ul>
				</div>
				 <div title="系统管理" style="padding:10px">
					<ul class="ww">
					<li onclick="javascript:addTab('用户管理','usersys.do');">用户管理</li>
					<li onclick="javascript:addTab('角色管理','rolesys.do');">角色管理</li>
						<li onclick="javascript:addTab('菜单管理','menusys.do');">菜单管理</li>
						<li onclick="javascript:addTab('部门管理','deptsys.do');">部门管理</li>
						<li onclick="javascript:addTab('部门数据级查看权限配置','deptviewsys.do');">部门数据级查看权限配置</li>
<li onclick="javascript:addTab('权限分配','jurisdictionsys.do');">权限分配</li>
<li onclick="javascript:addTab('修改密码','editpwdsys.do');">修改密码</li>
<li onclick="javascript:addTab('日志查询','logsearchsys.do');">日志查询</li>
						</ul>
				
			</div>--> 
			 
			 <!--  <div title="xx交易平台" style="padding:10px">

			  </div>  -->
			 
			
			 <ul id="et" class="easyui-tree" data-options="url:'viewLeftMenuJson.do?index=true',method:'get',animate:true,dnd:true"></ul>
			 
			 
			 </div> 
			 
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'右侧菜单'" style="width:100px;padding:10px;">空白区域</div>
	<div data-options="region:'south',split:true,collapsed:true" style="height:50px;background:#A9FACD;padding:10px;">xx科技</div>
	<div data-options="region:'center',title:'内容'">
	
	
	<div class="easyui-tabs" data-options="fit:true,closable:true" id="tabs"> 
	    		<div title="主页">
	    			<iframe width='100%' height='100%'  name="mailiframe" id='iframe' frameborder='0' scrolling='auto'  src='monitoring?period=jour'></iframe>
	    		</div>
	    	</div>
	</div>
	
	<div id="menu" class="easyui-menu" style="width:150px;">   
<div id="m-refresh">刷新</div>  
  <div class="menu-sep"></div>   
   <div id="m-closeall">全部关闭</div>  
     <div id="m-closeother">关闭其他</div>   
      <div class="menu-sep"></div>  
        <div id="m-close">关闭</div></div>
  <script type="text/javascript">
  	function onChangeTheme(theme){
	//alert(theme);
		var link = $('#content').find('link:first');
		link.attr('href', '/ui/themes/'+theme+'/easyui.css');
	}
$(function(){
				
	$(function(){
			$(document).bind('contextmenu',function(e){
				e.preventDefault();
				$('#menu').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			});
		});
	
	$('#et').tree({
    onClick: function(node){
    if(node.attributes.url=='/#'){
    	 $.messager.alert("系统提示","该菜单不支持跳转","info");
		                return;
    }
		addTab(node.text,'<%=path%>'+node.attributes.url);
        //alert(node.text);  // alert node text property when clicked
    }
});

	
	    	
});
 
</script>
  </body>
</html>
