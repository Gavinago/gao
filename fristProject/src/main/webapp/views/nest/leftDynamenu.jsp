<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
${leftNavigation}
<script type="text/javascript">
var roomurl = null;
var pageNum = null;
var PageSize = 15;
var searchText=null;
var roomname=[];
roomtype();
function roomtype(){
	<c:forEach items="${step}" var="item">
		roomname[${item.clazz}]="${item.stepname}";
	</c:forEach>
}
changeroom("<c:url value='/ajax/selectAllRoom.do?clazz=220'/>","");
function changeroom(args,param){
	pageNum = 1;
	$.ajax({
		url:args,
		dateType:"json",
		type:"get",
		success:function(data){
			roomurl = args;
			$("#ajaxcontent").html(data);
			$("#result").text(roomname[args.substring(args.indexOf("=")+1)]);
			for(var i =0;i< $("#accordion2 a").size();i++){
				$("#accordion2 a").eq(i).removeClass("active");
			}
			
			if(param==""){
				$("#accordion2 a").eq(1).addClass("active");
			}else{
				$(param).addClass("active");
			}
		},
		error:function(){
			$("#ajaxcontent").html('<img src="<c:url value="/assets/img/wait5.gif"/>" />');
		}
	})
}

function changpage(pageNum,searchText,roomurl){
	$.ajax({
		url:roomurl+"&pageNum="+pageNum+"&PageSize="+PageSize+"&searchText="+searchText,
		dateType:"json",
		type:"get",
		success:function(data){
			$("#ajaxcontent").html(data);
			$("#searchText").val(searchText);
		},
		error:function(){
			$("#ajaxcontent").html('<img src="<c:url value="/assets/img/wait5.gif"/>" />');
		}
	})
}

function RefreshCurrentButton(param){
	pageNum = param;
	searchText = $("#searchText").val();
	changpage(pageNum,searchText,roomurl);
}
function doSearch(args){
	if(args=='Manul'){
		RefreshCurrentButton(pageNum);
	}
}
function ajaxOperation( url,id,action){
	$.ajax({
		url:url+"?guestid="+id+"&action="+action+"&pageNum="+pageNum+"&PageSize="+PageSize+"&SearchText="+SearchText,
		type:"get",
		dataType:"html",
		success:function(data){
			alert("chengg");
		},
		error:function(e){
			alert("error"+e);
		}
	});
}
</script>
