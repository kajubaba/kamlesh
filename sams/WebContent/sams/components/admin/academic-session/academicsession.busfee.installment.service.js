
samsApp.service('busFeeInstallmentService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/academic-session/busfee/installment";
	
	
	this.getAcademicSessionBusFeeInstallments = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.changePercentageBusFeeInstallments = function(academicSessionId, installmentCount, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/percentage/change/"+academicSessionId+"?installmentCount="+installmentCount;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.savePercentageBusFeeInstallments = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/percentage/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveManualBusFeeInstallments = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/manual/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getManualBusFeeInstallments = function(academicSessionId, installments, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/manual/change/"+academicSessionId+"?installments="+installments;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
})










