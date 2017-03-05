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
    
    <title>货物详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>

   <form:form enctype="multipart/form-data" action="eidtGoods.do" method="post"  commandName="editGoodsForm" id="sysForm" target="mailiframe">
          
            <table border="1">
               <tr>
                    <td> 仓单号:</td>
                  
                    <td>
                
              ${editGoodsForm.goodsnum}
                  
                    </td>
                </tr>
                <tr>
                    <td> 品种名称:</td>
                  
                    <td>
                    ${vmname}
                    </td>
                </tr>
                <tr>
                    <td>图片:</td>
                  
                    <td>
                    <img src="<%=request.getContextPath() %>/getBIGGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=1&width=300&height=300"/> 
                     <img src="<%=request.getContextPath() %>/getBIGGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=2&width=300&height=300"/> 
                     <img src="<%=request.getContextPath() %>/getBIGGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=3&width=300&height=300"/> 
                    <img src="<%=request.getContextPath() %>/getBIGGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=4&width=300&height=300"/> 
                       <img src="<%=request.getContextPath() %>/getBIGGoodsImagePhotoById.do?id=${editGoodsForm.id}&imagenum=5&width=300&height=300"/> 
                  
                  
                   </td>
                </tr>
               
                 <tr>
                    <td>重量:</td>
                    <td>
                    ${editGoodsForm.weight }
            (吨)
                    </td>
                </tr>
                 
              <tr>
                    <td> 产地:</td>
                  
                    <td>
                    ${omname }
               
                    </td>
                </tr>
              
                 <tr>
                    <td> 供应商:</td>
                  
                    <td>
                       ${cname }
                    </td>
                </tr>
                   <tr>
                    <td> 供应商联系人:</td>
                  
                    <td>
               ${ccname}
			      </td>
                </tr> 
                 <tr>
                    <td> 提货仓库:</td>
                  
                    <td>
                     ${thckname }
             
                    </td>
                </tr>
                
                  <tr>
                    <td>卸货费:</td>
                    <td>
                     ${editGoodsForm.xhf }
            
                    </td>
                </tr>
                
                 <tr>
                    <td>入库过磅费:</td>
                    <td>
         ${editGoodsForm.rkgbf }

                   </td>
                </tr>
                <tr>
                    <td>到货日期:</td>
                    <td>
                     ${editGoodsForm.dhrq }
                </tr>
                <tr>
                    <td>计量方式:</td>
                    <td> 
                    
                     ${editGoodsForm.jlfs }
               </tr>
                <tr>
                    <td>包装方式:</td>
                    <td> 
                     ${editGoodsForm.bzfs }
                </tr>
                   <tr>
                    <td>车号:</td>
                    <td>  ${editGoodsForm.ch } </td>
                </tr>
                 <tr>
                    <td>箱号:</td>
                    <td> 
                     ${editGoodsForm.xh }
                    </td>
                </tr> 
                 <tr>
                    <td>材质:</td>
                    <td>  ${editGoodsForm.cz } </td>
                </tr>
                 <tr>
                    <td>件数:</td>
                    <td>  ${editGoodsForm.jianshu } </td>
                </tr>
                  <tr>
                    <td>货物质量:</td>
                    <td>   
                     <c:choose>
                     <c:when test="${editGoodsForm.hwzl=='0'}">好
      </c:when>
<c:when test="${editGoodsForm.hwzl=='1'}">一般
      </c:when>
      <c:when test="${editGoodsForm.hwzl=='2'}">差
      </c:when>
      <c:otherwise>未知
      </c:otherwise>
    </c:choose>
        
                    </td>
                </tr>
                 <tr>
                    <td>存货货位:</td>
                    <td>   ${editGoodsForm.hw }</td>
                </tr>
                 <tr>
                    <td>质量初步判断:</td>
                    <td>  ${editGoodsForm.zlcbpd } </td>
                </tr>
                <tr>
                    <td>不合格产品处理方式:</td>
                    <td> 
                       <c:choose>
      <c:when test="${editGoodsForm.bhgcpclfs=='0'}">退回
      </c:when>
<c:when test="${editGoodsForm.bhgcpclfs=='1'}">暂存
      </c:when>
      <c:when test="${editGoodsForm.bhgcpclfs=='2'}">由中心代加工
      </c:when>
      <c:otherwise>未知
      </c:otherwise>
    </c:choose>
                 </td>
                </tr>
                <tr>
                    <td>货物备注:</td>
                    <td>${editGoodsForm.hwbz }</td>
                </tr>
                <tr> 
                    <td>备注:</td>
                    <td>
                     ${editGoodsForm.remark }
                    </td>
                </tr>
                <tr>
                    <td> 验收人:</td>
                  
                    <td>
                    
               ${ysr}
                    </td>
                </tr>
             
               
            </table>
       </form:form>
       <form:errors/>

  </body>
</html>
