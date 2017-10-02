
samsApp.service('academicSessionDocumentService', function(ajaxService) {

	var resourceURL = _appContextPath+"/documents";
	
	
	this.getDocument = function(documentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/"+documentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getDocuments = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/academic-session/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.addDocument = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.updateDocument = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"";
		return ajaxService.put(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.removeAdmissionScheme = function(admissionSchemeId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/delete/"+admissionSchemeId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	
	this.saveSchemeDetails = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/scheme-detail";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
	this.getUnAssignedAdmissionSchemes = function(academicSessionId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/unassigned/"+academicSessionId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.assignAdmissionSchemes = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/assign";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
})










