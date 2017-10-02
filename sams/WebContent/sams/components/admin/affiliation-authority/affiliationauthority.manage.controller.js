
samsApp.controller('affiliationauthority.manage.controller', function(affiliationAuthorityService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.affiliationAuthortyForm ={
			id : "",
			name : "",
			displayName : "",
			active : ""
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getAffiliationAuthority = function(){
		
    	if($routeParams['affiliationAuthorityId'] != "new"){
    		affiliationAuthorityService.getAffiliationAuthority($routeParams['affiliationAuthorityId'], this.getAffiliationAuthoritySuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.affiliationAuthortyForm.active = true;
    	}
    	
    	
	}
	
	this.getAffiliationAuthoritySuccess = function(response, status){
		$scope.affiliationAuthortyForm.id = response.id;
		$scope.affiliationAuthortyForm.name = response.name;
		$scope.affiliationAuthortyForm.displayName = response.displayName;
		$scope.affiliationAuthortyForm.active = response.active;
	}
	
	this.saveAffiliationAuthority = function(){
		affiliationAuthorityService.saveAffiliationAuthority($scope.affiliationAuthortyForm, this.saveAffiliationAuthoritySuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveAffiliationAuthoritySuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.affiliationAuthortyForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






