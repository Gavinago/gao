<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
  </head>
  
  <body>
  	<form id="mainForm" name="mainForm" action="" method="post">
	  	<table bgcolor="lightblue" align="center" width="550" >
	  		<tr><td></td></tr>
	  		<tr><td>
	  			<table border="0">
					<tr><td colspan="3" align="center">Register</td></tr>
					<tr><td align="right">Name</td><td><input type="text" id="name" name="name" onblur="checkUser()" style="width:150; height:20"/><span style="color:red" id="name1"></span></td><td></td></tr>
					<tr><td align="right">Password</td><td><input type="password" id="password" name="password"  onblur="checkPassword()" style="width:150; height:20"/><span style="color:red" id="password1"></span></td><td></td></tr>
					<tr><td align="right">RePassword</td><td><input type="password" id="repassword" name="repassword" onblur="checkRepassword()" style="width:150; height:20"/><span style="color:red" id="repassword1"></span></td><td></td></tr>
					<tr><td align="right">DOB</td><td><input type="text" name="dob" style="width:150; height:20"/></td><td></td></tr>
					<tr><td align="right">Gender</td><td>
						<select name="gender">
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
					</td><td></td></tr>
					<tr><td align="right">Address</td><td><input type="text" name="address" style="width:150; height:20"/></td><td></td></tr>
					<tr><td align="right">Email</td><td><input type="text" id="email" name="email" onblur="checkEmail()" style="width:150; height:20"/>&nbsp;&nbsp;<span style="color: red" id="email1"></span></td><td></td></tr>
					<tr><td align="right">Mobile</td><td><input type="text" name="mobile" style="width:150; height:20"/></td><td></td></tr>
	  				<tr><td colspan="3"><div align="center"><input type="submit" name="submit" value="Regist" onclick="doCheckSubmit()"/></div></td></tr>
	  			</table>
	  		</td></tr>
	  	</table>
	  </form>	
  </body>
</html>
