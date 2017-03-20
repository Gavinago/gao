<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="panel-body alert-success">
				<div class="row ">
					<div class="col-xs-12 col-sm-12">
							<div style="padding: 12px;padding-top: 20px">
								<div class="form-group has-success">
									<label class="col-sm-2 control-label" for="inputSuccess">
										订单编号:
									</label>
									<div class="col-sm-10">
										<div class="form-cell">${order}</div>
									</div>
								</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
								<div class="form-group has-success">
									<label class="col-sm-2 control-label" >
										客户姓名:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestname}</div>
									</div>
									<label class="col-sm-2 control-label" >
										房 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestroomname}</div>
									</div>
									<label class="col-sm-2 control-label">
										入住时间:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestcometime}</div>
									</div>
									<label class="col-sm-2 control-label" >
										退房时间:
									</label>
									<div class="col-sm-4">
									<div class="form-cell">${guest.guestleavetime}</div>
									</div>
									<label class="col-sm-2 control-label">
										房 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestroomprice}</div>
									</div>
									<label class="col-sm-2 control-label" >
										会 &nbsp;&nbsp;员&nbsp;&nbsp;价:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestroomtotleprice}</div>
									</div>
									<label class="col-sm-2 control-label" >
										押 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestcash}　</div>
									</div>
									<label class="col-sm-2 control-label">
										收 &nbsp;&nbsp;款 &nbsp;人:
									</label>
									<div class="col-sm-4">
										<div class="form-cell">${guest.guestcomepayee}　</div>
									</div>
									<label class="col-sm-2 control-label">
										退还押金:
									</label>
									<div class="col-sm-4">
										<label class="control-label" for="yesExitCash">全部退还</label>
										<input type="radio" name="exitCash" onclick="javascript:exitCash('YES');"id="yesExitCash" checked/>
									</div>
									<div class="col-sm-6">
										<label class="control-label" for="noExitCash">部分退还或不退</label>
										<input type="radio" name="exitCash" onclick="javascript:exitCash('NO');" id="noExitCash"/>
									</div>
									<div  class="col-sm-12" id="returnCashDiv"  style="padding: 12px;padding-top: 5px;display: none;">
										<label class="col-sm-2 control-label">应退还:</label>
										<div class="col-sm-4">
											<div class="form-cell">${guest.guestcash}(元)</div>
										</div>
										<label class="col-sm-2 control-label">实际退还:</label>
										<div class="col-sm-4">
											<input class="form-control" type="text" name="refundAmount" placeholder="实际退还金额"></input>
										</div>
									<label class="col-sm-12 control-label" padding-top: 20px">
										备&nbsp;&nbsp;&nbsp;&nbsp;注:
										<br/>
									</label>
									<div class="col-sm-12">
										<textarea style=" width: 100%; height: 50px;resize: none;"></textarea>
									</div>
									<label class="col-sm-2 control-label" padding-top: 20px">
									</label>
										<div class="col-sm-4">
											<label class="control-label">不添加图片</label>
											<input type="radio" name="cashremarkimg" id="NoCashremarkimg" checked/>
										</div>
										<div class="col-sm-4">
											<label class="control-label">添&nbsp;&nbsp;&nbsp;加&nbsp;&nbsp;&nbsp;图&nbsp;&nbsp;&nbsp;片&nbsp;&nbsp;</label>
											<input type="radio" name="cashremarkimg" id="isCashremarkimg1"/>
										</div>
									</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
function exitCash(args){
	if(args=='YES'){
		if($("#yesExitCash").prop('checked')){
			$("#returnCashDiv").css("display","none");
		}
	}else{
		if($("#noExitCash").prop('checked')){
			$("#returnCashDiv").css("display","");
		}
	}
}
</script>