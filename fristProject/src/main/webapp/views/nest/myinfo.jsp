<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="panel-body alert-success">
				<div class="row ">
					
					
					<div class="col-xs-12 col-sm-12">
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-success">
								<label class="col-sm-4 control-label" for="inputSuccess">
									用户昵称:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputSuccess" disabled="disabled" value="${user.userName}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									用户姓名:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${user.name}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-error">
								<label class="col-sm-4 control-label" for="inputError">
									创建者 :
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputError" disabled="disabled" value="${user.creator}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-success">
								<label class="col-sm-4 control-label" for="inputSuccess">
									创建时间:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputSuccess" disabled="disabled" value="${user.creatorTime}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									身份证号码:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${user.idCard}">
								</div>
							</div>
							</div>
						<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-success">
								<label class="col-sm-4 control-label" for="inputError">
									电话号码:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputError"disabled="disabled" value="${user.phoneNum}">
								</div>
							</div>
						</div>
						<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputError">
									居住地址:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputError"disabled="disabled" value="${user.address}">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="alert" style="margin-top: 10px;">
					<div class="text-center">
						<div id="button" >
					            <button type="button" class="close"  data-dismiss="alert">
					             </button>我的权限
						        	
						</div>
					</div>
					<div class="row ">
					<c:forEach items="${right}" var="right1" varStatus="status">
						<c:if test="${status.index==0}">
							<div style="display: none;">
							<input type="hidden" id="rolename" value="${right1.rolename}">
							</div>
						</c:if>
						<c:choose>
							<c:when test="${status.index%3==0}">
								<div name="myright">
										<div class="form-group has-success">
											<div class="col-sm-4">
												<input type="text" class="form-control text-center" id="inputError"  disabled="disabled" value="${right1.rightname}">
											</div>
										</div>
								
							</c:when>
							<c:when test="${status.index%3==1}">
										<div class="form-group has-success">
											<div class="col-sm-4">
												<input type="text" class="form-control text-center" id="inputError"  disabled="disabled" value="${right1.rightname}">
											</div>
										</div>
							</c:when>
							<c:when test="${status.index%3==2}">
										<div class="form-group has-success">
											<div class="col-sm-4">
												<input type="text" class="form-control text-center" id="inputError"  disabled="disabled" value="${right1.rightname}">
											</div>
										</div>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
						<c:if test="${fn:length(right)%3!=0}">
							</div>
							<div style="display: none;">
							<input type="hidden" id="rightlenght" value="${fn:length(right)}">
							</div>
						</c:if>
				</div>
				</div>
		</div>