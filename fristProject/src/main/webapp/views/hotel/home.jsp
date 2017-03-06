<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
<script type="text/javascript">
$(function(){
	$("#search").on("click",function(){CCC();});
});

function CCC(){
	var cityName = $("#cityName").val();
	$.ajax({
		url:"<c:url value='/getWeather.do'/>",
		type:"post",
		data:"cityName="+cityName,
		success:function(data){
			alert(data);
			
		},
		error:function(e){
			alert(e+"cuowu ");
		}
	});
}
// function bbb(){
// dojo.xhrPost({
//     url: "stop.do",
//     load: function(response, ioArgs){
//         //用response干一些事
//         alert("success  "+response);
//         console.log("xhr get success:", response);
//         dojo.byId("divv").append("<br/>"+response);
//     return response; //必须返回response
//     },
//     error: function(response, ioArgs){
//     	alert("error");
//         console.log("xhr get failed:", response);
//         return response; //必须返回response
//     }
// });
//}
</script>
</head>
<body>
<c:if test="${not empty weather}" >
 城市名：<label>${weather[1]}</label>&nbsp;&nbsp;&nbsp;&nbsp; 气温 : <label>${weather[5]}</label>&nbsp;&nbsp;&nbsp;&nbsp;<img src="<c:url value='/one/assets/img/weather/${weather[8]}'/>">&nbsp;&nbsp;&nbsp;&nbsp;<img src="<c:url value='/one/assets/img/weather/${weather[9]}'/>">&nbsp;&nbsp;&nbsp;&nbsp;<label>${weather[10]}</label>
</c:if>
<hr>
<div id="divv"><button id="but" >click me</button></div><hr>
<button id="butt" >stop me</button><br/><hr>
城市名:<input id="cityName" type="text" name="cityName"/>
<input id="search" type="submit" name="submit" value="查询"/>
</body>
</html>