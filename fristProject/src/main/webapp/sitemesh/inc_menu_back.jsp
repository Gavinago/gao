<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@
	taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-collapse-menu"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
	
			<div class="navbar-brand" style="padding-top: 5px">
				<img alt="" src='${site_icon}'>
			</div>
			<div class="navbar-brand">
				<strong>${site_name}</strong>
			</div>
		</div>
	
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-collapse-menu">
			<jsp:include page="/widget/back/dynamenu.do" />
			<shiro:guest>
				<!-- 未登录菜单 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="login.do">登录</a></li>
				</ul>
			</shiro:guest>
			<shiro:user>
				<!-- 已登录菜单 -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><shiro:principal></shiro:principal><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="javascript:MyLogout()">注销</a></li>
							<li><a href="user/profile.do">我的资料</a></li>
							<li><a href="one/user/password.do">修改密码</a></li>
						</ul>
					</li>
				</ul>
			</shiro:user>
		</div>
	</div>
</nav>
