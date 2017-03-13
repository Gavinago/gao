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
									订单编号:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputSuccess" disabled="disabled" value="${order}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									客户姓名:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${guest.guestname}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-error">
								<label class="col-sm-4 control-label" for="inputError">
									房号 :
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputError" disabled="disabled" value="${guest.guestroomname}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-success">
								<label class="col-sm-4 control-label" for="inputSuccess">
									入住日期:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputSuccess" disabled="disabled" value="${guest.guestcometime}">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									退房日期:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${guest.guestleavetime}">
								</div>
							</div>
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									房价:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${guest.guestroomprice}">
								</div>
							</div>
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									会员价:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${guest.guestroomtotleprice}">
								</div>
							</div>
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									押金:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${guest.guestcash}">
								</div>
							</div>
							<div class="form-group has-warning">
								<label class="col-sm-4 control-label" for="inputWarning">
									收款人:
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="inputWarning" disabled="disabled" value="${guest.guestcomepayee}">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>