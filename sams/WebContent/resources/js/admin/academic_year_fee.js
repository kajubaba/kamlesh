function getFeePupup(courseName,courseYearSettingId, divId){
	selected_fee_link=divId;
	
	$("#feePopUpLabel").html(courseName);
	$.ajax({
		type: 'GET',
		url : admin_fee_setting_url+"/popup?courseYearSettingId="+courseYearSettingId,
		success: getFeePupupSuccess
	});
}
function getFeePupupSuccess(data){
	$("#feeContainer").html(data);
	$('#popupFee, #popupBackground').show();
	$(".numeric").numeric(false,false);
}
function saveFeeChanges(){
	var feeFormData = $("#feeSettingForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admin_fee_setting_url+"/save",
		data : feeFormData,
		success: saveFeeChangesSuccess
	});
}
function saveFeeChangesSuccess(response){
	if(response.status=="OK"){
		$("#"+selected_fee_link).text(response.newFee+" , "+response.regularFee);
		var prts=selected_fee_link.split("-");
		$("#instl-"+prts[1]).text("Customize");
		closeFeePopup();
	}
}

function sumNewAdmissionFee(type){
	var sum = 0;
    $(".newadmnfee").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#newadmntotal").html(sum);
}

function sumRegularAdmissionFee(type){
	var sum = 0;
    $(".rglradmnfee").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#rglradmntotal").html(sum);
}
