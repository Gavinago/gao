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
					<div class="row" style="margin-bottom: 8px">
						<div class="col-xs-12 col-sm-7">
							<!-- 分页按钮部分 -->
							<div class="btn-group" role="group" aria-label="...">
							  	<c:if test="${pageinfo.firstPage>1}">
									<a class="btn btn-default" href="javascript:RefreshCurrentButton(1)">
										<span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span>&nbsp;
									</a>
							  	</c:if>
							  	<c:if test="${pageinfo.firstPage<=1}">
									<a class="btn btn-default disabled">
										<span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span>&nbsp;
									</a>
							  	</c:if>
							  	<c:if test="${pageinfo.pageNum>1}">
									<a class="btn btn-default" href="javascript:RefreshCurrentButton(${pageinfo.pageNum-1})">
										<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>&nbsp;
									</a>
							  	</c:if>
							  	<c:if test="${pageinfo.pageNum<=1}">
									<a class="btn btn-default disabled">
										<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>&nbsp;
									</a>
							  	</c:if>
								<c:forEach items="${pageinfo.navigatepageNums}" var="item">
									<c:if test="${item!=pageinfo.pageNum}">
									<a class="btn btn-default" href="javascript:RefreshCurrentButton(${item})">${item}</a>
									</c:if>
									<c:if test="${item==pageinfo.pageNum}">
									<a class="btn btn-warning">${item}</a>
									</c:if>
							  	</c:forEach>
							  	<c:if test="${pageinfo.pageNum<pageinfo.pages}">
									<a class="btn btn-default" href="javascript:RefreshCurrentButton(${pageinfo.pageNum+1})">
										&nbsp;<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
									</a> 
									<a class="btn btn-default" href="javascript:RefreshCurrentButton(${pageinfo.pages})">
										&nbsp;<span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span>
									</a>
							  	</c:if>
							  	<c:if test="${pageinfo.pageNum>=pageinfo.pages}">
									<a class="btn btn-default disabled">
										&nbsp;<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
									</a>
									<a class="btn btn-default disabled">
										&nbsp;<span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span>
									</a>
							  	</c:if>
							</div>
						</div>
						<div class="col-xs-12 col-sm-5 text-right">
							<!-- 分页统计信息 -->
							<div class="btn-group" role="group" aria-label="...">
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-default disabled">共${pageinfo.pages}页</button>
										<button type="button" class="btn btn-default disabled">${pageinfo.total}行</button>
										<button type="button" class="btn btn-default disabled">${pageinfo.pageSize}行/页</button>
									</span>
									<input id="pageNum" name="pageNum" type="text"
										class="form-control text-right" value="${pageinfo.pageNum}"/>
									<span class="input-group-btn">
										<a class="btn btn-default" href="javascript:RefreshCurrentButton($('#pageNum').val())">
										刷新
										</a>
									</span>
								</div>
							</div>
						</div>
					</div>
