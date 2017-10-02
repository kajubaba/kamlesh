
/**
 * @author Narendra Patidar
 * @Date Jan-16-2016
 * 
 * This Angular JS based service that can be injected into controller and other services.
 * 
 * 
 * 
 */
samsApp.service('admissionRenewalService', function(ajaxService) {

	
	var resourceURL = _appContextPath+"/ws/admision/pendingRenewal";
	
	this.fetchAdmissionToBeRenewed = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.fetchClassWiseAdmissionsToBeRenewed = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/classwise/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.fetchClassAdmissionsToBeRenewed = function(academicYearClassId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/class/"+academicYearClassId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.bulkRenew = function(data, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(data, _appContextPath+"/ws/admission/manage/bulkrenew", successCallBackFunction, errorCallBackFunction);
	}
	
})










