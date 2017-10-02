 
  
  <div id="footer">&nbsp; </div>
</div>
<!-- 
<div id="dvLoadingBG"></div>
<div id="dvLoading"></div>
 -->
</body>


</html>
<div id="cpPopupBG" class="popupBack" onclick="closeChangePasswordPopup()"></div>
<div id="changePWS" class="popup">
  	<div class="popup_header">Change Password</div>
  	<div id="changePasswordContainer"></div>
</div>
<div id="loadingDiv"></div>
<script type="text/javascript">

$(function() {
	
	$('#loadingDiv').hide()
	.ajaxStart(function() {
	    $(this).show();
	})
	.ajaxStop(function() {
	    $(this).hide();
	})
	
	
});




function closeChangePasswordPopup(){
	$('#changePWS, #cpPopupBG').hide();
}

function getChangePasswordView(){
	$.ajax({
		type: 'GET',
		url : change_password_url+"/view",
		success: getChangePasswordViewSuccess
	});
}
function getChangePasswordViewSuccess(response){
	$("#changePasswordContainer").html(response);
	$('#changePWS, #cpPopupBG').show();
}

function changePassword(){
	var pwd= jQuery.trim($("#newPassword").val());
	
	if(pwd.length<8){
		alert("Password should have minimum 8 characters");
	}else{
		var cpFormData = $("#cpForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : change_password_url+"/change",
			data : cpFormData,
			success : changePasswordSuccess
		});	
	}
	
		
}

function changePasswordSuccess(response){
	if("OK"==response.status){
		//alert("Password changed successfully..");
	}
}

</script>