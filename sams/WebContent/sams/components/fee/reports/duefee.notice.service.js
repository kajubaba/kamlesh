
samsApp.service('dueFeeNoticeService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/duefee/notice";
	
	this.getNoticeInfo = function(successCallBackFunction, errorCallBackFunction){
		return ajaxService.get(resourceURL+"/noticeinfo", successCallBackFunction, errorCallBackFunction);
	}
	
})










