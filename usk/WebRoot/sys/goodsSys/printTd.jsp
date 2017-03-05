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
    
    <title>打印提单确认</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style>
body{ font-size:12px; color:#000000; font-size:14px;line-height:26px;font-family:"宋体";}

a { color:#000000; font-size:14px;line-height:26px; font-family:"宋体"; text-decoration:none; }
a:hover { color:#000000; font-size:14px; line-height:26px; font-family:"宋体"; text-decoration:none;}

.bt{color: #121212;font-size: 26px; font-weight:bold; line-height:80px;text-align: center;}

.A4 { margin: 0 auto;width: 794px;}
.f_12{ font-size:12px;}
.f_red{ color:#F00}
.t_tab td{ padding-left:10px;}
</style>
  </head>
  
  <body>
    <form:form action="printTd.do" method="post"  commandName="td" id="sysForm">
   <div class="A4">
<table width="100%" border="0" cellspacing="1" cellpadding="1">
<tr>
    <td><div class="bt">宁波市镇海汇金大通有色金属储备交易中心有限公司</div></td>
  </tr>
  <tr>
    <td><div class="bt">提货 单</div></td>
  </tr>
  <tr>
    <td align="right">提货单号：${ob.ownum }
     <input type="hidden" name="owid" value="${ob.owid }">
      <input type="hidden" name="ownum" value="${ob.ownum }">
    </td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666" class="t_tab";>
  <tr>
    <td width="50%" height="30" bgcolor="#FFFFFF">提货单位：${ob.buyerCompanyName }
    <input type="hidden" name="buyerCompanyName" value="${ob.buyerCompanyName }"></td>
    <td width="50%" height="30" bgcolor="#FFFFFF">存货单位<strong>:${ob.sellerCompanyName }</strong>
    <input type="hidden" name="sellerCompanyName" value="${ob.sellerCompanyName }">
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">货物名称：${ob.vmname }
    <input type="hidden" name="vmname" value="${ob.vmname }">
    </td>
    <td height="30" bgcolor="#FFFFFF">仓单编号：${ob.goodsnum }
     <input type="hidden" name="goodsnum" value="${ob.goodsnum }">
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">进仓重量（MT）：${ob.weight }
      <input type="hidden" name="weight" value="${ob.weight }">
    </td>
    <td height="30" bgcolor="#FFFFFF">出仓重量（MT）：${ob.realweight }
      <input type="hidden" name="realweight" value="${ob.realweight }">
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">存放货位：${ob.hw }
     <input type="hidden" name="hw" value="${ob.hw }">
    </td>
    <td height="30" bgcolor="#FFFFFF">提货日期：${ob.cksj }
      <input type="hidden" name="cksj" value="${ob.cksj }">
    
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">原存货箱号（车号）：${ob.xh } |${ob.ch }
       <input type="hidden" name="xh" value="${ob.xh }">

     <input type="hidden" name="ch" value="${ob.ch }">
    </td>
    <td height="30" bgcolor="#FFFFFF">提货箱号（车号）：${ob.ckxh } |${ob.trucknum }
     <input type="hidden" name="trucknum" value="${ob.trucknum }">
          <input type="hidden" name="ckxh" value="${ob.ckxh }">
    </td>
  </tr>
  <tr>
    <td height="30" colspan="2" bgcolor="#FFFFFF">合同编号：${ob.salesnum }
    
     <input type="hidden" name="salesnum" value="${ob.salesnum }">
    </td>
    </tr>
  <tr>
    <td height="30" colspan="2" bgcolor="#FFFFFF">备    注：${ob.remark }
    
         <input type="hidden" name="remark" value="${ob.remark }">
    </td>
    </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">业务受理员：${ob.owclUserName }
     <input type="hidden" name="owclUserName" value="${ob.owclUserName }">
     </td>
    <td height="30" bgcolor="#FFFFFF">出单日期：${ob.cksj }
    
    </td>
  </tr>
</table>
<input value="确认并打印提单信息" type="submit" >
</div>

</form:form>
  </body>
</html>
