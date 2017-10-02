
samsApp.service('feeHeadService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/fee-head";
	
	
	this.getFeeHeads = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getFeeHead = function(feeHeadId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+feeHeadId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteFeeHead = function(feeHeadId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+feeHeadId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveFeeHead = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










