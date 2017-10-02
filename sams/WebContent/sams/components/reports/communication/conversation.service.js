
samsApp.service('conversationService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/conversations";
	
	
	this.initSearchForm = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/init-search-param";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getConversations = function(searchForm, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list?frm="+searchForm.frm+"&to="+searchForm.to+"&mode="+searchForm.mode;
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getTodaysConversations = function(searchForm, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/list/todays";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
})










