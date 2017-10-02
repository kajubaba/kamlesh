
samsApp.service('enquiryStatusService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/enquiry/status";
	
	
	this.getStatusList = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/list", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAllStatusList = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiryStatus = function(enquiryStatusId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+enquiryStatusId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.addEnquiryStatus = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateEnquiryStatus = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL;
		return ajaxService.put(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










