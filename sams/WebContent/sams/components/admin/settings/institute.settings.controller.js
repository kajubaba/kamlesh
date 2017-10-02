
samsApp.controller('institute.settings.controller', function($scope, $rootScope, $route, $location, $routeParams, $timeout, instituteSettingService, academicSessionService, serverErrorHandlerService, blockUI){
	
	
	$scope.instituteSetting = null;
	$scope.academicsessions = null;
	$scope.showSuccessMessage= false; 
    this.init = function(){
    	this.getInstituteSetting();
    	this.getAcademicSessions();
    }
    
    this.getInstituteSetting = function(){
    	instituteSettingService.getSettings(this.getInstituteSettingSuccess, serverErrorHandlerService.handleError);
    }
    
    
    this.getInstituteSettingSuccess = function(response, status){
    	$scope.instituteSetting = response;
    }
    
    
    this.getAcademicSessions = function(){
    	academicSessionService.getAcademicSessions(this.getAcademicSessionsSuccess, serverErrorHandlerService.handleError);
    }
    
    this.getAcademicSessionsSuccess = function(response, status){
    	$scope.academicsessions = response;
    }
    
    this.saveInstituteSettings = function(){
    	instituteSettingService.saveSettings($scope.instituteSetting, this.saveInstituteSettingsSuccess, serverErrorHandlerService.handleError);
    }
    
    this.saveInstituteSettingsSuccess = function(response, status){
    	if("OK" == response.status){
			$scope.showSuccessMessage= true;
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}
    }
  
    
});






