function listMiscActivities(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_misc_activity_url+"/list?ayId="+academicYearId,
		success: listMiscActivitiesSuccess
	});
}

function listMiscActivitiesSuccess(response){
	$("#adminContainer").html(response);
	bindMiscActivitiesListingWithDT();
}


function newMiscActivities(academicYearId){
	$.ajax({
		type: 'GET',
		url : admin_misc_activity_url+"/new/"+academicYearId,
		success: newMiscActivitiesSuccess
	});
}

function newMiscActivitiesSuccess(response){
	$("#adminContainer").html(response);
}


function viewMiscActivity(activityId){
	$.ajax({
		type: 'GET',
		url : admin_misc_activity_url+"/view/"+activityId,
		success: viewMiscActivitySuccess
	});
}

function viewMiscActivitySuccess(response){
	$("#adminContainer").html(response);
	$(".numeric").numeric(false,false);
}

function sumMiscActivityHeadFee(){
	var sum = 0;
    $(".misc_activity_head_fee").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#misc_activity_head_fee_total").html(sum);
}

function assignCoursesToMiscActivity(){
	var selectedOpts = $('#list_maavc option:selected');
	if (selectedOpts.length == 0) {
	     alert("Nothing to move.");
	}
		 
	$('#list_maapc').append($(selectedOpts).clone());
	$(selectedOpts).remove();
}


function unAssignCoursesToMiscActivity(){
	var selectedOpts = $('#list_maapc option:selected');
	if (selectedOpts.length == 0) {
	     alert("Nothing to move.");
	}
		 
	$('#list_maavc').append($(selectedOpts).clone());
	$(selectedOpts).remove();
}

function saveMiscActivity(){
	var miscActivityFormData = $("#miscActivityForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_misc_activity_url+"/save",
		data : miscActivityFormData,
		success : saveMiscActivitySuccess
	});	
}

function saveMiscActivitySuccess(){
	
}

function updateMiscActivity(){
	var miscActivityFormData = $("#miscActivityForm").serialize();
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : admin_misc_activity_url+"/update",
		data : miscActivityFormData,
		success : updateMiscActivitySuccess
	});	
}

function updateMiscActivitySuccess(response){
	
}

/*
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
*/

function bindMiscActivitiesListingWithDT(){
	
	$('#tbl_list_miscactivities').dataTable({
	        "bPaginate": false,
	        "bLengthChange": false,
	        "bFilter": false,
	        "bInfo": false,
	        "bAutoWidth": false
	        
	    } );
	
}
