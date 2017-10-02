
samsApp.service('enquiryActivityService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/enquiry/activity";
	
	this.changeStatus = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/update-status" , successCallBackFunction, errorCallBackFunction);
	}
	
	this.issueForm = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/update-formno" , successCallBackFunction, errorCallBackFunction);
	}
	
	this.isFormNoExist = function(formNo, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/form-exist?formNo="+formNo, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










