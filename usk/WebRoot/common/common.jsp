<%
String paths = request.getContextPath();
String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+paths+"/";
%>
<link rel="stylesheet" type="text/css" id="easyuiTheme"  href="<%=basePaths %>ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePaths %>ui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePaths %>ui/demo.css">
	<!--  <script type="text/javascript" src="ui/jquery.min.js"></script>-->
	<script type="text/javascript" src="<%=basePaths %>ui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="<%=basePaths %>ui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePaths %>ui/locale/easyui-lang-zh_CN.js"></script>
	  <script type="text/javascript" src="<%=basePaths %>ui/jquery.cookie.js"></script>
 <script type="text/javascript" src="<%=basePaths %>ui/changeEasyuiTheme.js"></script>
<script type="text/javascript" src="<%=basePaths %>/js/Common.js"></script>
