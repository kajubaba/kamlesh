
samsApp.service('academicSessionAdmissionSchemeService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/academic-session/admission-scheme";
	
	
	this.getAcademicSessionAdmissionSchemes = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.removeAdmissionScheme = function(admissionSchemeId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+admissionSchemeId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAdmissionScheme = function(admissionSchemeId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/get/"+admissionSchemeId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveSchemeDetails = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/scheme-detail";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.getUnAssignedAdmissionSchemes = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/unassigned/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.assignAdmissionSchemes = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/assign";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
})










