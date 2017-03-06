<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Shiro标签库 -->
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!-- SiteMesh标签库 -->
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<?xml version="1.0" encoding="UTF-8" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	//设定页面的相对基路径
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";	
	/*
	if(!request.getServerName().equals("www.sxshequ.cn")){
		response.sendRedirect("http://"+request.getServerName());
	}*/
%>
<base href="<%=basePath%>">
<title>用户登录</title>
</head>
<!-- 上面的部分会被 SiteMesh模板置换，所以任意内容均可 -->
<body>
	<link href='<c:url value="/assets/css/bootstrap.min.css"/>' rel="stylesheet">
	<link href='<c:url value="/assets/css/hotel.css"/>' rel="stylesheet">
	<link href='<c:url value="/assets/css/login.css"/>' rel="stylesheet">

	<form id="myform" action="<c:url value="/page/user/login.do"/>" method="post">
	<div style="margin: 50px"></div>
	<div class="hidden-xs" style="margin: 100px"></div>
	
	<div style="max-width: 500px;margin: 100px auto;">
		<!-- 用户身份板块 -->
		<div class="panel panel-success" id="unlogin">
			<div class="panel-heading">
				<strong><span class="glyphicon glyphicon-home" aria-hidden="true"></span> 用户登录</strong>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-sm-4 hidden-xs text-center">
						<div class="row" style="padding-top: 20px">
							<img src="<c:url value="/assets/img/home128.jpg"/>" />
						</div>			
						<div clas="row">
							<img src="<c:url value="/assets/img/zxing.jpg"/>" />
						</div>		
					</div>
					<div class="col-xs-12 col-sm-8">
						<div style="padding: 12px;padding-top: 20px">
							<div class="input-group">
								<span class="input-group-addon" aria-hidden="true">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</span>
								<input id="userName" name="userName" type="text" class="form-control"
										style="font-size: 16px"
									placeholder="登录账号" aria-describedby="basic-addon1">
							</div>
						</div>						
						<div style="padding: 12px">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</span>
								<input id="password" name="password" type="password" class="form-control"
										style="font-size: 16px"
									placeholder="登录密码" aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="row" style="padding-left:15px;padding-right:15px">
							<div class="col-xs-12 col-sm-6" style="padding: 12px">
								<div class="input-group">
									<span class="input-group-addon" >
										<span class="glyphicon glyphicon-ok"></span>
									</span>
									<input id="kaptcha" name="verification" type="text" class="form-control"
										style="font-size: 16px;text-transform: uppercase;"
										placeholder="验证码"  onkeyup="value=value.replace(/[^\w\.\/]/ig,'')">
								</div>
							</div>
							
							<div class="col-xs-12 col-sm-6 text-center"
								style="padding: 12px">
								<img style="width:100%;height: 50px; cursor: pointer;"class="img-rounded" src="assets/img/kaptcha.jpg" id="kaptchaImage"
									 alt="点击更换验证图片" title="点击更换验证图片" aria-describedby="basic-addon1" />
							</div>
						</div>
						<div class="row" style="padding-left:15px;padding-right:15px">
							<div class="col-xs-12 col-sm-6" style="padding: 12px">
								<input class="btn btn-primary btn-block" type="submit" value="登录"></button>
							</div>
							<div class="col-xs-12 col-sm-6" style="padding: 12px">
								<a disabled="disabled" href="#" class="btn btn-primary btn-block">忘记密码</a>
							</div>
						</div>
						<c:if test="${not empty qq_enable}">
							<div class="row" style="padding-left:15px;padding-right:15px">
								<div class="col-xs-12" style="padding: 12px">
									第三方登录： <span id="qqLoginBtn"></span>								
									<!--  
									<a href="javascript:MyQQLogin()" class="btn btn-primary btn-block">
										<img src="assets/oauth/qq16.png" />
										QQ登录
									</a>
									-->
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<div class="panel-footer">
				<div id="prompt" class="row text-center" style="color: ${color};">
					${site}
				</div>
			</div>
		</div>
		<c:if test="${tips!=null}">
		<div class="alert alert-danger" role="alert">${tips}</div>
		</c:if>
	</div>
	</form>
<%-- <content tag="footerscript"> --%>
<script type="text/javascript" src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/jquery.md5.js"/>"></script>

<script type="text/javascript" 
	src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
	data-appid="${qq_appid}" 
	data-redirecturi="${qq_redirecturi}" 
	charset="utf-8"></script>

<!-- document ready 事件处理 --> 
<script type="text/javascript">    
$(function() {
	$("#kaptchaImage").click(
			function() {
				$(this).hide().attr(
						'src',
						'<c:url value="/assets/img/kaptcha.jpg"/>?'
								+ Math.floor(Math.random() * 100))
						.fadeIn();
			});
	$("#myform").submit(function(){
		flag =vlidation();
		if(flag){
			var username = $("#userName").val();
			var password = $("#password").val();
			var password1 =$.md5(password);
			var password2 =$.md5(username+password1);
			$("#password").val(password2);
			return true;
		}else{
			return false;
			
		}
	});
});

function vlidation(){
	var username = $("#userName").val();
	var password = $("#password").val();
	var kaptcha = $("#kaptcha").val();
	if(!kaptcha){
		$("#prompt").css("color","red");
		$("#prompt").text("验证码不能为空！");
		flag =  false;
	}else if(kaptcha.length!=4){
			$("#prompt").css("color","red");
			$("#prompt").text("验证码错误！");
			flag =  false;
	}else if(!username){
			$("#prompt").css("color","red");
			$("#prompt").text("用户名不能为空！");
			flag =  false;
	}else if(!password){
			$("#prompt").css("color","red");
			$("#prompt").text("密码不能为空！");
			flag =  false;
	}else{
		flag = true;
	}
	return flag;
}
function MyAddQQConnect(){
	//qq登录注册
	//QC.Login({
		//插入按钮的节点id
       //btnId:"qqLoginBtn",
       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
       //size: "B_S"
	//});
	//调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中
	   QC.Login({
	       //btnId：插入按钮的节点id，必选
	       btnId:"qqLoginBtn",    
	       //用户需要确认的scope授权项，可选，默认all
	       scope:"all",
	       //按钮尺寸，可用值[A_XL| A_L| A_M| A_S|  B_M| B_S| C_S]，可选，默认B_S
	       size: "B_S"
	   }, function(reqData, opts){//登录成功
			alert(opts);		  
	       //根据返回数据，更换按钮显示状态方法
	       var dom = document.getElementById(opts['btnId']),
	       _logoutTemplate=[
	            //头像
	            '<span><img src="{figureurl}" class="{size_key}"/></span>',
	            //昵称
	            '<span>{nickname}</span>',
	            //退出
	            '<span><a href="javascript:QC.Login.signOut();">退出</a></span>'    
	       ].join("");
	       dom && (dom.innerHTML = QC.String.format(_logoutTemplate, {
	           nickname : QC.String.escHTML(reqData.nickname), //做xss过滤
	           figureurl : reqData.figureurl
	       }));
	   }, function(opts){//注销成功
	         alert('QQ登录 注销成功');
	   }
	);
}

function MyQQLogin(){
	QC.Login.showPopup({
	   appId:"${qq_appid}",
	   redirectURI:"${qq_redirecturi}"
	});	
}
</script>
<%-- </content> --%>

</body>
</html>
