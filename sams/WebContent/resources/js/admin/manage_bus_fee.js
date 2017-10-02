function exportBusFeeinExcel(academicYearId){
	window.location=admin_bus_fee_setting_url+"/export?academicYearId="+academicYearId;
}

function busFeeSettingView(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_bus_fee_setting_url+"/defaultList?academicYearId="+academicYearId,
		success: busFeeSettingViewSuccess
	});
}

function busFeeSettingViewSuccess(response){
	$("#adminContainer").html(response);
	$(".numericBusFee").numeric(false,false);
	bindBusFeeSettingListingWithDataTable();
}

function updateAcademicYearBusFee(){
	var busFeeFormData = $("#busFeeForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_bus_fee_setting_url+"/update",
		data : busFeeFormData,
		success : saveAcademicYearBusFeeSuccess
	});	
}

function saveAcademicYearBusFeeSuccess(response){
}

function busStopPopupView(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_bus_fee_setting_url+"/leftBusStops?academicYearId="+academicYearId,
		success: busStopPopupViewSuccess
	});
}

function busStopPopupViewSuccess(response){
	$("#popupRemainingBusStopsContainer").html(response);
	$('#popupRemainingBusStops, #popupBackground').show();
}

function addBusStopsInAcademicYear(){
	var busStopPopupFormData = $("#busStopPopupForm").serialize();
	$.ajax({
		type : 'POST',
		url : admin_bus_fee_setting_url+"/add",
		data : busStopPopupFormData,
		success : addBusStopsInAcademicYearSuccess
	});	
}

function addBusStopsInAcademicYearSuccess(response){
	$("#adminContainer").html(response);
	closeFeePopup();
}

function busInstallmentPopupView(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_bus_fee_setting_url+"/instl/"+academicYearId,
		success: busInstallmentPopupViewSuccess
	});
}

function busInstallmentPopupViewSuccess(response){
	$("#busFeeInstlContainer").html(response);
	$(".numericBusFeeInstl").numeric(false,false);
	$('#popupBusFeeInstallment, #popupBackground').show();
	validateBusFeeInstallment();
}

function sumBusFeeInstallment(){
	
	 var instal1=0;
	 var instal2=0;
	 if(!isNaN($("#busFeeInstl1").val()) && $("#busFeeInstl1").val().length!=0  ) {
		 instal1 = parseInt($("#busFeeInstl1").val());
     }
	 if(!isNaN($("#busFeeInstl2").val()) && $("#busFeeInstl2").val().length!=0) {
		 instal2 = parseInt($("#busFeeInstl2").val());
     }
	$("#busFeeInstlTotal").html(instal1+instal2);
	validateBusFeeInstallment();
}

function validateBusFeeInstallment(){
	if($("#busFeeInstlTotal").html()!='100'){
		$("#btnSaveBussFeeInstallment").hide();
		$("#btnSaveBussFeeInstallment").attr("disabled", "disabled");
	}else{
		$("#btnSaveBussFeeInstallment").show();
		$("#btnSaveBussFeeInstallment").removeAttr("disabled", "disabled");
	}
}

function addBussFeeInstallment(academicYearId){
	var busFeeInstlFormData = $("#busFeeInstlForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admin_bus_fee_setting_url+"/instl/add/"+academicYearId,
		data : busFeeInstlFormData,
		success: addBussFeeInstallmentSuccess
	});
}

function addBussFeeInstallmentSuccess(response){
	if("OK"==response.status){
		closeFeePopup();
	}
}

function bindBusFeeSettingListingWithDataTable(){
	
	$('#bus_fee_settings_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      null,
	                      {"bVisible": false},
	                      {"iDataSort": 2}
	                     ]
	    } );
	
}
