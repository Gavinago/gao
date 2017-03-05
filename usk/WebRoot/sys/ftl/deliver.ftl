<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
  <head>
    <title>出库单</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style mce_bogus="1" type="text/css">
body {font-family: SimSun; background:none;margin-left: auto;margin-right: auto;}
body,html,div,p{ font-size:14px; margin:0px; padding:0px;}
.ruku_code{ height:50px; line-height:50px; text-align:center; width:680px; font-size:18px; font-weight:bold}
.sub_title{ font-weight:normal; font-size:16px; height:50px; line-height:50px;}
.code_huowu{ height:30px; line-height:30px; text-align:right;}
.clfs{ vertical-align:sub; _vertical-align:middle}
.clfs_span { display:inline-block;  padding-right:30px;}
.desc{ height:40px; line-height:40px; text-align:center;}
.table_block{ padding:10px;}
.table_block td{ padding:0px 5px;}
.gaizhang{padding-top:20px;}
.gaizhang p{ text-align:right; height:22px; padding:0px 10px;  line-height:22px;}

</style>
  </head>
  <body> 
 <div class="ruku_code">出库单(电子交易专用)</div>
<div class="sub_title">仓库：${owh.dwname }（盖章）</div>

<div class="code_huowu">出库单号：${owh.cknum}
</div>
<div class="table_block">
<table width="680" border="0" cellspacing="1" cellpadding="1" bgcolor="#CCCCCC"  style="table-layout:fixed; word-break:break-strict;">
<tr>
<td height="30" bgcolor="#FFFFFF" >仓单号</td>
<td height="30" bgcolor="#FFFFFF" >品名</td>
<td height="30" bgcolor="#FFFFFF" >产地</td>
<td height="30" bgcolor="#FFFFFF" width="180">规格</td>
<td height="30" bgcolor="#FFFFFF">重量（吨）</td>
<td height="30" bgcolor="#FFFFFF" >成交价</td>
</tr>
<tr>
<td height="30" width="20%" bgcolor="#FFFFFF">${owh.goodsnum }</td>
<td  height="30" bgcolor="#FFFFFF">${owh.vmname}</td>
<td  height="30" bgcolor="#FFFFFF">${owh.omname}</td>
<td  height="30" bgcolor="#FFFFFF"></td>
<td  height="30" bgcolor="#FFFFFF">${owh.weight}</td>
<td  height="30" bgcolor="#FFFFFF">${owh.price}
</td>
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
<td height="30" bgcolor="#FFFFFF">买受人</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.thrname}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">合同重量</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.weight}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">出库重量</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">
${owh.realweight}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">重量差额</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.weightdiff}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">金额差额</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.amountdiff}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">过磅费</td>
<td height="30" colspan="5" bgcolor="#FFFFFF"> ${owh.gbf}</td>
</tr>
   <tr>
    <td height="30" bgcolor="#FFFFFF">仓储费(15天内免费,超过后0.6元/天/吨)</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
     ${owh.ccf}
  元
  </td>
    </tr>
<tr>
<td height="30" bgcolor="#FFFFFF">上车费</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.onfares}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">提货车号</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.trucknum}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">出库箱号</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.ckxh}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">出库时间</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.cksj}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">提货人</td>
<td height="30" bgcolor="#FFFFFF">${owh.sellcontactsname }</td>
<td height="30" bgcolor="#FFFFFF">身份证号</td>
<td height="30" bgcolor="#FFFFFF"> ${owh.sellcontactsidcard }</td>
<td height="30" bgcolor="#FFFFFF">电话</td>
<td height="30" bgcolor="#FFFFFF">${owh.sellcontactsphone }</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">备注</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${owh.remark }</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">操作员</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">
${owh.useraccout}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">仓库经办人</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">${ckjbr}</td>
</tr>
<tr>
<td height="30" bgcolor="#FFFFFF">特殊说明</td>
<td height="30" colspan="5" bgcolor="#FFFFFF">两天内有效</td>
</tr>
 <tr>
    <td height="30" bgcolor="#FFFFFF">客户签字</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
</table>
</div>

<div class="gaizhang">
<p>${c.name}</p>
<p>${owh.thrq }</p>
</div>

  </body>
</html>