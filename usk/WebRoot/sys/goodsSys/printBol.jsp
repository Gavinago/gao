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
    
    <title>打印提单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@include file="../../common/common.jsp" %>
	<!--  
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>   
<script type="text/javascript" src="js/jquery.PrintArea.js"></script>--> 
<style type="text/css">
.divcss5-4{border-bottom:1px dashed #000; height:30px;width:650px;margin-left: auto;margin-right: auto;}
</style>
<style>
body,html,div,p{ font-size:14px; margin:0px; padding:0px;}
.ruku_code{ height:50px; line-height:50px; text-align:center; width:100%; font-size:18px; font-weight:bold}
.sub_title{ font-weight:normal; font-size:16px; height:50px; line-height:50px;}
.code_huowu{ height:30px; line-height:30px; text-align:right;}
.clfs{ vertical-align:sub; _vertical-align:middle}
.clfs_span { display:inline-block;  padding-right:30px;}
.desc{ height:40px; line-height:40px; text-align:center;}
.table_block{ padding:10px;}
.table_block td{ padding:0px 5px;}
.gaizhang{padding-top:50px;}
.gaizhang p{ text-align:right; height:22px; padding:0px 10px;  line-height:22px;}

</style>
<script>  
//jq打印
/* $(document).ready(function(){  
  $("input#biuuu_button").click(function(){  
  
  
  
  $("div#myPrintArea").printArea();  
  
});  
});  */ 
   
</script> 
  </head>
  
  <body>
      <form:form action="saveBol.do" method="post"  commandName="bol" id="sysForm" >
 <div class="ruku_code">提货单</div>
<div class="sub_title">仓库：${cpm.dwname }（盖章）<input type="hidden" name="dwname" value="${cpm.dwname }"></div>
<div class="code_huowu">提货单号：${bolnum}<input type="hidden" name="bolnum" value="${bolnum}">
<input type="hidden" name="orderid" value="${cpm.id}">
<input type="hidden" name="ordernum" value="${cpm.ordernum}">
</div>
<div class="table_block">
<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#CCCCCC">
  <tr>
    <td height="30" bgcolor="#FFFFFF">仓单号</td>
    <td height="30" bgcolor="#FFFFFF">品名</td>
    <td height="30" bgcolor="#FFFFFF">产地</td>
    <td height="30" bgcolor="#FFFFFF">规格</td>
    <td height="30" bgcolor="#FFFFFF">重量（吨）</td>
    <td height="30" bgcolor="#FFFFFF">成交价</td>
  </tr>
  <tr>
    <td height="30" width="20%" bgcolor="#FFFFFF">&nbsp;${cpm.goodsnum }<input type="hidden" name="goodsnum" value="${cpm.goodsnum }"></td>
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;${cpm.vmname}<input type="hidden" name="vmname" value="${cpm.vmname}"></td>
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;${cpm.omname}<input type="hidden" name="omname" value="${cpm.omname}"></td>
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;${cpm.addedweight}<input type="hidden" name="weight" value="${cpm.addedweight}"></td>
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;${CJJ}
    <input type="hidden" name="price" value="${CJJ}">
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">合计</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">提货单位</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;${thr.name}
    <input type="hidden" name="thrname" value="${thr.name}">
    </td>
    </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">提货重量</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;${cpm.addedweight}

    </td>
    </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">备注</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;<input type="text" style="width:600px" name="remark"></td>
    </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">提货车号</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;<input type="text" name="trucknum" class="easyui-validatebox" data-options="required:true"></td>
    </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">提货人</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;${sellcontacts.name }
    <input type="hidden" name="sellcontactsname" value="${sellcontacts.name }">
    </td>
    <td height="30" bgcolor="#FFFFFF">身份证号</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp; ${sellcontacts.idcard }
    <input type="hidden" name="sellcontactsidcard" value="${sellcontacts.idcard }">
    </td>
    <td height="30" bgcolor="#FFFFFF">联系方式</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;${sellcontacts.phone }
    <input type="hidden" name="sellcontactsphone" value="${sellcontacts.phone }">
    </td>
  </tr>
   <!--  <tr>
    <td height="30" bgcolor="#FFFFFF">仓库经办人</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;</td>
    </tr> -->
    <tr>
    <td height="30" bgcolor="#FFFFFF">特殊说明</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;两天内有效</td>
    </tr>
</table>
</div>

<div class="gaizhang">
 <p>${c.name}</p>
 <p>${thrq }<input type="hidden" name="thrq" value="${thrq}"></p>
</div>
 <input value="打印提单信息" type="submit" class="easyui-linkbutton" > 注：<font color="red">此提单需要加盖公章才有效</font>
</form:form>

    <script>
$(function(){ 

       $('#sysForm').form({  
	        url:'saveBol.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
});

       
    </script>
  </body>
</html>
