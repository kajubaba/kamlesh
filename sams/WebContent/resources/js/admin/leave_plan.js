function listLeavePlans(){
	$.ajax({
		type: 'GET',
		url : admin_leave_plan_url+"/list",
		success: listLeavePlansSuccess
	});
}

function listLeavePlansSuccess(response){
	$("#adminContainer").html(response);
}

function newLeavePlan(){
	$.ajax({
		type: 'GET',
		url : admin_leave_plan_url+"/list",
		success: listLeavePlansSuccess
	});
}



