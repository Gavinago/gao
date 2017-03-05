	//验证用户
		function checkUser(){
			var name = $("#name").val();
			if(name==null||name.length==0){
				$("#name1").html("用户不能为空！");
				return false;
			}else if(!name.match( /^[\u4E00-\u9FA5a-zA-Z0-9_]{1,14}$/)){
				$("#name1").html("用户名只允许为英文，下划线，数字和汉字！");
				return false;
			}else{
				//ajax
//				$.ajax({
//					type:"post",
//					url:"",
//					data:{username:name},
//					async: false,
//					dataType:"json",
//					success:function(jsonData){
//						if(jsonData.flag=="true"){
//							$("#name1").html("<font color='red'>会员账号已被使用！</font>");
//							f=false;
//						}else{
//							$("#name1").html("该用户可用！");
//							f=true;
//						}
//					}
//				});
				$("#name1").html("");
			}
			return true;
		}

		//验证手机号码格式是否正确
		function IsMobile(str){
			return str.search(/^1[3|4|5|8][0-9]\d{8}$/)==0?true:false;
		}
		//验证手机号码
		function checkMobile(){
			var mobile = $("#mobile").val();
			if(mobile==null||mobile.length==0){
				$("#mobile1").html("手机号码不能为空！");
				return false;
			}else if(!IsMobile(mobile)){
				$("#mobile1").html("请填写有效的Mobile！");
				return false;
			}else{
				$("#mobile1").html("填写正确");
			}
			return true;
		}
		
		//验证邮箱格式是否正确
		function IsEmail(str){
			return str.search(/[\w\-]{1,}@[\w\-]{1,}\.[\w\-]{1,}/)==0?true:false
		}
		//邮件验证
		function checkEmail(){
			var email =$("#email").val();
			if(email==null||email.length==0){
				$("#email1").html("邮箱不能为空！");
				return false;
			}else if(!IsEmail(email)){
				$("#email1").html("请填写有效的Email地址！");
				return false;
			}else{
				$("#email1").html("填写正确");
			}
			return true;
		}
		//密码校验
		function checkPassword(){
			var password =$("#password").val();
			if(password.length<8||password.length>30){
				$("#password1").html("密码必须在8~30位之间！");
				return false;
			}else if(!password.match(/^[a-zA-Z0-9_]{1,}$/)){
				$("#password1").html("密码只允许由英文，下划线和数字组成！");
				return false;
			}else{
				$("#password1").html("填写正确");
			}
			return true;
		}
		//重复密码校验
		function checkRepassword(){
			var password =$("#password").val();
			var rpassword =$("#repassword").val();
				if(password.length>0){
					if(password!=rpassword){
						$("#repassword1").html("两次输入的密码不一致！");
						return false;
					}else{
						$("#repassword1").html("两次密码输入一致");
					}
				}else{
					$("#repassword1").html("");
					return false;
				}
				return true;
		}
		
		function doCheckSubmit(){
			var username =$("#name").val();
			var password =$("#password").val();
			var repassword =$("#repassword").val();
			var email =$("#email").val();
			var mobile =$("#mobile").val();
			var date=$("#date").val();
			var code=$("#code").val();
			var status=$("#status").val();
			var type=$("#type").val();
			var isdelete=$("#isdelete").val();
			if(!checkUser()){
				$("#name").focus();
				return false;
			}
			if(!checkEmail()){
				$("#email").focus();
				return false;
			}
			if(!checkMobile()){
				$("#mobile").focus();
				return false;
			}
			if(!checkPassword()){
				$("#password").focus();
				return false;
			}
			if(!checkRepassword()){
				$("#rpassword").focus();
				return false;
			}
			
			if(!checkCode()){
				$("#code").focus();
				return false;
			}
			$("#mainForm").submit();
		}
		
		if(document.addEventListener){//如果是Firefox
			document.addEventListener("keypress",fireFoxHandler, true);
			}else{
				document.attachEvent("onkeypress",ieHandler);
			}
			
			function fireFoxHandler(evt){
			//firefox
			if(evt.keyCode==13){
				doCheckSubmit();
			}
			}
			
			function ieHandler(evt){
			//IE
			if(evt.keyCode==13){
				doCheckSubmit();
			}
		} 