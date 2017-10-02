
samsApp.service('enquiryService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/enquiry";
	
	
	this.addEnquiry = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/add";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateEnquiry = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/update";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiry = function(enquiryId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/"+enquiryId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiryByMobileNo = function(mobileNo, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/by-contact?contactNo="+mobileNo, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiryDetail = function(enquiryId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/detail/"+enquiryId, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
		
})










