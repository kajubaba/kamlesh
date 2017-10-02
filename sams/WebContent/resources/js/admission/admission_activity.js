function openChangeStatusPopup(){
	$.ajax({
		type : 'GET',
		url : student_activity_base_url+"/changeStatus/view/"+$("#studentId").val(),
		success : openChangeStatusPopupSuccess
	});
	
}

function openChangeStatusPopupSuccess(response){
	$( "#popupChangeStudentStatusContainer" ).html(response);
	$('#popupChangeStudentStatus, #popupBackground').show();
}

function changeStatus(){
	var data = $("#changeStatusForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : student_activity_base_url+"/changeStatus/"+$("#studentId").val(),
		data : data,
		success : changeClassSuccess
	});	

}

function closeChangeStudentStatusPopup(){
	$('#popupChangeStudentStatus,#popupChangeStudentClass, #popupBackground').hide();
}


function openChangeClassPopup(){
	$.ajax({
		type : 'GET',
		url : student_activity_base_url+"/changeClass/view/"+$("#studentId").val(),
		success : openChangeClassPopupSuccess
	});
	
}

function openChangeClassPopupSuccess(response){
	$( "#popupChangeStudentClassContainer" ).html(response);
	$('#popupChangeStudentClass, #popupBackground').show();
}

function changeClass(){
	var data = $("#changeClassForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : student_activity_base_url+"/changeClass/"+$("#studentId").val(),
		data : data,
		success : changeClassSuccess
	});	

}

function changeClassSuccess(){
	location.reload();
}



function getChangeBusStopPopup(){
	var studentId = $("#studentId").val();
	$.ajax({
		type: 'GET',
		url : student_activity_base_url+"/changebusstop/view?studentId="+studentId,
		success: getChangeBusStopPopupSuccess
	});
}

function getChangeBusStopPopupSuccess(response){
	$("#popupChangeBusStop").html(response);
	$("#popupChangeBusStop").show();
	$("#changeBusStopPopupBackground").show();
}

function closeChangeBusStopPopup(){
	$('#popupChangeBusStop, #changeBusStopPopupBackground').hide();
}

function changeBusStopPopup(){
	var busStopId= $("#newBusStopId").val();
	var studentId = $("#studentId").val();
	var chnageCustBusStop = $("#chnageCustBusStop").val();
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : student_activity_base_url+"/changebusstop?studentId="+studentId+"&busStopId="+busStopId+"&chnageCustBusStop="+chnageCustBusStop,
		success: changeBusStopPopupSuccess
	});
}

function changeBusStopPopupSuccess(response){
	
	$("#changeBusStopMsg").html(response.msg);
	if("OK"==response.status){
		changeClassSuccess();
	}
	
}

function verifyBusStop(){
	var busStopId= $("#newBusStopId").val();
	var studentId = $("#studentId").val();
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : student_activity_base_url+"/verifybusstopchange?studentId="+studentId+"&busStopId="+busStopId,
		success: verifyBusStopSuccess
	});
}




function verifyBusStopSuccess(response){
	$("#changeBusStopMsg").html(response.msg);
	if("ERROR"==response.status){
		$("#saveBusStop").hide();
	}else{
		$("#saveBusStop").show();
	}
}

