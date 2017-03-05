function go(url)
{
    window.location = url;
}

function deleteContact(url)
{
    var isOK = confirm("Are you sure to delete?");
    if(isOK)
    {
        go(url);
    }
}
function exit(url){
	var isOK = confirm("确定退出么?");
	if(isOK){
		go(url);
	}
}