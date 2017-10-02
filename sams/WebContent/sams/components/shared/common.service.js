
samsApp.service('commonService', function(ajaxService) {

	this.getActiveBusStops = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/busStops";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAffiliationAuthorities = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/affiliationAuthorities";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveClasses = function(affiliationAuthorityId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/classes/"+affiliationAuthorityId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveClassesByAcademicYear = function(affiliationAuthorityId, academicYearId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/classes/"+affiliationAuthorityId;
		if(academicYearId != null){
			resourceURL = resourceURL+"?academicYearId="+academicYearId;
		}
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveAdmissionSchemes = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/admissionschemes";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveStudentStatus = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/admissionstatuslist";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveStudentStatusList = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/activestudentstatuslist";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveBanks = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/bank/list";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
})










