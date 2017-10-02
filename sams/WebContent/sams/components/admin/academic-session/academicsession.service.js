
samsApp.service('academicSessionService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/academic-session";
	
	
	this.getAcademicSessions = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveAcademicSessions = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list-active";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAcademicSession = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteAcademicSession = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAcademicSession = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.publishAcademicSession = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/publish/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










