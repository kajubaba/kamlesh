
samsApp.service('studentSectionService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/acad/studentsection";
	
	this.getStudents = function(filterCrt, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/studentlist?academicYearClassId="+filterCrt.classId;
		if(filterCrt.sectionId != null){
			url = url+"&classSectionId="+filterCrt.sectionId;
		}
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.seachStudentsByClassAndSection = function(classId, sectionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/studentlist?academicYearClassId="+classId;
		if(sectionId != null){
			url = url+"&classSectionId="+sectionId;
		}
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.changeStduentClassSection = function(data, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(data, resourceURL+"/change", successCallBackFunction, errorCallBackFunction);
	}
	
})










