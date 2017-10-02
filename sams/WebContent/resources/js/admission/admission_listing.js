function renewAdmission(){
	window.location = admission_base_url+"/renewal";
}

function findStudents(academicYearId){
	var searchFormData = $("#findForm").serialize();
	$.ajax({
		type : 'GET',
		url : admission_list_base_url+"/find/"+academicYearId,
		data : searchFormData,
		success : findStudentsSuccess
	});	
}

function findStudentsSuccess(response){
	$("#listContainer").html(response);
	bindStudentListWithDataTable();
}

function exportStudents(academicYearId){
	$("#findForm").attr("action", admission_list_base_url+"/export/"+academicYearId);
	$("#findForm").attr("method", "GET");
	$('#findForm').submit();	
}

function toggleAdmissionChecked(status) {
	$(".admissionCheckbox").each( function() {
		$(this).attr("checked",status);
	});
}

function promoteStudents(){
	
	var data = $("#tempAdmissionForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admission_list_base_url+"/promote",
		data : data,
		success: promoteStudentsSuccess
	});
}

function promoteStudentsSuccess(response){
	alert("Promoted Successfully");
}

function changeStudentsStatus(){
	$("#newStatusId").val($("#sttsid").val());
	$("#changeStatusComments").val($("#sttscmts").val());
	var data = $("#changeStatusForm").serialize();

	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admission_list_base_url+"/changeStatus",
		data : data,
		success: changeStudentsStatusSuccess
	});
}

function changeStudentsStatusSuccess(response){
	if(response.status="ERROR"){
		$("#chgstserror").html(response.error);
	}else{
		closeChangeStudentStatusPopup();
	}
	findStudents($("#academicYearId").val());
}
