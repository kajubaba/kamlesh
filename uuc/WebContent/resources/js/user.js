function saveUser(userAction){
	
	if(validateUserForm()){
		if(ACTION_ADD==userAction){
			addUser();
		}else if(ACTION_UPDATE==userAction){
			updateUser();
		}	
	}
	
}

function validateUserForm(){
	var firstName=jQuery.trim($("#firstName").val());
	
	var lastName=jQuery.trim($("#lastName").val());
	var userName=jQuery.trim($("#userName").val());
	var password=jQuery.trim($("#password").val());
	
	if(firstName!="" && lastName!="" && userName!="" && password!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
	
}

function addUser(){
	
	var userData = $("#userForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : user_base_url+"/add",
		data : userData,
		success : addUserSuccess
	});	
}

function addUserSuccess(response){
	
	if("OK"==response.status){
		userAction=ACTION_UPDATE;
		$("#userSave").val("Update");
		$("#userId").val(response.id);
		alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("User name already exists. Please choose different name");
	}
	
}

function updateUser(){
	
	var userData = $("#userForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : user_base_url+"/update",
		data : userData,
		success : updateUserSuccess
	});	
}

function updateUserSuccess(response){
	if("OK"==response.status){
		alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("User name already exists. Please choose different name");
	}
}


function getUserListOfRole(roleId){
	$.ajax({
		type: 'GET',
		url : "<c:url value='/admin/user/role/"+roleId+"'/>",
		success: getUserListOfRoleSuccess
	});
}

function getUserListOfRoleSuccess(data){
	$("#adminContainer").html(data);
}
