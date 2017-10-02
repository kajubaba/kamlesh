
samsApp.service('evaluationTypeService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/assessment-type";
	
	
	this.getCoScholasticAssessmentTypeDetails = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/coscholastic/exam-pattern/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getScholasticAssessmentTypeDetails = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/scholastic/exam-pattern/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getExamPattern = function(examPatternId, successCallBackFunction, errorCallBackFunction){
		var url = _appContextPath+"/ws/academics/exam/pattern/get/"+examPatternId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	/*this.getEvaluationTypeByClass = function(classId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/byclass?academicYearClassId="+classId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}*/
	
	this.saveGradeScaleAndScoringMethod = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/saveGradeSclaeAndScoringMetod";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










