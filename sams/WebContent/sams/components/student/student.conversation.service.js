
samsApp.service('studentConversationService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/student/conversation";
	
	
	this.getConversations = function(studentId, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/"+studentId;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getConversationTypes = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/types";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveConversation = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	
})










