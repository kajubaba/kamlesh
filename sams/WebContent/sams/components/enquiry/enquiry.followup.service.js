
samsApp.service('enquiryFollowupService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/enquiry-followup";
	
	
	this.getCommunicationTypes = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/comm-type/list", successCallBackFunction, errorCallBackFunction);
	}	
	
	this.getCommunicationWithList = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/comm-with/list", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getCommunicationConclusions = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/comm-conclusion/list", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getSuggestions = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/suggestion/list", successCallBackFunction, errorCallBackFunction);
	}
	
	this.getNextActions = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/next-action/list", successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveEnquiryFollowup = function(formData, successCallBackFunction, errorCallBackFunction){
		return ajaxService.post(formData, resourceURL+"/save" , successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiryFollowups = function(enquiryId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/list/"+enquiryId, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getEnquiryFollowup = function(followupId, successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/get/"+followupId, successCallBackFunction, errorCallBackFunction);
	}
	
})










