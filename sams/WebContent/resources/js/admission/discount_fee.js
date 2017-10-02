function getFeeDiscountPopupView(studentId){
	$.ajax({
		type: 'GET',
		url : discount_fee_base_url+"/"+studentId,
		success: getFeeDiscountPopupViewSuccess 
	});
}

function getFeeDiscountPopupViewSuccess(response){
	$("#feeDiscountContainer").html(response);
	$('#popupFeeDiscount, #popupBackground').show();
	$(".fee_dicnt_txt_box_numeric").numeric(false,true);
	sumDiscountedFee();
}



function sumDiscountedFee(rowNo,colNo){
	var sum = 0;
    $(".fee_discnt_col_"+colNo).each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
  
    $("#fee_discnt_col_"+colNo+"_total").html(sum);
    sumFeeDiscountHead(rowNo);
    sumFeeDisocuntGrandTotal();
    validateDiscountedFee();
}

function sumFeeDiscountHead(rowNo){
	var sum = 0;
    $(".fee_discnt_row_"+rowNo).each(function() {
        if(!isNaN(this.value) && this.value.length!=0) {
            sum += parseInt(this.value);
        }
    });
    $("#fee_discnt_row_"+rowNo+"_total").html(sum);
}

function sumFeeDisocuntGrandTotal(){
	var instlCount=$("#noOfInstallmentsForFeeDiscount").val();
	var sum=0;
	for ( var i = 0; i < instlCount; i++) {
		var instlTotal=jQuery.trim($("#fee_discnt_col_"+i+"_total").html());
		
		if(!isNaN(instlTotal) && instlTotal.length!=0) {
            sum += parseInt(instlTotal);
        }
	}
	$("#fee_discnt_grand_total").html(sum);
}

function validateDiscountedFee(){
	
	var enable=true;
	var totoalDiscnt=jQuery.trim($("#fee_discnt_grand_total").html());
	
	for ( var i = 0; i < noOfHeadsForFeeDiscount; i++) {
		
		var feeHeadTotal=0;
		var cusomizeFeeHeadTotal=0;
		
		if(!isNaN( jQuery.trim( $("#feeDiscountHeadTotal_"+i).html())) && jQuery.trim( $("#feeDiscountHeadTotal_"+i).html())!=0) {
			feeHeadTotal=parseInt(jQuery.trim( $("#feeDiscountHeadTotal_"+i).html()));
        }
		if(!isNaN( jQuery.trim( $("#fee_discnt_row_"+i+"_total").html())) && jQuery.trim( $("#fee_discnt_row_"+i+"_total").html())!=0) {
			cusomizeFeeHeadTotal=parseInt(jQuery.trim( $("#fee_discnt_row_"+i+"_total").html()));
        }
		
		
		if(feeHeadTotal<=cusomizeFeeHeadTotal){
			enable=false;
			break;
		}
	}
	
	if(enable==true && totoalDiscnt!="0"){
		$("#btnSaveFeeDiscount").show();
		$("#btnSaveFeeDiscount").removeAttr("disabled", "disabled");
	}else{
		$("#btnSaveFeeDiscount").hide();
		$("#btnSaveFeeDiscount").attr("disabled", "disabled");
	}
	
}