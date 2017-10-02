samsApp.service('busNotAdoptedService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/admissions/busnotadopted";
	
	this.getStudents = function(studentStatusId, academicYearId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/studentView/"+studentStatusId;
		if(academicYearId != null && academicYearId != "null"){
			url = url+"?academicSessionId="+academicYearId;
		}
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










