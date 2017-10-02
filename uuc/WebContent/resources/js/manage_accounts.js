function showHideDiv(target){
	$("#forgotPassUserName").val("");
	$("#changePassUserName").val("");
	$("#errorMsg").hide();
	$("#errorMsg").html("");
	if(target=="forgotPass"){
		$("#changePass").hide();
		$("#"+target).slideToggle("slow");
	}else if(target=="changePass"){
		$("#forgotPass").hide();
		$("#"+target).slideToggle("slow");
	}else{
		$("#changePass").hide("slow");
		$("#forgotPass").hide("slow");
	}
}

function continueToProcess(){
	if(!$("input[name='actionName']:checked").val()){
		$("#errorMsg").html("Please select one of the following options.");
		$("#errorMsg").show();
	}else{
		var troubleFormData = $("#troubleForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : ma_base_url+"/resolve",
			data : troubleFormData,
			success : continueToProcessSuccess
		});
	}
}

function continueToProcessSuccess(response){
	if("OK"==response.status){
		$("#troubleForm").submit();
	}else if("ERROR"==response.status){
		$("#errorMsg").html(response.msg);
		$("#errorMsg").show();
	}
}

function changePassword(){
	var newPassword=$("#newPassword").val();
	var confirmPassword=$("#confirmPassword").val();
	
	if($("#password").val()==""){
		$("#errorMsg").html("Password can not be blank");
		$("#errorMsg").show();
	}else if(newPassword!=confirmPassword){
		$("#errorMsg").html("New password and Confirm password are not same");
		$("#errorMsg").show();
	}else if(newPassword.length<8){
		$("#errorMsg").html("New password should have minimum 8 characters");
		$("#errorMsg").show();
	}else{
		$("#changePassForm").submit();
	}	
}