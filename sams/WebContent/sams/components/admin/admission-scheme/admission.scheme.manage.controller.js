
samsApp.controller('admission.scheme.manage.controller', function(admissionSchemeService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.admissionSchemeForm  ={
			schemeId : "",
			schemeName : "",
			active : ""
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getAdmissionScheme = function(){
		
    	if($routeParams['admissionSchemeId'] != "new"){
    		admissionSchemeService.getAdmissionScheme($routeParams['admissionSchemeId'], this.getAdmissionSchemeSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.admissionSchemeForm.active = true;
    	}
    	
    	
	}
	
	this.getAdmissionSchemeSuccess = function(response, status){
		$scope.admissionSchemeForm.schemeId = response.schemeId;
		$scope.admissionSchemeForm.schemeName = response.schemeName;
		$scope.admissionSchemeForm.active = response.active;
		
	}
	
	this.saveAdmissionScheme = function(){
		admissionSchemeService.saveAdmissionScheme($scope.admissionSchemeForm, this.saveAdmissionSchemeSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveAdmissionSchemeSuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.admissionSchemeForm.schemeId = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






