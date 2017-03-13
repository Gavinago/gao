
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
						href="javascript:doSearch('Manul')"> 搜索 </a> <a
						href="one/home.do" class="btn btn-default" title="返回首页"><img
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
					<th>客房类型</th>
					<th>价格</th>
					<th>折扣</th>
					<th>当前价格</th>
					<th>押金</th>
					<th>当前状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageinfo.list}" var="item">
					<tr>
						<td>${item.roomsnum}
						</td>
						<td>${item.roomname}
						</td>
						<td>${item.roomprice}
						</td>
						<td>${item.roomvip}
						</td>
						<td>${item.roomfirmprice}
						</td>
						<td>${item.roomcash}
						</td>
						<td>${item.statename}
						</td>
						<c:choose>
							<c:when test="${item.roomstate eq 1}">
								<td><a href='<c:url value="/back/room/roomguest.do?roomid=${item.roomid}"/>' target="_blank" >入住</a></td>
							</c:when>
							<c:when test="${item.roomstate eq 2}">
								<td><a href='<c:url value="/back/room/roomguest.do?roomid=${item.roomid}"/>' target="_blank" >退房</a></td>
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<button id="but" type="button" style="display: none;"
			data-toggle="modal" data-target="#myModal"></button>
	
	<div class="panel-footer">
		<%@include file="../common/inc_page_buttons.jsp"%>
	</div>
</div>




