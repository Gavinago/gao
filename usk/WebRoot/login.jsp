<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title><spring:message code="App.Title"></spring:message> </title>
   
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/css/base.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/css/magiczoom.css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/ui/css/typography.css">
	<script type="text/javascript" src="js/contacts.js"></script>
    <style>
    /*登录页面*/
.login_bj{ background:url(<%=path%>/images/ay.jpeg) repeat-x center top;}
.logon_window{ width:486px; height:262px; margin:0 auto; margin-top:270px; padding:0px 30px 0px 20px; position:relative;}
.logon_window dl{ width:auto; height:58px; margin-top:20px;}
.logon_window dl dt{ width:90px; height:58px; float:left; color:#767c8b; line-height:58px; font-size:24px; font-family:"微软雅黑"; font-weight:500; padding-left:10px;}
.logon_window dl dd{}
.login_input{ width:248px; height:58px; float:left; background:url(<%=path%>/images/t_input.jpg) no-repeat left top; border:none; text-indent:10px;font-size:14px;color:#767c8b; font-family:"微软雅黑";}
.login_btn{ width:121px; height:135px; position:absolute;background:url(<%=path%>/images/login_btn.jpg) no-repeat left top; border:none; top:20px; right:30px;}
.login_input_yzm{ width:165px; height:58px; float:left; background:url(<%=path%>/images/yzm_input.jpg) no-repeat left top; border:none; text-indent:10px;font-size:14px;color:#767c8b; font-family:"微软雅黑";}
.login_yzm{ width:auto; float:left; padding-left:20px;}
.login_yzm .yzm_pic{ width:200px; height:50px;}
.login_yzm .yzm_wz{ width:200px; height:20px; line-height:20px; text-align:center; font-size:14px;color:#ff6600; font-family:"微软雅黑";}
.login_yzm .yzm_wz a{height:20px; line-height:20px;font-size:14px;color:#ff6600; font-family:"微软雅黑";}

.login_foot{ margin-top:60px; text-align:center; line-height:40px; color:#c67f78;}
    .logon_window .tip{ position:absolute; left:15px; bottom:6px; color:#F00}
    </style>
    <title><spring:message code="App.Title"></spring:message></title>


 <script type="text/javascript">
        // 不允许此页面被作为iframe页面
        if (top.location != location)
            top.location.href = location.href;
        self.moveTo(0, 0);
        self.resizeTo(screen.availWidth, screen.availHeight);
    </script>

  </head>
  
  <body class="login_bj" >
    <form:form action="loginValid.do" method="post" commandName="login" modelAttribute="login">
    	<div class="logon_window">

<dl>
<dt>账　号</dt>
<dd>
 <form:input path="account" class="login_input"/>
 <form:errors path="account" cssStyle="color:red"></form:errors>
</dd>
</dl>
<dl>
<dt>密　码</dt>
<dd>
 <form:password path="pwd" class="login_input"/>

</dd>
</dl>
<dl style="height:auto;">
<dt>验证码</dt>
<dd>
<input type="text" name="captcha"  class="login_input_yzm"/>
<div class="yam_pic"><img alt="验证码" src="images/kaptcha.jpg" title="点击更换"  
                    id="img_captcha" onclick="javascript:refreshCaptcha();"></div>
<div class="login_yzm">

<div class="yzm_wz"><a href="javascript:void(0)" onclick="javascript:refreshCaptcha()">看不清换一张</a></div>
<div class="tip">${message}  </div>
<input type="hidden"   name="remembered" value="true">
<!-- 记住我 <input type="checkbox"   name="remembered"> -->

</div>
</dd>
</dl>

<input type="submit" value="" class="login_btn" />
</div>
    </form:form>
    <div class="login_foot">
© 2014-2512 暗影涂涂版权所有    技术支持：暗影刺客
</div>
    <script type="text/javascript">  
function refreshCaptcha(){  
    document.getElementById("img_captcha").src="<%=basePath%>images/kaptcha.jpg?t=" + Math.random();  
}  
</script> 
  </body>
</html>
