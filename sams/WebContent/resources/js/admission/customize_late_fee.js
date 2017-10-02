function saveCustomizeLateFee(){
	var lateFeeFormData = $("#lateFeeForm").serialize();
	
	$.ajax({
		type : 'POST',
		dataType: 'json',
		url : customize_fee_base_url+"/saveLateFee",
		data : lateFeeFormData,
		success : saveCustomizeLateFeeSuccess
	});	
}

function saveCustomizeLateFeeSuccess(response){
	if(response.status=="OK"){
		//alert("Saved successfully..");
	}
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
