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
					<form class="form-horizontal" role="form" id="myform" enctype="multipart/form-data" method="POST">
						<div class="col-xs-12 col-sm-6">
								<div class="form-group">
									<label class="col-sm-3 control-label" for="roomsnum1">房号:</label>
									<div class="col-sm-9">
											<input class="form-control" id="roomsnum1" type="text" name="roomsnum1" value = "${guest.guestroomname}" disabled="disabled"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestname">姓名:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestname" name="guestname" type="text"  value="${guest.guestname}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestidcard">身份证号码:</label>
									<div class="col-sm-9">
											<input class="form-control" id="guestidcard" name="guestidcard" type="text"  value="${guest.guestidcard}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestphone">电话号码:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestphone" name="guestphone" type="text" value="${guest.guestphone}" >
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestsex">性别:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestsex" name="guestsex" type="text"  value="${guest.guestsex}" >
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestaddress">家庭住址:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestaddress" name="uestaddress" type="text"  value="${guest.guestaddress}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestcometime">入住日期:</label>
									<div class="col-sm-9">
											<input class="form-control" id="guestcometime" name="guestcometime" type="text"  value="${guest.guestcometime}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestleavetime">离开日期:</label>
									<div class="col-sm-9" >
											<input class="form-control" id="guestleavetime" name="guestleavetime" type="text"  value="${guest.guestleavetime}">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4">
									</div>
									<div class="col-sm-4 text-left">
										<input type="button" id="submitbutton"  class="btn btn-default" onclick="javascript:submitbut();" value="退房">
									</div>
									<div class="col-sm-4 ">
										<input type="reset" class="btn btn-default" value="取消">
									</div>
								</div>
						</div>
							<textarea id="guestimg" name="guestimg" style="display: none;"></textarea>  
						</form>
						<div class="col-xs-12 col-sm-6">
							<div class="col-xs-12 col-sm-11">
								<div class="thumbnail panel-body">
									<img id="imgShow" alt="" style="width: 400px; height: 265px;" src="${guest.guestimg}">
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
<script type="text/javascript">
function closePage(){
	window.close();
}
</script>
</body>
</html>