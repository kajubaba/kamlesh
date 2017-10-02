function findRenewalStudent(){
	var studentId=$("#renewalStudentId").val();
	window.location = admission_renewal_base_url+"/student?id="+studentId;
}

function confirmAddmissionRenewalAndPayFee(isBusStopMandatory,isFormNoMandatory){
	if(validateStudentForm(isBusStopMandatory,isFormNoMandatory)){
		var studentFormData = $("#studentForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : admission_renewal_base_url+"/confirm",
			data : studentFormData,
			success : confirmAddmissionRenewalAndPayFeeSuccess
		});	
	}else{
		alert("Mandatory fields are missing..");
	}
}

function confirmAddmissionRenewalAndPayFeeSuccess(response){
	if(response.status=="OK"){
		window.location = student_fee_url+"/admissionrenewal/"+response.id;
	}
}

function cancelRenewal(academicYearId){
	window.location = admission_renewal_base_url+"/list/def";
}

function newRenewal(){
	window.location = admission_renewal_base_url+"/student";
}

function cancelAdmission(academicYearId){
	window.location = admission_list_base_url+"/"+academicYearId;
}

function confirmAddmissionRenewal(isBusStopMandatory,isFormNoMandatory){
	if(validateStudentForm(isBusStopMandatory,isFormNoMandatory)){
		var studentFormData = $("#studentForm").serialize();
		$.ajax({
			type : 'POST',
			dataType: 'json',
			url : admission_renewal_base_url+"/confirm",
			data : studentFormData,
			success : confirmAddmissionRenewalSuccess
		});	
	}else{
		alert("Mandatory fields are missing..");
	}
}

function confirmAddmissionRenewalSuccess(response){
	$("#btnTempRenew").hide();
	$("#btnTempRenewNPay").hide();
	alert("Admission has been renewed successfully");
}

function findStudentsToBeRenewed(){
	var searchFormData = $("#findForm").serialize();
	$.ajax({
		type : 'GET',
		url : admission_renewal_base_url+"/find/",
		data : searchFormData,
		success : findStudentsToBeRenewedSuccess
	});	
}

function findStudentsToBeRenewedSuccess(response){
	$("#listContainer").html(response);
	bindStudentListWithDataTable();
}


function validateStudentForm(isBusStopMandatory,isFormNoMandatory){
	var academicYearClass=$("#drpDwnClass").val();
	var firstName=jQuery.trim($("#firstName").val());
	var lastName=jQuery.trim($("#lastName").val());
	var busStop=jQuery.trim($("#busStopId").val());
	var formNo=jQuery.trim($("#admissionFormNo").val());
	
	
	if(academicYearClass=="" || firstName=="" || lastName==""){
		return false;
	}
	
	if(isBusStopMandatory==true && busStop==""){
		return false;
	}
	
	if(isFormNoMandatory==true && formNo==""){
		return false;
	}
	
	return true;
	
}

function validateStudentWhileUpdating(){
	
	var firstName=jQuery.trim($("#firstName").val());
	var lastName=jQuery.trim($("#lastName").val());
	
	if(firstName!="" && lastName!="" ){
		return true;
	}
	
	return false;
}


function saveStudent(isBusStopMandatory,isFormNoMandatory){
	
	if(ACTION_ADD==btnStudentSaveAction){
		if(validateStudentForm(isBusStopMandatory,isFormNoMandatory)){
			addStudent();
		}else{
			alert("Mandatory fields are missing..");
		}
	}else if(ACTION_UPDATE==btnStudentSaveAction){
		if(validateStudentForm()){
			updateStudent(isBusStopMandatory,isFormNoMandatory);
		}else{
			alert("Mandatory fields are missing..");
		}
	}
}

function addStudent(){
	var studentFormData = $("#studentForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admission_base_url+"/add",
		data : studentFormData,
		success : addStudentSuccess
	});	
}

function updateStudent(){
	var studentFormData = $("#studentForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admission_base_url+"/update",
		data : studentFormData,
		success : updateStudentSuccess
	});	
}

function updateStudentSuccess(response){
	if("OK"==response.status){
		alert("Updated Successfully..");
	}
}

function addStudentSuccess(response){
	if("OK"==response.status){
		btnStudentSaveAction=ACTION_UPDATE;
		/*$("#btnStudentSave").val("Update");*/
		
		$("#studentId").val(response.id);
		$("#ggggg").html(response.studentId);
		$("#btnStudentSave").hide();
		alert("Temporary admitted Successfully..");
	}
}




function bindAdmissionDatePicker(){
	$( "#admissionDob" ).datepicker({
		showOn: "button",
		buttonImage: image_base_url+"/images/calendar.gif",
		dateFormat:'d-M-yy',
		buttonImageOnly: true,
		changeMonth: true,
		changeYear: true
	});
}

function saveOldStudent(isBusStopMandatory,isFormNoMandatory){
	
	if(ACTION_ADD==btnStudentSaveAction){
		if(validateStudentForm(isBusStopMandatory,isFormNoMandatory)){
			var studentFormData = $("#studentForm").serialize();
			$.ajax({
				type : 'POST',
				dataType: 'json',
				url : admission_base_url+"/addold",
				data : studentFormData,
				success : addOldStudentSuccess
			});
		}else{
			alert("Mandatory fields are missing..");
		}
	}else if(ACTION_UPDATE==btnStudentSaveAction){
		if(validateStudentForm()){
			updateStudent(isBusStopMandatory,isFormNoMandatory);
		}else{
			alert("Mandatory fields are missing..");
		}
	}
}

function addOldStudentSuccess(response){
	if("OK"==response.status){
		btnStudentSaveAction=ACTION_UPDATE;
		$("#studentId").val(response.id);
		$("#ggggg").html(response.studentId);
		$("#btnStudentSave").hide();
		alert("Admitted Successfully..");
	}
}
