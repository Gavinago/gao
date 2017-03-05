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
    
    <title>打印出库单</title>
    
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
var num1 = 50;  
                //数字2  
                var num2 = 0.5; 
//alert(num2.mul(num1));

function gbfzk(obj){
//alert(obj.value);
if(document.getElementById("checkgbfzk").checked){

var zk=parseFloat(obj.value)/100;
//alert(zk);
var gbfv=parseFloat($('#gbf').val());

    $('#gbf').val(gbfv.mul(zk));
    //alert(gbfv.sub(zk));
}
}


function scfzk(obj){
if(document.getElementById("checkscf").checked){
var zk=parseFloat(obj.value)/100;
var gbfv=parseFloat($('#onfares').val());
    $('#onfares').val(gbfv.mul(zk));
}

}


function scf(obj){
var num=parseInt(obj.value);
var scf=${gbfem.price };
$("#gbf").val(num.mul(scf));
}


function cf(arg1, arg2) {  
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();  
    try {  
        m += s1.split(".")[1].length;  
    }  
    catch (e) {  
    }  
    try {  
        m += s2.split(".")[1].length;  
    }  
    catch (e) {  
    }  
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);  
}   



</script> 
  </head>
  
  <body>
      <form:form action="saveDeliver.do" method="post"  commandName="owh" id="sysForm" >
 <div class="ruku_code">出库单</div>
<div class="sub_title">仓库：${cpm.dwname }（盖章）<input type="hidden" name="dwname" value="${cpm.dwname }"></div>
<!--  <div class="code_huowu">提单号：${tdh}<input type="hidden" name="bolnum" value="${tdh}">-->
<div class="code_huowu">出库单号：${cknum}<input type="hidden" name="cknum" value="${cknum}">
<input type="hidden" name="ckjbr" value="${cpm.acceptanceid}">
<input type="hidden" name="orderid" value="${cpm.id}">
<input type="hidden" name="ordernum" value="${cpm.ordernum}">
</div>
<div class="table_block">
<table width="100%" border="0" cellspacing="1" cellpadding="1" bgcolor="#CCCCCC" >
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
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;${cpm.addedweight}<input type="hidden" id="weight" name="weight" value="${cpm.addedweight}"></td>
    <td width="16%" height="30" bgcolor="#FFFFFF">&nbsp;${jine}
    <input type="hidden" name="price" value="${jine}">
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
    <td height="30" bgcolor="#FFFFFF">合计</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
    <td height="30" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">买受人</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;${thr.name}
    <input type="hidden" name="thrname" value="${thr.name}">
    </td>
    </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">合同重量</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;${cpm.addedweight}
  </td>
    </tr>
    <tr>
    <td height="30" bgcolor="#FFFFFF">出库重量</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
    <input name="realweight" class="easyui-numberbox" required data-options="precision:2,
			onChange: function(value){
			var w=parseFloat($('#weight').val());
			var ckzl=parseFloat(value);
			//alert(ckzl);
			//alert(w);
			var  jgvalue=ckzl.sub(w);
			//jianf(ckzl,w);
			
			var dj=parseFloat('${cpm.addedprice }');
			//alert(num2.mul(num1));
			
			var jece=cf(jgvalue,dj);
			//alert(jg);
				$('#vv').text(jgvalue);
				$('#weightdiff').val(jgvalue);
				
				$('#jecy').text(jece);
				$('#amountdiff').val(jece);
				
				var onfares=parseFloat('${em.price }');
				$('#onfares').val(onfares.mul(ckzl));
			}">
   

    </td>
    </tr>
      <tr>
    <td height="30" bgcolor="#FFFFFF">重量差额</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
 <div style="margin:10px 0;">
		<span id="vv" style="color:red;"></span>吨
		<input type="hidden" name="weightdiff" id="weightdiff">
	</div>
    </td>
    </tr>
      <tr>
    <td height="30" bgcolor="#FFFFFF">金额差额</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
<span id="jecy" style="color:red;"></span>元
<input type="hidden" name="amountdiff" id="amountdiff">
    </td>
    </tr>
     <tr>
    <td height="30" bgcolor="#FFFFFF">过磅费</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">
      <select onchange="scf(this)">
   <option value="1">1</option>
    <option value="2">2</option>
     <option value="3">3</option>
      <option value="4">4</option>
       <option value="5">5</option>
        <option value="6">6</option>
         <option value="7">7</option>
          <option value="8">8</option>
           <option value="9">9</option>
            <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
    </select>车
    &nbsp;
    
    <input type="text" style="width:200px" id="gbf" name="gbf" value="${gbfem.price }">元
<input type="checkbox" id="checkgbfzk">折扣<input type="text" style="width:100px" name="zk" onchange="gbfzk(this)">%
    </td>
    </tr>
        <tr>
    <td height="30" bgcolor="#FFFFFF">仓储费(15天内免费,超过后0.6元/天/吨)</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
    <input type="text" style="width:200px" id="ccf" name="ccf" value="${ccfvalue }">元
  </td>
    </tr>
      <tr>
    <td height="30" bgcolor="#FFFFFF">上车费</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
  
    <input type="text" style="width:200px" id="onfares" name="onfares" value="${em.price }">元
   <input type="checkbox" id="checkscf">折扣<input type="text" style="width:100px" name="sczk" id="sczk" onchange="scfzk(this)">%
   
    </td>
    </tr>
  
  <tr>
    <td height="30" bgcolor="#FFFFFF">提货车号</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;<input type="text" name="trucknum" class="easyui-validatebox" data-options="required:true"></td>
    </tr>
    
      <tr>
    <td height="30" bgcolor="#FFFFFF">出库箱号</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;<input type="text" name="ckxh" class="easyui-validatebox" data-options="required:false"></td>
    </tr>
     <tr>
    <td height="30" bgcolor="#FFFFFF">出库时间</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;
    <input class="easyui-datebox" id="cksj" name="cksj" data-options="formatter:myformatter,parser:myparser"></input></td>
    </tr>
  <tr>
    <td height="30"  bgcolor="#FFFFFF">提货人</td>
    <td height="30"  bgcolor="#FFFFFF">&nbsp;${sellcontacts.name }
    <input type="hidden" name="sellcontactsname" value="${sellcontacts.name }">
    </td>
    <td height="30" bgcolor="#FFFFFF"    style="width:50px;">身份证号</td>
    <td height="30" bgcolor="#FFFFFF" style="word-break:break-all">&nbsp; ${sellcontacts.idcard }
    <input type="hidden" name="sellcontactsidcard" value="${sellcontacts.idcard }">
    </td>
    <td height="30"  bgcolor="#FFFFFF" width="50px">电话</td>
    <td height="30"  bgcolor="#FFFFFF">&nbsp;${sellcontacts.phone }
    <input type="hidden" name="sellcontactsphone" value="${sellcontacts.phone }">
    </td>
  </tr>
  <tr>
    <td height="30" bgcolor="#FFFFFF">备注</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;<input type="text" style="width:600px" name="remark"></td>
    </tr>
      <tr>
    <td height="30" bgcolor="#FFFFFF">操作员</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;<shiro:principal property="account"/>
    <input type="hidden"  name="useraccout" value="<shiro:principal property="account"/>">
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
     <tr>
    <td height="30" bgcolor="#FFFFFF">客户签字</td>
    <td height="30" colspan="5" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
</table>
</div>

<div class="gaizhang">
 <p>${c.name}</p>
 <p>${thrq }<input type="hidden" name="thrq" value="${thrq}"></p>
</div>
 <input value="确认并打印出库单信息" type="submit" > 注：<font color="red">此出库单需要加盖公章才有效</font>
</form:form>
<script type="text/javascript">
 $(function (){
var curr_time = new Date();
   var strDate = curr_time.getFullYear()+"-";
   strDate += curr_time.getMonth()+1+"-";
   strDate += curr_time.getDate();	//+"-"
  // strDate += curr_time.getHours()+":";
   //strDate += curr_time.getMinutes()+":";
   //strDate += curr_time.getSeconds();
   $("#cksj").datebox("setValue", strDate); 
    // $("#cksj").datebox("setValue",new Date()); 
    
    
    	$('#sysForm').form({  
	        url:'saveDeliver.do',  
	        onSubmit:function(){  
	            return $(this).form('validate');  
	        },  
	        success:function(data){ 
	        	$.messager.alert("操作提示", data); 

	        }  
    });
     
  });
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
		//alert(s);
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
