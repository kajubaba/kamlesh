
/**
 * @author Narendra Patidar
 * @Date Jan-16-2016
 * 
 * This Angular JS based service that can be injected into controller and other services.
 * 
 * 
 * 
 */
samsApp.service('newAdmissionService', function(ajaxService) {

	
	this.getAdmissionByFormNo = function(formNo, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/manage/byformno?formNo="+formNo;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAffiliationAuthorities = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/affiliationAuthorities";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	/**
	 * Fetch active categories
	 * 
	 * 
	 */
	this.getCategories = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/categories";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getStates = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/states";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	/**
	 * Fetch active bus stops
	 * 
	 * 
	 */
	this.getActiveBusStopps = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/busStops";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.getActiveClasses = function(affiliationAuthorityId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/classes/"+affiliationAuthorityId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiryActiveClasses = function(affiliationAuthorityId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/enqclasses/"+affiliationAuthorityId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getActiveAdmissionSchemes = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/support/admissionschemes";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.registerNewAdmission = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/manage/newregistration";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.renewAdmission = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/manage/renew";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.isStudentEligibleForRenewal = function(studentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admision/pendingRenewal/isEligible/"+studentId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.loadStudentDetails = function(studentId, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/updatestudent/viewdetails/"+studentId;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateStudentInformation = function(data, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/updatestudent/update";
		return ajaxService.post(data, resourceURL, successCallBackFunction, errorCallBackFunction);
	}
})










