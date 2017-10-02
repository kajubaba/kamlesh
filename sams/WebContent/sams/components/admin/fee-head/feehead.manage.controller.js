
samsApp.controller('feehead.manage.controller', function(feeHeadService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.feeHeadForm ={
			id : "",
			name : "",
			active : ""
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getFeeHead = function(){
		
    	if($routeParams['feeHeadId'] != "new"){
    		feeHeadService.getFeeHead($routeParams['feeHeadId'], this.getFeeHeadSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.feeHeadForm.active = true;
    	}
    	
    	
	}
	
	this.getFeeHeadSuccess = function(response, status){
		$scope.feeHeadForm.id = response.id;
		$scope.feeHeadForm.name = response.name;
		$scope.feeHeadForm.active = response.active;
	}
	
	this.saveFeeHead = function(){
		feeHeadService.saveFeeHead($scope.feeHeadForm, this.saveFeeHeadSuccess, serverErrorHandlerService.handleError);
	}
	
	this.saveFeeHeadSuccess = function(response, status){
	
		if("OK" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.feeHeadForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






