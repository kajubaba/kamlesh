function viewChangeEnqOwnerPopup(enquiryId){
	$.ajax({
		type: 'GET',
		url : enqActivityUrl+"/viewChangeOwner",
		data : "enquiryId="+enquiryId,
		success: popupSuccess
	});
}

function viewChangeEnqAssigneePopup(enquiryId){
	$.ajax({
		type: 'GET',
		url : enqActivityUrl+"/viewChangeAssignee",
		data : "enquiryId="+enquiryId,
		success: popupSuccess
	});
}

function viewChangeEnqStatusPopup(enquiryId){
	$.ajax({
		type: 'GET',
		url : enqActivityUrl+"/viewChangeStatus",
		data : "enquiryId="+enquiryId,
		success: popupSuccess
	});
}

function viewEnqfollowupPopup(enquiryId){
	$.ajax({
		type: 'GET',
		url : enqActivityUrl+"/viewFollow",
		data : "enquiryId="+enquiryId,
		success: popupSuccess
	});
}

function popupSuccess(response){
	$("#enq_activity_popup" ).html(response);
	$("#enq_activity_popup").modal('show');
	/*$("#enq_activity_popup").draggable( { handle:"div.popup_header" } );
	$("#enq_activity_popup").draggable( { handle:"div.popup_header" } );
	$("#enq_activity_popup").draggable( { handle:"div.popup_header" } );
	$("#enq_activity_popup").draggable( { handle:"div.popup_header" } );
	$("#enq_activity_popup, #popupBackground").show();*/
}

function closeEnqActivityPopup(){
	//$('#enq_activity_popup, #popupBackground').hide();
	$("#enq_activity_popup").modal('hide');
}

function changeEnqOwner(){
	$("#newOwnerId").val($("#owners").val());
	$("#comments").val($("#changeOwnerComments").val());
	var data = $("#EnquiryActivityForm").serialize();
	
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : enqActivityUrl+"/changeOwner",
		data : data,
		success: closeEnqActivityPopup
	});
}

function changeEnqAssignee(){
	$("#newAssigneeId").val($("#assignees").val());
	$("#comments").val($("#changeAssigneeComments").val());
	var data = $("#EnquiryActivityForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : enqActivityUrl+"/changeAssignee",
		data : data,
		success: closeEnqActivityPopup
	});
}

function changeEnquiryStatus(){
	$("#newStatusId").val($("#enqStatusList").val());
	$("#comments").val($("#changeStatusComments").val());
	var data = $("#EnquiryActivityForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : enqActivityUrl+"/changeStatus",
		data : data,
		success: closeEnqActivityPopup
	});
}

function followEnquiry(){
	$("#followupActivity").val($("#followupActivities").val());
	$("#comments").val($("#followUpComments").val());
	var data = $("#EnquiryActivityForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : enqActivityUrl+"/follow",
		data : data,
		success: closeEnqActivityPopup
	});
}

