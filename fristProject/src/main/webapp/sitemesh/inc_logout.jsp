<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <button id="loginoutbut" style="display: none" data-toggle="modal" data-target="#dialogLogout">注销</button>
<div class="modal fade" id="dialogLogout" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               	&nbsp;
            </h4>
         </div>
         <div class="modal-body">
            	是否确定要退出？
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default"
               data-dismiss="modal">不退出
            </button>
            <a class="btn btn-primary" href="logout.do">确定退出</a>
         </div>
      </div><!-- /.modal-content -->
	</div>
</div>
<script>
function MyLogout(){
	document.getElementById("loginoutbut").click(); 
}
</script>
