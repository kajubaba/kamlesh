
samsApp.service('affiliationAuthorityService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/affiliation-authority";
	
	
	this.getAffiliationAuthorities = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getAffiliationAuthority = function(affiliationAuthorityId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+affiliationAuthorityId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.deleteAffiliationAuthority = function(affiliationAuthorityId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+feeHeadId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveAffiliationAuthority = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










