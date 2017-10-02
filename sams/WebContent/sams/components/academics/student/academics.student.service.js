
samsApp.service('studentAcademicsService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/academics/student/scorecard";
	
	this.getStudentFullScholasticScoreCard = function(studentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+studentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentScholasticScoreCardOfTerm = function(studentId, termId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/scholastic/single/"+studentId+"?evaluationTermId="+termId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










