
samsApp.service('gradeService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/exam/grade";
	
	this.getGradeScales = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	
})










