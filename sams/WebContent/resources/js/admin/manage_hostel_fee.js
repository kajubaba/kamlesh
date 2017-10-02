function hostelFeeSettingView(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_hostel_fee_setting_url+"/defaultList?academicYearId="+academicYearId,
		success: hostelFeeSettingViewSuccess
	});
}

function hostelFeeSettingViewSuccess(response){
	$("#adminContainer").html(response);
	$(".numericHostelFee").numeric(false,false);
	bindHostelFeeSettingListingWithDataTable();
}

function updateAcademicYearHostelFee(){
	var hostelFeeFormData = $("#hostelFeeForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_hostel_fee_setting_url+"/update",
		data : hostelFeeFormData,
		success : updateAcademicYearHostelFeeSuccess
	});	
}

function updateAcademicYearHostelFeeSuccess(response){
	if("OK"==response.status){
		//alert("Saved Successfully..");
	}
}

function hostelPopupView(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_hostel_fee_setting_url+"/leftHostels?academicYearId="+academicYearId,
		success: hostelPopupViewSuccess
	});
}

function hostelPopupViewSuccess(response){
	$("#popupRemainingHostelContainer").html(response);
	$('#popupRemainingHostels, #popupBackground').show();
}

function addHostelsInAcademicYear(){
	var hostelPopupFormData = $("#hostelPopupForm").serialize();
	$.ajax({
		type : 'POST',
		url : admin_hostel_fee_setting_url+"/add",
		data : hostelPopupFormData,
		success : addHostelsInAcademicYearSuccess
	});	
}

function addHostelsInAcademicYearSuccess(response){
	$("#adminContainer").html(response);
	closeFeePopup();
}

function hostelInstallmentPopupView(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_hostel_fee_setting_url+"/instl/"+academicYearId,
		success: hostelInstallmentPopupViewSuccess
	});
}

function hostelInstallmentPopupViewSuccess(response){
	$("#hostelFeeInstlContainer").html(response);
	$(".numericHostelFeeInstl").numeric(false,false);
	$('#popupHostelFeeInstallment, #popupBackground').show();
	validateHostelFeeInstallment();
}

function sumHostelFeeInstallment(){
	
	 var instal1=0;
	 var instal2=0;
	 if(!isNaN($("#hostelFeeInstl1").val()) && $("#hostelFeeInstl1").val().length!=0  ) {
		 instal1 = parseInt($("#hostelFeeInstl1").val());
     }
	 if(!isNaN($("#hostelFeeInstl2").val()) && $("#hostelFeeInstl2").val().length!=0) {
		 instal2 = parseInt($("#hostelFeeInstl2").val());
     }
	$("#hostelFeeInstlTotal").html(instal1+instal2);
	validateHostelFeeInstallment();
}



function validateHostelFeeInstallment(){
	if($("#hostelFeeInstlTotal").html()!='100'){
		$("#btnSaveHostelFeeInstallment").hide();
		$("#btnSaveHostelFeeInstallment").attr("disabled", "disabled");
	}else{
		$("#btnSaveHostelFeeInstallment").show();
		$("#btnSaveHostelFeeInstallment").removeAttr("disabled", "disabled");
	}
}

function addHostelFeeInstallment(academicYearId){
	var hostelFeeInstlFormData = $("#hostelFeeInstlForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admin_hostel_fee_setting_url+"/instl/add/"+academicYearId,
		data : hostelFeeInstlFormData,
		success: addHostelFeeInstallmentSuccess
	});
}

function addHostelFeeInstallmentSuccess(response){
	if("OK"==response.status){
		closeFeePopup();
		alert(response.message);
	}
}

function bindHostelFeeSettingListingWithDataTable(){
	
	$('#hostel_fee_settings_table').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false,
	        "aoColumns": [
	                      null,
	                      {"bVisible": false},
	                      {"iDataSort": 1}
	                     ]
	    } );
	
}
