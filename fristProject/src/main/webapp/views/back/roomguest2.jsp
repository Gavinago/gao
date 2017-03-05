<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+path+"/";
%>
<base href="<%=basePath %>">

</head>
<body>
<div class="panel-body">
	<div class="row">
		<div class="col-xs-12 col-sm-2">
		</div>
		<div class="col-xs-12 col-sm-8">
		<div class="panel panel-default">
			<div class="panel panel-heading">
				<div class="row ">
					<div class="col-xs-12 col-sm-2">
						<img src="<c:url value="/assets/img/Org24.png"/>"><span id="result">${roomnum}</span>
					</div>
					<div class="col-xs-12 col-sm-6 text-center">
					</div>
					<div class="col-xs-12 col-sm-4 text-right"><h1 style="cursor: pointer;display:inline;" onclick="javascript:closePage()">&times;</h1>
					</div>
				</div>
			</div>
				<div class="row ">
					<form class="form-horizontal" action="<c:url value="/back/room/guestinfo.do"/>" role="form" id="myform" method="POST">
						<div class="col-xs-12 col-sm-6">
						<div class="form-group">
									<label class="col-sm-3 control-label" for="roomsnum">房号:</label>
									<div class="col-sm-9">
											<input class="form-control" id="roomsnum" type="text" name="roomsnum"  placeholder="房号" disabled="disabled" value = "${roomsnum}">
											<input id="roomid" type="text" name="roomid" value="${roomid}" style="display: none;">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestname">姓名:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestname" name="guestname" type="text"  placeholder="顾客姓名">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestidcard">身份证号码:</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" id="guestidcard" name="guestidcard" type="text"  placeholder="身份证号码">
											<span class="input-group-addon" title="通过身份证阅读器读取"><span class="glyphicon glyphicon-list-alt"></span></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestphone">电话号码:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestphone" name="guestphone" type="text"  placeholder="电话号码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestsex">性别:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestsex" name="guestsex" type="text"  placeholder="性别">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestaddress">家庭住址:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestaddress" name="uestaddress" type="text"  placeholder="家庭住址">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" id="guestcometime" name="guestcometime" type="text"  placeholder="入住日期">
											<span class="input-group-addon" title="日历"><span class="glyphicon glyphicon-calendar" ></span></span>
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-4">
									</div>
									<div class="col-sm-4 text-left">
										<input type="submit" class="btn btn-default"  value="提交">
									</div>
									<div class="col-sm-4 ">
										<input type="reset" class="btn btn-default" value="取消">
									</div>
								</div>
						</div>
						</form>
						<div class="col-xs-12 col-sm-6">
							<div class="col-xs-12 col-sm-11">
								<div class="thumbnail panel-body">
									<img id="imgShow" alt="" style="width: 400px; height: 265px;" src="<c:url value="assets/img/user128c.png"/>">
								</div>
									
							</div>
							<div class="col-xs-12 col-sm-1">
							</div>
						</div>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-2">
		</div>
	</div>
</div>

</body>
</html>


