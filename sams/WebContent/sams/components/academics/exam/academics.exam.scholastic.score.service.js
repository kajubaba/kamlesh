
samsApp.service('scholasticScoreService', function(ajaxService) {
	
	var resourceURL = _appContextPath+"/ws/academics/scorecollection/scholastic/score";
	
	this.getStudentScores = function(classId, sectionId, subjectId, termAssessmentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/get?classId="+classId+"&sectionId="+sectionId+"&subjectId="+subjectId+"&termAssessmentId="+termAssessmentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveStduentMarks = function(classId, sectionId, subjectId, termAssessmentId, studentScores, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/"+classId+"/"+subjectId+"/"+termAssessmentId+"?sectionId="+sectionId;
		return ajaxService.post(studentScores, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStudentScoreCard = function(studentId, classId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/scorecard/"+studentId+"/"+classId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
})










