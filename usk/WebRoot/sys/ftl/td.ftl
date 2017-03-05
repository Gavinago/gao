<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>提单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	
<style mce_bogus="1" type="text/css">
body{ font-size:12px; color:#000000; font-size:14px;line-height:26px;font-family:SimSun;}
body {font-family: SimSun;    
     font-style:italic;    
     font-weight:bold;    
             background:none;margin-left: auto;margin-right: auto;}
a { color:#000000; font-size:14px;line-height:26px; font-family:SimSun; text-decoration:none; }
a:hover { color:#000000; font-size:14px; line-height:26px; font-family:SimSun; text-decoration:none;}

.bt{color: #121212;font-size: 26px; font-weight:bold; line-height:80px;text-align: center;}

.A4 { margin: 0 auto;width: 794px;}
.f_12{ font-size:12px;}
.f_red{ color:#F00}
.t_tab td{ padding-left:10px;}
</style>
  </head>
  
  <body>
   
   <div class="A4">
<table width="680" border="0" cellspacing="1" cellpadding="1" style="table-layout:fixed; word-break:break-strict;">
<tr>
    <td><div class="bt">宁波市镇海汇金大通有色金属储备交易中心有限公司</div></td>
  </tr>
  <tr>
    <td><div class="bt">提货单</div></td>
  </tr>
  <tr>
    <td align="right">提货单号：${ob.ownum }
     


    </td>
  </tr>
</table>
<table width="680" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666" class="t_tab" style="table-layout:fixed; word-break:break-strict;">
  <tr>
    <td width="50%" height="30" bgcolor="#FFFFFF">提货单位：${ob.buyerCompanyName }
 </td>
    <td width="50%" height="30" bgcolor="#FFFFFF">存货单位<strong>:${ob.sellerCompanyName }</strong>
   
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">货物名称：${ob.vmname }
 
    </td>
    <td height="30" bgcolor="#FFFFFF">仓单编号(电子交易专用)：${ob.goodsnum }
    
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">进仓重量（MT）：${ob.weight }
   
    </td>
    <td height="30" bgcolor="#FFFFFF">出仓重量（MT）：${ob.realweight }
     
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">存放货位：${ob.hw }
   
    </td>
    <td height="30" bgcolor="#FFFFFF">提货日期：${ob.cksj }
   
    
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">原存货箱号（车号）：${ob.xh } |${ob.ch }
    
    </td>
    <td height="30" bgcolor="#FFFFFF">提货箱号（车号）：${ob.ckxh } |${ob.trucknum }
    
    </td>
  </tr>

  <tr>
    <td height="30" bgcolor="#FFFFFF">业务受理员：${ob.owclUserName }
    
     </td>
    <td height="30" bgcolor="#FFFFFF">出单日期：${ob.cksj }
    
    </td>
  </tr>
</table>
</div>


  </body>
</html>