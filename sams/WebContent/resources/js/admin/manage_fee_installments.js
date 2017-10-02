
/**
 * Purpose of this JS file is to manage the installments by system admin.
 *  
 */


/*
function getInstallmentsPopupView(courseName,courseYearSettingId){
	$("#instlPopupLabel").html(courseName);
	$.ajax({
		type: 'GET',
		url : admin_fee_installment_url+"/default?courseYearSettingId="+courseYearSettingId,
		success: getInstallmentsPopupViewSuccess
	});
}
*/

/**
 * It display interface to manage installments fee
 */
function manageInstallments(courseYearSettingId, admissionType){
	
	$.ajax({
		type: 'GET',
		url : admin_fee_installment_url+"?courseYearSettingId="+courseYearSettingId+"&admissionType="+admissionType,
		success: manageInstallmentsSuccess
	});
}

function manageInstallmentsSuccess(response){
	$("#adminContainer").html(response);
	$(".numeric").numeric(false,false);
	sumAllInstallments();
	//makeTabs();
}

/**
 * Prepare tab functionality
 */

function makeTabs(){
	 $("#fee-tabs").tabs({
		 beforeLoad: function(event, ui){
			 //$(ui.panel).siblings('.ui-tabs-panel').empty();
		 },
		 load: function(event, ui){
			$(".numeric").numeric(false,false);
			sumAllInstallments();
		 }
		 
	});
}

/**
 * This method calculate total of each installment and displays in last row. 
 */

function sumAllInstallments(){
	var instlCount=$("#feeInstallments").val();
	for ( var i = 0; i < instlCount; i++) {
		var instlSum=0;
		for ( var headCount = 0; headCount < feeHeadCount; headCount++) {
			var amt= jQuery.trim($("#feeHeads_"+headCount+"_installments_"+i+"_amount").val());
	
			if(!isNaN(amt) && amt.length!=0) {
				instlSum += parseInt(amt);
			}
        }
		$("#inst_col_"+i+"_total").html(instlSum);
	}
	bindAdminInstallmentDueDatePicker();
	validateInstallmentsFee();
}

/**
 * This functions disables save button if head total is not equal to the sum of head fee of all installments, otherwise enable save button
 */

function validateInstallmentsFee(){
	var enable=true;
	
	for ( var i = 0; i < feeHeadCount; i++) {
		
		var headFee=0;
		var headTotal=0;
		
		if(!isNaN( jQuery.trim( $("#fee_head_"+i).html())) && jQuery.trim( $("#fee_head_"+i).html())!=0) {
			headFee=parseInt(jQuery.trim( $("#fee_head_"+i).html()));
        }
		if(!isNaN( jQuery.trim( $("#instl_row_"+i+"_total").html())) && jQuery.trim( $("#instl_row_"+i+"_total").html())!=0) {
			headTotal=parseInt(jQuery.trim( $("#instl_row_"+i+"_total").html()));
        }
		
		if(headFee!=headTotal){
			enable=false;
			break;
		}
	}
	
	if(enable==true){
		$("#btnSaveCustInstallment").show();
		$("#btnSaveCustInstallment").removeAttr("disabled", "disabled");
	}else{
		$("#btnSaveCustInstallment").hide();
		$("#btnSaveCustInstallment").attr("disabled", "disabled");
	}
	
}

/**
 * Invokes when user change selection of installments
 * 
 */
function changeInstallments(dropDown){
	
	var courseYearSettingId = $("#courseYearSettingId").val();
	var admissionType= $("#admissionType").val();
	
	$.ajax({
		type: 'GET',
		url : admin_fee_installment_url+"?courseYearSettingId="+courseYearSettingId+"&installment="+$(dropDown).val()+"&admissionType="+admissionType,
		success:function(response, tab){
			$("#adminContainer").html(response);
			$(".numeric").numeric(false,false);
			bindAdminInstallmentDueDatePicker();
			sumAllInstallments();
		}
	});
}

/**
 * Invokes when user change the fee in any text box. It sum the installments total, head total and then validate fee to enable/disable Save button
 * 
 * @param rowNo 
 * @param colNo
 */
function sumInstallment(rowNo,colNo){
	var sum = 0;
    
	$(".inst_col_"+colNo).each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
	
    $("#inst_col_"+colNo+"_total").html(sum);
    
    sumRow(rowNo);
    sumInstallmentsFee();
    validateInstallmentsFee();
}

function sumRow(row){
	var instlCount=$("#feeInstallments").val();
	var feeHeadSum=0;
	for ( var i = 0; i < instlCount; i++) {
		var amt= jQuery.trim($("#feeHeads_"+row+"_installments_"+i+"_amount").val());
		if(!isNaN(amt) && amt.length!=0) {
			feeHeadSum += parseInt(amt);
		}
		$("#instl_row_"+row+"_total").html(feeHeadSum);
	}
}

function sumInstallmentsFee(){
	var instlCount=$("#feeInstallments").val();
	
	var sum=0;
	for ( var i = 0; i < instlCount; i++) {
		var instlTotal=jQuery.trim($("#inst_col_"+i+"_total").html());
		
		if(!isNaN(instlTotal) && instlTotal.length!=0) {
            sum += parseInt(instlTotal);
        }
	}
	$("#inst_grand_total").html(sum);
}

/*
 * Save installments into database 
 */
function saveInstallments(){
	var instlFormData = $("#instlForm").serialize();
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : admin_fee_installment_url+"/save",
		data : instlFormData,
		success: saveInstallmentsSuccess
	});
}

function saveInstallmentsSuccess(response){
	if(response.status=="OK"){
		changeInstallments();
	}else{
		alert("Error occured !!!");
	}
	
	
}


/*
function getInstallmentsPopupViewSuccess(response){
	$("#instlContainer").html(response);
	$('#popupFeeInstallment, #popupBackground').show();
	$(".numeric").numeric(false,false);
	validateFee();
	bindAdminInstallmentDueDatePicker();
}
*/

/*
function getInstallments(){
	
	var academicYearFeeId=$("#drpdwnayfeeid").val();
	
	$.ajax({
		type: 'GET',
		url : admin_fee_installment_url+"/ayfeeid/"+academicYearFeeId,
		success: getInstallmentsSuccess
	});
}

function getInstallmentsSuccess(response){
	$("#admntypeinstlContailer").html(response);
	$(".numeric").numeric(false,false);
	validateFee();
	bindAdminInstallmentDueDatePicker();
}

*/


/*

function sumFirstInstlFee(indx){
	sumInstallmentFeeHead(indx);
	var sum = 0;
    $(".firstinstlfee").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#frtsinstltotal").html(sum);
    sumGrandTotal();
}

function sumSecondInstlFee(indx){
	sumInstallmentFeeHead(indx);
	var sum = 0;
    $(".scndtinstlfee").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#scndinstltotal").html(sum);
    sumGrandTotal();
}

function sumInstallmentFeeHead(indx){
	var sum = 0;
    $(".feehead"+indx).each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#feeheadtotal"+indx).html(sum);
}

function sumGrandTotal(){
	$("#grandtotal").html(  parseInt($("#frtsinstltotal").html()) + parseInt($("#scndinstltotal").html())   );
	validateFee();
}

function validateFee(){
	var feeHeadCount=$("#totalFeeHeadCount").val();
	var enable=true;
	if(!isNaN(feeHeadCount) && feeHeadCount.length!=0) {
		for(var i=0;i<feeHeadCount;i++){
			var headAmt=0;
			var headTotal=parseInt(jQuery.trim($("#feeheadtotal"+i).html()));
			if(jQuery.trim($("#headamnt"+i).html())!=""){
				headAmt=parseInt(jQuery.trim($("#headamnt"+i).html()));
			}
			if(headAmt!=headTotal){
				enable=false;
				break;
			}
		}
	}
	
	if(enable==true){
		$("#btnSaveInstallment").show();
		$("#btnSaveInstallment").removeAttr("disabled", "disabled");
	}else{
		$("#btnSaveInstallment").hide();
		$("#btnSaveInstallment").attr("disabled", "disabled");
	}
	
}
*/

function bindAdminInstallmentDueDatePicker(){

	var instlCount=$("#feeInstallments").val();
	
	for ( var counter = 0; counter <= instlCount-1; counter++) {
			$( "#due_date_picker_"+counter).datepicker({
				dateFormat:'d-M-yy',
				changeMonth: true,
				changeYear: true
			});
	}
	
}
