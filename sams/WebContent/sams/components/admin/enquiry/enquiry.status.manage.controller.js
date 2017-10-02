
samsApp.controller('enquirystatus.manage.controller', function(enquiryStatusService, serverErrorHandlerService, $scope, $route, $rootScope, $location, $routeParams, $timeout, blockUI){
	
	$scope.enquiryStatusForm  ={
			id : null,
			name : null,
			active : null
	}
	
	$scope.showSuccessMessage= false; 
	$scope.isActionNew= false;
	$scope.isDuplicateName= false;
	
    this.getEnquiryStatus = function(){
		
    	if($routeParams['enquiryStatusId'] != "new"){
    		enquiryStatusService.getEnquiryStatus($routeParams['enquiryStatusId'], this.getEnquiryStatusSuccess, serverErrorHandlerService.handleError);
    	}else{
    		$scope.isActionNew= true;
    		$scope.enquiryStatusForm.active = true;
    	}
    	
    	
	}
	
	this.getEnquiryStatusSuccess = function(response, status){
		$scope.enquiryStatusForm = response
	}
	
	this.saveEnquiryStatus = function(){
		if($scope.enquiryStatusForm.id == null){
			enquiryStatusService.addEnquiryStatus($scope.enquiryStatusForm, this.saveEnquiryStatusSuccess, serverErrorHandlerService.handleError);
		}else{
			enquiryStatusService.updateEnquiryStatus($scope.enquiryStatusForm, this.saveEnquiryStatusSuccess, serverErrorHandlerService.handleError);
		}
		
	}
	
	this.saveEnquiryStatusSuccess = function(response, status){
	
		if("SUCCESS" == response.status){
			$scope.isDuplicateName= false;
			$scope.showSuccessMessage= true;
			if($scope.isActionNew){
				$scope.enquiryStatusForm.id = response.generatedId;
			}
			$timeout(function () { $scope.showSuccessMessage= false; }, $rootScope.SUCCESS_MSG_DURATION);  
		}else if("ERROR" == response.status){
			$scope.isDuplicateName= true;
		}
	}
	
    
    
});






