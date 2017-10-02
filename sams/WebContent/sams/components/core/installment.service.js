
samsApp.service('installmentService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/installment";
	
	this.getInstallments = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/list", successCallBackFunction, errorCallBackFunction);
	}
	
})










