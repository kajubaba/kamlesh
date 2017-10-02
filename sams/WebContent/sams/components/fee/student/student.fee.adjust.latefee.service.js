
samsApp.service('adjustLateFeeService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/fee/adjustlatefee";
	
	this.getStudentLateFeeDetails = function(studentId, classHistoryId , successCallBackFunction, errorCallBackFunction){
		var URL = resourceURL+"/"+studentId+"/"+classHistoryId;
		return ajaxService.get(URL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveStudentLateFeeAdjustments = function(formData , successCallBackFunction, errorCallBackFunction){
		var URL = resourceURL+"/savechanges";
		return ajaxService.post(formData, URL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.onOffLateFeeCalculation = function(dueInstallmentId , onOffFlag , successCallBackFunction, errorCallBackFunction){
		var URL = resourceURL+"/onofflatefeecalculation/"+dueInstallmentId+"/"+onOffFlag;
		return ajaxService.get(URL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
})










