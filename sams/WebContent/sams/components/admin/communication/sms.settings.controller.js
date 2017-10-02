
samsApp.controller('sms.settings.controller', function($scope, $route, $location, $routeParams, smsSettingService){
	
	$scope.feeCollectionSettingForm ={
			id : "",
			isEnabled : "",
			sendToStudent : "",
			sendToFather : "",
			sendToMother : ""
	}
	
	$scope.birthDaySettingForm ={
			id : "",
			isEnabled : "",
			sendToStudent : "",
			sendToFather : "",
			sendToMother : ""
	}
	
	$scope.smsProviderForm ={
			id : "",
			url : "",
			authKey : "",
			senderId : "",
			isEnabled : "",
			smsProviderName : ""
	}
	
    this.init = function(){
    	this.getOnFeeCollectionSetting();
    	this.getOnBirthdaySetting();
    	this.getSMSProviderSetting();
    }
    
    this.getOnFeeCollectionSetting = function(){
    	smsSettingService.getOnFeeCollectionSetting(this.renderOnFeeCollectionSetting, this.errorFuntion);
    }
    
    this.renderOnFeeCollectionSetting = function(response, status){
    	$scope.feeCollectionSettingForm = response;
    }
    
    this.getOnBirthdaySetting = function(){
    	smsSettingService.getOnBirthdaySetting(this.renderOnBirthdaySetting, this.errorFuntion);
    }
    
    this.renderOnBirthdaySetting = function(response, status){
    	$scope.birthDaySettingForm = response;
    }
    
    this.saveSMSSetting = function(){
    	smsSettingService.saveSMSSetting($scope.feeCollectionSettingForm, this.saveSMSSettingSuccess,this.errorFuntion);
    	smsSettingService.saveSMSSetting($scope.birthDaySettingForm, this.saveSMSSettingSuccess,this.errorFuntion);
    	this.saveSMSProviderSetting();
    }
    
    this.saveSMSSettingSuccess = function(response, status){
    	
    }
    
    this.getSMSProviderSetting = function(){
    	smsSettingService.getSMSProviderSetting(this.renderSMSProviderSetting, this.errorFuntion);
    }
    
    this.renderSMSProviderSetting = function(response, status){
    	$scope.smsProviderForm = response;
    }
    
    this.saveSMSProviderSetting = function(){
    	smsSettingService.saveSMSProviderSetting($scope.smsProviderForm, this.saveSMSSettingSuccess,this.errorFuntion);
    }
    
    
    
    this.errorFuntion = function(response){
		
	}
    
});






