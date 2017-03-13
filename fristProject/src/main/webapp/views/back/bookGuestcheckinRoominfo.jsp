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
											<input class="form-control" id="guestroomname" type="hidden" name="guestroomname" value = "${guest.guestroomname}"/>
											<input class="form-control" id="guestroomid" type="hidden" name="guestroomid" value = "${guest.guestroomid}"/>
											<input class="form-control" id="guestid" type="hidden" name="guestid" value = "${guest.guestid}"/>
											<input class="form-control" id="gueststate" type="hidden" name="gueststate" value = "${guest.gueststate}"/>
											<input class="form-control" id="roomcash" type="hidden" name="guestcash" value = "${guest.guestcash}"/>
											<input class="form-control" id="guestbooktime" type="hidden" name="guestbooktime" value = "${guest.guestbooktime}"/>
											<input class="form-control" id="roomsnum1" type="text" name="roomsnum1" value = "${guest.guestroomname}" disabled="disabled"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestname">姓名:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestname" name="guestname" type="text"  placeholder="顾客姓名" disabled="disabled" value = "${guest.guestname}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestidcard">身份证号码:</label>
									<div class="col-sm-9">
										<div class="input-group">
											<input class="form-control" id="guestidcard" name="guestidcard" type="text"  placeholder="身份证号码" value = "${guest.guestidcard}">
											<span class="input-group-addon" title="通过身份证阅读器读取"><span class="glyphicon glyphicon-list-alt"></span></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestphone">电话号码:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestphone" name="guestphone" type="text"  placeholder="电话号码" value = "${guest.guestphone}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestsex">性别:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestsex" name="guestsex" type="text"  placeholder="性别" value = "${guest.guestsex}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestaddress">家庭住址:</label>
									<div class="col-sm-9">
										<input class="form-control" id="guestaddress" name="uestaddress" type="text"  placeholder="家庭住址" value = "${guest.guestaddress}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestcometime">入住日期:</label>
									<div class="col-sm-9">
										<div class="input-group date" id='datetimepicker6'>
											<input class="form-control" id="guestcometime" name="guestcometime" type="text" disabled="disabled" placeholder="入住日期" value = "${guest.guestcometime}">
											<span class="input-group-addon" title="日历"><span class="glyphicon glyphicon-calendar" ></span></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="guestleavetime">离开日期:</label>
									<div class="col-sm-9" >
										<div class="input-group date" id='datetimepicker7'>
											<input class="form-control" id="guestleavetime" name="guestleavetime" type="text"  placeholder="离开日期" value = "${guest.guestleavetime}">
											<span class="input-group-addon" title="日历"><span class="glyphicon glyphicon-calendar"></span></span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-4">
									</div>
									<div class="col-sm-4 text-left">
										<input type="button" id="submitbutton"  class="btn btn-default" onclick="javascript:submitbut();" value="提交">
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
									<img id="imgShow" alt="" style="width: 400px; height: 265px;" src="<c:url value="assets/img/user128c.png"/>">
								</div>
									<button type="button" onclick="javascript:updateImg()" class="btn btn-default btn-lg" title="本地上传"><span class="glyphicon glyphicon-cloud-upload"></span></button>
									<button type="button" class="btn btn-default btn-lg" title="打开相机拍照"><span class="glyphicon glyphicon-camera"></span</button>
									<input type="file" id="img" style="display: none;">
									<input type="button" id="guestimgfilebut" style="display: none;" >
									
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
<input id="guestorder" type="hidden" >
<canvas id="cans"></canvas>
<script type="text/javascript">
var img = document.getElementById('img'),imgShow= document.getElementById('imgShow') 
$(function () {
	var flag1 =${not empty guest.guestimg};
	if(flag1){
		$("#imgShow").attr("src","${guest.guestimg}");
		 $("#guestimg").text("${guest.guestimg}")
	}
    $("#img").change(function(){
		uploadfile(this.files[0]);
	});
    (function () {
    $('#guestorder').click(function () {
    	var Htmldata="";
    	var guestroomid = $("#guestroomid").val();
    	var guestcometime = $("#guestcometime").val();
    	$.ajax({
    		url:'<c:url value="/ajax/guestOrder.do"/>',
    		data:"guestroomid="+guestroomid+"&guestcometime="+guestcometime,
    		dateType:"json",
    		async:false,
    		type:"get",
    		success:function(data){
    			Htmldata = data;
    		}
    	});
        Lobibox.window({
        	title: '订单信息',
            //Available types: string, jquery object, function
            content: function () {
                return Htmldata;
            }
        });
    });
    })();
});
addTime("guestcometime");
function addTime(param){
	var dayTime =getNowFormatDate();
	$("#"+param).val(dayTime);
}
function closePage(){
	window.close();
}
function updateImg(){
	$("#img").click();
}
function uploadfile(obj) {
	if(!/image\/\w+/.test(obj.type)){  
        alert("请确保文件为图像类型");  
        return false;  
      }
//	reduceimg(obj);
	startt();
}

function submitbut(){
	addTime("guestcometime");
	$.ajax({
		url:"<c:url value='/back/room/guestinfo.do'/>",
		type:"post",
		dataType:"json",
		data:$('#myform').serialize(),
		success:function(data){
			if(data[0]==1){
				$("#guestorder").click();
				$("#submitbutton").attr("disabled",true);
			}else if(data[0]==-1){
				alert("用户信息不能为空！");
			}if((data[1]!=""&&data[1]!=null)||(data[2]!=""&&data[2]!=null)){
				var str = data[1]||data[2];
				alert(str);
			}
		},
		error: function(data){
			alert("error "+data);
		}
	})
} 
//    cpData.addEventListener('click', cpDataF);

/**
 * 上传前先压缩图片
 */
function reduceimg(objj){
	alert(objj.type+"----"+objj.wwidth);
// 	var width = objj.width;
// 	var height = objj.height;
// 	var scale = width/height;
// 	var width1 =300px;
// 	var height1 = parseInt(width1 / scale);
// 	var canvas = $("#cans");
// 	var canvas[0].width = width1;
// 	var canvas[0].height = height1;
// 	var ctx = canvas[0].getContext('2d');
// 	ctx.drawImage(img_this,0,0,width,height,0,0,width1,height1);
// 	var cropStr = canvas[0].toDataURL("image/jpeg",0.7);
//	startt();
// 	alert(objj+"----"+width);
}

/*转换函数*/  
function startt() {
//   	var casv = $("#cans");
    var imgFile = new FileReader();  
    imgFile.readAsDataURL(img.files[0]); //imgFile.readAsDataURL(img.files[0]); 
    imgFile.onload = function () { 
         
        var imgData = this.result; //base64数据    
        filesize(imgData);
    }  
} 
/**
 *验证文件大小 
 */
function filesize(param) {
	//param.length
    	imgShow.setAttribute('src', param);  
        $("#guestimg").text(param);
}
    /*复制数据*/  
//     function cpDataF() {  
//         conte.select(); // 选择对象    
//         var cpd=document.execCommand("Copy"); // 执行浏览器复制命令    
//         cpd ? document.getElementById('succ').innerHTML = '复制成功' :console.warn('复制失败');  
//         window.setTimeout(function () {  
//             document.getElementById('succ').innerHTML = '';  
//         }, 1000)  
//     }  
    
    
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds(); 
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if(hours>=0&&hours<=9){
    	hours = "0"+hours;
    }
    if(minutes>=0&&minutes<=9){
    	minutes ="0"+minutes;
    }
	if(seconds>=0&&seconds<=9){
		seconds = "0"+seconds;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + hours + seperator2 + minutes
            + seperator2 + seconds;
    return currentdate;
} 
</script> 

</body>
</html>


