<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
  <head>
    <title>提单</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style mce_bogus="1" type="text/css">
body {font-family: SimSun; background:none;margin-left: auto;margin-right: auto;}
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
  </head>
  
  <body>
  <div class="ruku_code">提货单</div>
<div class="sub_title">仓库：${bol.dwname}</div>
<div class="code_huowu">提货单号：${bol.bolnum}</div>
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
<td height="30" width="20%" bgcolor="#FFFFFF">${bol.goodsnum }</td>
<td width="16%" height="30" bgcolor="#FFFFFF">${bol.vmname}</td>
<td width="16%" height="30" bgcolor="#FFFFFF">${bol.omname}</td>
<td width="16%" height="30" bgcolor="#FFFFFF"></td>
<td width="16%" height="30" bgcolor="#FFFFFF">${bol.weight}</td>
<td width="16%" height="30" bgcolor="#FFFFFF">${bol.price}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">合计</td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
<td height="30" bgcolor="#FFFFFF"></td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">提货单位</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${bol.thrname}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">提货重量</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${bol.weight}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">备注</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${bol.remark}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">提货车号</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${bol.trucknum}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">提货人</td>
<td height="30" bgcolor="#FFFFFF">${bol.sellcontactsname }</td>
<td height="30" bgcolor="#FFFFFF">身份证号</td>
<td height="30" bgcolor="#FFFFFF"> ${bol.sellcontactsidcard }</td>
<td height="30" bgcolor="#FFFFFF">联系方式</td>
<td height="30" bgcolor="#FFFFFF">${bol.sellcontactsphone }</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">特殊说明</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">两天内有效</td>
</tr>
</table>
</div>
<div class="gaizhang">
<p>${c.name}</p>
<p>${bol.thrq }</p>
</div>
  </body>
</html>
