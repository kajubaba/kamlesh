function getCustomizeInstallmentsPopupView(studentId,classHistoryId){
	var acessUrl=customize_fee_base_url+"/"+studentId+"/"+classHistoryId;
	acessUrl+="?selectedInstallmentCount="+$("#drpdwnnselectedinstl").val();
	
	$.ajax({
		type: 'GET',
		url : acessUrl,
		success: getCustomizeInstallmentsPopupViewSuccess
	});
}

function getCustomizeInstallmentsPopupViewSuccess(response,drpdwnFlag){
	
	$("#student_cust_fee_container").html(response);
	$(".cust_fee_txt_box_numeric").numeric(false,true);
	sumCustomizeFee();
	validateCustomizedFee();
	bindInstallmentDueDatePicker();
	
}

function sumInstallmentFee(rowNo,colNo){
	var sum = 0;
    $(".cust_inst_col_"+colNo).each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#cust_inst_col_"+colNo+"_total").html(sum);
    sumCustomizedPayableHeadFee(rowNo);
    sumCustomizeFee();
    validateCustomizedFee();
    validateFinal();
}

function sumCustomizedPayableHeadFee(rowNo){
	var sum = 0;
    $(".cust_instl_row_"+rowNo).each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#cust_instl_row_"+rowNo+"_total").html(sum);
}

function sumCustomizeFee(){
	var instlCount=$("#drpdwnnselectedinstl").val();
	var sum=0;
	for ( var i = 0; i < instlCount; i++) {
		var instlTotal=jQuery.trim($("#cust_inst_col_"+i+"_total").html());
		
		if(!isNaN(instlTotal) && instlTotal.length!=0) {
            sum += parseInt(instlTotal);
        }
	}
	$("#cust_inst_grand_total").html(sum);
}

function sumEveryInstallment(){
	var instlCount=$("#drpdwnnselectedinstl").val();
	
	for ( var i = 0; i < instlCount; i++) {
		var instlSum=0;
		for ( var headCount = 0; headCount < noOfHeads; headCount++) {
			var amt= jQuery.trim($("#feeHeads_"+headCount+"_installments_"+i+"_amount").val());
	
			if(!isNaN(amt) && amt.length!=0) {
				instlSum += parseInt(amt);
			}
        }
		$("#cust_inst_col_"+i+"_total").html(instlSum);
		
	}
}


function validateCustomizedFee(){
	var enable=true;
	for ( var i = 0; i < noOfHeads; i++) {
		
		var feePayable=0;
		var cusomizeFeePayable=0;
		
		if(!isNaN( jQuery.trim( $("#fee_payable_"+i).html())) && jQuery.trim( $("#fee_payable_"+i).html())!=0) {
			feePayable=parseInt(jQuery.trim( $("#fee_payable_"+i).html()));
        }
		if(!isNaN( jQuery.trim( $("#cust_instl_row_"+i+"_total").html())) && jQuery.trim( $("#cust_instl_row_"+i+"_total").html())!=0) {
			cusomizeFeePayable=parseInt(jQuery.trim( $("#cust_instl_row_"+i+"_total").html()));
        }
		if(feePayable!=cusomizeFeePayable){
			enable=false;
			break;
		}
	}
	
	
	
	//if(enable==true && jQuery.trim($("#cust_inst_grand_total").html())!="0" && jQuery.trim($("#cust_inst_grand_total").html())!=""){
	if(enable==true){
		$("#btnSaveCustInstallment").show();
		$("#btnSaveCustInstallment").removeAttr("disabled", "disabled");
	}else{
		$("#btnSaveCustInstallment").hide();
		$("#btnSaveCustInstallment").attr("disabled", "disabled");
	}
	
}

function sumDiscount(feeHeadIndex){
	calculateFeeHeadPayable(feeHeadIndex);
	var sum = 0;
    $(".fee_discount").each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#total_fee_discount").html(sum);
    sumPayableFee();
    validateCustomizedFee();
    validateFinal();
}

function calculateFeeHeadPayable(feeHeadIndex){
	var fee_head=0;
	var discount=0;
	
	$("#fee_head_"+feeHeadIndex).html();
	$("#discount_"+feeHeadIndex).val();
	
	if(!isNaN($("#fee_head_"+feeHeadIndex).html()) && $("#fee_head_"+feeHeadIndex).html().length!=0) {
		fee_head = parseInt($("#fee_head_"+feeHeadIndex).html());
    }
	if(!isNaN($("#discount_"+feeHeadIndex).val()) && $("#discount_"+feeHeadIndex).val().length!=0) {
		discount = parseInt($("#discount_"+feeHeadIndex).val());
    }
	$("#fee_payable_"+feeHeadIndex).html(fee_head-discount);
	
}

function sumPayableFee(){
	var sum = 0;
    $(".fee_payable").each(function() {
       if(!isNaN($("#"+this.id).html()) && $("#"+this.id).html().length!=0) {
            sum += parseInt($("#"+this.id).html());
        }
    });

    $("#total_fee_payable").html(sum);
}

function saveCustomizeFee(){
	$("#btnSaveCustInstallment").hide();
	
	var dueDateFilled=true;
	
	var instlCount= $("#noOfInstallments").val();
	for ( var i = 0; i < instlCount; i++) {
		if( jQuery.trim($("#due_date_picker_"+i).val())=="" ){
			dueDateFilled=false;
			break;
		}
	}
	
	if(dueDateFilled==false){
		alert("Due date(s) are mandatory");
		return;
	}
	
	/*
	var ruleFilled=true;
	
	var instlCount= $("#noOfInstallments").val();
	for ( var i = 0; i < instlCount; i++) {
		if( jQuery.trim($("#latefeeRuleId_"+i).val())=="" ){
			ruleFilled=false;
			break;
		}
	}
	
	if(ruleFilled==false){
		alert("Late Fee rule(s) are mandatory");
		return;
	}
	*/
	var customizeFeeFormData = $("#customizeFeeForm").serialize();
	
	$.ajax({
		type: 'POST',
		dataType: 'json',
		url : customize_fee_base_url+"/save",
		data : customizeFeeFormData,
		success: saveCustomizeFeeSuccess
	});
}

function saveCustomizeFeeSuccess(response){
	if("OK"==response.status){
		//alert("Saved successfully..");
		 location.reload();

		//findStudentInfo();
		//closeStudentFeePopup();
	}
}

function promoteStudent(studentId){
	
	$.ajax({
		type: 'GET',
		dataType: 'json',
		url : admission_base_url+"/promote/"+studentId,
		success: promoteStudentSuccess
	});
}

function promoteStudentSuccess(response){
	alert(response.message);
}

function bindInstallmentDueDatePicker(){
	var instlCount= $("#noOfInstallments").val();
	for ( var i = 0; i < instlCount; i++) {
		$( "#due_date_picker_"+i).datepicker({
			dateFormat:'d-M-yy',
			changeMonth: true,
			changeYear: true
		});
	}
}

function validateFinal(){
	
	var instlCount=parseInt($("#drpdwnnselectedinstl").val());
	
	for ( var headCount = 0; headCount < noOfHeads; headCount++) {
		
		for ( var instl = 0; instl < instlCount; instl++) {
			
			var customFeeStr=   $("#feeHeads_"+headCount+"_installments_"+instl+"_amount").val();
			var depositedFeeStr=$("#feeHeads_"+headCount+"_installments_"+instl+"_deposited").val();
			
			
			var customFee=0;
			var depositedFee=0;	
				
			 if(!isNaN(customFeeStr) && customFeeStr.length!=0) {
				 customFee = parseInt(customFeeStr);
		     }
			 
			 if(!isNaN(depositedFeeStr) && depositedFeeStr.length!=0) {
				 depositedFee = parseInt(depositedFeeStr);
		     }
			 
			 if(depositedFee > 0 && customFee < depositedFee){
				 $("#feeHeads_"+headCount+"_installments_"+instl+"_amount").css("border-color", "#FF0000");
				 $("#feeHeads_"+headCount+"_installments_"+instl+"_amount").attr("title","Must be greater than or equal to "+depositedFee);
				 
				 $("#btnSaveCustInstallment").hide();
				 $("#btnSaveCustInstallment").attr("disabled", "disabled");
			 }else{
				 $("#feeHeads_"+headCount+"_installments_"+instl+"_amount").css("border-color", "#C0C0C0");
				 $("#feeHeads_"+headCount+"_installments_"+instl+"_amount").attr("title","");
			 }
			
		}
	}
}
