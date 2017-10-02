function getStudentMiscFeeInfo() {
	var studentId = jQuery.trim($("#studentAssignedId").val());
	$.ajax({
		type : 'GET',
		url : student_misc_fee_url + "/student/" + studentId,
		success : getStudentMiscFeeInfoSuccess
	});
}

function getStudentMiscFeeInfoSuccess(response) {
	$("#student_fee_info_container").html(response);
}

function getMiscFeePaymentView(miscActivityId, miscActivityCourseYearId,
		studentId, studentClassId) {

	$.ajax({
		type : 'GET',
		url : student_misc_fee_url + "/payview?miscActivityId="
				+ miscActivityId + "&studentId=" + studentId
				+ "&miscActivityCourseYearId=" + miscActivityCourseYearId
				+ "&classId=" + studentClassId,
		success : getMiscFeePaymentViewSuccess
	});

}

function getMiscFeePaymentViewSuccess(response) {
	$("#miscFeePaymentContainer").html(response);
	$(".numeric").numeric(false,false);
	validateMiscFeePayment();
}

function payStudentMiscFee() {
	var miscFeePaymentForm = $("#miscFeePaymentForm").serialize();
	$.ajax({
		type : 'POST',
		dataType : 'json',
		url : student_misc_fee_url + "/pay",
		data : miscFeePaymentForm,
		success : payStudentMiscFeeSuccess
	});
}

function payStudentMiscFeeSuccess(response){ 
	if("OK"==response.status){
	  getStudentMiscFeeInfo();
	}
}

function payAndPrintStudentMiscFee() {
	
	var miscFeePaymentForm = $("#miscFeePaymentForm").serialize();
	$.ajax({
		type : 'POST',
		url : student_misc_fee_url + "/payandprint",
		data : miscFeePaymentForm,
		success : payAndPrintStudentMiscFeeSuccess
	});
}

function payAndPrintStudentMiscFeeSuccess(response){ 
	$("#printRecieptDiv").html(response);
	$('#printRecieptDiv').jqprint();
	 getStudentMiscFeeInfo();
}

function sumMiscFeePayment(){
	
	var sum = 0;
    $(".mffh").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#miscFeePaymentTotal").html(sum);
    validateMiscFeePayment();
}

function validateMiscFeePayment(){
	var feeHeadCount=$("#miscFeeHeadCnt").val();
	var enable=true;
	if(!isNaN(feeHeadCount) && feeHeadCount.length!=0) {
		for(var i=0;i<feeHeadCount;i++){
			var dueFee=0;
			var payAmt=0;
			if(jQuery.trim($("#dueMiscFee"+i).html())!=""){
				dueFee=parseInt(jQuery.trim($("#dueMiscFee"+i).html()));
			}
			if($("#miscPayAmt"+i).val()!=""){
				payAmt=$("#miscPayAmt"+i).val();
			}
			if(payAmt>dueFee){
				enable=false;
				break;
			}
		}
	}
	
	var feePaymentTotal=jQuery.trim( parseInt($("#miscFeePaymentTotal").html()) );
	
	if(enable==true && feePaymentTotal>0){
		$("#btnMiscPayment").show();
		$("#btnMiscPayment").removeAttr("disabled", "disabled");
		$("#btnMiscPaymentPrint").show();
		$("#btnMiscPaymentPrint").removeAttr("disabled", "disabled");
	}else{
		$("#btnMiscPayment").hide();
		$("#btnMiscPayment").attr("disabled", "disabled");
		$("#btnMiscPaymentPrint").hide();
		$("#btnMiscPaymentPrint").attr("disabled", "disabled");
	}
}

function getMiscActivities(){
	$("#miscActivityId").empty();
	$("#miscActivityId").append($("<option />").val("").text(""));
	var academicYearId = $("#academicYearId").val();
	$.ajax({
		type : 'GET',
		dataType: 'json',
		url : misc_activity_url+"/miscactivites/"+academicYearId,
		success : getMiscActivitiesSuccess
	});
}

function getMiscActivitiesSuccess(response){
	
	var options = $("#miscActivityId");
		jQuery.each(response.miscActivites, function(i, miscActivity) {
			if(null != miscActivity){
				options.append($("<option />").val(miscActivity.id).text(miscActivity.name));	
			}
			
		});
}


