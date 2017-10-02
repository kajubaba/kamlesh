
samsApp.service('admissionDashboardService', function(ajaxService) {

	this.getConfirmAdmissions = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admission/dashboard/totalAdmissions";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getUnderSchemeAdmissions = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admission/dashboard/schemeAdmissions";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getNewAdmissions = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admission/dashboard/newAdmissions";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getCancelAdmissions = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admission/dashboard/cancelAdmissions";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getNewRegistrations = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admission/dashboard/newRegistrations";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getRenewRegistrations = function(successCallBackFunction, errorCallBackFunction){
		 
		var resourceURL = _appContextPath+"/ws/admission/dashboard/renewRegistrations";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getDegreeAwardedAdmissionCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/dashboard/degreeawarded";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusAdoptedAdmissionCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/dashboard/busAdopted";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getBusNotAdoptedAdmissionCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/dashboard/busNotAdopted";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getPendingForRenewalAdmissionCount = function(successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admision/pendingRenewal/count";
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getChartData = function(studentStatus, successCallBackFunction, errorCallBackFunction){
		var resourceURL = _appContextPath+"/ws/admission/dashboard/classwisexml/"+studentStatus;
		return ajaxService.get(resourceURL, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










