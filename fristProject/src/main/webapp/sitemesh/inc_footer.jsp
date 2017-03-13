<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src='<c:url value="/assets/js/jquery-2.1.4.min.js"/>'></script>
<script type="text/javascript" src='<c:url value="/assets/js/bootstrap.min.js"/>'></script>
<script type="text/javascript">
/*
	获得当前时间
*/
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
<footer class="navbar navbar-default navbar-fixed-bottom text-center">${footer}</footer>
