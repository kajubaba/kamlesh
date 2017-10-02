
samsApp.service('studentFeeServiceService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/fee/student";
	
	this.getStudentInstallments = function(studentId, classHistoryId , successCallBackFunction, errorCallBackFunction){
		
		if(classHistoryId=="null"){
			classHistoryId = "";
		}
		var url = resourceURL+"/"+studentId+"?classHistoryId="+classHistoryId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getInstallmentForPayment = function(studentId,installmentId, academicYearFeeInstallmentId,customizeFeeInstallmentId, classHistoryId, successCallBackFunction, errorCallBackFunction){
		
		
		
		if(academicYearFeeInstallmentId=='null'){
			academicYearFeeInstallmentId="";
		}
		
		if(customizeFeeInstallmentId=='null'){
			customizeFeeInstallmentId="";
		}
		
		var url = resourceURL+"/installment/"+studentId+"?installmentId="+installmentId+"&ayFeeInstallmentId="+academicYearFeeInstallmentId+"&custFeeInstallmentId="+customizeFeeInstallmentId+"&classHistoryId="+classHistoryId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}

	this.collectFee = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/payFee";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentPaymentHistory = function(studentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/paymenthistory/"+studentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getFeeSummary = function(studentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/feesummary/"+studentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.totalDueFee = function(studentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/totalDueFee/"+studentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	this.deleteStudentFeeTransaction = function(feeTransactionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/feetransaction/"+feeTransactionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getReceiptHeaders = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/feereceiptheader/getall";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})










