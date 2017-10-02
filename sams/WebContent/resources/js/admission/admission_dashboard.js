function renderClasswiseChart(academicYearId){
	 var studStatus=$("#studStatus").val();
	 $.ajax({
		type : 'GET',
		url : admission_db_base_url+"/classwise?academicYearId="+academicYearId+"&studentStatus="+studStatus,
		success : renderClasswiseChartSuccess
	});	
}
function renderClasswiseChartSuccess(response){
	classwiseChart.setDataXML(response); 
	classwiseChart.render("classwiseChartContainer");
}

function updateClassWiseChart(academicYearId){
	 var studStatus=$("#studStatus").val();
	 $.ajax({
		type : 'GET',
		url : admission_db_base_url+ "/classwise?academicYearId="+ academicYearId + "&studentStatus=" + studStatus,
		success : updateClassWiseChartSuccess
	});
	updateAdmissionCount(academicYearId, studStatus); 
}

function updateClassWiseChartSuccess(response){
	 updateChartXML("classwiseChart",response);
}

function updateAdmissionCount(academicYearId, studStatus){
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : admission_db_base_url + "/classwise/count?academicYearId="+ academicYearId + "&studentStatus=" + studStatus,
		success : updateAdmissionCountSuccess
	});
}

function updateAdmissionCountSuccess(response){
	var studStatus=$("#studStatus").val();
	var ayId=$("#activeAcademicYearId").val();
	
	if(response.status=="OK"){
		$("#admnCount").html("<a href='"+admission_list_base_url+"/"+ayId+"?statusId="+studStatus+"' >"+response.count+"</a>");
	}else{
		$("#admnCount").html("<a href='"+admission_list_base_url+"/"+ayId+"?statusId="+studStatus+"' >0</a>");
	}
}

function updateAdmissionTypeWiseChart(academicYearId){
	 var studStatus=$("#studStatus").val();
	 $.ajax({
		type : 'GET',
		url : admission_db_base_url+ "/classwise?academicYearId="+ academicYearId + "&studentStatus=" + studStatus,
		success : updateChartSuccess
	});
	updateAdmissionCount(academicYearId, studStatus); 
}

function renderadmissionTypeChart(academicYearId){
	var admissionType=$("#admissionType").val();
	 $.ajax({
		type : 'GET',
		url : admission_db_base_url+"/admissionType?academicYearId="+academicYearId+"&admissionType="+admissionType,
		success : renderadmissionTypeChartSuccess
	});
}

function renderadmissionTypeChartSuccess(response){
	admissionTypeChart.setDataXML(response); 
	admissionTypeChart.render("admissionTypeChartContainer");
}

function updateAdmissionTypeChart(academicYearId){
	var admissionType=$("#admissionType").val();
	
	$.ajax({
		type : 'GET',
		url : admission_db_base_url+"/admissionType?academicYearId="+academicYearId+"&admissionType="+admissionType,
		success : updateAdmissionTypeChartSuccess
	});
	updateAdmissionTypeCount(academicYearId, admissionType); 
}

function updateAdmissionTypeChartSuccess(response){
	updateChartXML("admissionTypeChart",response);
}

function updateAdmissionTypeCount(academicYearId, admissionType){
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : admission_db_base_url + "/admissionType/count?academicYearId="+ academicYearId + "&admissionType=" + admissionType,
		success : updateAdmissionTypeCountSuccess
	});
}

function updateAdmissionTypeCountSuccess(response){
	var admissionType=$("#admissionType").val();
	var ayId=$("#activeAcademicYearId").val();
	if(response.status=="OK"){
		$("#admnTypeCount").html("<a href='"+admission_list_base_url+"/"+ayId+"?admissionTypeId="+admissionType+"&statusId=5' >"+response.count+"</a>");
	}else{
		$("#admnTypeCount").html("<a href='"+admission_list_base_url+"/"+ayId+"?admissionTypeId="+admissionType+"&statusId=5' >0</a>");
	}
}
