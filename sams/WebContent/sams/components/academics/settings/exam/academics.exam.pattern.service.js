
samsApp.service('examPatternService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/patterns";
	
	this.getExamPatterns = function(academicYearId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"?academicYearId="+academicYearId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getExamPattern = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.createExamPattern = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getMasterExamPatterns = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/masters";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteExamPattern = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+examPatternId;
		return ajaxService.remove(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	
})










