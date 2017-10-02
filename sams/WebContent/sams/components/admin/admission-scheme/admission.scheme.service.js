
samsApp.service('admissionSchemeService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/admissionscheme";
	
	
	this.getInstituteAdmissionSchemes = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAdmissionScheme = function(admissionSchemeId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/get/"+admissionSchemeId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAdmissionScheme = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	/*
	
	this.deleteLateFeeRule = function(lateFeeRuleId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+lateFeeRuleId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	*/
	
	
})










