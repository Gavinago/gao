<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="../../taglib_includes.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购合同确认</title>
<style>
html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, address, cite, code, del, dfn, em, img, ins, kbd, q, samp, small, sub, sup, var, b, i, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, caption, tfoot, thead, th, s { margin:0; padding:0; border:0; font-size:100%; vertical-align:baseline; font-style:normal; text-decoration:none;word-wrap: break-word;}
body{ font-size:12px; color:#000000; font-size:14px;line-height:26px;font-family:"宋体";}
a { color:#000000; font-size:14px;line-height:26px; font-family:"宋体"; text-decoration:none; }
a:hover { color:#000000; font-size:14px; line-height:26px; font-family:"宋体"; text-decoration:none;}
.bt{color: #121212;font-size: 26px; line-height:80px;text-align: center;}
.A4 { margin: 0 auto;width: 794px;}
.f_12{ font-size:12px;}
.f_red{ color:#F00}
.t_tab td{ padding-left:10px;}
.table_block{border:solid 1px #000000; padding:6px 0px;}

.payment_btn{ width:302px; height:52px; margin:0 auto; margin-top:20px; background:url(images/fk_btn.jpg) no-repeat left top; text-align:center;}
.payment_btn a{ height:52px; line-height:52px;font-family:"微软雅黑"; font-size:24px; font-weight:400; color:#ffffff; text-decoration:none}
.payment_btn a:hover{ height:52px; line-height:52px;font-family:"微软雅黑"; font-size:24px; font-weight:400; color:#ffffff;  text-decoration:none}
.payment_btn a em{ font-size:16px;color:#ffff00;}
.payment_btn a:hover em{ font-size:16px;color:#ffff00;}
</style>
 <script type="text/javascript">
 function okht(){
 //alert(window.location.href);
 var url=window.location.href;
 
 //location.href="conPMfirmOrders.do?url="+encodeURIComponent(url)+"&htbh=${htbh}";
 location.href="conPMfirmOrders.do?orderid=${cpm.id}&htbh=${htbh }&htsj=${htsj }&jhsj=${jhsj}&htyxq1=${htyxq1}&htyxq2=${htyxq2}";
 }
 
 </script>
</head>
<body>

<div class="A4">
<table width="100%" border="0" cellspacing="1" cellpadding="1" >
  <tr>
    <td><div class="bt">采购合同</div></td>
  </tr>
  <tr>
    <td align="right"><div class="f_12">保密</div></td>
  </tr>
  <tr>
    <td align="right"><div class="f_12">合约编号：${htbh }</div></td>
  </tr>
  <tr>
    <td align="right"><div class="f_12">签定时间：${htsj }</div></td>
  </tr>
  <tr>
    <td><div class="f_12">出卖人：（甲方）${cpm.sellcompanyname}</div></td>
  </tr>
  <tr>
    <td><div class="f_12">买受人：（乙方）${buyername }</div></td>
  </tr>
</table>


<table width="100%" border="0" cellspacing="1" cellpadding="1">
  <tr>
    <td height="30">一、产品名称、规格、数量、金额、交货时间、付款时间
    <table width="100%" border="0" cellspacing="1" cellpadding="1"  bgcolor="#666666">
  <tr>
    <th align="center" bgcolor="#FFFFFF">产品名称、规格</th>
    <th align="center" bgcolor="#FFFFFF">数量（吨）</th>
    <th align="center" bgcolor="#FFFFFF">单价（元/吨）</th>
    <th align="center" bgcolor="#FFFFFF">金额（元）</th>
    <th align="center" bgcolor="#FFFFFF">交（提）货时间</th>
  </tr>
  <tr>
    <td align="center" bgcolor="#FFFFFF">${cpm.vmname}</td>
    <td align="center" bgcolor="#FFFFFF">${cpm.realweight}</td>
    <td align="center" bgcolor="#FFFFFF"><p align="center">${cpm.addedprice}</p>
（<span class="f_red">含17%发票不含2.5‰手续费</span>）</td>
    <td align="center" bgcolor="#FFFFFF">${jine}</td>
    <td rowspan="3" align="center" bgcolor="#FFFFFF">${jhsj}</td>
  </tr>
  <tr>
    <td colspan="3" align="center" bgcolor="#FFFFFF">合计</td>
    <td align="center" bgcolor="#FFFFFF">${jine}</td>
    </tr>
  <tr>
    <td colspan="4" align="center" bgcolor="#FFFFFF">合计人民币金额（大写）：${TotalRMB}</td>
    </tr>
</table>

    </td>
  </tr>
  <tr>
    <td height="30">二、质量标准：以实体货物为准。</td>
  </tr>
  <tr>
    <td height="30">三、交货地点：乙方仓库</td>
  </tr>
  <tr>
    <td height="30">四、运输方式：汽车，集装箱</td>
  </tr>
  <tr>
    <td height="30">五、重量标准：以甲方货物出库磅单为准。</td>
  </tr>
  <tr>
    <td height="30">六、结算方式及期限：提货当日乙方支付甲方80%货款，乙方收到甲方全额有效的（税率为17%）增值税发票后支付余款</td>
  </tr>
  <tr>
    <td height="30">七、如需提供担保，另立合同担保书，作为本合同附件。</td>
  </tr>
  <tr>
    <td height="30">八、违约责任：如有争议，按照汇金大通公司交易规则执行。</td>
  </tr>
  <tr>
    <td height="30">九、解决合同纠纷的方式：执行本合同发生争议，由当事人双方协商解决。协商不成，向人民法院起诉。</td>
  </tr>
  <tr>
    <td height="30">十、合同有效期： <B>${htyxq1 }</B>到 <B>${htyxq2}</B> 日</td>
  </tr>
  <tr>
    <td height="30">十一、其它约定：
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#666666" class="t_tab">
  <tr>
    <td height="26" bgcolor="#FFFFFF">1.乙方所在地为合同履行地。如本合同以传真形式签订，与原件具有同等法律效力。 </td>
  </tr>
  <tr>
    <td height="26" bgcolor="#FFFFFF">2.如本合同是长期合同，价格随市场行情波动较大，经双方协商可适当调整，协商不成视为合同终止。如合同期满后继续履行的，本合同仍有效。合同期内实际成交数量超过合同约定的，本合同仍适用。</td>
  </tr>
  <tr>
    <td height="26" bgcolor="#FFFFFF">3.甲方及其经办人不得以任何形式贿赂或者变相贿赂乙方经办人。甲方及其经办人违反本约定，须承担违约责任，并增加支付相当于50倍商业贿赂的违约金给乙方。</td>
  </tr>
</table>
    </td>
  </tr>
  <tr>
    <td height="30"><p>备注：${cpm.hwbz }.  仓单号：${cpm.goodsnum }</p>
	
	<div class="table_block">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#666666" class="t_tab";>
  <tr>
    <td width="50%" height="24" bgcolor="#FFFFFF">甲  方</td>
    <td width="50%" height="24" bgcolor="#FFFFFF">乙  方</td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">法定代表人：${cpm.legalrep }</td>
    <td height="24" bgcolor="#FFFFFF">法定代表人：${buyerlegalrep}  </td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">委托代理人：${sellcontacts.name }</td>
    <td height="24" bgcolor="#FFFFFF">委托代理人：${buyercontacts.name } </td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">电话：${sellcontacts.tel} </td>
    <td height="24" bgcolor="#FFFFFF">电话：${buyercontacts.tel}</td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">传真：${sellcontacts.fax}</td>
    <td height="24" bgcolor="#FFFFFF">传真：${buyercontacts.fax} </td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">开户银行：${cpm.bank}</td>
    <td height="24" bgcolor="#FFFFFF">开户银行： ${buyerbank}</td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">账号：${cpm.bankaccount} </td>
    <td height="24" bgcolor="#FFFFFF">账号：${buyerbankaccount }</td>
  </tr>
  <tr>
    <td height="24" bgcolor="#FFFFFF">地址：${cpm.address}</td>
    <td height="24" bgcolor="#FFFFFF">地址：${buyeraddress} </td>
  </tr>
</table>
</div>
    <strong>合同请盖章后立即回传，谢谢合作！</strong>
    </td>
  </tr>
</table>

<div class="payment_btn">
<a href="javascript:void(0);" onclick="okht()">确认合同</a>
</div>
<br/>
</div>
</body>
</html>

