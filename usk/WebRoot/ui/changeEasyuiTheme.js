
function changeThemeFun(themeName) {/* 更换主题 */
	//alert(themeName);
	var $easyuiTheme = $('#easyuiTheme');
	var url = $easyuiTheme.attr('href');
	var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
	//alert(url);
	$easyuiTheme.attr('href', href);

	var $iframe = $('iframe');
	
	//setInterval(show,3000);// 注意函数名没有引号和括弧！
	//alert(href);
	//alert($iframe.length);
	//setTimeout(function(){
        //在这里执行你的代码
		
	//},500);
		if ($iframe.length > 0) {
			for ( var i = 0; i < $iframe.length; i++) {
				var ifr = $iframe[i];
				//alert($(ifr).contents().find('#easyuiTheme'));
				$(ifr).contents().find('#easyuiTheme').attr('href', href);
			}
			
		}
		$.cookie('easyuiThemeName', themeName, {
			expires : 7
		});

	

	

};
if ($.cookie('easyuiThemeName')) {
	//火狐下不用延迟加载会有问题
	setTimeout(function(){
    //在这里执行你的代码
		changeThemeFun($.cookie('easyuiThemeName'));
},500);
	
}