
samsApp.service('feeCustomizeService', function(ajaxService) {

	this.getCustomizeFee = function(studentId, classHistoryId, selectedInstallments, successCallBackFunction, errorCallBackFunction){
		var resourceURL = "";
		if(selectedInstallments != null){
			resourceURL = _appContextPath+"/ws/fee/customize/"+studentId+"/"+classHistoryId+"?selectedInstallmentCount="+selectedInstallments;
		}else{
			resourceURL = _appContextPath+"/ws/fee/customize/"+studentId+"/"+classHistoryId;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAdjustedFee = function(adjustedFee, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/fee/customize/save";
		return ajaxService.post(adjustedFee, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.resetFeeAdjustment = function(studentId, feeAdjustmentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/fee/customize/reset/adjustment/"+studentId+"/"+feeAdjustmentId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
})










