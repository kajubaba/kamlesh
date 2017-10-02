
function saveRole(roleAction){
	
	if(validateRoleForm()){
		if(ACTION_ADD==roleAction){
			addRole();
		}else if(ACTION_UPDATE==roleAction){
			updateRole();
		}	
	}
	
}

function addRole(){
	
	var roleData = $("#roleForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : role_base_url+"/add",
		data : roleData,
		success : addRoleSuccess
	});	
}

function addRoleSuccess(response){
	
	if("OK"==response.status){
		roleAction=ACTION_UPDATE;
		$("#roleSave").val("Update");
		$("#roleId").val(response.id);
		alert("Saved Successfully..");
	}else if("DUPLICATE"==response.status){
		alert("Group name already exists. Please choose different name");
	}
	
}

function updateRole(){
	
	var roleData = $("#roleForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : role_base_url+"/update",
		data : roleData,
		success : updateRoleSuccess
	});	
}

function updateRoleSuccess(response){
	if("OK"==response.status){
		alert("Updated Successfully..");	
	}else if("DUPLICATE"==response.status){
		alert("Group name already exists. Please choose different name");
	}
}

function validateRoleForm(){
	var name=jQuery.trim($("#name").val());
	var appId=jQuery.trim($("#applicationId").val());
	if(name!="" && appId!=""){
		return true;
	}else{
		alert("Mandatory Fields are missing !!");
		return false;
	}
}

function getAppRoles(appId){
	if($("#appId").val()==0){
		window.location=role_base_url+"/list";
	}else{
		window.location=role_base_url+"/list?applicationId="+$("#appId").val();
	}
	
}