// JavaScript Document
$(function(){ 
jsonpajax_1();
});


function jsonpajax_1() {

    $.ajax({

        url: "<%=basePath%>backMail.do?pch=4d9bac5e-56b0-4e0c-a9ce-708592c4dcaf-1384307920925",

        type: "get",

        dataType: "jsonp",

        jsonp: "callback",

        success: function(data) {

            //var tt = '';

           // $.each(data, function(k, v) {

            //    tt += k + "：" + v + "<br/>";

           // });

           // $("#divmessage").html(tt);

        }

    });

}