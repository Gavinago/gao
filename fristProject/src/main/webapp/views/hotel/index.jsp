<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>

<%
	//设置页面的相对路径
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
</head>

<body>
<script type="text/javascript" src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/assets/js/jquery.md5.js"/>"></script>
	<br />
	<div id="totaldiv">
		<form id="loginForm" action="<c:url value="/page/user/login.do"/>" method="post">
			<table border="1">

				<tr>
					<td colspan="1" rowspan="4"><img
						src="<c:url value="/assets/img/zxing.jpg"/>" alt="二维码"></td>
					<td>account :</td>
					<td><input id="userName" type="text" name="userName" /></td>
					<td><span class="aaa" id="userNameError">${errorMsg}</span></td>
				</tr>
				<tr>
					<td>password:</td>
					<td><input id="password" type="password" name="password" /></td>
					<td><span class="aaa" id="passwordError">${passwordError}</span></td>
				</tr>
				<tr>
					<td>code :</td>
					<td><input type="text" name="verification" /></td>
					<td><img id="kaptcha" src="<c:url value="/assets/img/kaptcha.jpg"/>"
						alt="验证码" title="点击刷新"></span></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
					<td><input type="reset" value="cancel"></td>
				</tr>

			</table>
		</form>
		<div id="hintdiv">
			公告一

			<fmt:bundle basename="test" >
			<fmt:message key="hello.one"></fmt:message>
			<fmt:message key="hello" ></fmt:message>
			</fmt:bundle>
		</div>
		<div id="versiondiv">公告二</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$("#kaptcha").click(
					function() {
						$(this).hide().attr(
								'src',
								'one/assets/img/kaptcha.jpg?'
										+ Math.floor(Math.random() * 100))
								.fadeIn();
					});
			$("#loginForm").submit(function(){
				var username = $("#userName").val();
				var password = $("#password").val();
				var password1 = $.md5(password);
				var password2 = $.md5(username+password1);
				$("#password").val(password2);
			});
		});
		
		
	</script>
</body>
</html>