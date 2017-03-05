<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仓单</title>

<style>
html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp, small, sub, sup, var, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, caption, tfoot, thead, th, s { margin:0; padding:0; border:0; font-size:100%; vertical-align:baseline; font-style:normal; text-decoration:none;word-wrap: break-word;}
body {font-family: SimSun;    
     font-style:italic;    
     font-weight:bold;    
             background:none;margin-left: auto;margin-right: auto;}
body{ font-size:12px; color:#000000; font-size:14px;line-height:26px;font-family:SimSun;}
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

<div class="bt"> ${qfdwname }</div>

<table width="680" border="0" cellspacing="1" cellpadding="1" bgcolor="#666666" class="t_tab" style="table-layout:fixed; word-break:break-strict;">
  <tr>
    <th height="30" colspan="7" bgcolor="#FFFFFF">仓 单</th>
  </tr>
  <tr>
    <td height="30" colspan="5" bgcolor="#FFFFFF">存货单位：${cname } </td>
    <td height="30" colspan="2" bgcolor="#FFFFFF">编号：${editGoodsForm.goodsnum }</td>
  </tr>
  <tr>
    <td height="30" colspan="3" bgcolor="#FFFFFF">货物名称：${vmname }</td>
    <td  height="30" colspan="2" bgcolor="#FFFFFF">产地：${omname }</td>
    <td height="30" colspan="2" bgcolor="#FFFFFF">到货日期： ${editGoodsForm.dhrq}</td>
  </tr>
  <tr>
    <td height="30" colspan="3" bgcolor="#FFFFFF">计量方式：${editGoodsForm.jlfs}</td>
    <td height="30" colspan="2"  bgcolor="#FFFFFF">计量单位：公斤</td>
    <td height="30" colspan="2"  bgcolor="#FFFFFF">包装方式： ${editGoodsForm.bzfs}</td>
  </tr>
  <tr>
    <td height="30" colspan="7" align="center" bgcolor="#FFFFFF"><strong>收货记录</strong></td>
  </tr>
  <tr>
    <td width="15%" height="30" align="center" bgcolor="#FFFFFF">车号</td>
    <td width="15%" height="30" align="center" bgcolor="#FFFFFF">集装箱号</td>
    <td width="15%" height="30" align="center" bgcolor="#FFFFFF">材质</td>
    <td width="8%" height="30" align="center" bgcolor="#FFFFFF">件数</td>
    <td width="13%" height="30" align="center" bgcolor="#FFFFFF">货物重量</td>
    <td height="30" align="center" bgcolor="#FFFFFF">货物质量</td>
    <td height="30" align="center" bgcolor="#FFFFFF">存放货位</td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#FFFFFF">${editGoodsForm.ch}</td>
    <td height="30" align="center" bgcolor="#FFFFFF"></td>
    <td height="30" align="center" bgcolor="#FFFFFF">${editGoodsForm.cz}</td>
    <td height="30" align="center" bgcolor="#FFFFFF">${editGoodsForm.jianshu}</td>
    <td height="30" align="center" bgcolor="#FFFFFF">${editGoodsForm.weight*1000}</td>
    <td height="30" align="center" bgcolor="#FFFFFF">
                     ${hwzlstr}
                     </td>
    <td height="30" align="center" bgcolor="#FFFFFF">${editGoodsForm.hw}</td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" colspan="2" align="center" bgcolor="#FFFFFF">合  计：</td>
    <td height="30" align="center" bgcolor="#FFFFFF">件  数</td>
    <td height="30" colspan="2" align="center" bgcolor="#FFFFFF">总重量</td>
    <td height="30" colspan="2" align="center" bgcolor="#FFFFFF">——</td>
    </tr>
  <tr>
    <td height="30" colspan="2" align="center" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" align="center" bgcolor="#FFFFFF">${editGoodsForm.jianshu}</td>
    <td height="30" colspan="2" align="center" bgcolor="#FFFFFF">${editGoodsForm.weight*1000}
磅差为3‰</td>
    <td height="30" colspan="2" align="center" bgcolor="#FFFFFF">——</td>
    </tr>
  <tr>
    <td height="30" colspan="7" bgcolor="#FFFFFF">备注：${editGoodsForm.hwbz}</td>
  </tr>
  <tr>
    <td height="30" colspan="7" bgcolor="#FFFFFF">质量初步判断：${editGoodsForm.zlcbpd}</td>
  </tr>
 <!--  <tr>
    <td height="30" colspan="7" bgcolor="#FFFFFF">市场价格：       元/吨                 参考价格：      元/吨</td>
  </tr> -->
  <tr>
    <td height="30" colspan="7" bgcolor="#FFFFFF">不合格品处理方式 ：  
      <#if editGoodsForm.bhgcpclfs=='0'>退回 </#if>  
<#if editGoodsForm.bhgcpclfs=='1'>2.暂存  </#if>  
     <#if editGoodsForm.bhgcpclfs=='2'>   由中心代加工  </#if></td>
  </tr>
  <tr>
    <td height="30" colspan="7" bgcolor="#FFFFFF">业务受理员： ${slr }                    出单日期： ${ editGoodsForm.createtime}</td>
  </tr>
  <tr>
    <td height="30" colspan="7" bgcolor="#FFFFFF">  ${qfdwname }（签章）：</td>
  </tr>
</table>
<strong>备注：存货单位收到仓单后如有异议，请当天提出，否则视为认同仓单信息。</strong>




</div>
</body>
</html>