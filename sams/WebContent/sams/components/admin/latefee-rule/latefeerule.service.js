
samsApp.service('lateFeeRuleService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/latefeerule";
	
	
	this.getLateFeeRules = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getLateFeeRule = function(lateFeeRuleId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+lateFeeRuleId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteLateFeeRule = function(lateFeeRuleId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+lateFeeRuleId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveLateFeeRule = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










