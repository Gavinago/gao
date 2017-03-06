<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="assets/css/Lobibox.min.css"/>

                <!--Messageboxes-->
                    <!--Example-->
                       
                            <!--Basic examples-->
                                <!--Confirm-->
                                <fieldset>
                                    <div class="bs-example">
                                        <p id="popupYesNoBasic" >Confirm</p>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="bs-example">
                                        <p id="popupWindowExample" >Confirmrrr</p>
                                    </div>
                                </fieldset>
                                <fieldset>
                                    <div class="bs-example">
                                        <p id="popupWindowBasic" >Confirmrrr44</p>
                                    </div>
                                </fieldset>
                               
                               <div>
                                <div class="bs-example">
                                    <h3>Disable icon</h3>
                                    <button id="popupConfirmNoIcon" class="btn btn-primary">Button</button>
                                </div>
                            </div>
                        <!--Callbacks-->
                        
                       
                <!--Notifications-->
		
	<script src="assets/js/jquery-2.1.4.min.js"></script>
    
    <script src="assets/js/lobibox.min.js"></script>
  <script type="text/javascript">
 $(function () {
	    (function () {

	        (function () {
	            $('#popupYesNoBasic').click(function () {
	                Lobibox.confirm({
	                	title: '修改密码',
	                	iconClass: false,
	                	msg: '<pre><div class="panel-body"><div class="row"><div class="col-sm-3 hidden-xs text-center"></div><div class="col-xs-12 col-sm-6"><div style="padding: 12px;padding-top: 20px"><div class="input-group"><span class="input-group-addon" aria-hidden="true"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span><input id="userName" name="userName" type="text" class="form-control"style="font-size: 16px"placeholder="登录账号" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> </span><input id="password" name="password" type="password" class="form-control"style="font-size: 16px"placeholder="登录密码" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" ><span class="glyphicon glyphicon-ok"></span></span><input id="kaptcha" name="verification" type="text" class="form-control"style="font-size: 16px"placeholder="验证码" ></div></div></div><div class="col-sm-3 hidden-xs text-center"></div></div></div></pre>',
	                	callback: function ($this, type, ev) {
	                        if (type === 'yes') {
	                            Lobibox.notify('success', {
	                                msg: 'You have clicked "Yes" button.'
	                            });
	                        } else if (type === 'no') {
	                            Lobibox.notify('error', {
	                                msg: 'You have clicked "No" button.'
	                            });
	                        }
	                    }
	                });
						
	            });
	            
	        })();
	        
	    })();
	    //-----------------------------------
	    (function () {
            $('#popupWindowExample').click(function () {
                Lobibox.window({
                    title: '修改密码',
                    //Available types: string, jquery object, function
                    content: function () {
                        return $('.container');
                    },
                    url: 'http://192.168.1.111/fristProject/views/nest/myinfo.jsp',
                    autoload: true,
                    loadMethod: 'GET',
                    //Load parameters
                    params: {
                        param1: 'Lorem',
                        param2: 'Ipsum'
                    },
                    callback: function ($this, type, ev) {
                        if (type === 'load') {
                            $this.load(function () {
                                var $body = $this.$el.find('.lobibox-body');
                                $body.html('<div class="highlight"><pre><code>' + $body.html() + '</code></pre></div>');
                                hljs.highlightBlock($body.find('code')[0]);
                            });
                        }
                    }
                });
            });
        })();
	    
	    //----------------------------------------
	    $('#popupWindowBasic').click(function () {
                Lobibox.window({
                    title: '修改密码',
                    content: [
                        
                        '<pre><div class="panel-body"><div class="row"><div class="col-sm-3 hidden-xs text-center"></div><div class="col-xs-12 col-sm-6"><div style="padding: 12px;padding-top: 20px"><div class="input-group"><span class="input-group-addon" aria-hidden="true"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span><input id="userName" name="userName" type="text" class="form-control"style="font-size: 16px"placeholder="登录账号" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> </span><input id="password" name="password" type="password" class="form-control"style="font-size: 16px"placeholder="登录密码" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" ><span class="glyphicon glyphicon-ok"></span></span><input id="kaptcha" name="verification" type="text" class="form-control"style="font-size: 16px"placeholder="验证码" ></div></div></div><div class="col-sm-3 hidden-xs text-center"></div></div></div></pre>'
                        
                    ].join(""),
                    buttons: {
                        load: {
                            text: '确认'
                        },
                        close: {
                            text: '取消',
                            closeOnClick: true
                        }
                        
                    },
                    callback: function ($this, type, ev) {
                        if (type === 'load') {
                        	var a = $("#userName").val();
                            alert(a);
                        }
                    }
                });
            });
	    //----------------------------------------
	    $('#popupConfirmNoIcon').click(function () {
                Lobibox.confirm({
                	 title: '修改密码',
                	iconClass: false,
                    msg:  '<pre><div class="panel-body"><div class="row"><div class="col-sm-3 hidden-xs text-center"></div><div class="col-xs-12 col-sm-6"><div style="padding: 12px;padding-top: 20px"><div class="input-group"><span class="input-group-addon" aria-hidden="true"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span><input id="userName" name="userName" type="text" class="form-control"style="font-size: 16px"placeholder="登录账号" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> </span><input id="password" name="password" type="password" class="form-control"style="font-size: 16px"placeholder="登录密码" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" ><span class="glyphicon glyphicon-ok"></span></span><input id="kaptcha" name="verification" type="text" class="form-control"style="font-size: 16px"placeholder="验证码" ></div></div></div><div class="col-sm-3 hidden-xs text-center"></div></div></div></pre>',
                    	
                    	callback: function ($this, type, ev) {
	                        if (type === 'yes') {
	                            Lobibox.notify('success', {
	                                msg: 'You have clicked "Yes" button.'
	                            });
	                        } else if (type === 'no') {
	                            Lobibox.notify('error', {
	                                msg: 'You have clicked "No" button.'
	                            });
	                        }
	                    }
                    
                    
                    
                });
            });
	    //-------------------------------
	});
 
 </script>
 
 <div class="panel-body">
				<div class="row">
					
					<div class="col-xs-12 col-sm-6">
						<div style="padding: 12px;padding-top: 20px">
							<div class="input-group">
								<span class="input-group-addon" aria-hidden="true">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</span>
								<input id="userName" name="userName" type="text" class="form-control"
										style="font-size: 16px"
									placeholder="登录账号" aria-describedby="basic-addon1">
							</div>
						</div>						
						<div style="padding: 12px">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">
									<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
								</span>
								<input id="password" name="password" type="password" class="form-control"
										style="font-size: 16px"
									placeholder="登录密码" aria-describedby="basic-addon1">
							</div>
						</div>
							<div style="padding: 12px">
								<div class="input-group">
									<span class="input-group-addon" >
										<span class="glyphicon glyphicon-ok"></span>
									</span>
									<input id="kaptcha" name="verification" type="text" class="form-control"
										style="font-size: 16px"
										placeholder="验证码" >
								</div>
							</div>
					</div>
					<div class="col-xs-12 col-sm-6">
						<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-success">
								<label class="col-sm-2 control-label" for="inputSuccess">
									输入成功
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputSuccess">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-warning">
								<label class="col-sm-2 control-label" for="inputWarning">
									输入警告
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputWarning">
								</div>
							</div>
							</div>
							<div style="padding: 12px;padding-top: 20px">
							<div class="form-group has-error">
								<label class="col-sm-2 control-label" for="inputError">
									输入错误
								</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputError">
								</div>
							</div>
							</div>
					</div>
				</div>
		</div>
</body>
</html>