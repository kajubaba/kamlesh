
samsApp.service('evaluationTermService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/evalterm";
	
	this.getEvaluationTerms = function(etId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/byet?etId="+etId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getScholasticEvaluationTermsOfExamPattern = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/scholastic/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	this.getCoScholasticEvaluationTermsOfClass = function(classId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/co-sch/"+classId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getScholasticEvaluationTermsOfClass = function(classId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/sch/"+classId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveEvaluationTerm = function(termForm, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(termForm, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteAssessmentTerm = function(termId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+termId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










