
samsApp.service('smsSettingService', function(ajaxService) {

	var resourceURL = _appContextPath+"/ws/settings/sms";
	
	
	this.getOnFeeCollectionSetting = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/on-fee-collection";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getOnBirthdaySetting = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/on-birth-day";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.getSMSProviderSetting = function(successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/provider";
		return ajaxService.get(url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveSMSSetting = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
	this.saveSMSProviderSetting = function(formData, successCallBackFunction, errorCallBackFunction){
		var url = resourceURL+"/save/sms-provider";
		return ajaxService.post(formData, url, successCallBackFunction, errorCallBackFunction);
	}
	
})










