
samsApp.service('academicsessionService', function(ajaxService) {

	this.getCurrentAdmissionAcademicYear = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/academicsession/currentadmissionyear";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getCurrentEnquiryAcademicYear = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/academicsession/currentenquiryyear";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAllAcademicYears = function(academicYear, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = _appContextPath+"/ws/academicsession/all";
		
		
		if(academicYear != null && academicYear != "null"){
			resourceURL = _appContextPath+"/ws/academicsession/all?academicYear="+academicYear;
		}
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAllFeeAcademicYears = function(academicYear, successCallBackFunction, errorCallBackFunction){
		
		var resourceURL = _appContextPath+"/ws/academicsession/allfee?academicYear="+academicYear;
		
		
		/*if(academicYear != null && academicYear != "null"){
			resourceURL = _appContextPath+"/ws/academicsession/all?academicYear="+academicYear;
		}*/
		
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})










