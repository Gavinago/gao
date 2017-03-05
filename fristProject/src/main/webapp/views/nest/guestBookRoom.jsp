
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Shiro标签库 -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!-- SiteMesh标签库 -->
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>



<div class="panel panel-default">
	<div class="panel-heading">
		<div class="row">
			<!-- 标题 -->
			<div class="col-xs-12 col-sm-2">
				<img src="<c:url value="/assets/img/Org24.png"/>"><span id="result">查询结果</span>
			</div>
			<div class="col-xs-12 col-sm-6 text-center">
			</div>
			<!-- 搜索 -->
			<div class="col-xs-12 col-sm-4 text-right">
				<div class="input-group">
					<input id="searchText" name="searchText" type="text"
						class="form-control"><span class="input-group-btn">
						<a class="btn btn-default"
						href="javascript:RefreshCurrentPage('Manul')"> 搜索 </a> <a
						href="<c:url value='/home.do'/>" class="btn btn-default" title="返回首页"><img
							src="<c:url value="/assets/img/back16b.png"/>"></a>
					</span>
				</div>
			</div>
		</div>
	</div>

	
		<!-- 数据表 -->
		<c:if test="${pageinfo.total=='0'}">
			<h1>暂无数据</h1>
		</c:if>
		<c:if test="${pageinfo.total!='0'}">
			<table class="table table-bordered">
				<tr>
					<th>房号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>折扣</th>
					<th>价格</th>
					<th>押金</th>
					<th onclick="javascript:orderBy();"title="点击重新排序" style="background-color: gray;cursor: pointer;">入住时间</th>
					<th>离开时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageinfo.list}" var="item">
					<tr>
						<td>
							${item.guestroomname}
						</td>
						<td>
							${item.guestname}
						</td>
						<td>
							${item.guestsex}
						</td>
						<td>
							${item.guestroomvip}
						</td>
						<td>
							${item.guestroomtotleprice}
						</td>
						<td>
							${item.guestroomprice}
						</td>
						<td>
							${item.guestcometime}
						</td>
						<td>
							${item.guestleavetime}
						</td>
						<td>
							<a  herf="javascript:bookCheckinRoom("+${item.guestid}+")">入住</a> | <a herf="javascript:exitBookRoom("+${item.guestid}+")">退订</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	<div class="panel-footer">
		<%@include file="../common/inc_page_buttons.jsp"%>
		<div class="row">
		<div class="col-xs-12 col-sm-2 text-left">
		<button type="button" class="btn btn-default" data-toggle="button" onclick="javascript:location.href='one/home.do'">返回首页</button>
		</div>
		<div class="col-xs-12 col-sm-6 text-centen"></div>
		<div class="col-xs-12 col-sm-4 text-right">
		 <div class="btn-group">
		<button type="button" class="btn btn-default" data-toggle="button" onclick="javascript:refer('upload')"> <span class="glyphicon glyphicon-floppy-open"> 进度导入</span></button>
		<div class="btn-group dropup">
							<c:choose>
							<c:when test="${pageinfo.total=='0'}">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" disabled>
									<span class="glyphicon glyphicon-floppy-disk"
										aria-hidden="true"></span> 导出 <span class="caret"></span>
								</button>
								</c:when>
								<c:otherwise>
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<span class="glyphicon glyphicon-floppy-disk"
										aria-hidden="true"></span> 导出 <span class="caret"></span>
								</button>
								</c:otherwise>
								</c:choose>
								<ul class="dropdown-menu" role="menu">
									<li><a href="javascript:Export('xls');"onclic="javascript:Export('xls');"><span
											class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>导出Excel</a></li>
									<li><a href="javascript:Export('csv');"><span
											class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>导出CSV</a></li>
									<li><a href="javascript:Export('json');"><span
											class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>导出JSON</a></li>
									<li><a href="javascript:Export('xml');"><span
											class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>导出XML</a></li>
								</ul>
							</div>
  		
  		</div>
		</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var url="<c:url value='ajax/ajaxBookRoomOperation.do'/>";
var orderby = "";
var SearchText ="";
function bookCheckinRoom(args){
	ajaxOperation(url,args,"checkin")
}
function exitBookRoom(args){
	ajaxOperation(url,id,"exit");
}
function orderBy(){
	if(orderby==""){
		orderby = "DESC";
	}else if(orderby =="DESC"){
		orderby = "ASC";
	}else if(orderby=="ASC"){
		orderby="DESC";
	}
	ajaxOperation(url,0,orderby);
}
</script>



