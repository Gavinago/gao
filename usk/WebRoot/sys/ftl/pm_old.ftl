<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
  <head>
    <title>采购合同</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style  mce_bogus="1" type="text/css">
body {font-family: SimSun;    
     font-style:italic;    
     font-weight:bold;    
             background:none;margin-left: auto;margin-right: auto;}
.payment{border:solid 1px #4d5467; padding-bottom:30px; }
.clues{ width:100%; height:50px; line-height:50px; text-align:center; background:#4d5467; font-family:"微软雅黑"; font-size:24px; font-weight:400; color:#ffff00;}
.payment_cont{ width:840px; margin:0 auto; margin-top:60px; padding-bottom:30px;}
.payment_cont .clues_3{ width:100%; height:50px; line-height:50px; margin-top:30px; text-indent:55px; font-size:14px; font-weight:bold; color:#333333;}
.bank_list{ width:810px; height:206px; margin-top:20px; border:solid 1px #c4c4c4; padding:10px 14px;}
.bank_list ul li{ width:140px; height:40px; float:left;padding:15px 11px;}
.contract_t{ color:#FFF;}
.clues span{ width:auto; font-size:14px; color:#a6aab3; float:right; padding-right:20px; font-family:"宋体";}
.contract_tab{ width:98%; margin:0 auto; margin-top:10px;font-size:14px; }
.contract_tab td{ height:30px; line-height:30px;}
.contract_tab td strong{ text-decoration:underline;}
.contract_tab td i{ font-weight:bold;}
.contract_line{ width:98%; margin:0 auto; margin-top:10px; padding-bottom:10px; border-top:solid 1px #999999;}
</style>


  </head>
  
  <body>
  
  <div >
     <div class="payment">
<div class="clues contract_t">
<span >保密</span>
采购合同</div>


<table width="680" border="0" cellspacing="1" cellpadding="1"  >
 <tr>
    <td  align="center">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     <span style="font-size:34px;">采购合同</span> </td>
   <td align="right">保密</td>
  </tr>
</table>


<table  border="0" cellspacing="1" cellpadding="1" class="contract_tab" style="margin-top: 60px;table-layout:fixed; word-break:break-strict;">
<tr>
<td>出卖人：（甲方）<strong> ${cpm.sellcompanyname}</strong></td>
<td>合约编号：${htbh }</td>
</tr>
<tr>
<td>买受人：（乙方）<strong> ${buyername }</strong></td>
<td>合同时间：${htsj }</td>
</tr>
<tr>
<td colspan="2"> 一、 产品名称、规格、数量、金额、交货时间、付款时间
<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="333333">
<tr>
<td align="left" bgcolor="#e5e5e5" style="padding-left:10px;">产品名称、规格</td>
<td align="center" bgcolor="#e5e5e5">重量（吨）</td>
<td align="center" bgcolor="#e5e5e5">单价（元/吨）</td>
<td align="center" bgcolor="#e5e5e5">金额（元）</td>
<td align="center" bgcolor="#e5e5e5">交（提）货时间</td>
</tr>
<tr>
<td align="left" bgcolor="#FFFFFF" style="padding-left:10px;"><i>${cpm.vmname}</i></td>
<td align="center" bgcolor="#FFFFFF">${cpm.weight}±10%</td>
<td align="center" bgcolor="#FFFFFF">${cpm.addedprice}(含17%发票,不含2.5%手续费)</td>
<td align="center" bgcolor="#FFFFFF">${cpm.addedprice*cpm.weight}</td>
<td align="center" bgcolor="#FFFFFF">${jhsj}</td>
</tr>
<tr>
<td colspan="5" align="left" bgcolor="#FFFFFF" style="padding-left:10px;">合计人民币(大写)：<i><span style="font-weight:bold;font-size:22px;">${TotalRMB}</span></i></td>
</tr>
</table>
</td>
</tr>
<tr>
<td colspan="2">二、 质量标准：以实体货物为准。</td>
</tr>
<tr>
<td colspan="2">三、 交货地点：乙方仓库。</td>
</tr>
<tr>
<td colspan="2">四、 运输方式：汽车，集装箱。</td>
</tr>
<tr>
<td colspan="2">五、 重量标准：以甲方货物入库至乙方仓库的磅单为准,其中磅差为±3%。</td>
</tr>

<tr>
<td colspan="2">六、 结算方式及期限：提货当日乙方支付甲方80%货款，乙方收到甲方发票后乙方支付余款。</td>
</tr>
<tr>
<td colspan="2">七、 如需提供担保，另立合同担保书，作为合同附件。</td>
</tr>
<tr>
<td colspan="2">八、 违约责任。</td>
</tr>
<!-- 转pdf出错的地方  开始-->

<tr>
<td colspan="2">九、 解决合同纠纷的方式：执行本合同发生争议，由当事人双方协商解决。协商不成，向人民法院起诉。</td>
</tr>
<!-- 转pdf出错的地方  结束-->

<tr>
<td colspan="2">十、合同有效期：<span style="font-weight:bold;font-size:22px;">${htyxq1 }</span>到<span style="font-weight:bold;font-size:22px;">${htyxq2} </span> 日。</td>
</tr>
<tr>
<td colspan="2"><p>十一、其它约定：</p>
<p>
1、 乙方所在地为合同履行地。如本合同以传真形式签订，与原件具有同等法律效力。<br />
2、 如本合同是长期合同，价格随市场行情波动较大，经双方协商可适当调整，协商不成视为合同终止。如合同期满后继续履行的，本合同仍有效。合同期内实际成交数量超过合同约定的，本合同仍适用。<br />
3、 甲方在 <strong> ${htyxq2} </strong> 日前提供给乙方全额有效的（税率为17%）增值税发票。<br />
4、 甲方及其经办人不得以任何形式贿赂或者变相贿赂乙方经办人。甲方及其经办人违反本约定，须承担违约责任，并增加支付相当于50倍商业贿赂的违约金给乙方</p></td>
</tr>
</table>

<p class="contract_line"></p>
<table border="0" cellspacing="1" cellpadding="1" class="contract_tab">
 <tr>
  <th width="50%" align="left">乙  方</th>
    <th width="50%" align="left">甲  方</th>
    
  </tr>
  <tr>
   <td>法定代表人：${buyerlegalrep}</td>
    <td>法定代表人：${cpm.legalrep }</td>
   
  </tr>
  <tr>
  <td>委托代理人：${buyercontacts.name }</td>
    <td>委托代理人：${sellcontacts.name }</td>
    
  </tr>
  <tr>
   <td>电话：${buyercontacts.tel}</td>
    <td>电话：${sellcontacts.tel}</td>
   
  </tr>
  <tr>
    <td>传真：${buyercontacts.fax}</td>
    <td>传真：${sellcontacts.fax}</td>
  </tr>
  <tr>
    <td>地址：${buyeraddress}</td>
    <td>开户银行及帐号：${cpm.bank}</td>
  </tr>
  <tr>
   <td>&nbsp;</td>
    <td>账号：${cpm.bankaccount}</td>
  </tr>
  <tr>
   <td>&nbsp;</td>
    <td>地址：${cpm.address}</td>
  </tr>
</table>


</div>
</div>
  </body>
</html>
