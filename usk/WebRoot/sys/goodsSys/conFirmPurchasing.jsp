<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购合同确认</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@include file="../../common/common.jsp" %>
  </head>
  
  <body>
   <form id="cghtForm" method="post" action="pmtempView.do" onsubmit="return false;">
    <table width="500">
    <tr>
    <td>合约编号：</td><td>${PMNumber}</td>
    </tr>
     <tr>
    <td>签订时间：</td><td><input class="easyui-datebox" id="qdsj" name="qdsj" data-options="required:true,formatter:myformatter,parser:myparser"></input></td>
    </tr>
     <tr>
    <td>出卖人：</td><td>${cpm.sellcompanyname}</td>
    </tr>
     <tr>
    <td>买受人：</td><td>${buyername }</td>
    </tr>
     <tr>
    <td>产品名称、规格：</td><td>${cpm.vmname}</td>
    </tr>
     <tr>
    <td>重量（吨）：</td><td>${cpm.realweight}</td>
    </tr>
     <tr>
    <td>单价（元/吨）：</td><td>${cpm.addedprice}</td>
    </tr>
     <tr>
    <td>金额(元)：</td><td>${jine}</td>
    </tr>
     <tr>
    <td>交货时间：</td><td><input class="easyui-datebox" id="jhsj" name="jhsj" data-options="required:true,formatter:myformatter,parser:myparser">前</td>
    </tr>
     <tr>
    <td>合同有效期：</td><td><input class="easyui-datebox" id="htyxq1" name="htyxq1" data-options="required:true,formatter:myformatter,parser:myparser">到
    <input class="easyui-datebox" id="htyxq2" name="htyxq2" data-options="required:true,formatter:myformatter,parser:myparser"></td>
    </tr>
     <tr>
    <td>
    <input type="button" value="提交" onclick="submits()">
   <!--  <a href="javscript:void(0);" onclick="submits()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">提交</a></td><td></td>
    -->
    </tr>
    </table>
    </form>
    
     <script type="text/javascript">
 
     
     
   function  submits(){
   //alert($("#cghtForm").form('validate'));
   var v=$("#cghtForm").form('validate');
    if(!v){	//easyuiForm表单验证
		return false;
	}
	var htsj=$("#qdsj").datebox("getValue"); 
	var jhsj=$("#jhsj").datebox("getValue"); 
	var htyxq1=$("#htyxq1").datebox("getValue"); 
	var htyxq2=$("#htyxq2").datebox("getValue"); 
  // alert(1);
  parent.addPanel('pmtempView.do?orderid=${cpm.id}&htbh=${PMNumber}&htsj='+htsj+"&jhsj="+jhsj+"&htyxq1="+htyxq1+"&htyxq2="+htyxq2,'采购合同'+new Date());
   
   }
     
     
     $(function(){
   var curr_time = new Date();
   var strDate = curr_time.getFullYear()+"-";
   strDate += curr_time.getMonth()+1+"-";
   strDate += curr_time.getDate();	//+"-"
  // strDate += curr_time.getHours()+":";
   //strDate += curr_time.getMinutes()+":";
   //strDate += curr_time.getSeconds();
    var strDate2 = curr_time.getFullYear()+"-";
   strDate2 += curr_time.getMonth()+2+"-";
   strDate2 += curr_time.getDate();
   
   
      var strDate3 = curr_time.getFullYear()+"-";
   strDate3 += curr_time.getMonth()+1+"-";
   strDate3 += curr_time.getDate()+2;
   
   $("#qdsj").datebox("setValue", strDate); 
   $("#jhsj").datebox("setValue", strDate3); 
   $("#htyxq1").datebox("setValue", strDate); 
   $("#htyxq2").datebox("setValue", strDate2); 
  });
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
	</script>
  </body>
</html>
