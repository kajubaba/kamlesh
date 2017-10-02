
samsApp.service('classSectionService', function(ajaxService) {
	var resourceURL = _appContextPath+"/ws/academics/class-section";
	
	this.saveSection = function(data, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(data, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getClasswiseSectionCount = function(academicYear, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/classwise/sectioncount?academicYearId="+academicYear;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.getClassSections = function(academicYear, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list?academicYearClassId="+academicYear;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










