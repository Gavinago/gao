<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@
	taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
	<link rel="stylesheet" href="<c:url value="assets/css/bootstrap.min.css"/>"/>
	<link rel="stylesheet" href="<c:url value="assets/css/Lobibox.min.css"/>"/>
<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-collapse-menu"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
	
			<div class="navbar-brand" style="padding-top: 5px">
				<img alt="" src='<c:url value="/assets/img/home32.png"/>'/>
			</div>
			<div class="navbar-brand">
				<strong>${title}</strong>
			</div>
		</div>
	
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-collapse-menu">
			<jsp:include page="/navigation/dynamenu.do" />
			<shiro:guest>
				<!-- 未登录菜单 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="login.do">登录</a></li>
				</ul>
			</shiro:guest>
			<shiro:user>
				<!-- 已登录菜单 -->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><shiro:principal></shiro:principal><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a id="popupYes" href="javascript:void();">注销</a></li>
							<li class='divider'></li>
							<li><a id="myinfo" href="javascript:void();">我的资料</a></li>
							<li class='divider'></li>
							<li><a id="changepassword" href="javascript:void();">修改密码</a></li>
						</ul>
					</li>
				</ul>
			</shiro:user>
		</div>
	</div>
</nav>
<script src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/assets/js/lobibox.min.js"/>"></script>
 <script type="text/javascript">
 $(function () {
	 var w = window.innerWidth;
	    (function () {

	        (function () {
	            $('#popupYes').click(function () {
	                Lobibox.confirm({
	                	msg: "你确认退出吗?",
	                	callback: function ($this, type, ev) {
	                        if (type === 'yes') {
	                        	window.location.href='<c:url value="/index/login.do"/>';
	                        } else if (type === 'no') {
	                        }
	                    }
	                });
						
	            });
	            $('#changepass').click(function () {
	                Lobibox.notify('warning', {
	                	position: 'top left',
	                	size: 'mini',
	                    width:''+w,
	                    title: '通知',
	                    msg: '系统检测到您是初始密码，请及时修改密码！'
	                });
	            });
	            
	        })();
	        
	    })();
	    $('#changepassword').click(function () {
            Lobibox.confirm({
            	 title: '修改密码',
            	iconClass: false,
                msg:  '<pre><div class="panel-body"><div class="row"><div class="col-sm-3 hidden-xs text-center"></div><div class="col-xs-12 col-sm-6"><div style="padding: 12px;padding-top: 20px"><div class="input-group"><span class="input-group-addon" aria-hidden="true"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span><input id="oldpassword" name="oldpassword" type="password" class="form-control"style="font-size: 16px"placeholder="旧密码" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span> </span><input id="password" name="password" type="password" class="form-control"style="font-size: 16px"placeholder="新密码" aria-describedby="basic-addon1"></div></div><div style="padding: 12px"><div class="input-group"><span class="input-group-addon" ><span class="glyphicon glyphicon-ok"></span></span><input id="repassword" name="repassword" type="password" class="form-control"style="font-size: 16px"placeholder="再次输入新密码" ></div></div> <div id="errordiv"class="input-group text-center alert"></div></div><div class="col-sm-3 hidden-xs text-center"></div></div></div></pre>',
                buttons: {
                    yes: {
                        text: ' 确认 ',
                        closeOnClick: false
                    },
                    no: {
                        text: ' 取消 ',
                        closeOnClick: true
                    }
                },
                callback: function ($this, type, ev) {
                    if (type === 'yes') {
                    	var oldpassword = $("#oldpassword").val();
                    	var password = $("#password").val();
                    	var repassword = $("#repassword").val();
                    	if(oldpassword.trim()==""){
                    		$("#oldpassword").focus();
                    		Lobibox.notify('error', {
                            	title:"错误",
                            	msg: '旧密码不能为空！'
                            });
                    	}else if(password.trim()==""){
                    		$("#password").focus();
                    		Lobibox.notify('error', {
                            	title:"错误",
                            	msg: '密码不能为空！'
                            });
                    	} else if(repassword.trim()==""){
                    		$("#repassword").focus();
                    		Lobibox.notify('error', {
                            	title:"错误",
                            	msg: '确认密码不能为空！'
                            });
                    	}else if(oldpassword==password){
                    		Lobibox.notify('error', {
                            	title:"错误",
                            	msg: '旧密码和新密码不能相同！'
                            });
                    	}else if(password!=repassword){
                    		Lobibox.notify('error', {
                            	title:"错误",
                            	msg: '两次密码不一致！'
                            });
                    	}else{
                    		$.ajax({
                    			url:"<c:url value='changepassword.do'/>?oldpassword="+oldpassword+"&password="+password,
                    			async:false,
                    			cache:false,
                    			type:"post",
                    			dataType:"json",
                    			success:function(e){
                    				if(e==1){
                    					$(".btn-close").click();
                    					Lobibox.alert('success', {
                    						 msg: '密码修改成功！2秒后跳转登录页面 请重新登录'
                    					});
                    					setTimeout("window.location.href='<c:url value="index/login.do"/>'",2000);
                    				}else{
                    					Lobibox.alert('error', {
                       					 msg:'密码修改失败！请重新修改'
                       				 });
                    				}
                    			},erreo:function(e){
                    				 Lobibox.alert('error', {
                    					 msg:'密码修改失败！请重新修改'
                    				 });
                    			}
                    		})
                    		
                    	}
                    	
                    }else if (type === 'no') {
                    }
                }
            });
        });
	    $('#myinfo').click(function () {
	    	var Htmldata="";
	    	$.ajax({
	    		url:'<c:url value="/ajax/myinfo.do"/>',
	    		dateType:"json",
	    		async:false,
	    		type:"get",
	    		success:function(data){
	    			Htmldata = data;
	    		}
	    	});
            Lobibox.window({
            	title: '我的信息',
                //Available types: string, jquery object, function
                content: function () {
                    return Htmldata;
                }
            });
            var rolename = $("#rolename").val();
	    	var rightlenght= $("#rightlenght").val();
	    	$("#button").append("("+rolename+")");
	    	if(rightlenght){
	    		if(rightlenght%3==1){
	    			for(var i=0;i<=1;i++){
		    			$("div[name='myright']").last().append(
	    					'<div class="form-group has-success">'+
							'<div class="col-sm-4">'+
								'<input type="text" class="form-control" id="inputError"  disabled="disabled" value="">'+
							'</div>'+
							'</div>'	
		    			);
	    			}
	    		}else if(rightlenght%3==2){
	    			$("div[name='myright']").last().append(
	    					'<div class="form-group has-success">'+
							'<div class="col-sm-4">'+
								'<input type="text" class="form-control" id="inputError"  disabled="disabled" value="">'+
							'</div>'+
							'</div>'	
		    		);
	    		}
	    	}
        });
	});
 
 </script>