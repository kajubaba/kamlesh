
samsApp.service('termAssessmentService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/term/assessment";
	
	this.getTermAssessments = function(termId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list?termId="+termId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteTermAssessment = function(termAssessmentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+termAssessmentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateTermAssessment = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










