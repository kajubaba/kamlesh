function findStudentInfo(){
	var studentId=jQuery.trim($("#studentAssignedId").val());
	$.ajax({
		type: 'GET',
		url : student_fee_url+"/student/"+studentId,
		success: findStudentInfoSuccess
	});
}

function findStudentInfoSuccess(response){
	$("#student_fee_info_container").html(response);
}

function getFeePaymentView(){
	var instldetailformData = $("#instldetailform").serialize();
	$.ajax({
		type: 'GET',
		url : student_fee_url+"/student/feepaymentview",
		data : instldetailformData,
		success: getFeePaymentViewSuccess
	});
}

function getFeePaymentViewSuccess(response){
	$("#student_fee_info_container").html(response);
	$(".numeric").numeric(false,false);
	validateFeePayment();
}

function sumFeePayment(){
	
	var sum = 0;
    $(".fhFeePayment").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#feePaymentTotal").html(sum);
    validateFeePayment();
}

function payStudentFee(){
	var feePaymentFormData = $("#feePaymentForm").serialize();
	
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : student_fee_url+"/student/payfee",
		data : feePaymentFormData,
		success: payStudentFeeSuccess
	});
}

function payAndPrintStudentFee(){
	$("#printReciept").val("true");
	var feePaymentFormData = $("#feePaymentForm").serialize();
	
	$.ajax({
		type: 'POST',
		url : student_fee_url+"/student/payfeeandprint",
		data : feePaymentFormData,
		success: payAndPrintStudentFeeSuccess
	});
}

function payAndPrintStudentFeeSuccess(response){
	$("#printRecieptDiv").html(response);
	$("#studentAssignedId").val($("#genStudId").val());
	$('#printRecieptDiv').jqprint();
	findStudentInfo();
	
}

function payStudentFeeSuccess(response){
	if("OK"==response.status){
		$("#studentAssignedId").val(response.studentId);
		alert("Fee paid successfully with transaction ID :"+response.transactionId);
		findStudentInfo();
	}
}

function validateFeePayment(){
	var feeHeadCount=$("#studentFeePaymentHeadCount").val();
	var enable=true;
	if(!isNaN(feeHeadCount) && feeHeadCount.length!=0) {
		for(var i=0;i<feeHeadCount;i++){
			var dueFee=0;
			var payAmt=0;
			if(jQuery.trim($("#dueFee"+i).html())!=""){
				dueFee=parseInt(jQuery.trim($("#dueFee"+i).html()));
			}
			if($("#payAmt"+i).val()!=""){
				payAmt=$("#payAmt"+i).val();
			}
			if(payAmt>dueFee){
				enable=false;
				break;
			}
		}
	}
	
	var feePaymentTotal=jQuery.trim( parseInt($("#feePaymentTotal").html()) );
	
	if(enable==true && feePaymentTotal>0){
		$("#btnConfirmPayment").show();
		$("#btnConfirmPayment").removeAttr("disabled", "disabled");
		$("#btnConfirmPrintPayment").show();
		$("#btnConfirmPrintPayment").removeAttr("disabled", "disabled");
	}else{
		$("#btnConfirmPayment").hide();
		$("#btnConfirmPayment").attr("disabled", "disabled");
		$("#btnConfirmPrintPayment").hide();
		$("#btnConfirmPrintPayment").attr("disabled", "disabled");
		
	}
}

function loadClassHistoryFee(id){
	$.ajax({
		type: 'GET',
		url : student_fee_url+"/classHistory/"+id,
		success: loadClassHistoryFeeSuccess
	});
}

function loadClassHistoryFeeSuccess(response){
	$("#classHistoryContainer").html(response);
}